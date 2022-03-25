package com.fyp.webapp.bean;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "question")
public class Question {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, length = 45)
	private String questionType;
	
	@Column(nullable = false, length=45)
	private String createdTime;
	
	@Column(nullable = false, length=45)
	private String createdBy;
	
	@OneToOne(fetch =  FetchType.LAZY, cascade = CascadeType.ALL) //here is owning side of 1:1 relationship
	@JoinColumn(name="objQuestion_id")
	private Objectivequestion objQuestion;
	
	@OneToOne(fetch =  FetchType.LAZY, cascade = CascadeType.ALL) //here is owning side of 1:1 relationship
	@JoinColumn(name="sbjQuestion_id")
	private Subjectivequestion sbjQuestion;

	
	//@OneToOne(mappedBy= "question") // here is the reference side of 1:1 relationship
	//private Objectivequestion objQuestion;
	
	//@OneToOne(mappedBy= "question") //here is the reference side of 1:1 relationship 
	//private Subjectivequestion sbjQuestion;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getQuestionType() {
		return questionType;
	}

	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}

	public String getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}


	public Objectivequestion getObjQuestion() {
		return objQuestion;
	}

	public void setObjQuestion(Objectivequestion objQuestion) {
		this.objQuestion = objQuestion;
	}

	public Subjectivequestion getSbjQuestion() {
		return sbjQuestion;
	}

	public void setSbjQuestion(Subjectivequestion sbjQuestion) {
		this.sbjQuestion = sbjQuestion;
	}	
}
