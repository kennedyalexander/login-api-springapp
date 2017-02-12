package com.web.controller;

import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.dao.UserDaoImpl;
import com.service.AuthenticatorServiceImpl;
import com.service.DataAccessServiceImpl;



@RestController
@RequestMapping("/api/")
public class ProjectController {	

	@Autowired
	AuthenticatorServiceImpl authenticatorServiceImpl;
	@Autowired
	DataAccessServiceImpl dataAccessServiceImpl;

	
	//in(@RequestBody LoginRequest loginRequest ) {
	//public String checkLogin(@RequestParam("username") String username, @RequestParam("email") String email) {

	@RequestMapping(value = "/login", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody Boolean checkLogin(@RequestBody final LoginRequest loginRequest) {
		return authenticatorServiceImpl.userValidation(loginRequest);
	}
	/**
	 * creates user
	 * all fields need to be present to make it work.
	 */
	//  @RequestMapping(method = RequestMethod.POST, value = "personDetails.html")

	@RequestMapping(value = "/create", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody void create(@RequestBody final User user) {
		//User user = new User(username, email, password, active);
		//dataAccessServiceImpl.createUser(user);
		dataAccessServiceImpl.createUser(user);
		//username=oranges&email='email@email.com'&password=password&active=1
		
	}
	/**
	 * if valid user and password reads user.
	 */
	@RequestMapping(value = "/read", method = RequestMethod.POST,  consumes = "application/json")
	public @ResponseBody User read(@RequestBody final LoginRequest loginRequest){
		User user = new User();
		Boolean valid = authenticatorServiceImpl.userValidation(loginRequest);
		if (valid){ 
			return dataAccessServiceImpl.getByName(loginRequest.username);
		}
		//Returns json Object
		return user;
	}
	/**
	 * Updates username with incoming variables.
	 * If new user creates new user.
	 * @param username
	 * @param email
	 * @param password
	 * @param active
	 * @return
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST,  consumes = "application/json")
	public @ResponseBody String update(@RequestBody final User user) {
		return dataAccessServiceImpl.updateUser(user);
	}
	/**
	 * If real username and password deletes it
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody String delete(@RequestBody final LoginRequest loginRequest){
		//validate
		//delete
		Boolean valid = authenticatorServiceImpl.userValidation(loginRequest);
		if (valid){ 
			dataAccessServiceImpl.deleteUser(loginRequest.username);
			return "Deleted user";
		}
		return "Not Deleted user";
	}
}


