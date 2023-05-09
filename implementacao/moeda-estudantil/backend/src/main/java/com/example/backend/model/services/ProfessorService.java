package com.example.backend.model.services;

import com.example.backend.model.entities.Professor;
import com.example.backend.model.repositories.ProfessorRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ProfessorService {
    private final ProfessorRepository professorRepository;

    public ProfessorService(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }

    public Optional<Professor> findById(Long id) {
        return professorRepository.findById(id);
    }

    public void delete(Long id) {
        professorRepository.deleteById(id);
    }

    public List<Professor> listaProfessores() {
        return professorRepository.findAll();
    }

    public void atualizar(Professor professorAtualizado) {
        Professor professor = professorRepository.findById(professorAtualizado.getId())
                .orElseThrow(() -> new RuntimeException("Professor não encontrado"));

        professor.setNome(professorAtualizado.getNome());
        professor.setEmail(professorAtualizado.getEmail());
        professor.setSenha(professorAtualizado.getSenha());
        professor.setSaldo(professorAtualizado.getSaldo());
        professor.setCpf(professorAtualizado.getCpf());
        professor.setDepartamento(professorAtualizado.getDepartamento());
        professor.setInstituicao(professorAtualizado.getInstituicao());
        professorRepository.save(professor);
    }
}
