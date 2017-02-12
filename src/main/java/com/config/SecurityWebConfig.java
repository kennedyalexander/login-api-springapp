package com.config;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@EnableWebSecurity
public class SecurityWebConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		 http.csrf().disable().
         sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).
         and().authorizeRequests()
         .antMatchers(HttpMethod.POST, "/api/create").permitAll()
         .antMatchers(HttpMethod.POST, "/api/update").permitAll()
         .antMatchers(HttpMethod.POST, "/api/delete").permitAll()
         .antMatchers(HttpMethod.POST, "/api/read").permitAll()
         .antMatchers(HttpMethod.POST, "/api/login").permitAll();

	}
}
	

