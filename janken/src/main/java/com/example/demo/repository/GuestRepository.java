package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Guest;
import com.example.demo.entity.UserInfo;

/**
 * @author masatoki.toyama
 */
public interface GuestRepository extends JpaRepository <Guest, Long> {
	
	List<Guest> findByUserInfo(UserInfo userInfo);

}