package com.example.backend.controllers;

import com.example.backend.model.entities.Empresa;
import com.example.backend.model.services.EmpresaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("/empresas")
public class EmpresaController {

    private final EmpresaService empresaService;

    public EmpresaController(EmpresaService empresaService) {
        this.empresaService = empresaService;
    }

    @GetMapping
    public List<Empresa> listarEmpresas() {
        return empresaService.listarEmpresas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Empresa> buscarEmpresaPorId(@PathVariable Long id) {
        Optional<Empresa> empresa = empresaService.buscarEmpresaPorId(id);
        return empresa.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Empresa> salvarEmpresa(@RequestBody Empresa empresa) {
        Empresa novaEmpresa = empresaService.salvarEmpresa(empresa);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaEmpresa);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Empresa> atualizarEmpresa(@PathVariable Long id, @RequestBody Empresa empresaAtualizada) {
        Optional<Empresa> empresa = empresaService.buscarEmpresaPorId(id);
        if (empresa.isPresent()) {
            empresaService.atualizarEmpresa(empresaAtualizada);
            return ResponseEntity.ok(empresaAtualizada);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerEmpresa(@PathVariable Long id) {
        Optional<Empresa> empresa = empresaService.buscarEmpresaPorId(id);
        if (empresa.isPresent()) {
            empresaService.removerEmpresa(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
