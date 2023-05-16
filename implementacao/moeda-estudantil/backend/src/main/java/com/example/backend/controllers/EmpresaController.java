package com.example.backend.controllers;

import com.example.backend.model.entities.Vantagem;
import com.example.backend.model.entities.users.Empresa;
import com.example.backend.model.services.EmpresaService;
import com.example.backend.model.services.VantagemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/empresas")
public class EmpresaController {

    private final EmpresaService empresaService;
    private final VantagemService vantagemService;

    public EmpresaController(EmpresaService empresaService, VantagemService vantagemService) {
        this.empresaService = empresaService;
        this.vantagemService = vantagemService;
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

    @PostMapping("/{idEmpresa}/{nome}/{descricao}/{foto}/{custo}")
    public ResponseEntity<Vantagem> cadastrarVantagem(@PathVariable Long idEmpresa,
                                                      @PathVariable String nome,
                                                      @PathVariable String descricao,
                                                      @PathVariable String foto,
                                                      @PathVariable Integer custo) {
        var empresa = empresaService.buscarEmpresaPorId(idEmpresa).orElseThrow(() -> new RuntimeException("Empresa não encontrada"));
        var vantagem = new Vantagem(nome, descricao, foto, custo, empresa);
        empresa.getListaDeVantagens().add(vantagem);
        empresaService.salvarEmpresa(empresa);
        vantagemService.salvar(vantagem);
        return ResponseEntity.status(HttpStatus.CREATED).body(vantagem);
    }

    @GetMapping("/vantagens/{id}")
    public ResponseEntity<List<Vantagem>> listarVantagensEmpresa(@PathVariable Long id) {
        var empresa = empresaService.buscarEmpresaPorId(id)
                .orElseThrow(() -> new RuntimeException("empresa não encontrada"));
        return ResponseEntity.ok(empresa.getListaDeVantagens());
    }
}
