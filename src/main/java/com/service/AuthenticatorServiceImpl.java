package com.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.UserDaoImpl;
import com.web.controller.LoginRequest;
import com.web.controller.User;

@Service
public class AuthenticatorServiceImpl implements AuthenticatorService {

	@Autowired
	DataAccessServiceImpl dataAccessServiceImpl;
	@Autowired
	ValidationServiceImpl validationServiceImpl;
	
	
	public Boolean userValidation(LoginRequest loginRequest) {
		// check the username
		// convert the password
		LoginRequest parsedRequest = new LoginRequest();

		//Surrounded in trycatch so you cant tell if its valid by causing it to crash.
		try {
			//Checking the username is valid
			//no need to check the pass as we compare against the hash.
			if (!validationServiceImpl.isValidEmail(loginRequest.getEmail())) {
				return false;
			}
			//convert username to lowercase
			parsedRequest.setEmail(loginRequest.getEmail().toLowerCase());
			parsedRequest.setPassword(validationServiceImpl.phraseHasher(loginRequest.getPassword()));

			// If valid username false
			// If valid password false
			if (!checkEmailWithRecords(parsedRequest.getEmail()) && !checkPasswordWithRecords(parsedRequest)) {
				return false;
			}
		} catch (Exception e) {
			// you break the validation will just get returned "false"
			return false;
		}
		return true;
	}

	private Boolean checkEmailWithRecords(String loginRequest) {
		// We return empty objects from the db to avoid nullpointers so this is why we do this.
		if (dataAccessServiceImpl.getByEmail(loginRequest).getEmail().equals("")) {
			return false;
		}
		// exists check
		return true;

	}

	private Boolean checkPasswordWithRecords(LoginRequest loginRequest) {
		// get password if equals password from db, only compair hashes.
		if (dataAccessServiceImpl.getPasswordForEmail(loginRequest.getEmail())
				.equals(loginRequest.getPassword())) {
			return true;
		}
		return false;
	}



}
