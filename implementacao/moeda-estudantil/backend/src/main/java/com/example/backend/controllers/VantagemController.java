package com.example.backend.controllers;

import com.example.backend.model.entities.Vantagem;
import com.example.backend.model.entities.users.Aluno;
import com.example.backend.model.services.VantagemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/vantagens")
public class VantagemController {
    private final VantagemService vantagemService;

    public VantagemController(VantagemService vantagemService) {
        this.vantagemService = vantagemService;
    }

    @GetMapping
    public ResponseEntity<List<Vantagem>> listarVantagens() {
        List<Vantagem> vantagemList = vantagemService.findAll();
        if (vantagemList.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(vantagemList);
        }
    }
}
