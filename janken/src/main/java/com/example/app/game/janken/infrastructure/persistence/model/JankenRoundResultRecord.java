package com.example.app.game.janken.infrastructure.persistence.model;

import java.util.List;

import com.example.app.room.domain.PlayerId;
import com.example.app.room.domain.RoomId;

import lombok.Getter;

/**
 * janken_round_result テーブルのデータ登録用POJO。
 * 
 *  @author takeshi.kashiwagi
 */
@Getter
public class JankenRoundResultRecord {
    private RoomId roomId;
    private Integer orderNo;
    private boolean isDraw;
    private  List<PlayerId>  winnerPlayerIdsJson;
    private  List<PlayerId>  loserPlayerIdsJson;

    public JankenRoundResultRecord(
            RoomId roomId,
            Integer orderNo,
            boolean isDraw,
            List<PlayerId> winnerPlayerIdsJson,
            List<PlayerId> loserPlayerIdsJson) {
        this.roomId = roomId;
        this.orderNo = orderNo;
        this.isDraw = isDraw;
        this.winnerPlayerIdsJson = winnerPlayerIdsJson;
        this.loserPlayerIdsJson = loserPlayerIdsJson;
    }

}
