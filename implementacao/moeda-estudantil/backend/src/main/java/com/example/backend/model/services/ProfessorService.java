package com.example.backend.model.services;

import com.example.backend.model.dto.TransferDTO;
import com.example.backend.model.entities.users.Professor;
import com.example.backend.model.repositories.ProfessorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
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
        List<Professor> professores = professorRepository.findAll();
        return professores.stream()
                .map(this::adicionarTransfersDTO)
                .collect(Collectors.toList());
    }

    private Professor adicionarTransfersDTO(Professor professor) {
        List<TransferDTO> transfersDTO = professor.getTransfers().stream()
                .map(transfer -> new TransferDTO(
                        transfer.getId(),
                        professor.getNome(),
                        transfer.getAluno().getNome(),
                        transfer.getValor(),
                        transfer.getDescricao()
                ))
                .collect(Collectors.toList());
        professor.setTransfersDTO(transfersDTO);
        return professor;
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
    public Professor save(Professor professor){
        return professorRepository.save(professor);
    }
}
