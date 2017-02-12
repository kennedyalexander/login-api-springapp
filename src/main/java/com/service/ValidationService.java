package com.service;

import java.security.NoSuchAlgorithmException;

public interface ValidationService {
	
	Boolean isValidUsername(String username);
	Boolean isValidEmail(String email);
	Boolean isValidPassword(String password);
	Boolean isValidActive(Integer active);
	String phraseHasher(String phrase) throws NoSuchAlgorithmException;
}
