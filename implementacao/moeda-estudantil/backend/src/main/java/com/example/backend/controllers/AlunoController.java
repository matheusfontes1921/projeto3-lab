package com.example.backend.controllers;

import com.example.backend.model.entities.users.Aluno;
import com.example.backend.model.services.AlunoService;
import com.example.backend.model.services.InstituicaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/alunos")
public class AlunoController {

    private final AlunoService alunoService;

    public AlunoController(AlunoService alunoService, InstituicaoService instituicaoService) {
        this.alunoService = alunoService;
    }

    @GetMapping
    public ResponseEntity<?> listarAlunos() {
        return alunoService.listarAlunos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return alunoService.findById(id);
    }

    @PostMapping
    public ResponseEntity<?> criarAluno(@RequestBody Aluno aluno) {
        return alunoService.salvarAluno(aluno);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<?> atualizarAluno(@PathVariable Long id, @RequestBody Aluno alunoAtualizado) {
        return alunoService.atualizarAluno(id, alunoAtualizado);
    }

    @DeleteMapping("/remover/{id}")
    public ResponseEntity<?> removerAluno(@PathVariable Long id) {
        return alunoService.removerAluno(id);
    }

    @GetMapping("/saldo/{id}")
    public ResponseEntity<?> consultaSaldo(@PathVariable Long id) {
        return alunoService.consultaSaldo(id);
    }

    @GetMapping("/transferencias/{id}")
    public ResponseEntity<?> listarTransferencias(@PathVariable Long id) {
        return alunoService.listarTransferencias(id);
    }
}

