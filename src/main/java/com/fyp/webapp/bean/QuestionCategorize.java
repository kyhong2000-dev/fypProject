package com.fyp.webapp.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "question_categorize")
public class QuestionCategorize {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, length = 245)
	private String categorizeName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCategorizeName() {
		return categorizeName;
	}

	public void setCategorizeName(String categorizeName) {
		this.categorizeName = categorizeName;
	}
}
	
