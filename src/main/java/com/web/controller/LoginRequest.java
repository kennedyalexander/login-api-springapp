package com.web.controller;

import java.io.Serializable;

public class LoginRequest implements Serializable {
	
	String email;
	String password;
	
	public LoginRequest(){
	}
	
	public LoginRequest(String email,  String password){
		this.email = email;
		this.password = password;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	

}
