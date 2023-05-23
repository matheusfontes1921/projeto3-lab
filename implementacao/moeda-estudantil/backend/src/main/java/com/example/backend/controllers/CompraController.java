package com.example.backend.controllers;

import com.example.backend.model.entities.Vantagem;
import com.example.backend.model.services.CompraService;
import com.example.backend.model.services.VantagemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/compras")
public class CompraController {
    private final CompraService compraService;
    private final VantagemService vantagemService;

    public CompraController(CompraService compraService, VantagemService vantagemService) {
        this.compraService = compraService;
        this.vantagemService = vantagemService;
    }

    @GetMapping("/aluno/{id})")
    public ResponseEntity<?> listarComprasDoAluno(@PathVariable Long id) {
        return ResponseEntity.ok(compraService.listarComprasDoAluno(id));
    }

    @PostMapping("/finalizar/{id}")
    public ResponseEntity<?> finalizarCompra(@PathVariable Long id) {
        return ResponseEntity.ok(compraService.finalizar(id));
    }

    @DeleteMapping("/apagarItem/{id}")
    public ResponseEntity<?> apagarItem(@PathVariable Long id, @RequestBody Long idItem) {
        var compra = compraService.findById(id);
        Vantagem item = vantagemService.findById(idItem);
        return ResponseEntity.ok(compraService.removerItem(compra, item));
    }

    @PostMapping("/adicionarItem/{id}")
    public ResponseEntity<?> adicionarItem(@PathVariable Long id, @RequestBody Long idItem) {
        var compra = compraService.findById(id);
        Vantagem item = vantagemService.findById(idItem);
        return ResponseEntity.ok(compraService.adicionarItem(compra, item));
    }

    @PostMapping("/iniciar")
    public ResponseEntity<?> iniciarCompra(@RequestBody Long idAluno) {
        return ResponseEntity.ok(compraService.iniciarCompra(idAluno));
    }
}

