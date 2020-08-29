package com.altafjava.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import com.altafjava.beans.PostRequestData;
import com.altafjava.constant.AppConstant;
import com.altafjava.dao.UserDAO;
import com.altafjava.service.HttpService;

/**
 * @author altaf
 *
 */
@Repository
public class UserDAOImpl implements UserDAO {

	@Autowired
	private HttpService httpService;

	/**
	 * Used to fetch users
	 * 
	 * @param url
	 * 
	 * @return {@link ResponseEntity} String response of users
	 */
	@Override
	public ResponseEntity<String> fetchUsers(String url) {
		HttpHeaders headers = new HttpHeaders();
		return httpService.get(url, headers);
	}

	@Override
	public ResponseEntity<String> createPost(PostRequestData body, String url) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("charset", AppConstant.UTF8);
		return httpService.post(body, url, headers);
	}

}
