package com.example.demo.model;

import java.util.List;

import lombok.Data;

@Data
public class MypageDTO {
    private Long gameId;
    private String gameStatus;
    private String gameName;
    private String roomPassword;
    
    // 複数回分の出した手（choiceIdとchoiceName）を保持
    private List<Long> choiceIds;
    private List<String> choiceNames;
}