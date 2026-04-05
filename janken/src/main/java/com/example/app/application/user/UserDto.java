package com.example.app.application.user;


import com.example.app.domain.user.vo.UserId;
import com.example.app.domain.user.vo.UserName;
import com.example.app.infrastructure.user.entity.UserEntity;

import lombok.Data;

/**
 * ユーザーの名前とIDだけを保持するDTO。ルームの参加者の選択に用いる。
 */
@Data
public class UserDto {
    private UserId userId;
    private UserName userName;

    public static UserDto from(UserEntity entity) {
        UserDto dto = new UserDto();
        dto.setUserId(new UserId( entity.getUserId()));
        dto.setUserName(new UserName(entity.getUserName()));
        return dto;
    }
}
