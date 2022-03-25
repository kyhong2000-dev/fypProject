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
@Table(name = "subjective_question")
public class Subjectivequestion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable= false, length=1000)
	private String questionContent;
	
	@Column(nullable= true, length=100)
	private String questionAnswer;
	
	//@OneToOne(fetch =  FetchType.LAZY, cascade = CascadeType.ALL) //here is the owning side of the 1:1 relationship
	//@JoinColumn(name="question_id") 
	//private Question question;
	
	@OneToOne(mappedBy= "sbjQuestion") //here is the reference side of 1:1 relationship 
	private Question question;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getQuestionContent() {
		return questionContent;
	}

	public void setQuestionContent(String questionContent) {
		this.questionContent = questionContent;
	}

	public String getQuestionAnswer() {
		return questionAnswer;
	}

	public void setQuestionAnswer(String questionAnswer) {
		this.questionAnswer = questionAnswer;
	}
	
	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

}
