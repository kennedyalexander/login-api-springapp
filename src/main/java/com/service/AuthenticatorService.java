package com.service;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import com.web.controller.LoginRequest;
import com.web.controller.User;

public interface AuthenticatorService {
	

	Boolean userValidation(LoginRequest loginRequest) throws NoSuchAlgorithmException;

	
}
