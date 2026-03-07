package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Choice;
import com.example.demo.entity.Game;
import com.example.demo.entity.UserInfo;

@Repository
public interface ChoiceRepository extends JpaRepository<Choice, Long> {
    List<Choice> findByGameAndUserInfo(Game game, UserInfo userInfo);
}
