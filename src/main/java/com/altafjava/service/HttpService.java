package com.altafjava.service;

import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

/**
 * Provides Common Http Services functionality.
 *
 * @author altaf
 */
public interface HttpService {

	/**
	 * Provides a wrapper for restTemplate get method.
	 *
	 * @param url     the url
	 * @param headers request headers
	 * @return the object
	 */
	ResponseEntity<String> get(String url, MultiValueMap<String, String> headers);

	/**
	 * Provides a wrapper for restTemplate post method.
	 *
	 * @param object  the object
	 * @param url     the url
	 * @param headers request headers
	 * @return the object
	 */
	ResponseEntity<String> post(Object object, String url, MultiValueMap<String, String> headers);

}
