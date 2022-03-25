package com.fyp.webapp;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.fyp.webapp.bean.Role;
import com.fyp.webapp.repository.RoleRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class RoleRepositoryTests {
	@Autowired 
	RoleRepository repo;
	
	@Test
	public void testCreateRoles() {
		Role user = new Role("User"); //this role will be default assigned to each registered user
		Role student = new Role("Student");
		Role lecturer = new Role("Lecturer");
		Role admin = new Role("Admin");
		
		repo.saveAll(List.of(user, student,lecturer,admin));
		
		List<Role> listRoles = repo.findAll();
		
		assertThat(listRoles.size()).isEqualTo(4);
	}	
}
