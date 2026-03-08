package com.example.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.app.entity.Choice;
import com.example.app.entity.Game;
import com.example.app.entity.UserInfo;

/**
 * @author masatoki.toyama
 */
@Repository
public interface ChoiceRepository extends JpaRepository<Choice, Long> {
    List<Choice> findByGameAndUserInfo(Game game, UserInfo userInfo);
}
