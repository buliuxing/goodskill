package org.goodskill.dao;

import org.apache.ibatis.annotations.Param;
import org.goodskill.entity.SuccessKilled;

/**
 * @author ：yangzengrui
 * @date ：Created in 16/3/2019 11:43 PM
 * @description：
 */

public interface SuccessKillDao {

    /**
     * 插入购买明细，可过滤重复
     * @param goodId
     * @param userPhone
     * @return 插入行数
     */
    int insertSuccessKilled(@Param("goodId") long goodId, @Param("userPhone") long userPhone);

    /**
     * 根据id查询SuccessKilled并携带秒杀产品对象实体
     * @param goodId
     * @return
     */
    SuccessKilled queryByIdWithGoods(@Param("goodId") long goodId, @Param("userPhone") long userPhone);
}
