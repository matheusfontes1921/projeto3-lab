package com.example.backend.controllers;

import com.example.backend.model.entities.Compra;
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
        Compra compra = compraService.finalizar(id);
        return ResponseEntity.ok().body(compra.getId());
    }

    @DeleteMapping("/apagarItem/{id}/{idItem}")
    public ResponseEntity<?> apagarItem(@PathVariable Long id, @PathVariable Long idItem) {
        var compra = compraService.findById(id);
        Vantagem item = vantagemService.findById(idItem);
        compraService.removerItem(compra, item);
        return ResponseEntity.ok().body(compra.getId());
    }

    @PostMapping("/adicionarItem/{id}")
    public ResponseEntity<?> adicionarItem(@PathVariable Long id, @RequestBody Long idItem) {
        var compra = compraService.findById(id);
        Vantagem item = vantagemService.findById(idItem);
        return ResponseEntity.ok(compraService.adicionarItem(compra, item));
    }

    @PostMapping("/iniciar")
    public ResponseEntity<?> iniciarCompra(@RequestBody Long idAluno) {
        Compra compra = compraService.iniciarCompra(idAluno);
        return ResponseEntity.ok().body(compra.getId());
    }
}

