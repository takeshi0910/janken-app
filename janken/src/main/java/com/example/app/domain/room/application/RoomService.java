package com.example.app.domain.room.application;

import java.util.List;

import com.example.app.domain.room.RoomId;
import com.example.app.domain.room.application.dto.RoomListItemDto;
import com.example.app.domain.room.application.dto.RoomRegisterDto;
import com.example.app.presentation.room.RoomForm;
import com.example.app.user.domain.vo.UserId;

/**
 * ROOMに関連するサービスインターフェース
 * 
 * @author takeshi.kashiwagi
 */
public interface RoomService {

    /**
     * マイページ表示用のルーム情報を取得
     * 
     * @param userId
     * @return 対象者のマイページでの表示するルーム情報のリスト
     */
    List<RoomListItemDto> selectRoomsByUserId(UserId userId);
    
    /**
     * 登録済みのルーム情報を取得。
     * 
     * @param roomId
     * @return 登録済みのルーム情報
     */
    public RoomRegisterDto findById(RoomId roomId);

    /**
     * ルーム情報の登録・更新
     * 
     * @param room ルーム情報
     */
    public void save(RoomForm form);

}