package com.web.controller;

import java.io.Serializable;

public class User implements Serializable {
	private String username;
	private String email;
	private String password;
	private Integer active;
	
	public User() {
		this.username = "";
		this.email = "";
		this.password = "";
		this.active = 0;
	}
	
	public User(String username, String email, String password, int active) {
		this.username = username;
		this.email = email;
		this.password = password;
		this.active = active;
	}
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
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
	 * @return the active
	 */
	public Integer getActive() {
		return active;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
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
	/**
	 * @param active the active to set
	 */
	public void setActive(Integer active) {
		this.active = active;
	}

}
