package com.fyp.webapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fyp.webapp.bean.Objectivequestion;
import com.fyp.webapp.bean.Subjectivequestion;
import com.fyp.webapp.repository.SubjectiveQuestionRepository;

@Service
public class SubjectiveQuestionService {
 
	@Autowired
	SubjectiveQuestionRepository subjectiveQuestionRepo;
	
	public void saveNewCreatedSbjQuestion(Subjectivequestion sbjQuestion) {
		subjectiveQuestionRepo.save(sbjQuestion);
	}
	
	public List<Subjectivequestion> listAll(){
		return subjectiveQuestionRepo.findAll();
	}
}
