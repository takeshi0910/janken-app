package com.example.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.app.entity.UserInfo;

/**
 * @author masatoki.toyama
 */
@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Integer> {
    Optional<UserInfo> findByUserId(Integer userId);

    Optional<UserInfo> findByEmail(String email);

    Optional<UserInfo> findByUserName(String userName);

}
