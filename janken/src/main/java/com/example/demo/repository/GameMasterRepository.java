package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.GameMaster;

@Repository
public interface GameMasterRepository extends JpaRepository<GameMaster, Long> {

}
