package com.example.app.game.room.janken.domain;

import java.io.Serializable;

/** 
 * room_usersの複合キークラス
 * 
 * @author takeshi.kashiwagi
 */
public class JankenChoiceId implements Serializable {
    private Integer roomId;
    private Integer createdId;
    private Integer order;

}
