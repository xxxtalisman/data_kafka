package com.emdata.common.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.axis.client.Call;
import org.apache.axis.client.Service;

/**
 * @Date: 2019/12/12 16:31
 * @Version: 1.0
 * @Author: pengqingfeng
 * @Description:
 */
@Slf4j
public class WebServiceClient {

    public static String call(WsdlProperties properties , String xml) {
        Service service = new Service();
        try {
            Call call = (Call) service.createCall();
            call.setTargetEndpointAddress(properties.getUrl());
//            call.setOperationName(new QName("http://endpoint.axis.rm.tmri.com" , "writeObjectOut"));//此为测试用
            call.setOperationName("writeObjectOut");//保定生产用法
            call.setTimeout(10000);
            call.addParameter("xtlb" , org.apache.axis.encoding.XMLType.XSD_STRING , javax.xml.rpc.ParameterMode.IN);
            call.addParameter("jkxlh" , org.apache.axis.encoding.XMLType.XSD_STRING , javax.xml.rpc.ParameterMode.IN);
            call.addParameter("jkid" , org.apache.axis.encoding.XMLType.XSD_STRING , javax.xml.rpc.ParameterMode.IN);
            call.addParameter("UTF8XmlDoc" , org.apache.axis.encoding.XMLType.XSD_STRING , javax.xml.rpc.ParameterMode.IN);
            call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);
            call.setUseSOAPAction(true);
            call.setSOAPActionURI("http://www.wj.com/Rpc");
            String reXmlDoc = (String) call.invoke(new Object[]{properties.getXtlb() , properties.getJkxlh() , properties.getJkid() , xml});
            return reXmlDoc;
        } catch (Exception e) {
            log.error("发送集指报错 : {}" , e);
        }
        return null;
    }
}
