package com.emdata.common.util;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @Date: 2019/2/27 16:42
 * @Version: 1.0
 * @Author: pengqingfeng
 * @Description:
 */
@Slf4j
public class SystemUtil {

    /***
     * 添加权限
     * @param path
     */
    public static void addPermission(String path) {
        if (isWin() || StrUtil.isBlank(path)) {
            return;
        }
        String command = "sudo chmod 777 " + path;
        try {
            if (!command.equals("/")) {
                Runtime.getRuntime().exec(command);
            }
        } catch (Exception e) {
            log.error("======Runtime.getRuntime addPermission Exceptions======{}", command);
        }
    }

    /**
     * 判断当前系统
     *
     * @return
     */
    public static boolean isWin() {
        String os = System.getProperty("os.name");
        if (os.toLowerCase().startsWith("win")) {
            return true;
        }
        return false;
    }
}
