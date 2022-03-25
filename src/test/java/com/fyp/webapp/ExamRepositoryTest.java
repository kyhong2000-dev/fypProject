package com.fyp.webapp;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.fyp.webapp.bean.Exam;
import com.fyp.webapp.bean.User;
import com.fyp.webapp.repository.ExamRepository;
import com.fyp.webapp.repository.UserRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class ExamRepositoryTest {
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	ExamRepository examRepo;
	
	@Autowired
	private TestEntityManager entityManager;
	/*
	@Test
	public void testCreateExam() {
		Exam exam = new Exam();
		exam.setSubject("EPDA");
		exam.setDate(LocalDate.of(2022, 2, 23));
		exam.setDuration(2);
		exam.setStartTime(LocalTime.of(9, 30));
		exam.setEndTime(LocalTime.of(11, 30));
		exam.setQuestionType("Subjective");
		exam.setScore(100);
		//exam.setStatus("Incompleted");
		
		Exam savedExam = examRepo.save(exam); //save the user into database
		Exam existExam = entityManager.find(Exam.class, savedExam.getId()); //retrieve the record that just inserted into the database
		
		assertThat(existExam.getSubject()).isEqualTo(exam.getSubject()); //verify whether the exist record is matched with the subject of Exam object
	}*/
	
	@Test
	public void testEnrollStudent() {
		Exam exam = examRepo.getById((long)4);
		User user = userRepo.findUserById((long) 69);
		exam.enrollStudent(user);
		
		examRepo.save(exam); //save the user into database
		
		assertThat(exam.getEnrolledStudent().size()>0);
	}
}
