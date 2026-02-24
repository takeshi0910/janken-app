package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "game_status_master")
public class GameStatusMaster {
	
	@Id
	@Column(name = "status_id")
	private Integer statusId;
		
	@Column(name = "status_name")
	private String statusName;

}