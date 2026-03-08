package com.example.app.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "room")
public class Room {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "room_id")
	private long roomId;
	
	@Column(name = "room_password")
	private String roomPassword;
	
	@ManyToOne
	@JoinColumn(name = "host_id", referencedColumnName = "user_id")
	private UserInfo userInfo;
	
	@Column(name = "max_players")
	private long maxPlayers;

}