package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.GameStatusMaster;

/**
 * @author masatoki.toyama
 */
@Repository
public interface GameStatusMasterRepository extends JpaRepository<GameStatusMaster, Long> {

}
