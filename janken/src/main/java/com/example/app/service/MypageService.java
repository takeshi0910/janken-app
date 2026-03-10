package com.example.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.app.model.MypageDTO;
import com.example.app.user.domain.UserInfo;

import lombok.RequiredArgsConstructor;

/**
 * @author masatoki.toyama
 */
@Service
@RequiredArgsConstructor
public class MypageService {


    public List<MypageDTO> getMypageUser(UserInfo userInfo) {
        List<MypageDTO> result = new ArrayList<>();

        

        return result;
    }
}
