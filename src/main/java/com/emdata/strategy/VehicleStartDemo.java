package com.emdata.strategy;

import com.hikvision.artemis.sdk.kafka.config.StartConfig;
import com.hikvision.artemis.sdk.kafka.entity.enums.TopicValueEnum;
import com.hikvision.artemis.sdk.kafka.starter.VehicleKafkaStarter;

import java.util.Arrays;
import java.util.List;

/**
 * 车辆数据启动服务示例Demo
 */
public class VehicleStartDemo {

    public static void main(String[] args) {
        StartConfig config = new StartConfig();
        config.setArtemisIp("41.129.16.26");
        config.setArtemisPort(443);
        config.setAppKey("26997035");
        config.setAppSecret("KPDEz08yGsL6ZqrXn9Dc");
        List<TopicValueEnum> topics = Arrays.asList(TopicValueEnum.VEHICLE_PASS);
        config.setTopics(topics);
        config.setGroupId("test_vehicle");
        config.setDomainId("0");
        //线程池配置参数，非必传
        config.setProcessorConfig(100,100,600,10000);
        //Kafka配置参数，非必传
        config.setKafkaConfig(60000,100000,20000,300);
        //二次识别参数设置，必传
        config.setRecognitionSign(true,true,true,true);
        //config.setVehicleExecute(new VehicleExecuteDemo());
        VehicleKafkaStarter.start(config);
    }
}
