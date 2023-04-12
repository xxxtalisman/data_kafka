package com.emdata.common.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.axis.client.Call;
import org.apache.axis.client.Service;

/**
 * @Date: 2019/7/15 15:08
 * @Version: 1.0
 * @Author: pengqingfeng
 * @Description:
 */
@Slf4j
public class WsdlUtil {

    public static String callWsdl(WsdlProperties properties , String queryXmlDoc) {
        try {
            long s = System.currentTimeMillis();
            Service service = new Service();
            Call call = (Call) service.createCall();
            call.setTargetEndpointAddress(properties.getUrl());
            call.setOperationName("queryObjectOutNew");
            call.setTimeout(3000);
            call.addParameter("xtlb" , org.apache.axis.encoding.XMLType.XSD_STRING , javax.xml.rpc.ParameterMode.IN);
            call.addParameter("jkxlh" , org.apache.axis.encoding.XMLType.XSD_STRING , javax.xml.rpc.ParameterMode.IN);
            call.addParameter("jkid" , org.apache.axis.encoding.XMLType.XSD_STRING , javax.xml.rpc.ParameterMode.IN);
            call.addParameter("cjsqbh" , org.apache.axis.encoding.XMLType.XSD_STRING , javax.xml.rpc.ParameterMode.IN);
            call.addParameter("QueryXmlDoc" , org.apache.axis.encoding.XMLType.XSD_STRING , javax.xml.rpc.ParameterMode.IN);
            call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);
            call.setUseSOAPAction(true);

            call.setSOAPActionURI("http://www.wj.com/Rpc");
            String retXml = (String) call.invoke(new Object[]{properties.getXtlb() , properties.getJkxlh() , properties.getJkid() , queryXmlDoc});
            log.info("retXml:{}" , retXml);
            log.info("耗时:{}" , System.currentTimeMillis() - s);
            return retXml;
        } catch (Exception e) {
            log.error("callWsdl:error:{}" , e.getMessage());
            return null;

        }
    }



}
