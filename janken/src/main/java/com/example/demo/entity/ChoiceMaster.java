package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "choice_master")
public class ChoiceMaster {
	
	@Id
	@Column(name = "choice_id")
	private Integer choiceId;
		
	@Column(name = "choice_name")
	private String choiceName;

}