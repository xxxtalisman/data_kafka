package com.emdata.model.dao;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 违法数据接入表
 * </p>
 *
 * @author pengqingfeng
 * @since 2019-12-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("ivvs_source_data")
public class IvvsSourceData implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 唯一识别号uuid
     */
    @TableId(value = "uuid", type = IdType.AUTO)
    private Long uuid;

    /**
     * 设备编号
     */
    @TableField("sbbh")
    private String sbbh;

    /**
     * 执勤民警,警员代号
     */
    @TableField("zqmj")
    private String zqmj;

    /**
     * 车辆分类
     */
    @TableField("clfl")
    private String clfl;

    /**
     * 号牌种类
     */
    @TableField("hpzl")
    private String hpzl;

    /**
     * 号牌号码
     */
    @TableField("hphm")
    private String hphm;

    /**
     * 违法地行政区划
     */
    @TableField("xzqh")
    private String xzqh;

    /**
     * 违法地点
     */
    @TableField("wfdd")
    private String wfdd;

    /**
     * 路段代码,公里数
     */
    @TableField("lddm")
    private String lddm;

    /**
     * 地点米数
     */
    @TableField("ddms")
    private String ddms;

    /**
     * 违法地址
     */
    @TableField("wfdz")
    private String wfdz;

    /**
     * 违法时间
     */
    @TableField("wfsj")
    private Date wfsj;

    /**
     * 违法时间1
     */
    @TableField("wfsj1")
    private Date wfsj1;

    /**
     * 违法行为
     */
    @TableField("wfxw")
    private String wfxw;

    /**
     * 实测值
     */
    @TableField("scz")
    private String scz;

    /**
     * 标准值
     */
    @TableField("bzz")
    private String bzz;

    /**
     * 照片数量
     */
    @TableField("zpsl")
    private Integer zpsl;

    /**
     * 图片文件名
     */
    @TableField("zpwjm")
    private String zpwjm;

    /**
     * 照片1
     */
    @TableField("zpstr1")
    private String zpstr1;

    /**
     * 照片2
     */
    @TableField("zpstr2")
    private String zpstr2;

    /**
     * 照片3
     */
    @TableField("zpstr3")
    private String zpstr3;

    /**
     * 照片4
     */
    @TableField("zpstr4")
    private String zpstr4;

    @TableField("zpstr5")
    private String zpstr5;

    @TableField("zpstr6")
    private String zpstr6;

    /**
     * 违法视频地址
     */
    @TableField("wfspdz")
    private String wfspdz;

    /**
     * 0: 本地; 1: 远程
     */
    @TableField("ilocalremote")
    private Integer ilocalremote;

    /**
     * 创建时间
     */
    @TableField("createtime")
    private Date createtime;

    /**
     * 更新时间
     */
    @TableField("update_time")
    private Date updateTime;

    /**
     * （苏州）方向代码*0北向南1西向东2南向北3东向西4东南5东北6西南7西北9其它
     */
    @TableField("fxdm")
    private String fxdm;

    /**
     * （苏州）管辖部门*12位管辖部门编号（民警所在部门），由交警部门指定
     */
    @TableField("gxbm")
    private String gxbm;

    /**
     * （苏州）用户代码
     */
    @TableField("yhdm")
    private String yhdm;

    /**
     * 闯红灯设备为1；公路卡口设备为2；测速设备为3；闭路电视为4；移动摄像为5；警务通设备为6；区间测速为7；其他电子设备为9；
     */
    @TableField("sjly")
    private String sjly;

    /**
     * (苏州)车道号1位；按车辆行驶方向，从左到右开始编码
     */
    @TableField("cdh")
    private String cdh;

    /**
     * （苏州）车架号车架号，为空用NULL代替，黄标车违法为必填项
     */
    @TableField("cjh")
    private String cjh;

    /**
     * (苏州)区间测速结束时间
     */
    @TableField("qjcssj")
    private Date qjcssj;

    /**
     * 数据来源(1-，2-，3-，4-，10-海康，11-宇视)
     */
    @TableField("datasource")
    private String datasource;

    /**
     * 处理状态
     */
    @TableField("status")
    private String status;

    /**
     * 违法行为名称
     */
    @TableField("wfxwmc")
    private String wfxwmc;

    @TableField("sfjtp")
    private String sfjtp;

    /**
     * 车辆品牌
     */
    @TableField("clpp")
    private String clpp;

    /**
     * 车身颜色
     */
    @TableField("csys")
    private String csys;

    @TableField("sfhhdj")
    private String sfhhdj;

    /**
     * 序号
     */
    @TableField("Xh")
    private String Xh;

    @TableField("cdbh")
    private String cdbh;

    /**
     * 号牌颜色
     */
    @TableField("hpys")
    private String hpys;

    /**
     * 行驶方向
     */
    @TableField("xsfx")
    private String xsfx;

    /**
     * 车辆类型
     */
    @TableField("cllx")
    private String cllx;

    /**
     * 发证机关
     */
    @TableField("fzjg")
    private String fzjg;

    /**
     * 机动车所有人
     */
    @TableField("jdcsyr")
    private String jdcsyr;

    /**
     * 交通方式
     */
    @TableField("jtfs")
    private String jtfs;

    /**
     * 住所行政区划
     */
    @TableField("zsxzqh")
    private String zsxzqh;

    /**
     * 电话
     */
    @TableField("dh")
    private String dh;

    /**
     * 联系方式
     */
    @TableField("lxfs")
    private String lxfs;

    /**
     * 是否提供校车服务
     */
    @TableField("xcfw")
    private String xcfw;

    /**
     * 金华新增人工审核字段
     */
    @TableField("jdzt")
    private String jdzt;

    /**
     * 金华新增人工审核字段
     */
    @TableField("jdjg")
    private String jdjg;

    /**
     * 算法是否解析成功，-2正在解析，-1,未解析;0,未成功;1,成功
     */
    @TableField("alg_status")
    private Integer algStatus;

    @TableField("alg_uuid")
    private Long algUuid;

    /**
     * 摄像机编号
     */
    @TableField("sxjbh")
    private String sxjbh;

    /**
     * 苏州识别为高排污车辆字段
     */
    @TableField("gp01")
    private String gp01;

    /**
     * 算法错误代码
     */
    @TableField("alg_err_code")
    private Integer algErrCode;

    /**
     * 唯一编号（连云港用）
     */
    @TableField("view_id")
    private String viewId;

    /**
     * 算法处理次数
     */
    @TableField("alg_times")
    private Integer algTimes;

    /**
     * 发送第三方状态
     */
    @TableField("send_third_party")
    private Integer sendThirdParty;

    /**
     * 发送次数
     */
    @TableField("send_times")
    private Integer sendTimes;

    /**
     * 关联offline_tasks表tid
     */
    @TableField("task_id")
    private Long taskId;

    /**
     * 关联 traffic_restriction_info 的id
     */
    @TableField("tr_id")
    private Long trId;

    /**
     * 处理优先级 离线任务-1 默认0
     */
    @TableField("priority")
    private Integer priority;

    /**
     * 城市code码
     */
    @TableField("city_code")
    private String cityCode;


}
