package com.example.demo.entity;

import java.time.LocalDateTime;

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
@Table(name = "choice")
public class Choice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 自動採番される主キー
    @Column(name = "id")
    private Long Id;
    
    @ManyToOne
    @JoinColumn(name = "choice_id", referencedColumnName = "choice_id")
    private ChoiceMaster choiceMaster;
	
    @ManyToOne
    @JoinColumn(name = "game_id", referencedColumnName = "game_id")
    private Game game;
	
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private UserInfo userInfo;
    
    @Column(name = "hand_order")
	private long handOrder;
    
    @Column(name = "created_date")
	private LocalDateTime createdDate;
	
}