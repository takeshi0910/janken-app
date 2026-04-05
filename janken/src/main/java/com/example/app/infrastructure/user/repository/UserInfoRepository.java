package com.example.app.infrastructure.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.app.domain.user.vo.Email;
import com.example.app.domain.user.vo.UserId;
import com.example.app.domain.user.vo.UserName;
import com.example.app.infrastructure.user.entity.UserEntity;

/**
 * @author masatoki.toyama
 */
@Repository
public interface UserInfoRepository extends JpaRepository<UserEntity, UserId> {
    Optional<UserEntity> findByUserId(UserId userId);

    Optional<UserEntity> findByEmail(Email email);

    Optional<UserEntity> findByUserName(UserName userName);

}
