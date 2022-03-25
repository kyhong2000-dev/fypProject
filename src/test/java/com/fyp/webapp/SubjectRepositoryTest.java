package com.fyp.webapp;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.fyp.webapp.bean.Subject;
import com.fyp.webapp.repository.SubjectRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class SubjectRepositoryTest {
	@Autowired 
	SubjectRepository subjectRepo;
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Test
	public void testCreateSubject() {
		Subject subject = new Subject();
		subject.setName("EPDA");
		
		Subject savedSubject = subjectRepo.save(subject);
		Subject existSubject = entityManager.find(Subject.class, savedSubject.getId());
		
		assertThat(existSubject.getName()).isEqualTo(subject.getName()); 
	}
}
