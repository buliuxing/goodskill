package org.goodskill.service.impl;


import org.goodskill.dao.GoodsDao;
import org.goodskill.dao.SuccessKillDao;
import org.goodskill.dto.Exposer;
import org.goodskill.dto.SeckillExecution;
import org.goodskill.entity.Goods;
import org.goodskill.entity.SuccessKilled;
import org.goodskill.enums.SeckillStatEnum;
import org.goodskill.exception.RepeatKillException;
import org.goodskill.exception.SeckillCloseException;
import org.goodskill.exception.SeckillException;
import org.goodskill.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;

/**
 * @author ：yangzengrui
 * @date ：Created in 17/3/2019 11:46 AM
 * @description：
 */
@Service
public class SeckillServiceImpl implements SeckillService {

    //日志打印
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private GoodsDao goodsDao;

    @Autowired
    private SuccessKillDao successKillDao;

    //MD5混淆的盐值
    private final String salt = "jkanfkdabiq3ru4y895y49ioY*yhd";

    @Override
    public List<Goods> getGoodsList() {
        return goodsDao.queryAll(0, 10);
    }

    @Override
    public Goods getGoodById(long goodId) {
        return  goodsDao.queryById(goodId);
    }

    @Override
    public Exposer exportSeckillUrl(long goodId) {

        Goods good = goodsDao.queryById(goodId);

        if(good == null){
            return new Exposer(false, goodId);
        }

        Date startTime = good.getStartTime();
        Date endTime = good.getEndTime();
        Date nowTime = new Date();

        if(nowTime.getTime() < startTime.getTime() || nowTime.getTime() > endTime.getTime()){
            return new Exposer(false, goodId,nowTime.getTime(), startTime.getTime(), endTime.getTime());
        }

        String md5 = getMD5(goodId);

        return new Exposer(true, md5, goodId);
    }

    /**
     * 生成MD5值
     * @param goodId
     * @return
     */
    private String getMD5(long goodId){
        String base = goodId + "/" + salt;
        String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
        return md5;
    }


    @Override
    @Transactional
    public SeckillExecution executeSeckill(long goodId, long userPhone, String md5)
            throws SeckillException, RepeatKillException, SeckillCloseException {

        if(md5 == null || !md5.equals(getMD5(goodId))){
            throw new SeckillException("参数异常");
        }

        //执行秒杀逻辑：减库存 + 记录购买行为
        Date nowTime = new Date();

        try {
            int updateCount = goodsDao.reduceNumber(goodId, nowTime);
            if(updateCount <= 0){
                //没有返回更新的记录
                throw new SeckillCloseException("秒杀已经关闭");
            }else{
                //购买行为
                int insertCount = successKillDao.insertSuccessKilled(goodId, userPhone);
                if(insertCount <= 0){
                    //重复秒杀
                    throw new RepeatKillException("重复秒杀");
                }else{
                    //秒杀成功
                    SuccessKilled successKilled = successKillDao.queryByIdWithGoods(goodId, userPhone);
                    return new SeckillExecution(goodId, SeckillStatEnum.SUCCESS, successKilled);
                }
            }
        } catch (SeckillException e1) {
            throw e1;
        }catch (SeckillCloseException e2) {
            throw e2;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);

            throw new SeckillException("内部错误：" + e.getMessage());
        }

    }
}
