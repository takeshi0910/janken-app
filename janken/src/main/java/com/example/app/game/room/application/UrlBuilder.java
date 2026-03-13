package com.example.app.game.room.application;

/** 
 * マイページ一覧上のルームのURLを生成する。
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
            case ROOM_DETAIL -> "/room/registerform?roomId=" + roomId;
            case ROOM_RESULT -> "/room/result?roomId=" + roomId;
            case NONE -> "";
        };
    }
}

