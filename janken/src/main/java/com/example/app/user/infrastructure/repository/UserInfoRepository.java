package com.example.app.user.infrastructure.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.app.user.infrastructure.entity.UserInfo;

/**
 * @author masatoki.toyama
 */
@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Integer> {
    Optional<UserInfo> findByUserId(Integer userId);

    Optional<UserInfo> findByEmail(String email);

    Optional<UserInfo> findByUserName(String userName);

}
