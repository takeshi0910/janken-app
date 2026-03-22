package com.example.app.game.janken.application;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.app.game.janken.domain.model.JankenMode;
import com.example.app.game.janken.domain.model.round.OrderNo;
import com.example.app.game.janken.domain.model.round.RoundResult;
import com.example.app.game.janken.domain.service.JankenGameEngine;
import com.example.app.game.janken.infrastructure.mapper.JankenMapper;
import com.example.app.game.janken.infrastructure.persistence.model.JankenChoice;
import com.example.app.room.application.RoomService;
import com.example.app.room.application.dto.RoomRegisterDto;
import com.example.app.room.roomuser.infrastructure.mapper.RoomUserMapper;

import lombok.RequiredArgsConstructor;

/**
 * じゃんけんゲームのサービス実装クラス
 * 
 * @author takeshi.kashiwagi
 */
@Service
@RequiredArgsConstructor
public class JankenApplicationServiceImpl implements JankenApplicationService {

    private final JankenMapper jankenMapper;
    private final AuditorAware<Integer> auditorAware;
    private final RoomService roomService;
    private final JankenGameEngine jankenGameEngine;
    private final RoomUserMapper roomUserMapper;

    @Override
    public List<JankenChoice> getJankenChoices(Integer roomId, Integer playerId) {
        return jankenMapper.selectChoices(roomId, playerId);
    }

    @Override
    @Transactional
    public void registerJankenChoices(Integer roomId, List<JankenChoice> choices) {

        // ログインユーザーの ID は AuditorAware がセット
        Integer currentUserId = auditorAware.getCurrentAuditor()
                .orElseThrow(() -> new IllegalStateException("ログインユーザーが取得できません"));

        choices.forEach(c -> {
            c.setRoomId(roomId); // roomIdの整合性をサービス層で保証する
            c.setPlayerId(currentUserId);
        });

        jankenMapper.deleteChoices(roomId, currentUserId);

        jankenMapper.insertChoices(choices);
    }

    @Override
    public void battle(Integer roomId) {
        // 1. ルーム情報を取得
        RoomRegisterDto room = roomService.findById(roomId);

        // 2. JankenMode を取得
        String modeStr = room.getGameMode();
        JankenMode mode = JankenMode.fromString(modeStr);

        // 3. ルームに登録されている全プレイヤーID
        Set<Integer> allPlayers = roomUserMapper.selectUserIdsByRoomId(roomId);

        // 4. choices を組み立てる
        List<JankenChoice> choices = jankenMapper.selectChoicesByRoomId(roomId);

        // 5. maxRounds を取得
        int maxRounds = room.getRoundCount();

        // 6. JankenGameEngine に丸投げ（モードごとのディスパッチはエンジン側）
        Map<OrderNo, RoundResult> results = jankenGameEngine.judge(
                mode,
                choices,
                allPlayers,
                maxRounds);

        // 7. 結果を保存（必要なら）
       // TODO battleResultRepository.save(roomId, results);

    }

}
