package org.goodskill.service.impl;

import org.goodskill.dto.Exposer;
import org.goodskill.dto.SeckillExecution;
import org.goodskill.entity.Goods;
import org.goodskill.exception.RepeatKillException;
import org.goodskill.exception.SeckillCloseException;
import org.goodskill.exception.SeckillException;
import org.goodskill.service.SeckillService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * 配置spring和junit整合，junit启动时加载springIOC容器
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml",
                        "classpath:spring/spring-service.xml"})
public class SeckillServiceImplTest {
    //日志打印
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SeckillService seckillService;

    @Test
    public void getGoodsList() {
        List<Goods> goods = seckillService.getGoodsList();
        logger.info("list={}", goods);
    }

    @Test
    public void getGoodById() {
        long id = 1000L;
        Goods good = seckillService.getGoodById(id);
        logger.info("seckill={}", good);
    }


    @Test
    public void testSeckillLogic() {
        long id = 1001L;
        long userPhone = 11111111111L;
        Exposer exposer = seckillService.exportSeckillUrl(id);

        if(exposer.isExposed()){
            SeckillExecution seckillExecution = null;
            try {
                seckillExecution = seckillService.executeSeckill(id, userPhone,exposer.getMd5());
                logger.info("seckillExecution={}", seckillExecution);
            } catch (RepeatKillException e) {
                logger.error(e.getMessage());
            } catch (SeckillCloseException e) {
                logger.error(e.getMessage());
            }

        }else{
            logger.warn("exposer={}", exposer);
        }


    }
}