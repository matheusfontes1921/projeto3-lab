package com.example.backend.controllers;

import com.example.backend.model.services.TransferService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/transferencia")
public class TransferController {

    private final TransferService meuService;

    public TransferController(TransferService service) {
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
