package com.example.backend.controllers;

import com.example.backend.model.entities.Instituicao;
import com.example.backend.model.entities.Transfer;
import com.example.backend.model.entities.users.Aluno;
import com.example.backend.model.services.AlunoService;
import com.example.backend.model.services.InstituicaoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/alunos")
public class AlunoController {

    private final AlunoService alunoService;
    private final InstituicaoService instituicaoService;

    public AlunoController(AlunoService alunoService, InstituicaoService instituicaoService) {
        this.alunoService = alunoService;
        this.instituicaoService = instituicaoService;
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

    @GetMapping("/{id}")
    public ResponseEntity<Aluno> findById(@PathVariable Long id) {
        Optional<Aluno> aluno = alunoService.findById(id);
        return aluno.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Aluno> criarAluno(@RequestBody Aluno aluno) {
        Instituicao i = instituicaoService.findInstituicao(1L);
        aluno.setInstituicao(i);
        Aluno alunoCriado = alunoService.salvarAluno(aluno);
        return ResponseEntity.status(HttpStatus.CREATED).body(alunoCriado);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Aluno> atualizarAluno(@PathVariable Long id, @RequestBody Aluno alunoAtualizado) {
        Optional<Aluno> alunoExistente = alunoService.findById(id);

        if (alunoExistente.isPresent()) {
            Aluno aluno = alunoExistente.get();
            aluno.setCpf(alunoAtualizado.getCpf());
            aluno.setRg(alunoAtualizado.getRg());
            aluno.setEndereco(alunoAtualizado.getEndereco());
            aluno.setCurso(alunoAtualizado.getCurso());
            aluno.setSaldo(alunoAtualizado.getSaldo());

            alunoService.atualizarAluno(aluno);
            return ResponseEntity.ok(aluno);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/remover/{id}")
    public ResponseEntity<Void> removerAluno(@PathVariable Long id) {
        Optional<Aluno> aluno = alunoService.findById(id);

        if (aluno.isPresent()) {
            alunoService.removerAluno(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/saldo/{id}")
    public ResponseEntity<Integer> consultaSaldo(@PathVariable Long id) {
        Optional<Aluno> aluno = alunoService.findById(id);
        return aluno.map(value -> ResponseEntity.ok(value.getSaldo())).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/transferencias/{id}")
    public ResponseEntity<List<Transfer>> listarTransferencias(@PathVariable Long id) {
        var aluno = alunoService.findById(id).orElseThrow(() -> new RuntimeException("aluno não encontrado"));

        var transferList = aluno.getTransfers();
        if (transferList.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(transferList);
        }
    }
}

