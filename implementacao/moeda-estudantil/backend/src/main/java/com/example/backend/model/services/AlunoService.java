package com.example.backend.model.services;

import com.example.backend.model.entities.users.Aluno;
import com.example.backend.model.repositories.AlunoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlunoService {

    private final AlunoRepository alunoRepository;

    public AlunoService(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    public ResponseEntity<?> salvarAluno(Aluno aluno) {
        if (alunoRepository.findById(aluno.getId()).isPresent()) {
            return ResponseEntity.badRequest().body("Aluno já cadastrado");
        } else {
            alunoRepository.save(aluno);
            return ResponseEntity.ok().body("Aluno cadastrado com sucesso");
        }
    }

    public ResponseEntity<?> listarAlunos() {
        List<Aluno> alunos = alunoRepository.findAll();
        if (alunos.isEmpty()) {
            return ResponseEntity.badRequest().body("Não há alunos cadastrados");
        } else {
            return ResponseEntity.ok().body(alunos);
        }
    }

    public ResponseEntity<?> findById(Long id) {
        Optional<Aluno> aluno = alunoRepository.findById(id);
        if (aluno.isPresent()) {
            Aluno objAluno = aluno.get();
            return ResponseEntity.ok().body(objAluno);
        } else {
            return ResponseEntity.badRequest().body("Aluno não encontrado");
        }
    }

    public ResponseEntity<?> atualizarAluno(Long id, Aluno alunoAtualizado) {
        var aluno = alunoRepository.findById(id);
        if (aluno.isPresent()) {
            var alunoobj = aluno.get();
            alunoobj.setNome(alunoAtualizado.getNome());
            alunoobj.setEmail(alunoAtualizado.getEmail());
            alunoobj.setSenha(alunoAtualizado.getSenha());
            alunoobj.setSaldo(alunoAtualizado.getSaldo());
            alunoobj.setCpf(alunoAtualizado.getCpf());
            alunoobj.setRg(alunoAtualizado.getRg());
            alunoobj.setEndereco(alunoAtualizado.getEndereco());
            alunoobj.setInstituicao(alunoAtualizado.getInstituicao());
            alunoobj.setCurso(alunoAtualizado.getCurso());
            alunoRepository.save(alunoobj);
            return ResponseEntity.ok().body("Aluno atualizado com sucesso");
        } else return ResponseEntity.badRequest().body("Aluno não encontrado");
    }

    public ResponseEntity<?> removerAluno(Long id) {
        Optional<Aluno> aluno = alunoRepository.findById(id);
        if (aluno.isPresent()) {
            alunoRepository.deleteById(id);
            return ResponseEntity.ok().body("Aluno removido com sucesso");
        } else {
            return ResponseEntity.badRequest().body("Aluno não encontrado");
        }
    }

    public ResponseEntity<?> consultaSaldo(Long id) {
        Optional<Aluno> aluno = alunoRepository.findById(id);
        if (aluno.isPresent()) {
            Aluno objAluno = aluno.get();
            return ResponseEntity.ok().body(objAluno.getSaldo());
        } else {
            return ResponseEntity.badRequest().body("Aluno não encontrado");
        }
    }

    public ResponseEntity<?> listarTransferencias(Long id) {
        Optional<Aluno> aluno = alunoRepository.findById(id);
        if (aluno.isPresent()) {
            Aluno objAluno = aluno.get();
            return ResponseEntity.ok().body(objAluno.getTransfers());
        } else {
            return ResponseEntity.badRequest().body("Aluno não encontrado");
        }
    }

}
