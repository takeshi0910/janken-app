package com.example.app.room.web;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.example.app.room.domain.GameKind;
import com.example.app.room.domain.Room;
import com.example.app.room.domain.RoomStatus;

import lombok.Data;

/** 
 * ルームの登録・編集フォームクラス
 * 
 * @author takeshi.kashiwagi
 */
@Data
public class RoomForm {

    private Integer roomId;

    @NotBlank(message = "ルーム名を入力してください")
    @Size(max = 100)
    private String roomName;

    @NotNull(message = "ゲーム種別を選択してください")
    private GameKind gameKind;
    
    private String gameMode;
    
    @AssertTrue(message = "じゃんけんの場合はゲームモードを選択してください")
    public boolean isGameModeValid() {
        // gameKind が null の場合は他のバリデーションに任せる
        if (gameKind == null) {
            return true;
        }

        // じゃんけんのときだけ gameMode が必須
        if (gameKind == GameKind.じゃんけん) {
            return gameMode != null && !gameMode.isBlank();
        }

        // それ以外のゲームでは不要
        return true;
    }
    
    @NotNull(message = "ラウンド数を選択してください")
    @Min(value = 1, message = "ラウンド数は1以上で指定してください")
    @Max(value = 100, message = "ラウンド数は100以下で指定してください")
    private Integer roundCount;

    @NotNull(message = "進行ステータスを選択してください")
    private RoomStatus roomStatus;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime startedDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime endDate;

    /** 参加者のユーザーIDリスト */
    @NotEmpty(message = "ユーザーを1人以上選択してください")
    private List<Integer> userIds = new ArrayList<>();

    public Room toNewEntity() {
        Room room = new Room();
        room.setRoomId(roomId);
        room.setRoomName(roomName);
        room.setRoundCount(roundCount);
        room.setGameKind(gameKind);
        room.setRoomStatus(roomStatus);
        room.setStartedDate(startedDate);
        room.setEndDate(endDate);
        return room;
    }
}
