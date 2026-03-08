package com.example.app.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import com.example.app.user.domain.UserInfo;

import lombok.Data;

@Data
@Entity
@Table(name = "guest")
public class Guest {
	@Id
	@Column(name = "guest_id")
	private long guestId;
	
	@ManyToOne
	@JoinColumn(name = "room_id", referencedColumnName = "room_id")
	private Room room;
	
	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "user_id")
	private UserInfo userInfo;
	
	@ManyToOne
	@JoinColumn(name = "game_id", referencedColumnName = "game_id")
	private Game game;

}