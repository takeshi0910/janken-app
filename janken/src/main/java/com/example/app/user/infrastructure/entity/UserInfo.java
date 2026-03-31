package com.example.app.user.infrastructure.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import com.example.app.user.domain.vo.Email;
import com.example.app.user.domain.vo.HashedPassword;
import com.example.app.user.domain.vo.UserId;
import com.example.app.user.domain.vo.UserName;

import lombok.Data;

/** テーブル users に対応するEntity */
@Data
@Entity
@Table(name = "users")
public class UserInfo {
    @Id
    @Column(name = "user_id")
    private UserId userId;
    
    @Column(name = "email")
    private Email email;

    @Column(name = "user_name")
    private UserName userName;

    @Column(name = "password_hashed")
    private HashedPassword passwordHashed;

    @Column(name = "created_at")
    private LocalDateTime createAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted = false;

}