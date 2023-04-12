package com.emdata.common.util;


import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.naming.NoNameCoder;
import com.thoughtworks.xstream.io.xml.CompactWriter;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;
import lombok.extern.slf4j.Slf4j;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * @Date: 2018/11/22 10:07
 * @Version: 1.0
 * @Author: pengqingfeng
 * @Description:
 */
@Slf4j
public class XmlUtil {



    public static String trimStr(String xml) {
        StringBuffer sb = new StringBuffer();
        for (String s : xml.split("\n")) {
            sb.append(s.trim());
        }
        return sb.toString();
    }


    public static Object toBean(Class<?> clazz, String xml) {
        Object xmlObject = null;
        XStream xstream = new XStream();
        xstream.processAnnotations(clazz);
        xstream.ignoreUnknownElements();//忽略未知元素
        xstream.autodetectAnnotations(true);
        xmlObject = xstream.fromXML(xml);
        return xmlObject;
    }

    /**
     * 将XML转为指定的POJO
     *
     * @param clazz
     * @param xmlStr
     * @return
     * @throws Exception
     */
    public static Object xmlStrToOject(Class<?> clazz, String xmlStr) throws Exception {
        Object xmlObject = null;
        Reader reader = null;
        JAXBContext context = JAXBContext.newInstance(clazz);
        // XML 转为对象的接口
        Unmarshaller unmarshaller = context.createUnmarshaller();
        reader = new StringReader(xmlStr);
        //以文件流的方式传入这个string
        xmlObject = unmarshaller.unmarshal(reader);
        if (null != reader) {
            reader.close();
        }
        return xmlObject;
    }

    /**
     * 转换不带CDDATA的XML
     *
     * @return
     * @
     */
    private static XStream getXStream() {
        // 实例化XStream基本对象
        XStream xstream = new XStream(new DomDriver(StandardCharsets.UTF_8.name(), new NoNameCoder() {
            // 不对特殊字符进行转换，避免出现重命名字段时的“双下划线”
            @Override
            public String encodeNode(String name) {
                return name;
            }
        }));
        // 忽视XML与JAVABEAN转换时，XML中的字段在JAVABEAN中不存在的部分
        xstream.ignoreUnknownElements();
        return xstream;
    }

    /**
     * 转换带CDDATA的XML
     *
     * @return
     * @
     */
    private static XStream getXStreamWithCData() {
        // 实例化XStream扩展对象
        XStream xstream = new XStream(new XppDriver() {
            // 扩展xstream，使其支持CDATA块
            @Override
            public HierarchicalStreamWriter createWriter(Writer out) {
                return new PrettyPrintWriter(out) {
                    // 不对特殊字符进行转换，避免出现重命名字段时的“双下划线”
                    @Override
                    public String encodeNode(String name) {
                        return name;
                    }

                    // 对所有xml节点的转换都增加CDATA标记
                    @Override
                    protected void writeText(QuickWriter writer, String text) {
                        writer.write("<![CDATA[");
                        writer.write(text);
                        writer.write("]]>");
                    }
                };
            }
        });
        // 忽视XML与JAVABEAN转换时，XML中的字段在JAVABEAN中不存在的部分
        xstream.ignoreUnknownElements();
        return xstream;
    }

    /**
     * 以压缩的方式输出XML
     *
     * @param obj
     * @return
     */
    public static String toCompressXml(Object obj) {
        XStream xstream = getXStream();
        StringWriter sw = new StringWriter();
        // 识别obj类中的注解
        xstream.processAnnotations(obj.getClass());
        // 设置JavaBean的类别名
        xstream.aliasType("xml", obj.getClass());
        xstream.marshal(obj, new CompactWriter(sw));
        return sw.toString();
    }

    /**
     * 以格式化的方式输出XML
     *
     * @param obj
     * @return
     */
    public static String toXml(Object obj) {
        XStream xstream = getXStream();
        // 识别obj类中的注解
        xstream.processAnnotations(obj.getClass());
        // 设置JavaBean的类别名
        xstream.aliasType("xml", obj.getClass());
//        NullConverter v = new NullConverter();
//        xstream.registerConverter(v);
        return xstream.toXML(obj);
    }

    /**
     * 转换成JavaBean
     *
     * @param xmlStr
     * @param cls
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T toBean(String xmlStr, Class<T> cls) {
        XStream xstream = getXStream();
        // 识别cls类中的注解
        xstream.processAnnotations(cls);
        // 设置JavaBean的类别名
        xstream.aliasType("xml", cls);
        T t = (T) xstream.fromXML(xmlStr);
        return t;
    }

    /**
     * 以格式化的方式输出XML
     *
     * @param obj
     * @return
     */
    public static String toXmlWithCData(Object obj) {
        XStream xstream = getXStreamWithCData();
        // 识别obj类中的注解
        xstream.processAnnotations(obj.getClass());
        // 设置JavaBean的类别名
        xstream.aliasType("xml", obj.getClass());
        return xstream.toXML(obj);
    }

    /**
     * 转换成JavaBean
     *
     * @param xmlStr
     * @param cls
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T toBeanWithCData(String xmlStr, Class<T> cls) {
        XStream xstream = getXStreamWithCData();
        // 识别cls类中的注解
        xstream.processAnnotations(cls);
        // 设置JavaBean的类别名
        xstream.alias("xml", cls);
        T t = (T) xstream.fromXML(xmlStr);
        return t;
    }

    /**
     * 正转
     * 针对内容，将不可见字符转为可见字符
     * 注意！是针对字段内容，不是针对全报文
     */
    public static String checkXmlStr(String str) {
        if (str == null) {
            return "";
        }
        StringBuilder buff = new StringBuilder();
        char[] array = str.toCharArray();
        for (int k = 0, len = array.length; k < len; k++) {
            char cc = array[k];
            int ss = (int) cc;
            if (ss >= 32) {
                switch (cc) {
                    case '&':
                        buff.append("&amp;");
                        break;
                    case '<':
                        buff.append("&lt;");
                        break;
                    case '>':
                        buff.append("&gt;");
                        break;
                    case '\'':
                        buff.append("&apos;");
                        break;
                    case '\"':
                        buff.append("&quot;");
                        break;
                    default:
                        buff.append(cc);
                }
            } else if (((ss >= 0) && (ss <= 8)) || ((ss >= 11) && (ss <= 12)) || ((ss >= 14) && (ss < 32))) {
                if (ss < 16) {
                    buff.append("\\\\0x0" + Integer.toHexString(ss) + ";");
                } else {
                    buff.append("\\\\0x" + Integer.toHexString(ss) + ";");
                }
            } else {
                buff.append(cc);
            }
        }
        return buff.toString();
    }

    /**
     * 不可见字符转换处理
     * 逆转
     *
     * @param value
     * @return
     */
    public static String checkXmlStr1(String value) {
        if (value.indexOf('\\') < 0) {
            return value;
        } else {
            StringBuffer sb = new StringBuffer();
            char[] array = value.toCharArray();
            for (int i = 0, len = array.length; i < len; i++) {
                char c = array[i];
                if (c == '\\' && i + 7 <= len) {
                    if (array[i + 1] == '\\' && array[i + 2] == '0' && array[i + 3] == 'x' && array[i + 6] == ';') {
                        String temp = String.valueOf(array[i + 4]) + String.valueOf(array[i + 5]);
                        char v = (char) Integer.parseInt(temp, 16);
                        sb.append(v);
                        i = i + 6;
                    } else {
                        sb.append(c);
                    }
                } else {
                    sb.append(c);
                }
            }
            return sb.toString();
        }
    }

}