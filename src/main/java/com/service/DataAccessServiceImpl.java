package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.UserDaoImpl;
import com.web.controller.User;

@Service
public class DataAccessServiceImpl implements DataAccessService {

@Autowired UserDaoImpl userDaoImpl;
@Autowired ValidationServiceImpl validationServiceImpl;
	

	public void createUser(User user) {
		if(		validationServiceImpl.isValidUsername(user.getUsername()) &&
				validationServiceImpl.isValidEmail(user.getEmail()) &&
				validationServiceImpl.isValidPassword(user.getPassword()) &&
				validationServiceImpl.isValidActive(user.getActive())){
			User newUser = user;
		}
	 	userDaoImpl.addUser(user);
	}

	public User getByName(String username) {
		return 	userDaoImpl.getUserForUserName(username);
	}

	public User getById(String id) {
		return 	userDaoImpl.getUserForUserName(id);
	}

	public String updateUser(User user) {
		User oldUser = userDaoImpl.getUserForUserName(user.getUsername());
		User updatingUser = oldUser; //make updating equal to old
		if(oldUser.getUsername().equals("")){ //if not exists then create
			userDaoImpl.addUser(user);		
			return "added new User";
		} else{
			if (isDifferent(oldUser.getEmail(), user.getEmail())){
				if(validationServiceImpl.isValidEmail(user.getEmail())){
				updatingUser.setEmail(user.getEmail());
				} else{
					return "invalid Email";
				}
			}
			if (isDifferent(oldUser.getPassword(), user.getPassword())){
				if(validationServiceImpl.isValidPassword(user.getPassword())){
				updatingUser.setPassword(user.getPassword());
				} else{
					return "invalid Password";
				}
			}
			if (isDifferent(oldUser.getActive(), user.getActive())){
				if(validationServiceImpl.isValidActive(user.getActive())){
				updatingUser.setActive(user.getActive());
			} else{
				return "invalid Active";
			}
			}
			userDaoImpl.updateUser(updatingUser);
			return "updated User";
		}
		

	}

	public void deleteUser(String user) {
		userDaoImpl.deleteUser(user);
	}

	public List<User> getAllUsers() {
		return userDaoImpl.getAllUsers();
	}

	public String getPasswordForUsername(String username) {
		return userDaoImpl.getPasswordForUserName(username);
	}
	
	private Boolean isDifferent(Object string1, Object string2){
		return !string1.equals(string2);
	}

}
