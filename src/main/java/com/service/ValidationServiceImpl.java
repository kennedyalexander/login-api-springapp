package com.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

@Service
/**
 * validates all of the given inputs and retuns if they are valid in true or false.
 * @author alexander
 *
 */
public class ValidationServiceImpl implements ValidationService {
	
	  private static final String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})";
	  private static final String EMAIL_PATTERN = "^(.+)@(.+)$";
	  private static String PASSWORD_SALT = "Salting"; //inventive Hash, Would prefer to us something a bit more secret and complex but this will work

	/**
	 * length is under 50 and over 5
	 * Is Alphanumeric, Capitals don matter as we strip them later.
	 */
	public Boolean isValidUsername(String username) {
		if (!isOfCorrectLength(username)|| !(username.length() >=5)) {
			return false;
		}
		if (!StringUtils.isAlphanumeric(username)) {
			return false;
		}
		return true;
	}

	/**
	 * Could extract and use enums
	 */
	public Boolean isValidEmail(String email) {
		  Pattern pattern;
		  Matcher matcher;
		  pattern = Pattern.compile(EMAIL_PATTERN);
		  matcher = pattern.matcher(email);
		  return matcher.matches();
	}

	/**
	 * Check if password is under 50 and over 10
	 * Check password meets complexity rules.
	 */
	public Boolean isValidPassword(String password) {
		if(!isOfCorrectLength(password) || !(password.length() >=10)){
			return false;
		}
		  Pattern pattern;
		  Matcher matcher;
		  pattern = Pattern.compile(PASSWORD_PATTERN);
		  matcher = pattern.matcher(password);
		  return matcher.matches();
	}

	/**
	 * Active is allowed to be 0,1,2.
	 * I like to have other options for active just in case we have a differnt status.
	 */
	public Boolean isValidActive(Integer active) {
		if(active >= 0 && active <= 2){
			return true;
		}
		return false;
	}

	/**
	 * checks if the supplied string is not over the max length.
	 */
	private Boolean isOfCorrectLength(String string) {
		if (string.length() > 50) {
			return false;
		}
		return true;
	}
	
	public String phraseHasher(String phrase) throws NoSuchAlgorithmException {
		phrase += PASSWORD_SALT; 
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(phrase.getBytes());

		byte byteData[] = md.digest();

		// convert the byte to hex format method 1
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < byteData.length; i++) {
			sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 50).substring(1));
		}

		return sb.toString();
	}

}
