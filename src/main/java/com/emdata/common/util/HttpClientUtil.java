package com.emdata.common.util;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;
import cn.hutool.http.HttpUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

@Slf4j
public class HttpClientUtil {

    /***
     * 根据url下载文件
     * @param url
     * @return
     */
    public static byte[] downloadFile(String url) {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] b = null;
        try {
            if (StrUtil.isNotEmpty(url)) {
                long download = HttpUtil.download(url , outStream , true);
                b = outStream.toByteArray();
            }
        } catch (Exception e) {
            log.error("【downloadFile:error】:[{}-{}]" , url , e.getMessage());
        }
        return b;
    }


    /***
     *
     * @param urlStr
     * @param isCompress 是否压缩
     * @param threshold 压缩阈值 (KB)
     * @return
     */
    public static String downloadToBase64(String urlStr , boolean isCompress , int threshold) {
        if (StrUtil.isBlank(urlStr)) {
            return null;
        }
        String data = "";
        //打开链接HttpURLConnection
        HttpURLConnection conn = null;
        InputStream inputStream = null;
        try {
            URL url = new URL(URLUtil.decode(urlStr));
            log.debug("downloadToBase64 url={}" , urlStr);

            conn = (HttpURLConnection) url.openConnection();
            //设置请求方式为"GET"
            conn.setRequestMethod("GET");
            //超时响应时间为5秒
            conn.setConnectTimeout(5 * 1000);
            conn.connect();

            if (conn.getResponseCode() == 200) {
                int size = conn.getContentLength() / 1024;
                inputStream = conn.getInputStream();
                if (size > threshold && isCompress) {
                    try {
                        // 创建输出流
                        ByteArrayOutputStream output = new ByteArrayOutputStream();
                        ImageCompressUtil.compress(inputStream , output , 0.6f);
                        byte[] bytes = output.toByteArray();
                        log.info("压缩前:{} , 压缩后:{}" , FileUtil.readableFileSize(conn.getContentLength()) , FileUtil.readableFileSize(bytes.length));
                        data = Base64.encode(bytes);
                    } catch (Exception e) {
                        log.warn("压缩失败:{}" , e.getMessage());
                        data = Base64.encode(IoUtil.readBytes(inputStream));
                    }
                } else {
                    data = Base64.encode(IoUtil.readBytes(inputStream));
                }
            } else {
                log.error("下载失败：code={} , url={}" , conn.getResponseCode() , urlStr);
            }
        } catch (Exception e) {
            log.error("downloadToBase64: url={ } , {} " , urlStr , e);
        } finally {
            if (conn != null) {
                try {
                    IoUtil.close(inputStream);
                    conn.disconnect();
                } catch (Exception e) {
                    log.warn(e.getMessage());
                }
            }
        }
        return data;
    }
}