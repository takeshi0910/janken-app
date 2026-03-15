package com.example.app.janken.application;

import java.util.List;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.app.janken.domain.JankenChoice;
import com.example.app.janken.infrastructure.repository.JankenChoiceRepository;

import lombok.RequiredArgsConstructor;

/**
 * じゃんけんゲームのサービス実装クラス
 * 
 * @author takeshi.kashiwagi
 */
@Service
@RequiredArgsConstructor
public class JankenServiceImpl implements JankenService {

    private final JankenChoiceRepository jankenChoiceRepository;
    private final AuditorAware<Integer> auditorAware;

    @Override
    @Transactional
    public void saveChoices(Integer roomId, List<JankenChoice> choices) {

        // ログインユーザーの ID は AuditorAware が自動でセットする
        Integer currentUserId = auditorAware.getCurrentAuditor()
                        .orElseThrow(() -> new IllegalStateException("ログインユーザーが取得できません"));

        jankenChoiceRepository.deleteByRoomIdAndCreatedId(roomId, currentUserId);

        choices.forEach(c -> c.setRoomId(roomId));

        jankenChoiceRepository.saveAll(choices);
    }

}
