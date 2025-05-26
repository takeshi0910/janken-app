package com.example.demo.form;

import lombok.Data;

@Data
public class GuestForm {
	
	private String roomId;
	
    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }
}


