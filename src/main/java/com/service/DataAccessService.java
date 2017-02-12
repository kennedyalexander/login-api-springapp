package com.service;

import java.util.List;

import com.web.controller.User;

public interface DataAccessService {

	void createUser(User user);
	User getByName(String username);
	User getById(String id);
	String updateUser(User user);
	void deleteUser(String username);
	List<User> getAllUsers();
	String getPasswordForUsername(String username);
}
