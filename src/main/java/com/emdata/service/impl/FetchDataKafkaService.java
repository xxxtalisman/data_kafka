package com.emdata.service.impl;

import java.util.Arrays;
import java.util.List;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import com.emdata.mapper.ivvs.IvvsSbbhZhMapper;
import com.emdata.mapper.ivvs.IvvsViolateCodeMapper;
import com.emdata.strategy.VehicleExecuteDemo;
import com.hikvision.artemis.sdk.kafka.config.StartConfig;
import com.hikvision.artemis.sdk.kafka.entity.enums.TopicValueEnum;
import com.hikvision.artemis.sdk.kafka.starter.VehicleKafkaStarter;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class FetchDataKafkaService implements CommandLineRunner {
	
	@Autowired
	private IvvsViolateCodeMapper ivvsViolateCodeMapper ;
	
	@Autowired
	private IvvsSbbhZhMapper ivvsSbbhZhMapper;
	
	public StartConfig config;
	
	public volatile boolean runStatsu = true;
	
	@Override
	public void run(String... args) throws Exception {
		//System.out.println(sysParamProperties.getDownloadDirPath());
		
		//System.out.println(SpringContextHolder.getBean(SysParamProperties.class).getDownloadDirPath());
		//ObjectConverter.ss();
		StartConfig config = new StartConfig();
        config.setArtemisIp("41.129.16.22");
        config.setArtemisPort(443);
        config.setAppKey("28136495");
        config.setAppSecret("kBMDOynWjEPJ44zccj7K");
        List<TopicValueEnum> topics = Arrays.asList(TopicValueEnum.VEHICLE_ALARM,TopicValueEnum.VEHICLE_PASS);
        config.setTopics(topics);
        config.setGroupId("test_vehicle:"+ IdWorker.getId());
        config.setDomainId("0");
        //线程池配置参数，非必传
        config.setProcessorConfig(100,100,600,10000);
        //Kafka配置参数，非必传
        config.setKafkaConfig(60000,100000,20000,300);
        //二次识别参数设置，必传
        config.setRecognitionSign(true,true,true,true);
        config.setVehicleExecute(new VehicleExecuteDemo(ivvsViolateCodeMapper,ivvsSbbhZhMapper));
        this.config = config;
        try {
        	//启动另外一个线程监控
        	new Thread(()->{
        		restart();
        	}).start();
        	VehicleKafkaStarter.start(config);
		} catch (Exception e) {
			runStatsu = false;
			log.error("kafka 异常",e);
		}
        
	}
	
	public void restart() {
		while(true) {
			try {
				Thread.sleep(60000L);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			if (!runStatsu) {
				try {
					config.setGroupId("test_vehicle:"+ IdWorker.getId());
					VehicleKafkaStarter.start(config);
				} catch (Exception e) {
					log.error("kafka 异常",e);
				}
			}
		}
		
	}
	
}
