package com.example.app.user.application;


import com.example.app.user.domain.vo.UserId;
import com.example.app.user.domain.vo.UserName;
import com.example.app.user.infrastructure.entity.UserInfo;

import lombok.Data;

/**
 * ユーザーの名前とIDだけを保持するDTO。ルームの参加者の選択に用いる。
 */
@Data
public class UserDto {
    private UserId userId;
    private UserName userName;

    public static UserDto from(UserInfo entity) {
        UserDto dto = new UserDto();
        dto.setUserId(entity.getUserId());
        dto.setUserName(entity.getUserName());
        return dto;
    }
}
