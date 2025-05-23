package com.example.demo.controller;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.constant.MessageConst;
import com.example.demo.form.RegisterForm;
import com.example.demo.repository.UserInfoRepository;
import com.example.demo.service.RegisterService;
import com.example.demo.util.AppUtil;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class RegisterController {
	
	private final RegisterService service;
	
	private final UserInfoRepository repository;
	 
	private final MessageSource messageSource;
   
	@GetMapping("/register")
    public String view(Model model, RegisterForm form) {
		
        return "register";
    }
	
	@PostMapping("/register")
	public void  register(Model model, RegisterForm form) {
		var userInfo = service.registerUserInfo(form);
		if(userInfo.isEmpty()) {
			var message = AppUtil.getMessage(messageSource, MessageConst.REGISTER_ERROR);
	        model.addAttribute("message", message);
		}else {
			var message = AppUtil.getMessage(messageSource, MessageConst.REGISTER_SUCCESS);
	        model.addAttribute("message", message);
		}
	   
	}
}

