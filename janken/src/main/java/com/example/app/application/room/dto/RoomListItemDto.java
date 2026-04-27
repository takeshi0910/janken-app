package com.example.app.application.room.dto;

import java.time.LocalDateTime;

import com.example.app.domain.room.RoomStatus;

import lombok.Data;

/** 
 * マイページでの表示するルーム情報を保持するDTO
 * 
 * <p>RoomMapper.selectRoomsByUserIdの受取用フィールドおよび別途じゃんけんの出し手の登録情報を保持する。
 * 
 * @author takeshi.kashiwagi
 */
@Data
public class RoomListItemDto {
    private Integer roomId;
    private String roomName;
    private String gameKind;
    private String gameMode;
    private RoomStatus roomStatus;
    private LocalDateTime startedDate;
    private LocalDateTime endDate;
    private boolean isRoomMaster;
    private boolean isPlayer;
    
    /**  じゃんけんの出し手の登録有無判定。サービスで判定。じゃんけん以外のゲームはnullとなるのでラッパークラスを使用。 */
    private Boolean isHandRegistered;
}