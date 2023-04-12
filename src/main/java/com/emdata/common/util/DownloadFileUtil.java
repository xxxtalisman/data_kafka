package com.emdata.common.util;

import cn.hutool.core.io.FileTypeUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created on 2018/11/13.
 */
@Slf4j
public class DownloadFileUtil {

    /**
     * 可下载http和ftp协议文件
     *
     * @param urlStr
     * @param dirPath
     * @param fileName
     * @return
     */
    public static String downLoad(String urlStr, String dirPath, String fileName,byte[] imgBlob){
        if (StrUtil.isEmpty(urlStr) && imgBlob == null) {
            return null;
        }
        byte[] getData;
        InputStream in = null;
        try {
            if (imgBlob != null) {
                getData = imgBlob;
            } else {
                urlStr = urlStr.replaceAll("\\\\","/");
                //转义特殊字符
                urlStr = CharacterUtil.nginxSpecialCharacter(urlStr);
                log.info("{}【downLoad-begin】 - url:{}",Thread.currentThread().getName(),urlStr,"UTF-8");
                URL url = new URL(encode(urlStr,"UTF-8"));
                URLConnection conn =  url.openConnection();
                conn.setRequestProperty("accept", "*/*");
                conn.setRequestProperty("connection", "Keep-Alive");
                conn.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/47.0.2526.106 Safari/537.36");
                conn.setRequestProperty("accept-language", "zh-CN");
                conn.addRequestProperty("Cache-Control", "no-cache");
                conn.setUseCaches(false);
                //设置超时时间30s
                conn.setConnectTimeout(30*1000);
                in = conn.getInputStream();
                getData = IoUtil.readBytes(in);
            }
            String suffix = getSuffix(urlStr,getData);
            String path = dirPath + fileName + "."+suffix;
            FileUtil.writeBytes(getData,path);
            SystemUtil.addPermission(path);
            log.info("{}【downLoad-end】",Thread.currentThread().getName());
            return path.replaceAll("/data/public","");
        } catch (Exception e) {
            log.error("获取图片失败{}",urlStr,e);
            return null;
        }finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    log.error("关闭图片读取Stream失败{}",urlStr,e);
                }
            }
        }
    }


    /**
     * 
     * @param str
     * @param charset
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String encode(String str, String charset) throws UnsupportedEncodingException {
        String reg = "[\\u4E00-\\u9FA5]+";
        Pattern pattern = Pattern.compile(reg);
        Matcher m = pattern.matcher(str);
        StringBuffer b = new StringBuffer();
        while (m.find()) {
            m.appendReplacement(b, URLEncoder.encode(m.group(0), charset));
        }
        m.appendTail(b);
        return b.toString();
    }

    public static String getSuffix(String url,byte[] datas) {
        String suffix = null;
        if (StrUtil.isNotBlank(url)) {
            String suffixes="avi|mpeg|3gp|mp3|mp4|wav|jpeg|gif|jpg|png|bmp";
            //正则判断
            Pattern pat=Pattern.compile(".+[\\.]("+suffixes+")$");
            //条件匹配
            Matcher mc=pat.matcher(url);
            while(mc.find()){
                //截取文件名后缀名
                suffix = mc.group(1);
            }
        }
        if (StrUtil.isBlank(suffix)) {
            suffix = FileTypeUtil.getType(IoUtil.toStream(datas));
        }
        if (StrUtil.isBlank(suffix)) {
            suffix = "jpg";
        }
        return suffix;
    }


    /***
     * 创建目录
     * @param dirPath
     * @return
     */
    public static boolean mkdir(String dirPath){
        try {
            //判断文件夹是否存在
            if (!FileUtil.exist(dirPath)){
                FileUtil.mkdir(dirPath);
                SystemUtil.addPermission(dirPath);
            }
            return true;
        }catch (Exception e){
            log.error("创建文件夹失败",e);
        }
        return false;
    }

}
