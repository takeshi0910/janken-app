package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MyPageController {

	@GetMapping("/mypage")
	public String view() {
		return "mypage";
	}
	
	@PostMapping("/game")
    public String game() {
        return "game"; 
    }

    @PostMapping("/guest")
    public String guest() {
        return "guest"; 
    }
}
