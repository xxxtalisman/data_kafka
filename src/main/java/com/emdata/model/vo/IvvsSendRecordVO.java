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
public class IvvsSendRecordVO implements Serializable {

    private static final long serialVersionUID = -601977507097948021L;

    @NotBlank(message = "城市代码不能为空")
    @ApiModelProperty(value = "城市代码", required = true)
    private String cityCode;

    @NotBlank(message = "源数据id不能为空")
    @ApiModelProperty(value = "源数据id", required = true)
    private String sourceDataId;

    @ApiModelProperty(value = "违法状态(0-疑似;1-违法;2-未违法)")
    private Integer illegalState;

}

