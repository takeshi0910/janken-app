package com.example.app.game.room.domain;

/** 
 * ルームの進行状況を表す列挙型
 * 
 * @author takeshi.kashiwagi
 */
public enum RoomStatus {
    PREPARING("準備中", "準備中", "btn-secondary", false, ""),
    OPEN("開催中", "参加", "btn-primary", true, "/rooms/%d"),
    CLOSED("完了", "結果", "btn-danger", true, "/rooms/%d/result");

    private final String statusLabel;   // 状態の日本語（開催中など）
    private final String buttonLabel;   // ボタンに表示する文字（参加など）
    private final String buttonClass;   // ボタンの色
    private final boolean clickable;    // 押下可能か
    private final String urlPattern;    // 遷移先URLパターン

    RoomStatus(String statusLabel, String buttonLabel, String buttonClass,
               boolean clickable, String urlPattern) {
        this.statusLabel = statusLabel;
        this.buttonLabel = buttonLabel;
        this.buttonClass = buttonClass;
        this.clickable = clickable;
        this.urlPattern = urlPattern;
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

    public String buildUrl(long roomId) {
        if (!clickable) return "";
        return String.format(urlPattern, roomId);
    }
}