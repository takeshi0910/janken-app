package com.example.app.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "game_master")
public class GameMaster {
	@Id
	@Column(name = "game_name_id")
	private long gameId;
	
	@Column(name = "game_name")
	private String gameName;
	
}