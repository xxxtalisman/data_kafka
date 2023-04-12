package com.emdata.model.dto;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/***
 * 530300-曲靖
 * 回传数据对象
 */
@Data
@Accessors(chain = true)
public class CityReturnData530300DTO implements Serializable {

    private static final long serialVersionUID = 4183033013754981671L;


    /**
     * 唯一识别号uuid
     */
    private Long uuid;

    /**
     * 设备编号
     */
    @JSONField(name = "sbbh")
    private String sbbh;


    /**
     * 执勤民警,警员代号
     */
    @JSONField(name = "zpms")
    private String zqmj;

    /**
     * 车辆分类
     */
    @JSONField(name = "clsd")
    private String clfl;

    /**
     * 号牌种类
     */
    private String hpzl = "44";

    /**
     * 号牌号码
     */
    @JSONField(name = "hphm")
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
    private Date wfsj;

    /**
     * 违法时间1
     */
    private Date wfsj1;

    /**
     * 违法行为
     */
    @JSONField(name = "wfxw")
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
    @JSONField(name = "zpstr1")
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
     * 0: 本地; 1: 远程
     */
    private Integer ilocalremote;

    /**
     * 创建时间
     */
    private Date createtime;

    /**
     * 更新时间
     */
    private Date updateTime;

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
    private Date qjcssj;

    /**
     * 数据来源(1-，2-，3-，4-，10-海康，11-宇视)
     */
    private String datasource;

    /**
     * 处理状态
     */
    private String status;

    /**
     * 违法行为名称
     */
    private String wfxwmc;

    private String sfjtp;

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
    @JSONField(name = "xh")
    private String Xh;


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
    private String jdzt;

    /**
     * 金华新增人工审核字段
     */
    private String jdjg;

    /**
     * 算法是否解析成功，-2正在解析，-1,未解析;0,未成功;1,成功
     */
    private Integer algStatus;

    private Long algUuid;

    /**
     * 摄像机编号
     */
    private String sxjbh;

    /**
     * 苏州识别为高排污车辆字段
     */
    private String gp01;

    /**
     * 算法错误代码
     */
    private Integer algErrCode;

    /**
     * 唯一编号（连云港用）
     */
    @JSONField(name = "sbms")
    private String viewId;

    /**
     * 算法处理次数
     */
    private Integer algTimes;

    /**
     * 发送第三方状态
     */
    private Integer sendThirdParty;

    /**
     * 发送次数
     */
    private Integer sendTimes;

    /**
     * 关联offline_tasks表tid
     */
    private Long taskId;

    /**
     * 关联 traffic_restriction_info 的id
     */
    private Long trId;

    /**
     * 处理优先级 离线任务-1 默认0
     */
    private Integer priority;

    /**
     * 城市code码
     */
    private String cityCode;

}
