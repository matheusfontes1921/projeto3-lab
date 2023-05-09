package com.example.backend.model.repositories;

import com.example.backend.model.entities.Instituicao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface InstituicaoRepository extends JpaRepository<Instituicao, Long> {
}
