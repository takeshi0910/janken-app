package com.example.demo.service;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entity.UserInfo;
import com.example.demo.form.RegisterForm;
import com.example.demo.repository.UserInfoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RegisterService {

	 private final UserInfoRepository repository;
	 
	 private final PasswordEncoder passwordEncoder;
	 
	 public Optional<UserInfo> registerUserInfo(RegisterForm form){
		 Optional<UserInfo> existingUser = repository.findById(form.getUserId());
		 if(existingUser.isPresent()){
			return Optional.empty();
		 }
		 
		 Optional<UserInfo> existingUserName = repository.findById(form.getUserName());
		 if(existingUserName.isPresent()){
			return Optional.empty();
		 }
		 
		 var userInfo = new UserInfo();
		 var encodedPassword = passwordEncoder.encode(form.getPassword());
		 userInfo.setUserId(form.getUserId());
		 userInfo.setLoginPassword(encodedPassword);
		 userInfo.setUserName(form.getUserName());
		 return Optional.of(repository.save(userInfo));
	 }
}
