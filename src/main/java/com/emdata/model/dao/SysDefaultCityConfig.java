package com.emdata.model.dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: txw
 * @Date: 2019/12/12 16:07
 */
@Data
@TableName(value = "sys_default_city_config")
public class SysDefaultCityConfig implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键自增长
     */
    @TableId(value = "uuid", type = IdType.AUTO)
    private Integer uuid;

    /**
     * 省
     */
    private String province;

    /**
     * 市
     */
    private String city;

    /**
     * 区县
     */
    private String district;

    @TableField("provinceName")
    private String provinceName;

    @TableField("cityName")
    private String cityName;

    @TableField("districtName")
    private String districtName;

    /**
     * 网页标题
     */
    @TableField("projectName")
    private String projectName;

    /**
     * 批量开关按钮(0,关1,开)
     */
    @TableField("onOroff")
    private Boolean onOroff;

    /**
     * 放大效果;0为跟随放大；1为固定放大
     */
    private Integer magnified;

    /**
     * 数据申请开关;默认0为禁用；1，启用
     */
    @TableField("applyOff")
    private Boolean applyOff;

    /**
     * 数据提交,默认0,全部；2，违法+疑似；1，违法
     */
    private Integer submission;

}
