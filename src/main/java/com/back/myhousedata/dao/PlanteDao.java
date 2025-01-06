package com.back.myhousedata.dao;

import com.back.myhousedata.entities.Plante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanteDao extends JpaRepository<Plante, Long> {
}
