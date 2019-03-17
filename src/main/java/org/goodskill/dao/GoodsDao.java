package org.goodskill.dao;

import org.apache.ibatis.annotations.Param;
import org.goodskill.entity.Goods;

import java.util.Date;
import java.util.List;

/**
 * @author ：yangzengrui
 * @date ：Created in 16/3/2019 11:36 PM
 * @description：
 */

public interface GoodsDao {

    /**
     * 减库存
     *
     * @param goodId
     * @param killTime
     * @return 如果影响行数 > 1, 表示更新的记录行数
     */
    int reduceNumber(@Param("goodId") long goodId, @Param("killTime") Date killTime);

    /**
     * 根据Id查询商品
     *
     * @param goodId
     * @return
     */
    Goods queryById(@Param("goodId") long goodId);

    /**
     * 根据偏移量查询秒杀商品列表
     *
     * @param offet
     * @param limit
     * @return
     */
    List<Goods> queryAll(@Param("offet") int offet, @Param("limit") int limit);

}
