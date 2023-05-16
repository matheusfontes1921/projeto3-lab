package com.example.backend.model.services;

import com.example.backend.model.entities.Vantagem;
import com.example.backend.model.repositories.VantagemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VantagemService {
    private final VantagemRepository vantagemRepository;

    public VantagemService(VantagemRepository vantagemRepository) {
        this.vantagemRepository = vantagemRepository;
    }


    public void salvar(Vantagem vantagem) {
        vantagemRepository.save(vantagem);
    }

    public List<Vantagem> findAll() {
        return vantagemRepository.findAll();
    }
}
