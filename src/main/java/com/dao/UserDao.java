package com.dao;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import com.web.controller.User;

public interface UserDao {
	
	User getUserForUserName(String UserName);
	List<User> getAllUsers();
	String getPasswordForUserName(String UserName);
	void updateUser(User user);
	void deleteUser(String user);
	void addUser(User user) throws NoSuchAlgorithmException;
	
}
