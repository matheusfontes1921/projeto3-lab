package com.example.backend.model.services;

import com.example.backend.model.entities.Empresa;
import com.example.backend.model.repositories.EmpresaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpresaService {

    private final EmpresaRepository empresaRepository;

    public EmpresaService(EmpresaRepository alunoRepository) {
        this.empresaRepository = alunoRepository;
    }

    public Empresa salvarEmpresa(Empresa aluno) {
        return empresaRepository.save(aluno);
    }

    public List<Empresa> listarEmpresas() {
        return empresaRepository.findAll();
    }

    public Optional<Empresa> buscarEmpresaPorId(Long id) {
        return empresaRepository.findById(id);
    }

    public void atualizarEmpresa(Empresa empresaAtualizada) {
        var empresa = empresaRepository.findById(empresaAtualizada.getId()).orElseThrow(() -> new RuntimeException("Empresa não encontrada"));
    }

    public void removerEmpresa(Long id) {
        empresaRepository.deleteById(id);
    }
}
