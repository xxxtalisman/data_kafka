package com.emdata.common.util;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;
import cn.hutool.extra.ftp.Ftp;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.emdata.kafka.KafkaProducerAdapter;
import com.emdata.model.dto.CommonReturnDataDTO;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Date: 2019/12/12 15:04
 * @Version: 1.0
 * @Author: pengqingfeng
 * @Description:
 */
@Slf4j
public class ThirdPartyUtil {


    /***
     * 发送集指
     * @param properties
     * @param dto 数据实体
     */
    public static RespRet sendJiZhi(WsdlProperties properties , CommonReturnDataDTO dto) {
        RespRet ret = new RespRet();
        try {
            convertAllImgToBase64(dto);
            String reqXml = ConvertDataUtil.toReqXml(properties.getJkid() , dto);
            String respXml = WebServiceClient.call(properties , reqXml);

            if (log.isDebugEnabled()) {
                log.debug("集指返回报文 : {}" , respXml);
            }

            JSONObject head = jzRetToJson(respXml);
            //{"root":{"head":{"code":0,"message":"设备编号错误，设备编号应为18位字符串","value":"sbbh"}}}
            if (Objects.nonNull(head)) {
                String code = head.get("code") + "";
                //成功
                if (StrUtil.equals(Constant.JZ_SUCCESS_CODE , code)) {
                    return RespRet.ok(respXml);
                } else {
                    return RespRet.failed(respXml);
                }
            } else {
                return RespRet.failed(respXml);
            }
        } catch (Exception e) {
            log.error(e.getMessage() , e);
            return RespRet.failed(ThrowableUtil.getStackTrace(e));
        }
    }

    private static JSONObject jzRetToJson(String xml) {
        JSONObject object = JSONUtil.xmlToJson(xml);
        JSONObject head = (JSONObject) ((JSONObject) object.get("root")).get("head");
        return head;
    }

    /***
     *将所有图片转换为base64
     * @param dto
     */
    private static void convertAllImgToBase64(CommonReturnDataDTO dto) {
        convertAllImgToBase64(dto , true);
    }

    /***
     *
     * @param dto
     * @param isEncode
     */
    private static void convertAllImgToBase64(CommonReturnDataDTO dto , boolean isEncode) {
        AtomicInteger count = new AtomicInteger(0);
        dto.setZpstr1(downloadPicToBase64(dto.getZpstr1() , count , isEncode));
        dto.setZpstr2(downloadPicToBase64(dto.getZpstr2() , count , isEncode));
        dto.setZpstr3(downloadPicToBase64(dto.getZpstr3() , count , isEncode));
        dto.setZpsl(count.intValue());
    }

    /***
     * 下载图片并累加图片数量
     * @param url
     * @param count
     * @param isEncode 是否编码
     * @return
     */
    private static String downloadPicToBase64(String url , AtomicInteger count , boolean isEncode) {
        String base64 = HttpClientUtil.downloadToBase64(url , false , 500);
        if (isEncode && StrUtil.isNotBlank(base64)) {
            base64 = URLUtil.encodeAll(base64);
        }
        if (StrUtil.isNotBlank(base64)) {
            count.incrementAndGet();
        }
        return base64;
    }

    /***
     * 下载图片并累加图片数量
     * @param url
     * @param count
     * @return
     */
    private static String downloadPicToBase64(String url , AtomicInteger count) {
        return downloadPicToBase64(url , count , true);
    }


    public static String encodeUTF8(String xmlDoc) {
        String str = "";
        try {
            if (!"".equals(xmlDoc)) {
                str = URLEncoder.encode(xmlDoc , "utf-8");
            }
            return str;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return str;
    }


    /***
     * ftp上传
     * @param properties
     * @param fileName 文件名称
     * @param url 文件地址
     * @return
     * @throws Exception
     */
    public static boolean httpUploadFtp(FtpProperties properties , String fileName , String url) throws Exception {
        Ftp ftp = null;
        try {
            log.debug("httpUploadFtp begin   : {}" , url);
            ftp = new Ftp(properties.getHost() , properties.getPort() , properties.getUsername() , properties.getPassword());
            InputStream stream = URLUtil.getStream(URLUtil.url(url));
            boolean flag = ftp.upload(properties.getPath() , fileName , stream);
            return flag;
        } catch (Exception e) {
            throw e;
        } finally {
            if (ftp != null) {
                try {
                    ftp.close();
                } catch (IOException e) {
                    log.error(e.getMessage() , e);
                }
            }
        }
    }


    /***
     * 发送kafka
     * @param topicName 主题名称
     * @param jsonStr 发送报文
     * @return
     */
    public static RespRet sendKafka(String topicName , String jsonStr) {
        RespRet ret = RespRet.ok();
        try {
            KafkaProducerAdapter.getInstance().send(topicName , jsonStr).flush();
        } catch (Exception e) {
            log.error(e.getMessage() , e);
            ret = RespRet.failed(e.getMessage());
        }
        return ret;

    }


    public static void main(String[] args) {
        CommonReturnDataDTO dataDTO = new CommonReturnDataDTO();
        WsdlProperties wsdlProperties = new WsdlProperties();
        dataDTO.setHphm("急A1234561").setWfdz("上海谢谢谢谢休息休息休息休息");
        wsdlProperties.setJkid("60W01");
        wsdlProperties.setUrl("http://192.168.70.32:8030/rminf/services/RmOutAccess?wsdl");
        wsdlProperties.setXtlb("60");
        wsdlProperties.setJkxlh("7A1E1D08000703050D1502010002090200060904030D17E4FD326D72692E636E");
        dataDTO.setZpstr1("http://192.168.70.32/ivhs/picture/composite_picture/2019-12-12/257554784586829824_26328_composite.jpg");
//        dataDTO.setZpstr2("http://192.168.70.32/ivhs/picture/composite_picture/2019-12-12/257554784586829824_26328_composite.jpg");
//        dataDTO.setZpstr3("http://192.168.70.32/ivhs/picture/composite_picture/2019-12-12/257554784586829824_26328_composite.jpg");
//        RespRet respRet = sendJiZhi(wsdlProperties , dataDTO);

        FtpProperties ftpProperties = new FtpProperties();
        ftpProperties.setHost("192.168.70.32");
        ftpProperties.setPort(21);
        ftpProperties.setUsername("ykftp");
        ftpProperties.setPassword("ykftp");
        ftpProperties.setPath("/");
//        RespRet respRet = sendFtp(ftpProperties , dataDTO);
//        System.out.println(respRet);


    }


}
