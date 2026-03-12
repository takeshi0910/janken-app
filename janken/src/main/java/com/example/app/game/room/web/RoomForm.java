package com.example.app.game.room.web;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.example.app.game.room.domain.Room;

import lombok.Data;

/** 
 * ルームの登録・編集フォームクラス
 * 
 * @author takeshi.kashiwagi
 */
@Data
public class RoomForm {

    private Integer roomId;

    @NotBlank
    @Size(max = 100)
    private String roomName;

    @NotBlank
    @Size(max = 45)
    private String gameKind;

    @NotBlank
    @Size(max = 45)
    private String roomStatus;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime startedDate;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime endDate;

    public Room toEntity() {
        Room room = new Room();
        room.setRoomId(roomId);
        room.setRoomName(roomName);
        room.setGameKind(gameKind);
        room.setGameStatus(roomStatus);
        room.setStartedDate(startedDate);
        room.setEndDate(endDate);
        return room;
    }

}
