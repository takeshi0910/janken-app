package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.GameMaster;

@Repository
public interface GameMasterRepository extends JpaRepository<GameMaster, Long> {
    List<GameMaster> findALL();
}
