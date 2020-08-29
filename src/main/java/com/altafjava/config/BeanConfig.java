package com.altafjava.config;

import java.util.Collections;
import org.json.simple.parser.JSONParser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import com.altafjava.misc.LoggingRequestInterceptor;

/**
 * @author Altaf
 *
 */
@Configuration
public class BeanConfig {

	@Bean
	public RestTemplate buildrestTemplate() {
		RestTemplate restTemplate = new RestTemplate(new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory()));
		restTemplate.setInterceptors(Collections.singletonList(new LoggingRequestInterceptor()));
		return restTemplate;
	}
	@Bean
	public JSONParser createJSONParser() {
		return new JSONParser();
	}
}
