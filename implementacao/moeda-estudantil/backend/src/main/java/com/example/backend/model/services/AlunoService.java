package com.example.backend.model.services;

import com.example.backend.model.entities.Aluno;
import com.example.backend.model.repositories.AlunoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AlunoService {

    private final AlunoRepository alunoRepository;

    public AlunoService(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    public Aluno salvarAluno(Aluno aluno) {
        return alunoRepository.save(aluno);
    }

    public List<Aluno> listarAlunos() {
        return alunoRepository.findAll();
    }

    public Optional<Aluno> buscarAlunoPorId(UUID id) {
        return alunoRepository.findById(id);
    }

    public void atualizarAluno(Aluno alunoAtualizado) {
        Aluno aluno = alunoRepository.findById(alunoAtualizado.getId())
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado"));
        aluno.setNome(alunoAtualizado.getNome());
        aluno.setEmail(alunoAtualizado.getEmail());
        aluno.setSenha(alunoAtualizado.getSenha());
        aluno.setSaldo(alunoAtualizado.getSaldo());
        aluno.setCpf(alunoAtualizado.getCpf());
        aluno.setRg(alunoAtualizado.getRg());
        aluno.setEndereco(alunoAtualizado.getEndereco());
        aluno.setInstituicao(alunoAtualizado.getInstituicao());
        aluno.setCurso(alunoAtualizado.getCurso());
        alunoRepository.save(aluno);
    }

    public void removerAluno(UUID id) {
        alunoRepository.deleteById(id);
    }
}