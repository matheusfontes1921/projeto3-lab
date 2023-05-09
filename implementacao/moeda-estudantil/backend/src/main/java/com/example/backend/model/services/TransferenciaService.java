package com.example.backend.model.services;

import com.example.backend.model.entities.Aluno;
import com.example.backend.model.entities.Professor;
import com.example.backend.model.repositories.AlunoRepository;
import com.example.backend.model.repositories.ProfessorRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TransferenciaService {

    private final ProfessorRepository professorRepository;
    private final AlunoRepository alunoRepository;

    public TransferenciaService(ProfessorRepository professorRepository, AlunoRepository alunoRepository) {
        this.professorRepository = professorRepository;
        this.alunoRepository = alunoRepository;
    }

    public void transferirSaldo(Long idProfessor, Long idAluno, Integer valorTransferencia) {
        Professor professor = professorRepository.findById(idProfessor).orElseThrow(() -> new RuntimeException("Professor não encontrado"));
        Aluno aluno = alunoRepository.findById(idAluno).orElseThrow(() -> new RuntimeException("Aluno não encontrado"));

        if (professor.getSaldo().compareTo(valorTransferencia) >= 0) {
            professor.transferencia(-(valorTransferencia));
            aluno.transferencia(valorTransferencia);
            professorRepository.save(professor);
            alunoRepository.save(aluno);
        } else {
            throw new RuntimeException("Saldo do professor insuficiente para realizar a transferência");
        }
    }
}





