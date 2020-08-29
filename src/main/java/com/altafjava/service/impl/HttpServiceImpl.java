package com.altafjava.service.impl;

import java.nio.charset.Charset;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import com.altafjava.constant.AppConstant;
import com.altafjava.service.HttpService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class HttpServiceImpl implements HttpService {

	@Autowired
	private RestTemplate restTemplate;

	/**
	 * Provides a wrapper for restTemplate get method.
	 *
	 * @param url the url
	 * @return the object
	 */
	@Override
	public ResponseEntity<String> get(String url, MultiValueMap<String, String> headers) {
		HttpEntity<?> httpEntity = new HttpEntity<>(headers);
		ResponseEntity<String> responseEntity = null;
		log.info("URL : " + url);
		log.info("Request Headers : " + httpEntity.getHeaders().toString());
		try {
			responseEntity = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);
			log.info("Response Status : " + responseEntity.getStatusCode());
			log.info("Response Headers : " + responseEntity.getHeaders().toString());
		} catch (HttpClientErrorException | HttpServerErrorException e) {
			log.error("" + e);
			log.error("Error Message : {}", e.getResponseBodyAsString());
			log.debug("         " + e.getStatusCode());
		}
		return responseEntity;
	}

	/**
	 * Provides a wrapper for restTemplate post method.
	 *
	 * @param object  the object
	 * @param url     the url
	 * @param headers
	 * @return the object
	 */
	@Override
	public ResponseEntity<String> post(Object object, String url, MultiValueMap<String, String> headers) {
		HttpEntity<?> httpEntity = new HttpEntity<>(object, headers);
		ResponseEntity<String> responseEntity = null;
		log.info("URL : " + url);
		log.info("Request Headers : " + httpEntity.getHeaders().toString());
		log.info("Request Body : " + httpEntity.getBody().toString());
		try {
			HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
			factory.setReadTimeout(90000);
			factory.setConnectTimeout(90000);
			restTemplate.setRequestFactory(factory);
			restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(Charset.forName(AppConstant.UTF8)));
			responseEntity = restTemplate.exchange(url, HttpMethod.POST, httpEntity, String.class);
			log.info("Response Status : " + responseEntity.getStatusCode());
			log.info("Response Headers : " + responseEntity.getHeaders().toString());
			log.info("Response Body : " + responseEntity.getBody().toString());
		} catch (HttpClientErrorException | HttpServerErrorException e) {
			log.error("     " + e);
			log.error("Error Message : {}", e.getResponseBodyAsString());
			log.debug("         " + e.getStatusCode());
		}
		return responseEntity;
	}

}
