package com.example.backend.model.repositories;

import com.example.backend.model.entities.users.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
    Empresa findUsuarioByEmail(String email);
}
