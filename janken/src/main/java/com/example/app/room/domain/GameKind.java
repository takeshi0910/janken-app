package com.example.app.room.domain;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.example.app.janken.domain.JankenMode;

/**
 * ゲーム種別を表す列挙型。
 * 
 * <p>画面遷移で使用するURLパス（英字）を各ゲームに紐づけて提供する。
 * <p>ゲームは、それぞれに対応したゲームモードを持つ。
 * 
 * @author takeshi.kashiwagi
 */
public enum GameKind {
    じゃんけん("janken") {
        @Override
        public List<String> modes() {
            return Arrays.stream(JankenMode.values())
                    .map(Enum::name)
                    .toList();
        }
    },
    ポーカー("poker") {
        @Override
        public List<String> modes() {
            return Collections.emptyList();
        }
    },
    麻雀("mahjong") {
        @Override
        public List<String> modes() {
            return Collections.emptyList();
        }
    },
    あみだくじ("amidakuji") {
        @Override
        public List<String> modes() {
            return Collections.emptyList();
        }
    };

    private final String path;

    GameKind(String path) {
        this.path = path;
    }

    public String path() {
        return path;
    }
    
    /** 各ゲームが持つモード一覧を返す */
    public abstract List<String> modes();

}