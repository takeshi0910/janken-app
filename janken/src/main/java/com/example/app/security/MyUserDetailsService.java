package com.example.app.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.app.user.domain.UserInfo;
import com.example.app.user.domain.UserInfoRepository;

/**
 * @author masatoki.toyama
 */
@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserInfoRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	UserInfo userInfo = userRepository.findByUserName(username)
            .orElseThrow(() -> new UsernameNotFoundException("ユーザーが見つかりません"));
        return new MyUserDetails(userInfo);
    }
}

