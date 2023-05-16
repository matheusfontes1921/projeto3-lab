package com.example.backend.model.services;

import com.example.backend.model.entities.users.Aluno;
import com.example.backend.model.entities.users.Professor;
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

    public void transferirSaldo(Long idProfessor, Long idAluno, Integer valorTransferencia, String descricao) {
        Professor professor = professorRepository.findById(idProfessor).orElseThrow(() -> new RuntimeException("Professor não encontrado"));
        Aluno aluno = alunoRepository.findById(idAluno).orElseThrow(() -> new RuntimeException("Aluno não encontrado"));

        if (professor.getSaldo().compareTo(valorTransferencia) >= 0) {
            Transfer transfer = new Transfer(professor, aluno, valorTransferencia, descricao);
            transferir(transfer);
            alunoRepository.save(aluno);
            professorRepository.save(professor);
            transferRepository.save(transfer);
        } else {
            throw new RuntimeException("Saldo do professor insuficiente para realizar a transferência");
        }
    }


    private void transferir(Transfer transfer) {
        Professor professor = transfer.getProfessor();
        Aluno aluno = transfer.getAluno();
        var valor = transfer.getValor();
        var alunoTransfers = aluno.getTransfers();
        var professorTransfers = professor.getTransfers();
        var valorPositivo = convertePositivo(valor);
        transfer.setValor(valorPositivo);
        professor.setSaldo(professor.getSaldo() - transfer.getValor());
        professorTransfers.add(transfer);
        alunoTransfers.add(transfer);
        aluno.setSaldo(aluno.getSaldo() + transfer.getValor());
    }

    private Integer convertePositivo(Integer valor) {
        return Math.abs(valor);
    }
}




