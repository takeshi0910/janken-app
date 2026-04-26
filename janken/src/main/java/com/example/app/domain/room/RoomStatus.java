package com.example.app.domain.room;

import com.example.app.application.room.Destination;
import com.example.app.application.room.UrlBuilder;
import com.example.app.domain.game.core.GameKind;
import com.example.app.domain.room.vo.RoomId;

/** 
 * ルームの進行状況を表す列挙型
 * 
 * @author takeshi.kashiwagi
 */
public enum RoomStatus {
    PREPARING("準備中", "準備中", "btn-secondary", false, Destination.NONE),
    OPEN("開催中", "参加", "btn-primary", true, Destination.PLAY),
    CLOSED("完了", "結果", "btn-success", true, Destination.RESULT);
    
    private final String statusLabel; // 状態の日本語（開催中など）
    private final String buttonLabel; // ボタンに表示する文字（参加など）
    private final String buttonClass; // ボタンの色
    private final boolean clickable; // 押下可能か
    private final Destination destination; // 遷移先URL

    RoomStatus(String statusLabel, String buttonLabel, String buttonClass,
                    boolean clickable, Destination destination) {
        this.statusLabel = statusLabel;
        this.buttonLabel = buttonLabel;
        this.buttonClass = buttonClass;
        this.clickable = clickable;
        this.destination = destination;
    }

    public String getStatusLabel() {
        return statusLabel;
    }

    public String getButtonLabel() {
        return buttonLabel;
    }

    public String getButtonClass() {
        return buttonClass;
    }

    public boolean isClickable() {
        return clickable;
    }

    public Destination getDestination() {
        return destination;
    }
    
    public boolean isClosed() {
        return this == CLOSED;
    }
    
    /** 参加カラムのボタン制御 */
    public String buildUrlOrEmpty(GameKind gameKind, RoomId roomId) {
        if (!clickable) return "";
        return UrlBuilder.build(gameKind, destination, roomId);
    }

}