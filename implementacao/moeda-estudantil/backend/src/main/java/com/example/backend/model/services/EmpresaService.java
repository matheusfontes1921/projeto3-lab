package com.example.backend.model.services;

import com.example.backend.model.entities.Empresa;
import com.example.backend.model.repositories.EmpresaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EmpresaService {

    private final EmpresaRepository alunoRepository;

    public EmpresaService(EmpresaRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    public Empresa salvarEmpresa(Empresa aluno) {
        return alunoRepository.save(aluno);
    }

    public List<Empresa> listarEmpresas() {
        return alunoRepository.findAll();
    }

    public Optional<Empresa> buscarEmpresaPorId(UUID id) {
        return alunoRepository.findById(String.valueOf(id));
    }

    public void atualizarEmpresa(Empresa aluno) {
        alunoRepository.save(aluno);
    }

    public void removerEmpresa(UUID id) {
        alunoRepository.deleteById(String.valueOf(id));
    }
}
