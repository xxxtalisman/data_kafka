package com.emdata.model.dao;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 属性表
 * </p>
 *
 * @author pengqingfeng
 * @since 2019-12-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("ivvs_properties")
public class IvvsProperties implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 属性key
     */
    @TableField("property_key")
    private String propertyKey;

    /**
     * 属性值
     */
    @TableField("property_value")
    private String propertyValue;

    /**
     * 城市代码
     */
    @TableField("city_code")
    private Integer cityCode;

    /**
     * 备注
     */
    @TableField("remark")
    private String remark;


}
