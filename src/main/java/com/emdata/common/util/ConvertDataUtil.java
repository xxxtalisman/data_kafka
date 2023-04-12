package com.emdata.common.util;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;
import com.alibaba.fastjson.JSONObject;
import com.emdata.common.enums.IllegalEnum;
import com.emdata.model.dto.CommonReturnDataDTO;

import java.util.Objects;

/**
 * @Date: 2019/12/12 16:26
 * @Version: 1.0
 * @Author: pengqingfeng
 * @Description:
 */
public class ConvertDataUtil {

    public static final String SEPARATOR = "_";

    public static final String DEFAULT_VAL = "NULL";


    /***
     *
     * 转换发送集指报文
     * @param jkid 接口ID
     * @param dto 数据实体
     * @return
     */
    public static String toReqXml(String jkid , CommonReturnDataDTO dto) {
        StringBuffer sb = new StringBuffer();
        String rootNode = null;
        if (StrUtil.equals(jkid , Constant.JKID_60W02)) {
            rootNode = "surexamine";
        } else if (StrUtil.equals(jkid , Constant.JKID_60W01)) {
            rootNode = "surscreen";
        }

        sb.append("<root><" + rootNode + ">");

        if (dto.getZpsl() > 0) {
            sb.append("<zpsl>").append(URLUtil.encodeAll(dto.getZpsl() + "")).append("</zpsl>");
        }

        if (StrUtil.isNotBlank(dto.getBzz())) {
            sb.append("<bzz>").append(URLUtil.encodeAll(dto.getBzz())).append("</bzz>");
        }

        if (StrUtil.isNotBlank(dto.getClfl())) {
            sb.append("<clfl>").append(URLUtil.encodeAll(dto.getClfl())).append("</clfl>");
        }

        if (StrUtil.isNotBlank(dto.getDdms())) {
            sb.append("<ddms>").append(URLUtil.encodeAll(dto.getDdms())).append("</ddms>");
        }
        if (StrUtil.isNotBlank(dto.getHphm())) {
            sb.append("<hphm>").append(URLUtil.encodeAll(dto.getHphm())).append("</hphm>");
        }
        if (StrUtil.isNotBlank(dto.getHpzl())) {
            sb.append("<hpzl>").append(URLUtil.encodeAll(dto.getHpzl())).append("</hpzl>");
        }
        if (StrUtil.isNotBlank(dto.getLddm())) {
            sb.append("<lddm>").append(URLUtil.encodeAll(dto.getLddm())).append("</lddm>");
        }
        if (StrUtil.isNotBlank(dto.getSbbh())) {
            sb.append("<sbbh>").append(URLUtil.encodeAll(dto.getSbbh())).append("</sbbh>");
        }
        if (StrUtil.isNotBlank(dto.getScz())) {
            sb.append("<scz>").append(URLUtil.encodeAll(dto.getScz())).append("</scz>");
        }
        if (StrUtil.isNotBlank(dto.getWfdd())) {
            sb.append("<wfdd>").append(URLUtil.encodeAll(dto.getWfdd())).append("</wfdd>");
        }

        if (null != dto.getWfsj()) {
            sb.append("<wfsj>").append(DateUtil.format(dto.getWfsj() , DatePattern.NORM_DATETIME_PATTERN)).append("</wfsj>");
        }
        if (null != dto.getWfsj1()) {
            sb.append("<wfsj1>").append(DateUtil.format(dto.getWfsj1() , DatePattern.NORM_DATETIME_PATTERN)).append("</wfsj1>");
        }

        if (StrUtil.isNotBlank(dto.getWfdz())) {
            sb.append("<wfdz>").append(URLUtil.encodeAll(dto.getWfdz())).append("</wfdz>");
        }

        if (StrUtil.isNotBlank(dto.getWfspdz())) {
            sb.append("<wfspdz>").append(URLUtil.encodeAll(dto.getWfspdz())).append("</wfspdz>");
        }
        if (StrUtil.isNotBlank(dto.getWfxw())) {
            sb.append("<wfxw>").append(URLUtil.encodeAll(dto.getWfxw())).append("</wfxw>");
        }

        if (StrUtil.isNotBlank(dto.getXzqh())) {
            sb.append("<xzqh>").append(URLUtil.encodeAll(dto.getXzqh())).append("</xzqh>");
        }
        if (StrUtil.isNotBlank(dto.getZqmj())) {
            sb.append("<zqmj>").append(URLUtil.encodeAll(dto.getZqmj())).append("</zqmj>");
        }
        if (StrUtil.isNotBlank(dto.getZpwjm())) {
            sb.append("<zpwjm>").append(URLUtil.encodeAll(dto.getZpwjm())).append("</zpwjm>");
        }

        if (StrUtil.isNotBlank(dto.getCsys())) {
            sb.append("<csys>").append(URLUtil.encodeAll(dto.getCsys())).append("</csys>");
        }
        if (StrUtil.isNotBlank(dto.getClpp())) {
            sb.append("<clpp>").append(URLUtil.encodeAll(dto.getClpp())).append("</clpp>");
        }
//        if (StrUtil.isNotBlank(dto.getZpwjm())) {
//            sb.append("<jtfs>").append(URLUtil.encodeAll(dto.getJtfs())).append("</jtfs>");
//        }

//        if (StrUtil.isNotBlank(dto.getZpwjm())) {
//            sb.append("<zsxzqh>").append(URLUtil.encodeAll(dto.getZsxzqh())).append("</zsxzqh>");
//        }
        if (StrUtil.isNotBlank(dto.getDh())) {
            sb.append("<dh>").append(URLUtil.encodeAll(dto.getDh())).append("</dh>");
        }

        if (StrUtil.isNotBlank(dto.getLxfs())) {
            sb.append("<lxfs>").append(URLUtil.encodeAll(dto.getLxfs())).append("</lxfs>");
        }
        if (StrUtil.isNotBlank(dto.getXcfw())) {
            sb.append("<xcfw>").append(URLUtil.encodeAll(dto.getXcfw())).append("</xcfw>");
        }

        if (StrUtil.isNotBlank(dto.getFzjg())) {
            sb.append("<fzjg>").append(URLUtil.encodeAll(dto.getFzjg())).append("</fzjg>");
        }
        if (StrUtil.isNotBlank(dto.getJdcsyr())) {
            sb.append("<jdcsyr>").append(URLUtil.encodeAll(dto.getJdcsyr())).append("</jdcsyr>");
        }
        if (StrUtil.isNotBlank(dto.getZpstr1())) {
            sb.append("<zpstr1>").append(dto.getZpstr1()).append("</zpstr1>");
        }
        if (StrUtil.isNotBlank(dto.getZpstr2())) {
            sb.append("<zpstr2>").append(dto.getZpstr2()).append("</zpstr2>");
        }
        if (StrUtil.isNotBlank(dto.getZpstr3())) {
            sb.append("<zpstr3>").append(dto.getZpstr3()).append("</zpstr3>");
        }
        sb.append("</" + rootNode + "></root>"); //60W02
        return sb.toString();
    }


    /**
     * 苏州转换发送文件名称
     *
     * @param dto
     * @return
     */
    public static String convertFtpName(CommonReturnDataDTO dto) {
        StringBuffer sb = new StringBuffer();
        sb.append(getValue(dto.getSbbh())).append(SEPARATOR);//1
        sb.append(getValue(dto.getWfdd())).append(SEPARATOR);//路口编号//2
        sb.append(getValue(dto.getFxdm())).append(SEPARATOR);//3
        sb.append(getValue(DateUtil.format(dto.getWfsj() , DatePattern.PURE_DATETIME_PATTERN))).append(SEPARATOR);//4
        sb.append(getValue(dto.getScz())).append(SEPARATOR);//5
        sb.append(getValue(dto.getBzz())).append(SEPARATOR);//6
        sb.append(getValue(dto.getHpzl())).append(SEPARATOR);//7
        sb.append(getValue(dto.getHphm())).append(SEPARATOR);//8
        sb.append(getValue(dto.getWfxw())).append(SEPARATOR);//9
        sb.append(getValue(dto.getGxbm())).append(SEPARATOR);//10
        sb.append(getValue(dto.getYhdm())).append(SEPARATOR);//11
        sb.append(getValue(dto.getSjly())).append(SEPARATOR);//12
        sb.append(getValue(Objects.isNull(dto.getZpsl()) ? null : dto.getZpsl().toString())).append(SEPARATOR);//13
        sb.append(getValue("1")).append(SEPARATOR);//图片序号//14
        sb.append(getValue(dto.getCdh())).append(SEPARATOR);//15
        sb.append(getValue(dto.getCjh())).append(SEPARATOR);//16
        sb.append(getValue(DateUtil.format(dto.getQjcssj() , DatePattern.PURE_DATETIME_PATTERN))).append(SEPARATOR);//17
        sb.append(getValue(dto.getLddm())).append(SEPARATOR);//路口代码//18
        sb.append(getValue(dto.getDdms())).append(SEPARATOR);//19
        sb.append(getValue(dto.getCllx())).append(SEPARATOR);//20

        if (StrUtil.isNotBlank(dto.getGp01())) {
            sb.append(getValue(dto.getGp01().toUpperCase())).append(SEPARATOR);//21
        }

        //T=是，F=否，N=不确定   0: not sure; 1: violation; 2: no violation
        if (Objects.nonNull(dto.getIllegalState())) {
            String ret = "";
            if (dto.getIllegalState().intValue() == IllegalEnum.ILLEGAL.getCode()) {
                ret = "T";
            } else if (dto.getIllegalState().intValue() == IllegalEnum.ILLEGAL_NOT.getCode()) {
                ret = "F";
            } else if (dto.getIllegalState().intValue() == IllegalEnum.SUSPECTED.getCode()) {
                ret = "N";
            }
            sb.append(ret);//21 or 22
        } else {
            sb.append(getValue(""));
        }

        return sb.toString();
    }



    private static String getValue(String value) {
        String ret = value;
        if (StrUtil.isBlank(value)) {
            ret = DEFAULT_VAL;
        }
        return ret;
    }

    public static void main(String[] args) {
        String s = convertFtpName(new CommonReturnDataDTO());
        System.out.println(s);
    }
}
