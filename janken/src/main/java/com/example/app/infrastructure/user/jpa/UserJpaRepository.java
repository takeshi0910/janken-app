package com.example.app.infrastructure.user.jpa;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.app.infrastructure.user.entity.UserEntity;

/**
 * @author takeshi.kashiwagi
 */
@Repository
public interface UserJpaRepository extends JpaRepository<UserEntity, Integer> {
    Optional<UserEntity> findByUserId(Integer userId);

    Optional<UserEntity> findByEmail(String email);

    Optional<UserEntity> findByUserName(String userName);

}
