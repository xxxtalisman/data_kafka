package com.emdata.common.util;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Date: 2019/7/15 14:24
 * @Version: 1.0
 * @Author: pengqingfeng
 * @Description:
 */
@Data
public class WsdlProperties {

    /***
     * 接口地址
     */
    private String url;

    /***
     * 系统类别
     */
    private String xtlb;

    /**
     * 接口标识
     */
    private String jkid;

    /***
     * 接口序列号
     */
    private String jkxlh;


    /***
     * 场景申请编号
     */
    private String cjsqbh;

    /***
     * 单位机构代码
     */
    private String dwjgdm;

    /***
     * 单位名称
     */
    private String dwmc;

    /***
     * 用户标识
     */
    private String yhbz;

    /***
     * 用户姓名
     */
    private String yhxm;

    /***
     * 终端标识
     */
    private String zdbs;
}
