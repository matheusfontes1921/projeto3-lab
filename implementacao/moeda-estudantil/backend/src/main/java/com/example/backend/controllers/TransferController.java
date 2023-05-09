package com.example.backend.controllers;

import com.example.backend.model.services.TransferenciaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/transferencia")
public class TransferController {

    private final TransferenciaService meuService;

    public TransferController(TransferenciaService service) {
        this.meuService = service;
    }

    @PostMapping("/{idProfessor}/{idAluno}/{valor}")
    public ResponseEntity<String> transferirSaldo(
            @PathVariable Long idProfessor,
            @PathVariable Long idAluno,
            @PathVariable Integer valor) {
        try {
            meuService.transferirSaldo(idProfessor, idAluno, valor);
            return ResponseEntity.ok("Transferência realizada com sucesso");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
