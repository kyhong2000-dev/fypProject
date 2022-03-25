package com.fyp.webapp.bean;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "exam")
public class Exam {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "date", nullable = false,length = 100)
	private LocalDate date;
	
	@DateTimeFormat(pattern = "HH:mm")
	@Column(name = "startTime", nullable = false, length = 100)
	private LocalTime startTime;
	
	@DateTimeFormat(pattern = "HH:mm")
	@Column(name = "endTime", nullable = false, length = 100)
	private LocalTime endTime;
	
	@Column(name = "subject", nullable = false,length = 100)
	private String subject;
	
	@Column(name = "score", nullable = false, length = 100)
	private double score;
	
	@Column(name = "duration", nullable = false, length = 100)
	private double duration;
	
	@Column(name = "questionType", nullable = false, length = 100)
	private String questionType;
	
	@Column(name = "status", nullable = false, length = 50)
	private String status;
	
	@ManyToMany
	@JoinTable(
			name="exam_enrolled_student",
			joinColumns = @JoinColumn(name = "exam_id"),
			inverseJoinColumns = @JoinColumn(name = "user_id")
	)
	private List<User> enrolledStudent = new ArrayList<User>();
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public LocalTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}

	public LocalTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public double getDuration() {
		return duration;
	}

	public void setDuration(double duration) {
		this.duration = duration;
	}

	public String getQuestionType() {
		return questionType;
	}

	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public List<User> getEnrolledStudent() {
		return enrolledStudent;
	}

	public void setEnrolledStudent(List<User> enrolledStudent) {
		this.enrolledStudent = enrolledStudent;
	}
	
	public void enrollStudent(User user) {
		enrolledStudent.add(user);
	}
	
}
