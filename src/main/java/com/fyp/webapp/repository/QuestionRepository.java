package com.fyp.webapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fyp.webapp.bean.Exam;
import com.fyp.webapp.bean.Question;

public interface QuestionRepository  extends JpaRepository<Question,Long> {
	@Query("SELECT count(q) FROM Question q WHERE q.questionType = 'Objective Question'")
	Integer findQuestionByObjectiveQuestionType(String questionType);
	
	@Query("SELECT count(q) FROM Question q WHERE q.questionType = 'Subjective Question'")
	Integer findQuestionBySubjectiveQuestionType(String questionType); 
	
	@Query("SELECT q FROM Question q WHERE q.questionType like %?1%" )
	List<Question> findQuestionByKeyword(@Param("keyword")String keyword);
}
