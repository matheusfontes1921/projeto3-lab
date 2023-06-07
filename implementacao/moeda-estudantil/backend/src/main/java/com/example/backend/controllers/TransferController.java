package com.example.backend.controllers;

import com.example.backend.model.dto.TransferDTO;
import com.example.backend.model.entities.Transfer;
import com.example.backend.model.services.TransferService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/transferencia")
public class TransferController {

    private final TransferService meuService;

    public TransferController(TransferService service) {
        this.meuService = service;
    }


    @PostMapping("/{idProfessor}/{idAluno}/{valor}/{descricao}")
    public ResponseEntity<String> transferirSaldo(
            @PathVariable Long idProfessor,
            @PathVariable Long idAluno,
            @PathVariable Integer valor,
            @PathVariable String descricao) {
        try {
            meuService.transferirSaldo(idProfessor, idAluno, valor, descricao);
            return ResponseEntity.ok("Transferência realizada com sucesso");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping
    public List<Transfer> getTransfers(){
        return meuService.getAllTransfers();
    }
}
