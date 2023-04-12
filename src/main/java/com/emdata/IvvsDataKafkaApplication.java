package com.emdata;

import com.emdata.common.config.GlobalProperties;
import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableEncryptableProperties
@EnableScheduling
@EnableConfigurationProperties({GlobalProperties.class})
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class IvvsDataKafkaApplication {

    public static void main(String[] args) {
        SpringApplication.run(IvvsDataKafkaApplication.class , args);
    }

}
