package com.emdata.model.dto;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/***
 * 通用数据回传对象
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class CommonReturnDataDTO implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * ID
     */
    private Long sourceDataId;

    /**
     * 发送记录表ID
     */
    private Long sendRecordId;

    /**
     * 违法状态
     */
    private Integer illegalState;

    /**
     * 设备编号
     */
    private String sbbh;

    /**
     * 执勤民警,警员代号
     */
    private String zqmj;

    /**
     * 车辆分类
     */
    private String clfl;

    /**
     * 号牌种类
     */
    private String hpzl;

    /**
     * 号牌号码
     */
    private String hphm;

    /**
     * 违法地行政区划
     */
    private String xzqh;

    /**
     * 违法地点
     */
    private String wfdd;

    /**
     * 路段代码,公里数
     */
    private String lddm;

    /**
     * 地点米数
     */
    private String ddms;

    /**
     * 违法地址
     */
    private String wfdz;

    /**
     * 违法时间
     */

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date wfsj;

    /**
     * 违法时间1
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date wfsj1;

    /**
     * 违法行为
     */
    private String wfxw;

    /**
     * 实测值
     */
    private String scz;

    /**
     * 标准值
     */
    private String bzz;

    /**
     * 照片数量
     */
    private Integer zpsl;

    /**
     * 图片文件名
     */
    private String zpwjm;

    /**
     * 照片1
     */
    private String zpstr1;

    /**
     * 照片2
     */
    private String zpstr2;

    /**
     * 照片3
     */
    private String zpstr3;

    /**
     * 照片4
     */
    private String zpstr4;

    private String zpstr5;

    private String zpstr6;

    /**
     * 违法视频地址
     */
    private String wfspdz;

    /**
     * （苏州）方向代码*0北向南1西向东2南向北3东向西4东南5东北6西南7西北9其它
     */
    private String fxdm;

    /**
     * （苏州）管辖部门*12位管辖部门编号（民警所在部门），由交警部门指定
     */
    private String gxbm;

    /**
     * （苏州）用户代码
     */
    private String yhdm;

    /**
     * 闯红灯设备为1；公路卡口设备为2；测速设备为3；闭路电视为4；移动摄像为5；警务通设备为6；区间测速为7；其他电子设备为9；
     */
    private String sjly;

    /**
     * (苏州)车道号1位；按车辆行驶方向，从左到右开始编码
     */
    private String cdh;

    /**
     * （苏州）车架号车架号，为空用NULL代替，黄标车违法为必填项
     */
    private String cjh;

    /**
     * (苏州)区间测速结束时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date qjcssj;


    /**
     * 违法行为名称
     */
    private String wfxwmc;


    /**
     * 车辆品牌
     */
    private String clpp;

    /**
     * 车身颜色
     */
    private String csys;

    private String sfhhdj;

    /**
     * 序号
     */
    private String Xh;

    /**
     * 车道编号
     */
    private String cdbh;

    /**
     * 号牌颜色
     */
    private String hpys;

    /**
     * 行驶方向
     */
    private String xsfx;

    /**
     * 车辆类型
     */
    private String cllx;

    /**
     * 发证机关
     */
    private String fzjg;

    /**
     * 机动车所有人
     */
    private String jdcsyr;

    /**
     * 交通方式
     */
    private String jtfs;

    /**
     * 住所行政区划
     */
    private String zsxzqh;

    /**
     * 电话
     */
    private String dh;

    /**
     * 联系方式
     */
    private String lxfs;

    /**
     * 是否提供校车服务
     */
    private String xcfw;

    /**
     * 金华新增人工审核字段
     */
    private String jdjg;

    /**
     * 摄像机编号
     */
    private String sxjbh;

    /**
     * 苏州用
     */
    private String gp01;

    /**
     * 车标类型
     */
    private String cblx;


    /**
     * 车辆速度
     */
    private String clsd;


}
