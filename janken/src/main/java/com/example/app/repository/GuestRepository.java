package com.example.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.app.entity.Guest;
import com.example.app.entity.UserInfo;

/**
 * @author masatoki.toyama
 */
public interface GuestRepository extends JpaRepository <Guest, Long> {
	
	List<Guest> findByUserInfo(UserInfo userInfo);

}