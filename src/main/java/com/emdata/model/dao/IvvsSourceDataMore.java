package com.emdata.model.dao;

import com.alibaba.fastjson.annotation.JSONField;
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
 * 违法数据接入表（其它属性，仅回写需要）
 * </p>
 *
 * @author pengqingfeng
 * @since 2019-12-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("ivvs_source_data_more")
public class IvvsSourceDataMore implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 与 ivvs_source_data 中主键一致
     */
    private Long uuid;

    /**
     * 图片id(株洲)
     */
    @TableField("tpid")
    private String tpid;

    /**
     * 左上角坐标x(株洲)
     */
    @TableField("cpzb_left")
    private String cpzbLeft;

    /**
     * 左上角坐标y(株洲)
     */
    @TableField("cpzb_top")
    private String cpzbTop;

    /**
     * 右下角坐标x(株洲)
     */
    @TableField("cpzb_right")
    private String cpzbRight;

    /**
     * 右下角坐标y(株洲)
     */
    @TableField("cpzb_bottom")
    private String cpzbBottom;

    /**
     * 厂家代码(株洲)
     */
    @TableField("cjdm")
    private String cjdm;

    /**
     * 写入时间(株洲) yyyy-MM-dd HH:mm:ss.SSS
     */
    @TableField("xrsj")
    private String xrsj;

    /**
     * 过车时间毫秒(株洲)
     */
    @TableField("hm")
    private String hm;

    /**
     * 车标类型(株洲)
     */
    @TableField("cblx")
    private String cblx;

    /**
     * 远程http图片文件url
     */
    @TableField("tp1")
    private String tp1;

    @TableField("tp2")
    private String tp2;

    @TableField("tp3")
    private String tp3;

    /**
     * 合成图
     */
    @TableField("combined_pic")
    private String combinedPic;

    /**
     * 设备id
     */
    @TableField("sbdm")
    private String sbdm;

    /**
     * 通道序号
     */
    @TableField("sbtdxh")
    private String sbtdxh;

    /**
     * 车辆属地
     */
    @TableField("clsd")
    private String clsd;


    /**
     * 车身长度(单位厘米)
     */
    @TableField("cscd")
    private String cscd;

}
