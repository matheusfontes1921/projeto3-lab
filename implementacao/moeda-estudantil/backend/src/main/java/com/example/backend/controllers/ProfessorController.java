package com.example.backend.controllers;

import com.example.backend.model.entities.Aluno;
import com.example.backend.model.entities.Professor;
import com.example.backend.model.entities.Professor;
import com.example.backend.model.entities.Transfer;
import com.example.backend.model.services.ProfessorService;
import com.example.backend.model.services.ProfessorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/professores")
public class ProfessorController {
    private final ProfessorService professorService;

    public ProfessorController(ProfessorService professorService) {
        this.professorService = professorService;
    }


    @GetMapping("/saldo/{id}")
    public ResponseEntity<Integer> consultaSaldo(@PathVariable Long id) {
        Optional<Professor> professor = professorService.findById(id);
        return professor.map(value -> ResponseEntity.ok(value.getSaldo())).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Professor>> listarProfessores() {
        List<Professor> professors = professorService.listaProfessores();
        if (professors.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(professors);
        }
    }

    @GetMapping("/busca/{id}")
    public ResponseEntity<Professor> buscarProfessorPorId(@PathVariable Long id) {
        Optional<Professor> professor = professorService.findById(id);
        return professor.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Professor> criarProfessor(@RequestBody Professor professor) {
        Professor professorCriado = professorService.save(professor);
        return ResponseEntity.status(HttpStatus.CREATED).body(professorCriado);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Professor> atualizarProfessor(@PathVariable Long id, @RequestBody Professor professorAtualizado) {
        Optional<Professor> professorExistente = professorService.findById(id);

        if (professorExistente.isPresent()) {
            professorAtualizado.setId(id);
            professorService.atualizar(professorAtualizado);
            return ResponseEntity.ok().build();
        } else
            return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/remover/{id}")
    public ResponseEntity<Void> removerProfessor(@PathVariable Long id) {
        Optional<Professor> professor = professorService.findById(id);

        if (professor.isPresent()) {
            professorService.delete(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/transferencias/{id}")
    public ResponseEntity<List<Transfer>> listarTransferencias(@PathVariable Long id) {
        Professor aluno = professorService.findById(id)
                .orElseThrow(() -> new RuntimeException("Professor não encontrado"));
        return ResponseEntity.ok(aluno.getTransfers());
    }
}
