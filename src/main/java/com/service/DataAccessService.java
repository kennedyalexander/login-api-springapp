package com.service;

import java.util.List;

import com.web.controller.User;

public interface DataAccessService {

	void createUser(User user);
	User getByEmail(String email);
	User getById(String id);
	String updateUser(User user);
	void deleteUser(String email);
	List<User> getAllUsers();
	String getPasswordForEmail(String email);
}
