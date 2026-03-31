package com.example.app.domain.room.application;

/** 
 * マイページのルーム一覧の参加カラムの遷移先種別を表す列挙型。
 * RoomStatus に紐づき、UrlBuilder によって実際のURLへ変換される。
 * 
 * PLAY : 参加画面へ遷移
 * RESULT : 結果画面へ遷移
 * NONE : 遷移なし（クリック不可）
 * 
 * @author takeshi.kashiwagi
 */
public enum Destination {
    PLAY("play"),
    RESULT("result"),
    NONE(""); // 遷移なし

    private final String action;

    Destination(String action) {
        this.action = action;
    }

    public String action() {
        return action;
    }
}