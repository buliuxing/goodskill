package org.goodskill.dao;

import org.goodskill.entity.SuccessKilled;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.Assert.*;


/**
 * 配置spring和junit整合，junit启动时加载springIOC容器
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SuccessKillDaoTest {

    @Resource
    private SuccessKillDao successKillDao;

    @Test
    public void insertSuccessKilled() {

        /**
         * 不可重复插入
         */
        long goodId = 1000L;
        long userPhone = 11111111111L;
        int insertCount = successKillDao.insertSuccessKilled(goodId, userPhone);
        System.out.println(insertCount);
    }

    @Test
    public void queryByIdWithGoods() {
        long goodId = 1000L;
        long userPhone = 11111111111L;
        SuccessKilled successKilleds =  successKillDao.queryByIdWithGoods(goodId, userPhone);
        System.out.println(successKilleds.getGoods());

    }
}