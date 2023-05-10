package com.example.backend.model.repositories;

import com.example.backend.model.entities.Empresa;
import com.example.backend.model.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
    Empresa findUsuarioByEmail(String email);
}
