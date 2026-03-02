package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PlayController {

	@GetMapping("/play")
	public String play() {
		return "play";
	}
	
	@PostMapping("/play")
    public String play(@RequestParam(required = false) String role) {
        return "play"; 
    }
}
