package com.example.app.game.room.application;

/** 
 * マイページ一覧上のルームのURLの矛先
 * <p>
 * RoomStatus と組み合わせて使用し、実際の URL 生成は UrlBuilder が担当する。
 * </p>
 * 
 * NONE : 遷移なし（クリック不可）
 * PLAY : 参加
 * RESULT : 結果画面
 * 
 * @author takeshi.kashiwagi
 */
public enum Destination {
    NONE,
    PLAY,
    RESULT
}
