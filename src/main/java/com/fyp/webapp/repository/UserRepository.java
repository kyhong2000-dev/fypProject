package com.fyp.webapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fyp.webapp.bean.User;

public interface UserRepository extends JpaRepository<User,Long>{
	@Query("SELECT u FROM User u WHERE u.email = ?1")
	User findByEmail(String email);
	
	@Query("SELECT u FROM User u WHERE u.id = ?1")
	User findUserById(Long id);
	
	@Query("SELECT count(u) FROM User u WHERE u.role = 'Student'")
	Integer findUserByStudentRole(String role);
	
	@Query("SELECT count(u) FROM User u WHERE u.role = 'Lecturer'")
	Integer findUserByLecturerRole(String role);
	
	@Query("SELECT count(u) FROM User u WHERE u.role = 'Admin'")
	Integer findUserByAdminRole(String role);
	
	@Query("SELECT count(u) FROM User u")
	Integer findTotalUser();
	
	@Query("SELECT u FROM User u WHERE u.email like %?1% or u.role like %?1%" )
	List<User> findUserByKeyword(@Param("keyword")String keyword);
	
	List<User> findStudentUserByRole(String role);
}
