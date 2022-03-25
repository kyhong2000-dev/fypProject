package com.fyp.webapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fyp.webapp.bean.Exam;
import com.fyp.webapp.bean.Question;
import com.fyp.webapp.repository.QuestionRepository;

@Service
public class QuestionService {
	
	@Autowired
	private QuestionRepository questionRepo;
	
	public void saveQuestion(Question question) {
		questionRepo.save(question);
	}
	
	public Integer findTotalObjectiveQuestion() {
		return questionRepo.findQuestionByObjectiveQuestionType("Objective Question");
	}
	
	public Integer findTotalSubjectiveQuestion() {
		return questionRepo.findQuestionBySubjectiveQuestionType("Subjective Question");
	}
	
	public List<Question> listAll(){
		return questionRepo.findAll();
	}
	
	public List<Question> findByKeyword(String keyword){
		return questionRepo.findQuestionByKeyword(keyword);
	}
	
	
	public Optional<Question> getOneQuestion(Long id){
		return questionRepo.findById(id);
	}

}
