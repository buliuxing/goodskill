package org.goodskill.dao.cache;

import org.goodskill.dao.GoodsDao;
import org.goodskill.entity.Goods;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * 配置spring和junit整合，junit启动时加载springIOC容器
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class RedisDaoTest{

    private long id = 1001;

   @Autowired
   private RedisDao redisDao;

   @Autowired
   private GoodsDao goodsDao;


    @Test
    public void testSeckill() throws Exception {

        Goods good = redisDao.getGoods(id);

        if(good == null){
            good = goodsDao.queryById(id);
            if (good != null){
                String result = redisDao.putSeckill(good);
                System.out.println(result);
                good = redisDao.getGoods(id);
                System.out.println(good);
            }
        }
    }

}