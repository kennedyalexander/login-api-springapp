package com.service;

import java.security.NoSuchAlgorithmException;
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
		}
	 	userDaoImpl.addUser(user);
	}

	public User getByEmail(String email) {
		return 	userDaoImpl.getUserForEmail(email);
	}

	public User getById(String id) {
		return 	userDaoImpl.getUserForEmail(id);
	}

	public String updateUser(User user) {
		User oldUser = userDaoImpl.getUserForEmail(user.getEmail());
		User updatingUser = oldUser; //make updating equal to old
		if(oldUser.getEmail().equals("")){ //if not exists then create
			userDaoImpl.addUser(user);		
			return "Added as new User";
		} else{
			if (isDifferent(oldUser.getUsername(), user.getUsername())){
				if(validationServiceImpl.isValidUsername(user.getUsername())){
				updatingUser.setUsername(user.getUsername());
				} else{
					return "Invalid username";
				}
			}
			String hashedPassword = "";
			try {
				hashedPassword = validationServiceImpl.phraseHasher(user.getPassword());
			} catch (NoSuchAlgorithmException e) {
			}
			//Check old password vs new password hashed
			if (isDifferent(oldUser.getPassword(), hashedPassword)){
				if(validationServiceImpl.isValidPassword(user.getPassword())){
				updatingUser.setPassword(user.getPassword());
				} else{
					return "Invalid Password";
				}
			}
			if (isDifferent(oldUser.getActive(), user.getActive())){
				if(validationServiceImpl.isValidActive(user.getActive())){
				updatingUser.setActive(user.getActive());
			} else{
				return "Invalid Active";
			}
			}
			userDaoImpl.updateUser(updatingUser);
			return "Updated User";
		}
		

	}

	public void deleteUser(String user) {
		userDaoImpl.deleteUser(user);
	}

	public List<User> getAllUsers() {
		return userDaoImpl.getAllUsers();
	}

	public String getPasswordForEmail(String email) {
		return userDaoImpl.getPasswordForEmail(email);
	}
	
	private Boolean isDifferent(Object string1, Object string2){
		return !string1.equals(string2);
	}

}
