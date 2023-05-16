package com.example.backend.model.repositories;

import com.example.backend.model.entities.users.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long> {
    Professor findUsuarioByEmail(String email);
}
