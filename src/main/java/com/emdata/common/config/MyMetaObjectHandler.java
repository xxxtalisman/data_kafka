package com.emdata.common.config;


import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;

import java.sql.Timestamp;

/**
 * @Date: 2019/5/15 21:03
 * @Version: 1.0
 * @Author: pengqingfeng
 * @Description:
 */
public class MyMetaObjectHandler implements MetaObjectHandler {

    /**
     * 字段为空自动填充,如果要使填充生效,一定在在实体类对应的字段上设置fill = FieldFill.INSERT字段！
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        // 更多查看源码测试用例
        System.out.println("*************************");
        System.out.println("insert fill");
        System.out.println("*************************");

        //mybatis-plus版本2.0.9+
        Object createTime = getFieldValByName("createTime", metaObject);

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        if (createTime == null) {
            //mybatis-plus版本2.0.9+
            setFieldValByName("createTime", timestamp, metaObject);
        }

    }

    @Override
    public void updateFill(MetaObject metaObject) {
        //更新填充
        System.out.println("*************************");
        System.out.println("update fill");
        System.out.println("*************************");
        //mybatis-plus版本2.0.9+
        setFieldValByName("modifyTime", new Timestamp(System.currentTimeMillis()), metaObject);
    }

}

