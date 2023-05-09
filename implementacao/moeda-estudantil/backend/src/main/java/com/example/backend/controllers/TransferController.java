package com.example.backend.controllers;

import com.example.backend.model.services.TransferenciaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@CrossOrigin
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
