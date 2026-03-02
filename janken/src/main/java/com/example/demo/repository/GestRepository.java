package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Guest;
import com.example.demo.entity.UserInfo;

public interface GestRepository extends JpaRepository <Guest, Long> {
	
	List<Guest> findByUser(UserInfo userInfo);

}