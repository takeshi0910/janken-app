package com.example.app.service;

import org.springframework.stereotype.Service;

/**
 * @author masatoki.toyama
 */
@Service
public class MypageService {

    // ここで必要になるのは、ユーザーに関連のあるルーム情報のみ。
    // ルーム情報には、対象のゲーム情報、参加者、ゲームの進行状況があげられる。
    /* 
    public List<MypageDTO> getMypageUser(UserInfo userInfo) {
        List<MypageDTO> result = new ArrayList<>();
        return result;
        
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

            if (!choices.isEmpty()) {
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
            }

            dto.setChoiceIds(choiceIds);
            dto.setChoiceNames(choiceNames);

            result.add(dto);
        }

        return result;
    */
    
}
