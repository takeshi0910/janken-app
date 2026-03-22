package com.example.app.game.janken.infrastructure.persistence.model;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

/**
 * DB janken_round_result テーブルに対応したモデルクラス
 * 
 *  @author takeshi.kashiwagi
 */
@Getter
@Setter
public class JankenRoundResultRecord {
    private Integer roomId;
    private Integer orderNo;
    private boolean isDecided;
    private String winnerPlayerIdsJson;
    private String loserPlayerIdsJson;
    private LocalDateTime createdAt;
}
