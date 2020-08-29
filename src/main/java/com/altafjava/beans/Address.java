package com.altafjava.beans;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Address {
	
	private String street;
	private String suite;
	private String city;
	private String zipcode;
	Geo geo;
}
