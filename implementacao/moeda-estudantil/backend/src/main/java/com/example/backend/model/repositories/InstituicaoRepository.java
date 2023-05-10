package com.example.backend.model.repositories;

import com.example.backend.model.entities.Instituicao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface InstituicaoRepository extends JpaRepository<Instituicao, Long> {
}
