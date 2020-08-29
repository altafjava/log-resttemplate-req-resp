package com.altafjava.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import lombok.Data;

/**
 * Model to store constants.properties file details
 * 
 * @author altaf
 *
 */
@Data
@Configuration
@PropertySource("file:files/constant.properties")
public class AppConfig {

	@Value("${usersURL}")
	private String usersURL;

	@Value("${postsURL}")
	private String postsURL;

}
