package com.dao;

import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.service.ValidationServiceImpl;
import com.web.controller.User;
import com.dao.UserDao;

@Transactional
@Repository
public class UserDaoImpl implements UserDao {
	@Autowired
	private JdbcTemplate h2JdbcTemplate;
	@Autowired ValidationServiceImpl validationServiceImpl;

	/**
	 * Everytime you pass a username it get converted to lowercase.
	 */
	public User getUserForUserName(String username) {
		username = username.toLowerCase();
		String statement = String.format("SELECT * FROM USERS WHERE USER_USERNAME = '%s'", username);
		List<User> users = h2JdbcTemplate.query(statement, new RowMapper<User>() {
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User user = new User();
				user.setUsername(rs.getString("USER_USERNAME"));
				user.setEmail(rs.getString("USER_EMAIL"));
				user.setPassword(rs.getString("USER_PASSWORD"));
				user.setActive(rs.getInt("USER_ACTIVE"));
				return user;
			}
		});
		User returnedUser = new User();
		if (users.isEmpty()){
			return returnedUser;
		}
		return users.get(0);
	}

	// USERS
	// (USER_USERNAME VARCHAR(25) NOT NULL,
	// USER_EMAIL VARCHAR(50),
	// USER_PASSWORD VARCHAR(50) NOT NULL,
	// USER_ACTIVE INT NOT NULL,
	// INSERT INTO USERS VALUES ('oranges', 'orange@oranges.com', 'asrsdsd',
	// 1);
	public List<User> getAllUsers() {
		List<User> users = h2JdbcTemplate.query("select * from USERS", new RowMapper<User>() {
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User user = new User();
				user.setUsername(rs.getString("USER_USERNAME"));
				user.setEmail(rs.getString("USER_EMAIL"));
				user.setPassword(rs.getString("USER_PASSWORD"));
				user.setActive(rs.getInt("USER_ACTIVE"));
				return user;
			}
		});

		return users;
	}
	
	public String getPasswordForUserName(String username) {
		username = username.toLowerCase();
		String statement = String.format("SELECT USER_PASSWORD FROM USERS WHERE USER_USERNAME = '%s'", username);
		List<User> users = h2JdbcTemplate.query(statement, new RowMapper<User>() {
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User user = new User();
				user.setPassword(rs.getString("USER_PASSWORD"));
				return user;
			}
		});
		return users.get(0).getPassword();
	}

	public void updateUser(User user) {
			addUser(user);
	}

	public void deleteUser(String username) {
		username = username.toLowerCase();
		String statement = String.format("DELETE FROM USERS WHERE USER_USERNAME = '%s'", username);
		h2JdbcTemplate.update(statement);
	}

	public void addUser(User user) {
		if (getUserForUserName(user.getUsername()).getUsername().equals("")){
		try {
			String statement = "";
			statement = String.format("INSERT INTO USERS VALUES ('%s', '%s', '%s', %d)", user.getUsername().toLowerCase(),
					user.getEmail(), validationServiceImpl.phraseHasher(user.getPassword()), user.getActive());
			h2JdbcTemplate.update(statement);
		} catch (NoSuchAlgorithmException e) {
			//throw it down here so we can see it
			e.printStackTrace();	
		}
		}
	}
}
