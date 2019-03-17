package org.goodskill.dao;

import org.goodskill.entity.Goods;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * 配置spring和junit整合，junit启动时加载springIOC容器
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class GoodsDaoTest {

    //注入Dao实现类依赖
    @Resource
    private GoodsDao goodsDao;

    @Test
    public void reduceNumber() throws Exception{

        Date killTime = new Date();
        int updateCount = goodsDao.reduceNumber(1000L, killTime);
        System.out.println(updateCount);

    }

    @Test
    public void queryById() throws Exception{
        long id = 1000;
        Goods good = goodsDao.queryById(id);
        System.out.println(good.getName());
        System.out.println(good);
    }

    @Test
    public void queryAll() {

        List<Goods> goods = goodsDao.queryAll(0, 10);
        for (Goods good : goods) {
            System.out.println(good);
        }
    }
}