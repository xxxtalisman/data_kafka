package com.emdata.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @Date: 2019/7/2 20:11
 * @Version: 1.0
 * @Author: pengqingfeng
 * @Description:
 */
@Data
@ConfigurationProperties(prefix = "global")
public class GlobalProperties {


}
