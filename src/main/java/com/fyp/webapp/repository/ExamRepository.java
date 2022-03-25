package com.fyp.webapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fyp.webapp.bean.Exam;

public interface ExamRepository extends JpaRepository<Exam,Long> {
	@Query("SELECT e FROM Exam e WHERE e.subject like %?1%" )
	List<Exam> findExamByKeyword(@Param("keyword")String keyword);
}
