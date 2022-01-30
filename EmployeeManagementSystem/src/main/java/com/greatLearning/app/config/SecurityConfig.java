package com.greatLearning.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.greatLearning.app.service.impl.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	UserDetailsServiceImpl userDetailsServiceImpl;

	@Bean
	public UserDetailsService userDetailsService() {
		return userDetailsServiceImpl;
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsServiceImpl).passwordEncoder(passwordEncoder());
	}

//
	/* to ignore security layer for mentioned parts */
	@Override
	public void configure(WebSecurity web) throws Exception {

		web.ignoring().antMatchers("/h2-console/**");
	}

	/*
	 * Only ADMIN can add Employees
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers(HttpMethod.POST, "/api/user", "/api/role").hasAuthority("ADMIN")
		.antMatchers(HttpMethod.GET, "/api/listHeaders").hasAnyAuthority("ADMIN")
		.antMatchers(HttpMethod.GET, "/api/employees/getEmployeeById").hasAnyAuthority("USER", "ADMIN")
		.antMatchers(HttpMethod.GET, "/api/employees/sort").hasAnyAuthority("USER", "ADMIN")
		.antMatchers(HttpMethod.POST, "/api/employees").hasAuthority("ADMIN")
		.antMatchers(HttpMethod.PUT, "/api/employees").hasAuthority("ADMIN")
		.antMatchers(HttpMethod.DELETE, "/api/employees/*").hasAuthority("ADMIN")
		.antMatchers(HttpMethod.GET, "/api/employees/search").hasAnyAuthority("ADMIN", "USER")
		.anyRequest().authenticated().and().httpBasic()
		.and().cors().and().csrf().disable();
	}

}
