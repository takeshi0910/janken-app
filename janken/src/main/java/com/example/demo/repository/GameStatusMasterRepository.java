package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.GameStatusMaster;

@Repository
public interface GameStatusMasterRepository extends JpaRepository<GameStatusMaster, Long> {
    List<GameStatusMaster> findALL();
}
