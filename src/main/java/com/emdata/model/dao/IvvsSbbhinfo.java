package com.emdata.model.dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: txw
 * @Date: 2019/12/19 13:55
 */
@TableName(value = "ivvs_sbbhinfo")
public class IvvsSbbhinfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id自增
     */
    @TableId(value = "sbid", type = IdType.AUTO)
    private Integer sbid;

    /**
     * 设备编号
     */
    private String sbbh;

    /**
     * 设备名称
     */
    private String sbmc;

    /**
     * 设备类型1卡口，2电子警察，3监控，4其他
     */
    private String sblx;

    /**
     * 设备状态1正常，2故障，3维修中
     */
    private String sbzt;

    /**
     * 设备朝向
     */
    private String sbcx;

    /**
     * 维保单位
     */
    private String wbdw;

    /**
     * 备注
     */
    private String remark;

    /**
     * 数据权限表（cityinfo）, zcode
     */
    private String zcode;

    /**
     * 创建日期
     */
    private Date createTime;

    /**
     * 修改日期
     */
    private Date updateTime;

    /**
     * user表id(创建人)
     */
    private Integer userid;

    /**
     * 违法数据接入，1启用，2禁用 
     */

    private String accessState;

    /**
     * 智能审核状态 1启用，2禁用
     */
    private String auditStatus;

    /**
     * 审核上传状态 1启用 2禁用
     */
    private Integer uploadStatus;

    /**
     * 状态 1：正常 2：删除 3：禁用（切换城市时禁用原城市设备）
     */
    private Integer status;

    /**
     * 品牌类型，1-大华 2-海康
     */
    private String gsdw;

    /**
     * 设备预警推送状态：1-延时 2-实时
     */
    private Integer warningStatus;

    /**
     * 备案违法代码：多个以;隔开 如:1344;1345
     */
    private String receiveViolateCode;

    /**
     * 配置状态1已配置，2未配置
     */
    private Integer deployStatus;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getSbid() {
        return sbid;
    }

    public void setSbid(Integer sbid) {
        this.sbid = sbid;
    }

    public String getSbbh() {
        return sbbh;
    }

    public void setSbbh(String sbbh) {
        this.sbbh = sbbh;
    }

    public String getSbmc() {
        return sbmc;
    }

    public void setSbmc(String sbmc) {
        this.sbmc = sbmc;
    }

    public String getSblx() {
        return sblx;
    }

    public void setSblx(String sblx) {
        this.sblx = sblx;
    }

    public String getSbzt() {
        return sbzt;
    }

    public void setSbzt(String sbzt) {
        this.sbzt = sbzt;
    }

    public String getSbcx() {
        return sbcx;
    }

    public void setSbcx(String sbcx) {
        this.sbcx = sbcx;
    }

    public String getWbdw() {
        return wbdw;
    }

    public void setWbdw(String wbdw) {
        this.wbdw = wbdw;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getZcode() {
        return zcode;
    }

    public void setZcode(String zcode) {
        this.zcode = zcode;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getAccessState() {
        return accessState;
    }

    public void setAccessState(String accessState) {
        this.accessState = accessState;
    }

    public String getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(String auditStatus) {
        this.auditStatus = auditStatus;
    }

    public Integer getUploadStatus() {
        return uploadStatus;
    }

    public void setUploadStatus(Integer uploadStatus) {
        this.uploadStatus = uploadStatus;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getGsdw() {
        return gsdw;
    }

    public void setGsdw(String gsdw) {
        this.gsdw = gsdw;
    }

    public Integer getWarningStatus() {
        return warningStatus;
    }

    public void setWarningStatus(Integer warningStatus) {
        this.warningStatus = warningStatus;
    }

    public String getReceiveViolateCode() {
        return receiveViolateCode;
    }

    public void setReceiveViolateCode(String receiveViolateCode) {
        this.receiveViolateCode = receiveViolateCode;
    }

    public Integer getDeployStatus() {
        return deployStatus;
    }

    public void setDeployStatus(Integer deployStatus) {
        this.deployStatus = deployStatus;
    }

    @Override
    public String toString() {
        return "Sbbhinfo{" +
                "sbid=" + sbid +
                ", sbbh='" + sbbh + '\'' +
                ", sbmc='" + sbmc + '\'' +
                ", sblx='" + sblx + '\'' +
                ", sbzt='" + sbzt + '\'' +
                ", sbcx='" + sbcx + '\'' +
                ", wbdw='" + wbdw + '\'' +
                ", remark='" + remark + '\'' +
                ", zcode='" + zcode + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", userid=" + userid +
                ", accessState='" + accessState + '\'' +
                ", auditStatus='" + auditStatus + '\'' +
                ", uploadStatus=" + uploadStatus +
                ", status=" + status +
                ", gsdw='" + gsdw + '\'' +
                ", warningStatus=" + warningStatus +
                ", receiveViolateCode='" + receiveViolateCode + '\'' +
                ", deployStatus=" + deployStatus +
                '}';
    }
}
