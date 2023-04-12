package com.emdata.model.vo;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * `id` int(20) NOT NULL,
  `cross_no` varchar(256) DEFAULT NULL COMMENT '路口编号',
  `direction_code` varchar(255) DEFAULT NULL COMMENT '方向代码',
  `direction_desc` varchar(255) DEFAULT NULL COMMENT '方向描述',
  `jz_sbbh` varchar(255) DEFAULT NULL COMMENT '集指设备编号',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
 */

@TableName(value = "ivvs_sbbh_zh")
public class IvvsSbbhZhDO {
	
	@TableId(value = "id", type = IdType.AUTO)
	private Long id;
	
	private String crossNo;
	
	private String directionCode;
	
	private String directionDesc;
	
	private String jzSbbh;
	
	private Date createTime;
	
	private Date updateTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCrossNo() {
		return crossNo;
	}

	public void setCrossNo(String crossNo) {
		this.crossNo = crossNo;
	}

	public String getDirectionCode() {
		return directionCode;
	}

	public void setDirectionCode(String directionCode) {
		this.directionCode = directionCode;
	}

	public String getDirectionDesc() {
		return directionDesc;
	}

	public void setDirectionDesc(String directionDesc) {
		this.directionDesc = directionDesc;
	}

	public String getJzSbbh() {
		return jzSbbh;
	}

	public void setJzSbbh(String jzSbbh) {
		this.jzSbbh = jzSbbh;
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
	
}
