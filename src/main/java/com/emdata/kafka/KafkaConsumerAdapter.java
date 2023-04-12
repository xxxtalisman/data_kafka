package com.emdata.kafka;

import com.emdata.common.util.GetSpringBeanUtil;
import com.emdata.kafka.security.LoginUtil;
import com.emdata.model.dto.RecordDataDTO;
import com.emdata.service.impl.DataHandelBootServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.*;


/**
 * <p>
 * 默认的kafka消费者，用于消费kafka中发送的消息
 * </p>
 *
 * @author txw
 * @since 2019-12-26
 */

@Slf4j
public class KafkaConsumerAdapter implements Serializable {


    private static final Properties props = new Properties();


    /**
     * 用户自己申请的机机账号keytab文件名称
     */
    private static final String USER_KEYTAB_FILE = "user.keytab";

    /**
     * kafka消费者，消费kafka中发送消息
     */
    private static KafkaConsumer<String,String> kafkaConsumer;


    private KafkaConsumerAdapter() throws Exception {
        initKafkaConsumer();
    }


    public void close() {
        try {
            kafkaConsumer.close();
        } catch (Exception e) {
            log.error("consumer close Exception :"+e.getMessage());
        }
    }


    /***
     * 初始化属性
     * @param properties
     */
    public static void initProperty(Properties properties) {
        props.putAll(properties);
        try {
            initKafkaConsumer();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("initKafkaConsumer Exception is {}",e.getMessage());
        }
    }

    /**
     * 根据kafka生产者配置信息初始化kafka消息生产者,只初始化一次
     */
    private static void initKafkaConsumer() throws Exception {
        try {
            //安全验证
            initSecurity(props);
            //实例化生产对象
            kafkaConsumer =  new KafkaConsumer<String,String>(props);
            kafkaConsumer.subscribe(Arrays.asList(props.get("topic.name").toString().split(",")));
            /*kafkaConsumer.subscribe(Arrays.asList(props.get("topic.name").toString().split(",")),new org.apache.kafka.clients.consumer.ConsumerRebalanceListener() {
                @Override
                public void onPartitionsRevoked(Collection<TopicPartition> collection) {
                }
                @Override
                public void onPartitionsAssigned(Collection<TopicPartition> collection) {
                    //将偏移设置到最开始
                    kafkaConsumer.seekToBeginning(collection);
                }
            });*/
        } catch (Exception e) {
            log.error("根据配置文件[kafka.properties]初始化kafka消费者出现异常:" + e.getMessage() , e);
            throw e;
        }
    }


    /**
     * 获取消费消息
     */
    public static void run(){
        log.info("初始化kafka消费者 run start...");
        while (true){
            try {
                ConsumerRecords<String, String> records = kafkaConsumer.poll(100L);
                //Consumer message
                for (ConsumerRecord<String, String> record : records) {
                    //log.info("topic={} partition={} offset = {}, key = {}, value = {}",record.topic(),record.partition(), record.offset(), record.key(), record.value());
                    //get message to every client 组织获取参数
                    RecordDataDTO recordData = new RecordDataDTO();
                    recordData.setRecordKey(record.key());
                    recordData.setRecordValue(record.value());
                    DataHandelBootServiceImpl dataHandelBootServiceImpl = GetSpringBeanUtil.getBean(DataHandelBootServiceImpl.class);
                    dataHandelBootServiceImpl.handleData(recordData);
                }
            }catch (Exception e){
                continue;
            }
        }
    }


    private static void initSecurity(Properties props) {
        log.info("initSecurity start.");
        boolean isSecurity = Boolean.valueOf(props.getProperty("is.security"));
        log.info("isSecurity is {}",isSecurity);
        if (isSecurity) {
            try {
                securityPrepare(props);
            } catch (IOException e) {
                log.error("Security prepare failure." , e);
            }
            log.info("Security prepare success.");
        }
    }

    /**
     * 生成加密认证
     * @param props
     * @throws IOException
     */
    private static void securityPrepare(Properties props) throws IOException {
        String filePath = props.getProperty("config.path") + File.separator;
        String krbFile = filePath + "krb5.conf";
        String userKeyTableFile = filePath + USER_KEYTAB_FILE;
        String principal = props.getProperty("user.principal");
        log.info("keytab : {}" , userKeyTableFile);
        log.info("login user : {}" , principal);
        LoginUtil.setKrb5Config(krbFile);
        LoginUtil.setZookeeperServerPrincipal("zookeeper/hadoop.hadoop.com");
        LoginUtil.setJaasFile(filePath , principal , userKeyTableFile);
    }

}
