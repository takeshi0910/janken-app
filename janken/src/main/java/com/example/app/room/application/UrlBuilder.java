package com.example.app.room.application;

import com.example.app.game.domain.GameKind;

/** 
 * マイページ一覧上のルームの参加カラムのURLを生成する。
 *  
 * <p>
 * GameKindおよびDestinationに対応してURLを生成する。
 * </p>
 * 
 * @author takeshi.kashiwagi
 */
public class UrlBuilder {
    public static String build(GameKind game, Destination dest, Integer roomId) {
        
        if (dest == Destination.NONE) {
            return "";
        }

        return "/room/"
                        + game.path()
                        + "/"
                        + dest.action()
                        + "?roomId=" 
                        + roomId;
    }
}