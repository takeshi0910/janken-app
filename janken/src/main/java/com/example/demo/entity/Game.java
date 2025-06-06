package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "game")
public class Game {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "game_id")
	private Long gameId;  // 主キー
	
    @OneToOne
    @JoinColumn(name = "game_name_id", referencedColumnName = "game_name_id")
    private GameMaster gamemaster;
	
	@ManyToOne
	@JoinColumn(name = "room_id", referencedColumnName = "room_id")
	private Room room;
	
	@ManyToOne
	@JoinColumn(name = "game_status_id", referencedColumnName = "status_id")
	private GameStatusMaster gameStatusMaster;

}