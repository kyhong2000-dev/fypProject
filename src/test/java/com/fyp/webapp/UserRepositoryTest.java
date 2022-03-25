package com.fyp.webapp;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.fyp.webapp.bean.User;
import com.fyp.webapp.repository.RoleRepository;
import com.fyp.webapp.repository.UserRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTest {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private RoleRepository roleRepo;
	
	@Autowired
	private TestEntityManager entityManager;
	/*
	@Test
	public void testCreateUser() {
		User user = new User();
		user.setEmail("kyhong2@gmail.com");
		user.setPassword("yh");
		user.setUsername("yong hong");
		user.setGender("female");
		user.setRole("Student");
		user.setEduInst("Taylor College");
		
		User savedUser = userRepo.save(user);
		User existUser = entityManager.find(User.class, savedUser.getId());
		
		assertThat(existUser.getEmail()).isEqualTo(user.getEmail());
	}
	
	/*
	@Test
	public void testFindUserByEmail() {
		String email ="kyhong2000@gmail.com";
		User user = userRepo.findByEmail(email);
		assertThat(user).isNotNull();
	}*/
	/*
	@Test
	public void testAddRoleToNewUser() {
		User user = new User();
		user.setEmail("tester3@gmail.com");
		user.setPassword("test3");
		user.setUsername("test3");
		user.setGender("Female");
		user.setEduInst("Taylor");
		
		Role roleUser = roleRepo.findByName("Student");
		user.setRole(roleUser.getName());
		user.addRole(roleUser);
		
		User savedUser = userRepo.save(user);
		
		assertThat(savedUser.getRoles().size()).isEqualTo(1);
	}
	
	//compute the total user
	@Test
	public void testFindTotalAmountUser() {
		Integer user = userRepo.findTotalUser();
		System.out.println("Total amount of user: " + user);
	}
	
	//compute the total student user	
	@Test
	public void testFindStudentRoleUser() {
		Integer user = userRepo.findUserByStudentRole("Student");
		System.out.println("Amount of student user: " + user);
		assertThat(user).isNotNull();
	}
	
	@Test
	public void testFindAdminRoleUser() {
		Integer user = userRepo.findUserByAdminRole("Admin");
		System.out.println("Amount of admin user: " + user);
		assertThat(user).isNotNull();
	}
	
	@Test
	public void testFindLecturerRoleUser() {
		Integer user = userRepo.findUserByLecturerRole("Lecturer");
		System.out.println( "Amount of lecturer user: " + user);
		assertThat(user).isNotNull();
	}
	
	
	@Test
	public void testFindUserByKeyword() {
		String keyword = "amin";
		List<User> usrList = userRepo.findUserByKeyword(keyword);
		assertThat(usrList).isNotEmpty();
	}*/
	
	@Test
	public void testFindStudentUserByRole() {
		//String studentRole = "Lecturer";
		String role = "Lecturer";
		List<User> userList = userRepo.findStudentUserByRole(role);
		for( int i = 0; i<userList.size();i++) {
			System.out.println(userList.get(i).getEmail() + " ---> " + userList.get(i).getUsername() +   " ---> " +userList.get(i).getRole());
		}
		//System.out.println(user.getEmail() + " ---> " + user.getUsername());
		assertThat(userList).isNotNull();
	}
}
