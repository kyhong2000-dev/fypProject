package com.fyp.webapp.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private DataSource dataSource;
	
	@Autowired 
	private LoginSuccessHandler loginSuccessHandler;

	@Bean
	public UserDetailsService userDetailsService() {
		return new CustomUserDetailsService();
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());

		return authProvider;
	}

	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}

	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
			.antMatchers("/admin_Home",  //These four pages will only can be accessed by the authenticated user
					"/admin_ManageUser",
					"/admin_Report",
					"/admin_Manage_Schedule",
					"/admin_EnrollStudent",
					"/lecturer_Home",
					"/lecturer_QuestionBank",
					"/lecturer_ExamGrading"
					).authenticated() //only authorize the used access this page when that user is authenticated successfully
			.anyRequest().permitAll()
			.and()
			.formLogin()
				.loginPage("/login")  //custom login page instead of the built-in default login page
				.usernameParameter("email") //map the user name parameter with the email value 
				.successHandler(loginSuccessHandler) //custom success handler to redirect the user based on the user role 
				.permitAll()
		.and()
		.logout().logoutSuccessUrl("/login?logout").permitAll();		
	}
	
	
}
