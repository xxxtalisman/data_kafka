package com.emdata.common.util;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @Date: 2019/7/19 11:09
 * @Version: 1.0
 * @Author: pengqingfeng
 * @Description:
 */
@Component
@Slf4j
public class RedisLock {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 加锁
     *
     * @param key     锁唯一标志
     * @param timeout 超时时间
     * @return
     */
    public boolean lock(String key , long timeout) {
        return lock(key , timeout , TimeUnit.SECONDS);
    }

    /**
     * 加锁
     *
     * @param key     锁唯一标志
     * @param timeout 超时时间
     * @return
     */
    public boolean lock(String key , long timeout , TimeUnit unit) {

        String value = String.valueOf(timeout + System.currentTimeMillis());

        if (redisTemplate.opsForValue().setIfAbsent(key , value , timeout , unit)) {
            return true;
        }

        /*//判断锁超时,防止死锁
        String currentValue = (String) redisTemplate.opsForValue().get(key);
        //如果锁过期
        if (!StrUtil.isEmpty(currentValue) && Long.parseLong(currentValue) < System.currentTimeMillis()) {
            //获取上一个锁的时间value
            String oldValue = (String) redisTemplate.opsForValue().getAndSet(key , value);
            if (!StrUtil.isEmpty(oldValue) && oldValue.equals(currentValue)) {
                //校验是不是上个对应的商品时间戳,也是防止并发
                return true;
            }
        }*/
        return false;
    }

    /**
     * 解锁
     *
     * @param key
     */
    public void unlock(String key) {
        try {
            redisTemplate.delete(key);//删除key
        } catch (Exception e) {
            log.error("[Redis分布式锁] 解锁出现异常了，{}" , e);
        }
    }

    /**
     * 解锁
     *
     * @param key
     * @param value
     */
    public void unlock(String key , String value) {
        try {
            String currentValue = (String) redisTemplate.opsForValue().get(key);
            if (!StrUtil.isEmpty(currentValue) && currentValue.equals(value)) {
                redisTemplate.opsForValue().getOperations().delete(key);//删除key
            }
        } catch (Exception e) {
            log.error("[Redis分布式锁] 解锁出现异常了，{}" , e);
        }
    }
}
