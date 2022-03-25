package com.fyp.webapp;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.fyp.webapp.bean.Question;
import com.fyp.webapp.repository.QuestionRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class QuestionRepositoryTest {
	@Autowired 
	QuestionRepository questionRepo;
	/*
	@Test
	public void testFindObjectiveQuestionAmount() {
		Integer objectiveQuestionAmount = questionRepo.findQuestionByObjectiveQuestionType("Objective Question");
		System.out.println( "Amount of Objective Question: " + objectiveQuestionAmount);
		assertThat(objectiveQuestionAmount).isNotNull();
	}
	
	@Test
	public void testFindSubjectiveQuestionAmount() {
		Integer subjectiveQuestionAmount = questionRepo.findQuestionBySubjectiveQuestionType("Subjective Question");
		System.out.println( "Amount of Subjective Question: " + subjectiveQuestionAmount);
		assertThat(subjectiveQuestionAmount).isNotNull();;
	}*/
	
	@Test
	public void testFindQuestionByKeyword() {
		List<Question> questionWithKeyword = questionRepo.findQuestionByKeyword("Subjective Question");
		for(int i=0; i<questionWithKeyword.size() ; i++) {
			System.out.println(questionWithKeyword.get(i).getId() + " --->  "   + questionWithKeyword.get(i).getCreatedTime());	
		}
		assertThat(questionWithKeyword).isNotNull();
	}
	
	
}
