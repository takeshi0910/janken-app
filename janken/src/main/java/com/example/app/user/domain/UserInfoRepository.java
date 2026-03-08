package com.example.app.user.domain;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author masatoki.toyama
 */
@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Integer> {
    Optional<UserInfo> findByUserId(Integer userId);

    Optional<UserInfo> findByEmail(String email);

    Optional<UserInfo> findByUserName(String userName);

}
