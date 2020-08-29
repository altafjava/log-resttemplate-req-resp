package com.altafjava.beans;

import lombok.Data;

/**
 * @author Altaf
 *
 */
@Data
public class Post {

	private int id;
	private int userId;
	private String title;
	private String body;
}
