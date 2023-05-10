package com.example.backend.model.services;

import com.example.backend.model.entities.Instituicao;
import com.example.backend.model.repositories.InstituicaoRepository;
import org.springframework.stereotype.Service;

@Service
public class InstituicaoService {
    private final InstituicaoRepository repository;

    public InstituicaoService(InstituicaoRepository repository) {
        this.repository = repository;
    }

    public Instituicao findInstituicao(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Instituicao não encontrado"));
    }
}
