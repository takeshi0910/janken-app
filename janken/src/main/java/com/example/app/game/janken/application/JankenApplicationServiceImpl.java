package com.example.app.game.janken.application;

import java.util.List;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.app.game.janken.infrastructure.mapper.JankenMapper;
import com.example.app.game.janken.infrastructure.persistence.model.JankenChoice;

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

}
