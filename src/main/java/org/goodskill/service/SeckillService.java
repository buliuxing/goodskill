package org.goodskill.service;

import org.goodskill.dto.Exposer;
import org.goodskill.dto.SeckillExecution;
import org.goodskill.entity.Goods;
import org.goodskill.exception.RepeatKillException;
import org.goodskill.exception.SeckillCloseException;
import org.goodskill.exception.SeckillException;

import java.util.List;

/**
 * @author ：yangzengrui
 * @date ：Created in 17/3/2019 11:18 AM
 * @description：业务接口
 */

public interface SeckillService {

    /**
     * 查询秒杀所有记录
     * @return
     */
    List<Goods> getGoodsList();

    /**
     * 查询单个商品记录
     * @param goodId
     * @return
     */
    Goods getGoodById(long goodId);

    /**
     * 秒杀开启时输出接口地址
     * 否则返回系统时间和秒杀时间
     * @param goodId
     */
    Exposer exportSeckillUrl(long goodId);

    /**
     * 执行秒杀操作
     * @param goodId
     * @param userPhone
     * @param md5
     */
    SeckillExecution executeSeckill(long goodId, long userPhone, String md5)
        throws SeckillException, RepeatKillException, SeckillCloseException;



}
