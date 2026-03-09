package com.example.app.game.room.application;

import java.util.List;

import com.example.app.game.room.domain.Room;


/**
 * Room：ゲームを実施する部屋のサービスインターフェース
 * 
 * @author takeshi.kashiwagi
 */
public interface RoomService {

    List<Room> findRoomsByUserId(int userId);

}
