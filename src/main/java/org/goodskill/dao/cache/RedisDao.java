package org.goodskill.dao.cache;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtobufIOUtil;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import org.goodskill.entity.Goods;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author ：yangzengrui
 * @date ：Created in 28/3/2019 10:46 PM
 * @description：
 */

public class RedisDao {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final JedisPool jedisPool;

    public RedisDao(String ip, int port){
        jedisPool = new JedisPool(ip, port);
    }

    private RuntimeSchema<Goods> schema = RuntimeSchema.createFrom(Goods.class);

    public Goods getGoods(long seckillId){
        try{
            Jedis jedis = jedisPool.getResource();
            try {
                String keys = "seckill:" + seckillId;
                //序列化
                byte[] bytes = jedis.get(keys.getBytes());
                if(bytes != null){
                    Goods good = schema.newMessage();
                    ProtobufIOUtil.mergeFrom(bytes, good, schema);
                    return good;
                }

            }finally {
                jedis.close();
            }
        }catch (Exception e){
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    public String putSeckill(Goods good){

        try{
            Jedis jedis = jedisPool.getResource();
            try {
                String key = "seckill:" + good.getGoodId();
                byte[] bytes = ProtobufIOUtil.toByteArray(good, schema,
                        LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
                int timeout = 60 * 60; //一小时
                String result = jedis.setex(key.getBytes(), timeout, bytes);
                return result;
            }finally {
                jedis.close();
            }
        }catch (Exception e){
            logger.error(e.getMessage(), e);
        }
        return null;
    }


}
