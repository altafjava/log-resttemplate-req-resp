package com.altafjava.service;

/**
 * @author altaf
 *
 */
public interface UserService {

	/**
	 * Used to fetch the Users
	 * 
	 * @param url String
	 * 
	 * @return {@link List} List of User
	 */
	public void fetchUsers(String url);

	/**
	 * Used to create a post
	 * 
	 * @param url
	 * 
	 */
	public void createPost(String url);
}
