package com.example.app.game.room.application;

/** 
 * マイページ一覧上のルームの参加カラムのURLを生成する。
 *  
 * <p>
 * Destinationに対応してURLを生成する。
 * </p>
 * 
 * @author takeshi.kashiwagi
 */
public class UrlBuilder {
    public static String build(Destination dest, Integer roomId) {
        return switch (dest) {
            case PLAY -> "/room/play?roomId=" + roomId;
            case RESULT -> "/room/result?roomId=" + roomId;
            case NONE -> "";
        };
    }
}

