package com.example.app.game.domain;

/**
 * ゲーム種別を表す列挙型。
 * 
 * <p>画面遷移で使用するURLパス（英字）を各ゲームに紐づけて提供する。
 * 
 * @author takeshi.kashiwagi
 */
public enum GameKind {
    じゃんけん("janken"),
    ポーカー("poker"),
    麻雀("mahjong"),
    あみだくじ("amidakuji");

    private final String path;

    GameKind(String path) {
        this.path = path;
    }

    public String path() {
        return path;
    }
}