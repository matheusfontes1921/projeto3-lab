package com.example.backend.model.repositories;

import com.example.backend.model.entities.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
}
