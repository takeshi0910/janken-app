package com.example.app.infrastructure.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.app.domain.user.Email;
import com.example.app.domain.user.UserId;
import com.example.app.domain.user.UserName;
import com.example.app.infrastructure.user.entity.UserInfo;

/**
 * @author masatoki.toyama
 */
@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, UserId> {
    Optional<UserInfo> findByUserId(UserId userId);

    Optional<UserInfo> findByEmail(Email email);

    Optional<UserInfo> findByUserName(UserName userName);

}
