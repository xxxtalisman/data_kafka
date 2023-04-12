package com.emdata.common.util;

import lombok.Data;

/**
 * @Date: 2019/7/15 14:24
 * @Version: 1.0
 * @Author: pengqingfeng
 * @Description:
 */
@Data
public class FtpProperties {

    /***
     * ftp地址
     */
    private String host;

    /***
     * 端口
     */
    private Integer port;

    /**
     * 用户名
     */
    private String username;

    /***
     * 密码
     */
    private String password;

    /***
     * 路径
     */
    private String path;

}
