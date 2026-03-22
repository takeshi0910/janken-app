package com.example.app.room.domain;

import com.example.app.game.core.GameKind;
import com.example.app.room.application.Destination;
import com.example.app.room.application.UrlBuilder;

/** 
 * ルームの進行状況を表す列挙型
 * 
 * @author takeshi.kashiwagi
 */
public enum RoomStatus {
    PREPARING("準備中", "準備中", "btn-secondary", false, Destination.NONE),
    OPEN("開催中", "参加", "btn-primary", true, Destination.PLAY),
    CLOSED("完了", "結果", "btn-danger", true, Destination.RESULT);
    
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
    
    /** 参加カラムのボタン制御 */
    public String buildUrlOrEmpty(GameKind gameKind, Integer roomId) {
        if (!clickable) return "";
        return UrlBuilder.build(gameKind, destination, roomId);
    }

}