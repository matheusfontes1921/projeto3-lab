package com.example.backend.model.repositories;

import com.example.backend.model.entities.Professor;
import com.example.backend.model.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {
    Professor findUsuarioByEmail(String email);
}
