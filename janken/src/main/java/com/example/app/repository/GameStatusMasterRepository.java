package com.example.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.app.entity.GameStatusMaster;

/**
 * @author masatoki.toyama
 */
@Repository
public interface GameStatusMasterRepository extends JpaRepository<GameStatusMaster, Long> {

}
