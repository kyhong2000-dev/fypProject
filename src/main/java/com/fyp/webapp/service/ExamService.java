package com.fyp.webapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fyp.webapp.bean.Exam;
import com.fyp.webapp.bean.User;
import com.fyp.webapp.repository.ExamRepository;
import com.fyp.webapp.repository.UserRepository;

@Service
public class ExamService {
	@Autowired
	private ExamRepository examRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	public void enrollStudentIntoExam(long userId,long examId) {
		User usr = userRepo.findUserById(userId);
		Exam exam = examRepo.getById(examId);
		exam.enrollStudent(usr); 
		
		examRepo.save(exam); //use repository save it to update the data in the db
	}
	
	public List<Exam> listAllCreateExam(){
		return examRepo.findAll();
	}
	
	public void saveExamSchedule(Exam exam) {
		examRepo.save(exam);
	}
	
	public List<Exam> findByKeyword(String keyword){
		return examRepo.findExamByKeyword(keyword);
	}
	
	public void update(Exam exam) {
		examRepo.save(exam);
	}
	
	public Optional<Exam> getOneExam(Long id){
		return examRepo.findById(id);
	}

	public void deleteSubjectById(Long id){
		examRepo.deleteById(id);
	}
}
