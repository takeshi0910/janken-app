package com.example.app.game.room.domain;

/** 
 * ルームの進行状況を表す列挙型
 * 
 * @author takeshi.kashiwagi
 */
public enum RoomStatus {
    PREPARING("準備中"),
    IN_PROGRESS("開催中"),
    FINISHED("終了");
    
    private final String displayName;

    RoomStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}