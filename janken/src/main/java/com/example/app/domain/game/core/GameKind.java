package com.example.app.domain.game.core;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.example.app.domain.janken.model.JankenMode;

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
        public GameMode toMode(String value) {
            return JankenMode.fromString(value);
        }
        
        @Override
        public List<JankenMode> modes() {
            return Arrays.asList(JankenMode.values());
        }
    },
    ポーカー("poker") {
        @Override
        public GameMode toMode(String value) {
            return null;
        }

        @Override
        public List<? extends GameMode> modes() {
            return Collections.emptyList();
        }
    },
    麻雀("mahjong") {
        @Override
        public GameMode toMode(String value) {
            return null;
        }

        @Override
        public List<? extends GameMode> modes() {
            return Collections.emptyList();
        }
    },
    あみだくじ("amidakuji") {
        public GameMode toMode(String value) {
            return null;
        }
        
        @Override
        public List<? extends GameMode> modes() {
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
    public abstract List<? extends GameMode> modes();
    
    /** 各ゲームがもつゲームモードを返す。日本語名でもEnum名でも参照可
     * @Param Enum名もしくは日本語表記
     * @return GameMode(Enum)
     */
    public abstract GameMode toMode(String value);


    /** pathが該当するゲームの保持するモード一覧を返す。 */
    public static Optional<GameKind> fromPath(String path) {
        return Arrays.stream(values())
                .filter(k -> k.path.equals(path))
                .findFirst();
    }

}