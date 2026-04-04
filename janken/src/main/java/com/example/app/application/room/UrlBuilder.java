package com.example.app.application.room;

import com.example.app.domain.game.core.GameKind;
import com.example.app.domain.room.RoomId;

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
    public static String build(GameKind game, Destination dest, RoomId roomId) {

        if (dest == Destination.NONE) {
            return "";
        }

        return "/room/"
                + game.path()
                + "/"
                + dest.action()
                + "?roomId="
                + roomId.value();
    }
}