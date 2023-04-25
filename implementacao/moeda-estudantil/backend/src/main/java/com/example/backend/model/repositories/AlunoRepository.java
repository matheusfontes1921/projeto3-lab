package com.example.backend.model.repositories;

import com.example.backend.model.entities.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<Aluno,String> {
}
