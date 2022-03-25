package com.fyp.webapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fyp.webapp.bean.Subject;
import com.fyp.webapp.repository.SubjectRepository;

@Service
public class SubjectService {
	
	@Autowired
	private SubjectRepository subjectRepo;
	
	public void saveSubject(Subject subject) {
		subjectRepo.save(subject);
	}
	
	public List<Subject> listAllSubject(){
		return subjectRepo.findAll();
	}
}
