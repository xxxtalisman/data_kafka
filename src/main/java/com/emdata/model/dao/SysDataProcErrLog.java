package com.emdata.model.dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: txw
 * @Date: 2019/12/12 16:07
 */
@TableName(value = "sys_data_proc_err_log")
public class SysDataProcErrLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 号牌号码
     */
    private String hphm;

    /**
     * 违法地点
     */
    private String wfdd;

    /**
     * 违法时间
     */
    private Date wfsj;

    /**
     * 违法行为
     */
    private String wfxw;

    /**
     * 错误码
     */
    private String errCode;

    /**
     * 错误信息
     */
    private String errMsg;

    /**
     * 处理类型：1-数据接入，2-算法处理 3-人工处理
     */
    private Integer type;

    /**
     * 创建时间
     */
    private Date createTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHphm() {
        return hphm;
    }

    public void setHphm(String hphm) {
        this.hphm = hphm;
    }

    public String getWfdd() {
        return wfdd;
    }

    public void setWfdd(String wfdd) {
        this.wfdd = wfdd;
    }

    public Date getWfsj() {
        return wfsj;
    }

    public void setWfsj(Date wfsj) {
        this.wfsj = wfsj;
    }

    public String getWfxw() {
        return wfxw;
    }

    public void setWfxw(String wfxw) {
        this.wfxw = wfxw;
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "DataProcErrLog{" +
        ", id=" + id +
        ", hphm=" + hphm +
        ", wfdd=" + wfdd +
        ", wfsj=" + wfsj +
        ", wfxw=" + wfxw +
        ", errCode=" + errCode +
        ", errMsg=" + errMsg +
        ", type=" + type +
        ", createTime=" + createTime +
        "}";
    }
}
