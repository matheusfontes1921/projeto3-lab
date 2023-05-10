package com.example.backend.model.repositories;

import com.example.backend.model.entities.Professor;
import com.example.backend.model.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long> {
    Professor findUsuarioByEmail(String email);
}
