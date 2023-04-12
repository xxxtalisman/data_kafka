package com.emdata.model.dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 违法行为算法枚举对照表
 * </p>
 *
 * @author pengqingfeng
 * @since 2019-12-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("ivvs_violate_code")
public class IvvsViolateCode implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 主键
     */
      @TableId(value = "uuid", type = IdType.AUTO)
    private Long uuid;

    /**
     * 内部违法类型
     */
    @TableField("internal_violate_type")
    private Integer internalViolateType;

    /**
     * 违法代码
     */
    @TableField("code")
    private String code;

    /**
     * 违法代码
     */
    @TableField("secondary_code")
    private String secondaryCode;

    /**
     * 是否发送第三方 0-启用 1禁用
     */
    @TableField("is_send")
    private Integer isSend;

    /**
     * 是否手动上传(默认0，手动开启，1，手动关闭)
     */
    @TableField("is_auto")
    private Integer isAuto;

    /**
     * 车牌识别开关（默认0，启用识别；1，关闭识别）
     */
    @TableField("is_lpn")
    private Integer isLpn;

    /**
     * 对应的算法代码
     */
    @TableField("alg_type")
    private Integer algType;

    /**
     * 是否激活
     */
    @TableField("active")
    private Integer active;

    /**
     * 是否逻辑删除
     */
    @TableField("del")
    private Integer del;

    /**
     * 名字
     */
    @TableField("name")
    private String name;

    /**
     * 违法规则名称
     */
    @TableField("regulation_name")
    private String regulationName;

    /**
     * 国标code
     */
    @TableField("national_standard_code")
    private String nationalStandardCode;

    @TableField("text")
    private String text;

    /**
     * 回写第三方违法代码
     */
    @TableField("third_party_code")
    private String thirdPartyCode;

    @TableField("create_time")
    private Date createTime;

    @TableField("update_time")
    private Date updateTime;

    /**
     * 违法类型监控预警值
     */
    @TableField("cvm_value")
    private String cvmValue;

    /**
     * 接收状态0开启，1关闭
     */
    @TableField("receive_status")
    private Integer receiveStatus;

    /**
     * 智审状态0开启，1关闭
     */
    @TableField("check_status")
    private Integer checkStatus;

    /**
     * 异常监控0开启，1关闭
     */
    @TableField("monitor_status")
    private Integer monitorStatus;


}
