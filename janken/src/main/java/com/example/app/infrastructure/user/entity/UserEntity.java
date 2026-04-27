package com.example.app.infrastructure.user.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Data;
/**
 * users テーブル entity
 */
@Data
@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "email", nullable = false, length = 255)
    private String email;

    @Column(name = "user_name", nullable = false, length = 100)
    private String userName;

    @Column(name = "password_hashed", nullable = false, length = 255)
    private String passwordHashed;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @Column(name = "is_deleted", nullable = false)
    private boolean isDeleted = false;

    // JPA 用
    protected UserEntity() {}

    public UserEntity(
            String email,
            String userName,
            String passwordHashed,
            LocalDateTime createdAt,
            LocalDateTime updatedAt,
            boolean isDeleted
    ) {
        this.email = email;
        this.userName = userName;
        this.passwordHashed = passwordHashed;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.isDeleted = isDeleted;
    }
}