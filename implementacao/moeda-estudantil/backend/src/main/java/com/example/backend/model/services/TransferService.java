package com.example.backend.model.services;

import com.example.backend.model.entities.Aluno;
import com.example.backend.model.entities.Professor;
import com.example.backend.model.entities.Transfer;
import com.example.backend.model.repositories.AlunoRepository;
import com.example.backend.model.repositories.ProfessorRepository;
import com.example.backend.model.repositories.TransferRepository;
import org.springframework.stereotype.Service;

@Service
public class TransferService {

    private final ProfessorRepository professorRepository;
    private final AlunoRepository alunoRepository;
    private final TransferRepository transferRepository;

    public TransferService(ProfessorRepository professorRepository, AlunoRepository alunoRepository, TransferRepository transferRepository) {
        this.professorRepository = professorRepository;
        this.alunoRepository = alunoRepository;
        this.transferRepository = transferRepository;
    }

    public void transferirSaldo(Long idProfessor, Long idAluno, Integer valorTransferencia) {
        Professor professor = professorRepository.findById(idProfessor).orElseThrow(() -> new RuntimeException("Professor não encontrado"));
        Aluno aluno = alunoRepository.findById(idAluno).orElseThrow(() -> new RuntimeException("Aluno não encontrado"));

        if (professor.getSaldo().compareTo(valorTransferencia) >= 0) {
            Transfer transfer = new Transfer(professor, aluno, valorTransferencia);
            professor.setSaldo(professor.getSaldo() - valorTransferencia);
            aluno.setSaldo(aluno.getSaldo() + valorTransferencia);
            professor.getTransfers().add(transfer);
            aluno.getTransfers().add(transfer);
            alunoRepository.save(aluno);
            professorRepository.save(professor);
            transferRepository.save(transfer);
        } else {
            throw new RuntimeException("Saldo do professor insuficiente para realizar a transferência");
        }
    }
}





