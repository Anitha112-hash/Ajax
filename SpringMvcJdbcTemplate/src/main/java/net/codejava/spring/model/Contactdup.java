package net.codejava.spring.model;

import com.fasterxml.jackson.annotation.JsonView;

public class Contactdup {

	@JsonView(Views.Public.class)
    String fname;

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}
	
	
}
