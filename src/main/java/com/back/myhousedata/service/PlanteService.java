package com.back.myhousedata.service;

import com.back.myhousedata.dao.PlanteDao;
import com.back.myhousedata.entities.Plante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlanteService {

    @Autowired
    PlanteDao planteDao;

    public List<Plante> getPlantes() {
        return planteDao.findAll();
    }
}
