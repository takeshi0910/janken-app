package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Choice;
import com.example.demo.entity.Game;
import com.example.demo.entity.Guest;
import com.example.demo.entity.Room;
import com.example.demo.entity.UserInfo;
import com.example.demo.model.MypageDTO;
import com.example.demo.repository.ChoiceRepository;
import com.example.demo.repository.GuestRepository;

import lombok.RequiredArgsConstructor;

/**
 * @author masatoki.toyama
 */
@Service
@RequiredArgsConstructor
public class MypageService {

    private final GuestRepository gestRepository;
    private final ChoiceRepository choiceRepository;

    public List<MypageDTO> getMypageUser(UserInfo userInfo) {
        List<Guest> guestList = gestRepository.findByUserInfo(userInfo);
        List<MypageDTO> result = new ArrayList<>();

        for (Guest guest : guestList) {
            Game game = guest.getGame();
            if (game == null) continue;

            MypageDTO dto = new MypageDTO();
            dto.setGameId(game.getGameId());

            // ゲーム名取得
            if (game.getGamemaster() != null) {
                dto.setGameName(game.getGamemaster().getGameName());
            } else {
                dto.setGameName("ゲーム名未設定");
            }

            // ゲーム状態取得
            if (game.getGameStatusMaster() != null) {
                dto.setGameStatus(game.getGameStatusMaster().getStatusName());
            } else {
                dto.setGameStatus("状態未設定");
            }

            // Roomのパスワードを取得（Game → Room）
            Room room = game.getRoom();
            if (room != null && room.getRoomPassword() != null) {
                dto.setRoomPassword(room.getRoomPassword());
            } else {
                dto.setRoomPassword("あいことば未設定");
            }

            // 複数の手を取得（Game と User で検索）
            List<Choice> choices = choiceRepository.findByGameAndUserInfo(game, userInfo);
            List<Long> choiceIds = new ArrayList<>();
            List<String> choiceNames = new ArrayList<>();

            /*if (!choices.isEmpty()) {
                for (Choice choice : choices) {
                    choiceIds.add(choice.getId());
            
                    if (choice.getChoiceMaster() != null) {
                        choiceNames.add(choice.getChoiceMaster().getChoiceName());
                    } else {
                        choiceNames.add("不明な手");
                    }
                }
            } else {
                choiceNames.add("未提出");
            }*/

            dto.setChoiceIds(choiceIds);
            dto.setChoiceNames(choiceNames);

            result.add(dto);
        }

        return result;
    }
}
