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
@Table(name = "objective_question")
public class Objectivequestion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable= false, length = 145 )
	private String questionContent;
	
	@Column(nullable= false, length = 145 )
	private String choiceAContent;
	
	@Column(nullable= false, length = 145 )
	private String choiceBContent;
	
	@Column(nullable= false, length = 145 )
	private String choiceCContent;
	
	@Column(nullable= false, length = 145 )
	private String choiceDContent;
	
	@Column(nullable= false, length = 145 )
	private String correctAnswer;

	//@OneToOne(fetch =  FetchType.LAZY, cascade = CascadeType.ALL) //here is owning side of 1:1 relationship
	//@JoinColumn(name="question_id")
	//private Question question;
	
	@OneToOne(mappedBy= "objQuestion") // here is the reference side of 1:1 relationship
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

	public String getChoiceAContent() {
		return choiceAContent;
	}

	public void setChoiceAContent(String choiceAContent) {
		this.choiceAContent = choiceAContent;
	}

	public String getChoiceBContent() {
		return choiceBContent;
	}

	public void setChoiceBContent(String choiceBContent) {
		this.choiceBContent = choiceBContent;
	}

	public String getChoiceCContent() {
		return choiceCContent;
	}

	public void setChoiceCContent(String choiceCContent) {
		this.choiceCContent = choiceCContent;
	}

	public String getChoiceDContent() {
		return choiceDContent;
	}

	public void setChoiceDContent(String choiceDContent) {
		this.choiceDContent = choiceDContent;
	}

	public String getCorrectAnswer() {
		return correctAnswer;
	}

	public void setCorrectAnswer(String correctAnswer) {
		this.correctAnswer = correctAnswer;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}
}
