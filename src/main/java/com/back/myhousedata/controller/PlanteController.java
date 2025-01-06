package com.back.myhousedata.controller;
import org.springframework.http.ResponseEntity;
import com.back.myhousedata.entities.Plante;
import com.back.myhousedata.service.PlanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/plantes")
public class PlanteController {

    @Autowired
    public PlanteService planteService;

    @PostMapping
    public ResponseEntity<List<Plante>> getPlantes() {
        return ResponseEntity.ok(planteService.getPlantes());
    }
}
