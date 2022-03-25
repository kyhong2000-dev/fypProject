package com.fyp.webapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fyp.webapp.bean.Objectivequestion;
import com.fyp.webapp.bean.User;
import com.fyp.webapp.repository.ObjectiveQuestionRepository;

@Service
public class ObjectiveQuestionService {
	@Autowired
	ObjectiveQuestionRepository objectiveQuestionRepo;
	
	public void saveNewCreatedObjQuestion(Objectivequestion objQuestion) {
		objectiveQuestionRepo.save(objQuestion);
	}
	
	public List<Objectivequestion> listAll(){
		return objectiveQuestionRepo.findAll();
	}
}
