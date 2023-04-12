package com.emdata.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @Date: 2019/12/11 20:10
 * @Version: 1.0
 * @Author: pengqingfeng
 * @Description:
 */
@Data
@Accessors(chain = true)
@ApiModel(description = "发送数据对象")
public class IvvsSendRecordVO2 implements Serializable {

    private static final long serialVersionUID = -601977507097948021L;

    @NotBlank(message = "城市代码不能为空")
    @ApiModelProperty(value = "城市代码", required = true)
    private String cityCode;

//    @NotBlank(message = "发送模式不能为空")
//    @ApiModelProperty(value = "发送模式（详情见SendTargetModeEnum枚举），默认为-1", required = true)
//    private String mode = "-1";

    @ApiModelProperty(value = "源数据id", required = true)
    private String sourceDataId;

    @NotBlank(message = "违法时间不能为空")
    @ApiModelProperty(value = "违法时间(yyyy-MM-dd HH:mm:ss)", required = true)
    private String wfsj;

    @ApiModelProperty(value = "违法状态(0-疑似;1-违法;2-未违法)")
    private Integer illegalState;

    @ApiModelProperty(value = "设备编号")
    private String sbbh;

    @ApiModelProperty(value = "号牌种类")
    private String hpzl;

    @ApiModelProperty(value = "号牌号码")
    private String hphm;

    @NotBlank(message = "违法行为不能为空")
    @ApiModelProperty(value = "违法行为", required = true)
    private String wfxw;

    @ApiModelProperty(value = "执勤民警")
    private String zqmj;

    @ApiModelProperty(value = "车辆分类")
    private String clfl;

    @ApiModelProperty(value = "号牌颜色")
    private String hpys;

    @ApiModelProperty(value = "违法地行政区划")
    private String xzqh;

    @ApiModelProperty(value = "违法地点")
    private String wfdd;

    @ApiModelProperty(value = "路段代码")
    private String lddm;

    @ApiModelProperty(value = "地点米数")
    private String ddms;

    @ApiModelProperty(value = "违法地址")
    private String wfdz;

    @ApiModelProperty(value = "违法时间1")
    private String wfsj1;

    @ApiModelProperty(value = "实测值")
    private String scz;

    @ApiModelProperty(value = "标准值")
    private String bzz;

    @ApiModelProperty(value = "照片数量")
    private Integer zpsl;

    @ApiModelProperty(value = "图片文件名")
    private String zpwjm;

    @ApiModelProperty(value = "照片1")
    private String zpstr1;

    @ApiModelProperty(value = "照片2")
    private String zpstr2;

    @ApiModelProperty(value = "照片3")
    private String zpstr3;

    @ApiModelProperty(value = "照片4")
    private String zpstr4;

    @ApiModelProperty(value = "照片5")
    private String zpstr5;

    @ApiModelProperty(value = "照片6")
    private String zpstr6;

    @ApiModelProperty(value = "(苏州)区间测速结束时间")
    private String qjcssj;

    @ApiModelProperty(value = "违法视频地址")
    private String wfspdz;

    @ApiModelProperty(value = "过车时间毫秒(株洲)")
    private String hm;

    @ApiModelProperty(value = "车身颜色")
    private String csys;

    @ApiModelProperty(value = "车标类型")
    private String cblx;

    @ApiModelProperty(value = "图片id(株洲)")
    private String tpid;

    @ApiModelProperty(value = "左上角坐标x(株洲)")
    private String cpzbLeft;

    @ApiModelProperty(value = "左上角坐标y(株洲)")
    private String cpzbTop;

    @ApiModelProperty(value = "右下角坐标x(株洲)")
    private String cpzbRight;

    @ApiModelProperty(value = "右下角坐标y(株洲)")
    private String cpzbBottom;

    @ApiModelProperty(value = "车辆速度")
    private String clsd;

    @ApiModelProperty(value = "设备id(株洲)")
    private String sbdm;

    @ApiModelProperty(value = "通道序号(株洲)")
    private String sbtdxh;

    @ApiModelProperty(value = "车道编号")
    private String cdbh;

    @ApiModelProperty(value = "行驶方向")
    private String xsfx;

    @ApiModelProperty(value = "车身长度")
    private String cscd;

    @ApiModelProperty(value = "车辆类型")
    private String cllx;

    @ApiModelProperty(value = "最高车速(株洲)")
    private String maxSpeed;

    @ApiModelProperty(value = "合成图片路径(株洲)")
    private String combinedPic;

    @ApiModelProperty(value = "厂家代码(株洲)")
    private String cjdm;

    @ApiModelProperty(value = "写入时间(株洲)")
    private String xrsj;

    @ApiModelProperty(value = "???(株洲)")
    private String wfms;

    @ApiModelProperty(value = "备用违法图片地址1(株洲)")
    private String srcTp1;

    @ApiModelProperty(value = "备用违法图片地址2(株洲)")
    private String srcTp2;

    @ApiModelProperty(value = "备用违法图片地址3(株洲)")
    private String srcTp3;

    @ApiModelProperty(value = "序号")
    private String xh;

    @ApiModelProperty(value = "识别模式(曲靖)")
    private String sbms;

}

