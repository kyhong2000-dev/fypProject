package com.fyp.webapp;

import static org.assertj.core.api.Assertions.assertThat;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.fyp.webapp.bean.Objectivequestion;
import com.fyp.webapp.bean.Question;
import com.fyp.webapp.repository.ObjectiveQuestionRepository;
import com.fyp.webapp.repository.QuestionRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class ObjectiveQuestionRepositoryTest {
	@Autowired
	ObjectiveQuestionRepository objQuestionRepo;
	
	@Autowired
	QuestionRepository questionRepo;
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Test
	public void testCreateObjectiveQuestion() {
		Question q = new Question();
		
		//Below code used to get the current date time when user create the new question 
		Date date = new Date();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		df.setTimeZone(TimeZone.getTimeZone("Asia/Kuala_Lumpur")); // Use KL's time zone to format the date
		
		/*
		q.setCreatedBy("Adun");
		q.setCreatedTime(df.format(date));
		q.setQuestionType("Objective Question");
		questionRepo.save(q);
		
		Objectivequestion ob = new Objectivequestion();
		
		ob.setQuestionContent("What is your nickname");
		ob.setChoiceAContent("A");
		ob.setChoiceBContent("B");
		ob.setChoiceCContent("C");
		ob.setChoiceDContent("D");
		ob.setCorrectAnswer("D");
		ob.setQuestion(q);*/
		
		Objectivequestion ob = new Objectivequestion();
		
		ob.setQuestionContent("What is your nickname");
		ob.setChoiceAContent("A");
		ob.setChoiceBContent("B");
		ob.setChoiceCContent("C");
		ob.setChoiceDContent("D");
		ob.setCorrectAnswer("D");
		
		q.setCreatedBy("Adun");
		q.setCreatedTime(df.format(date));
		q.setQuestionType("Objective Question");
		q.setObjQuestion(ob);
		questionRepo.save(q);
	
		Objectivequestion savedObjQ = objQuestionRepo.save(ob); //save the user into database
		Objectivequestion existObjQ = entityManager.find(Objectivequestion.class, savedObjQ.getId());		
		
		assertThat(existObjQ.getQuestionContent()).isEqualTo(ob.getQuestionContent());
	}
}



	