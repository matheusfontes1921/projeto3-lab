package com.example.backend.controllers;

import com.example.backend.model.entities.Aluno;
import com.example.backend.model.services.AlunoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("/alunos")
public class AlunoController {

    private final AlunoService alunoService;

    public AlunoController(AlunoService alunoService) {
        this.alunoService = alunoService;
    }

    @GetMapping
    public ResponseEntity<List<Aluno>> listarAlunos() {
        List<Aluno> alunos = alunoService.listarAlunos();
        if (alunos.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(alunos);
        }
    }

    @GetMapping("busca/{id}")
    public ResponseEntity<Aluno> buscarAlunoPorId(@PathVariable Long id) {
        Optional<Aluno> aluno = alunoService.buscarAlunoPorId(id);
        return aluno.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Aluno> criarAluno(@RequestBody Aluno aluno) {
        Aluno alunoCriado = alunoService.salvarAluno(aluno);
        return ResponseEntity.status(HttpStatus.CREATED).body(alunoCriado);
    }

    @PutMapping("atualizar/{id}")
    public ResponseEntity<Aluno> atualizarAluno(@PathVariable Long id, @RequestBody Aluno alunoAtualizado) {
        Optional<Aluno> alunoExistente = alunoService.buscarAlunoPorId(id);

        if (alunoExistente.isPresent()) {
            Aluno aluno = alunoExistente.get();
            aluno.setCpf(alunoAtualizado.getCpf());
            aluno.setRg(alunoAtualizado.getRg());
            aluno.setEndereco(alunoAtualizado.getEndereco());
            aluno.setInstituicao(alunoAtualizado.getInstituicao());
            aluno.setCurso(alunoAtualizado.getCurso());
            aluno.setSaldo(alunoAtualizado.getSaldo());

            alunoService.atualizarAluno(aluno);
            return ResponseEntity.ok(aluno);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("remover/{id}")
    public ResponseEntity<Void> removerAluno(@PathVariable Long id) {
        Optional<Aluno> aluno = alunoService.buscarAlunoPorId(id);

        if (aluno.isPresent()) {
            alunoService.removerAluno(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
