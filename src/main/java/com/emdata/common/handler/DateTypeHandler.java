package com.emdata.common.handler;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.type.*;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

/**
 * @Date: 2019/10/8 13:41
 * @Version: 1.0
 * @Author: pengqingfeng
 * @Description:
 */
@MappedJdbcTypes(JdbcType.DATE)
@MappedTypes(Date.class)
public class DateTypeHandler extends BaseTypeHandler<Date> {

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement , int i , Date date , JdbcType jdbcType) throws SQLException {
        preparedStatement.setDate(i , DateUtil.date(date.getTime()).toSqlDate());
    }

    @Override
    public Date getNullableResult(ResultSet resultSet , String s) throws SQLException {
        Date date = resultSet.getDate(s);
        if (null != date) {
            return DateUtil.date(date.getTime()).toSqlDate();
        }
        return null;
    }

    @Override
    public Date getNullableResult(ResultSet resultSet , int i) throws SQLException {
        Date date = resultSet.getDate(i);
        if (null != date) {
            return DateUtil.date(date.getTime()).toSqlDate();
        }
        return null;
    }

    @Override
    public Date getNullableResult(CallableStatement callableStatement , int i) throws SQLException {
        Date date = callableStatement.getDate(i);
        if (null != date) {
            return DateUtil.date(date.getTime()).toSqlDate();
        }
        return null;
    }
}
