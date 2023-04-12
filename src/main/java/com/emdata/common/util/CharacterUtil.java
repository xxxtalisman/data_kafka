package com.emdata.common.util;

/**
 * @Author: txw
 * @Date: 2019/12/20 15:07
 */
public class CharacterUtil {

    /**
     * 处理nginx转义特殊字符
     */
    public static String nginxSpecialCharacter(String specialCharacter) {
        String pathStr = specialCharacter;
        //pathStr = pathStr.replaceAll("%" , "%25");
        pathStr = pathStr.replaceAll("#" , "%23");
        //pathStr = pathStr.replaceAll("&" , "%26");
        return pathStr;
    }

}
