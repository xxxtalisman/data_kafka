package com.emdata.common.config;


import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @Date: 2019/4/29 16:02
 * @Version: 1.0
 * @Author: pengqingfeng
 * @Description:
 */

public class DynamicDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        return  DbContextHolder.getDbType();
    }
}