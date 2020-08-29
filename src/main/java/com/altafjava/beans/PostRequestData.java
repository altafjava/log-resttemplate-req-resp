package com.altafjava.beans;

import lombok.Data;

/**
 * @author Altaf
 *
 */
@Data
public class PostRequestData {

	private int userId;
	private String title;
	private String body;
}
