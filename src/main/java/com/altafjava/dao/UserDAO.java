package com.altafjava.dao;

import org.springframework.http.ResponseEntity;
import com.altafjava.beans.PostRequestData;

/**
 * @author altaf
 *
 */
public interface UserDAO {

	/**
	 * Used to fetch Users
	 * 
	 * @param url
	 * @return {@link ResponseEntity} String response of Users
	 */
	public ResponseEntity<String> fetchUsers(String url);

	/**
	 * Used to create a Post
	 * 
	 * @param body
	 * @param url
	 * @return {@link ResponseEntity}
	 */
	public ResponseEntity<String> createPost(PostRequestData body, String url);

}
