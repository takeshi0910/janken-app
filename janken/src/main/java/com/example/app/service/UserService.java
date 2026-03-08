package com.example.app.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.app.entity.UserInfo;
import com.example.app.repository.UserInfoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	 private final UserInfoRepository repository;
	 
	 public Optional<UserInfo> searchUserByEmail(String email){
		 return repository.findByEmail(email);
	 }
}
