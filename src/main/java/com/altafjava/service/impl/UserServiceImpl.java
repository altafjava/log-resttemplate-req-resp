package com.altafjava.service.impl;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.altafjava.beans.Post;
import com.altafjava.beans.PostRequestData;
import com.altafjava.beans.User;
import com.altafjava.constant.AppConstant;
import com.altafjava.dao.UserDAO;
import com.altafjava.service.UserService;
import com.altafjava.util.CommonUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;

/**
 * @author Altaf
 *
 */
@Log4j2
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDAO;
	@Autowired
	private ObjectMapper objectMapper;

	@Override
	@SuppressWarnings("unchecked")
	public void fetchUsers(String url) {
		ResponseEntity<String> responseEntity = userDAO.fetchUsers(url);
		String body = responseEntity.getBody();
		List<User> users = Collections.emptyList();
		try {
			users = objectMapper.readValue(body, List.class);
			log.info("Response body converted to List<User>");
			String outputFileName = AppConstant.OUTPUT_DIR + AppConstant.USERS_FILE_NAME + AppConstant.JSON_FILE_EXT;
			CommonUtil.writeStringDataToFile(outputFileName, users);
			log.info(outputFileName + " successfully saved");
		} catch (IOException e) {
			log.error("Error occurred while parsing responseEntity to List<User> class : " + e);
		}
	}

	@Override
	public void createPost(String url) {
		String fileLocation = "files/post-request-data.json";
		try {
			String postRequestDataString = CommonUtil.loadPostRequestData(fileLocation);
			PostRequestData postRequestData = objectMapper.readValue(postRequestDataString, PostRequestData.class);
			log.info("postRequestDataString converted to PostRequestData object");
			ResponseEntity<String> responseEntity = userDAO.createPost(postRequestData, url);
			log.info("Post created " + responseEntity.getStatusCode());
			String body = responseEntity.getBody();
			Post post = objectMapper.readValue(body, Post.class);
			log.info("Response body converted into Post object");
			String outputFileName = AppConstant.OUTPUT_DIR + AppConstant.POST_FILE_NAME + AppConstant.JSON_FILE_EXT;
			CommonUtil.writeStringDataToFile(outputFileName, post);
			log.info(outputFileName + " successfully saved");
		} catch (JsonProcessingException e) {
			log.error("Error while converting String to PostRequestData " + e);
		}
	}

}
