package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class GameController {

	@GetMapping("/game")
	public String game() {
		return "game";
	}
	
	@PostMapping("/host")
    public String host() {
        return "host"; 
    }
}
