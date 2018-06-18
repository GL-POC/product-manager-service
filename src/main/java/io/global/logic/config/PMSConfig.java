package io.global.logic.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@ConfigurationProperties(prefix = "product")
public class PMSConfig {

	private String name;
	
	private String url;
	
	private String email;
	
	private String title;
	
	private String description;
	
	private String version;
	
	private String termsOfServiceUrl;
	
	private String license;
	
	private String licenseUrl;
}
