package com.fyp.webapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.fyp.webapp.bean.Role;
import com.fyp.webapp.bean.User;
import com.fyp.webapp.repository.RoleRepository;
import com.fyp.webapp.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private RoleRepository roleRepo;
	
	//assign default role when register the user 
	public void saveUserwithDefaultRole(User user) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); //spring built-in api to hash the password
	    String encodedPassword = passwordEncoder.encode(user.getPassword()); //retrieve the user password and then encrypt it
	    user.setPassword(encodedPassword); //set the new-updated encrypted password
	    
	    Role roleUser = roleRepo.findByName("User"); //retrieve the <<User>> role from repo
	    Role roleAdmin = roleRepo.findByName("Admin");
	    Role roleLecturer = roleRepo.findByName("Lecturer");
	    Role roleStudent = roleRepo.findByName("Student");
	    user.addRole(roleUser); //assign default 'User' role to every new registered user 
	    
	    //if-logic to verify that what role choose in the sign-up form
	    if(user.getRole().equals("Admin")){
	    	user.addRole(roleAdmin);
	    }else if(user.getRole().equals("Lecturer")) {
	    	user.addRole(roleLecturer);
	    }else if(user.getRole().equals("Student")) {
	    	user.addRole(roleStudent);
	    }
	    //assign the chosen role to the registered user
	   
		userRepo.save(user); //use repo save it to update the data in the db
	}
	
	public List<User> listAll(){
		return userRepo.findAll();
	}
	
	public User findUserByEmailPrincipal(String email) {
		return userRepo.findByEmail(email);
	}
	
	public Integer calcTotalUser() {
		return userRepo.findTotalUser();
	}
	
	public Integer calcTotalAdminUser() {
		return userRepo.findUserByAdminRole("Admin");
	}
	
	public Integer calcTotalLecturerUser() {
		return userRepo.findUserByLecturerRole("Lecturer");
	}
	
	public Integer calcTotalStudentUser() {
		return userRepo.findUserByStudentRole("Student");
	}
	
	public List<User> findByKeyword(String keyword){
		return userRepo.findUserByKeyword(keyword);
	}
	
	public Optional<User> getOne(Long id){
		return userRepo.findById(id);
	}
	
	public List<User> findByStudentRole(String role){
		return userRepo.findStudentUserByRole(role);
	}
	
	public void update(User user) {
		userRepo.save(user);
	}
}
