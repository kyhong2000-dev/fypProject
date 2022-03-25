package com.fyp.webapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fyp.webapp.bean.Subject;

public interface SubjectRepository extends JpaRepository<Subject,Long> {
	
}
