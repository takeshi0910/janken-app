package com.example.demo.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.entity.UserInfo;
import com.example.demo.repository.UserInfoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	 private final UserInfoRepository repository;
	 
	 public Optional<UserInfo> searchUserByEmail(String email){
		 return repository.findByEmail(email);
	 }
}
