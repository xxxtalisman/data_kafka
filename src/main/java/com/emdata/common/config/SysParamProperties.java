package com.emdata.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@ConfigurationProperties(prefix = "sys")
@Configuration
@Data
public class SysParamProperties {
	
	/**
	 * 下载图片的地址
	 */
	private String downloadDirPath;
	
}
