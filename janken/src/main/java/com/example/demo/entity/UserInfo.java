package com.example.demo.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Data;

/** ユーザーテーブル users に対応するEntity */
@Data
@Entity
@Table(name = "users")
public class UserInfo {
    @Id
    @Column(name = "user_id")
    private int userId;
    
    @Column(name = "email")
    private String email;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "password_hashed")
    private String passwordHashed;

    @Column(name = "created_at")
    private LocalDateTime createAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

}