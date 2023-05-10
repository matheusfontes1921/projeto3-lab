package com.example.backend.model.repositories;

import com.example.backend.model.entities.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface AlunoRepository extends JpaRepository<Aluno,Long> {
    Aluno findUsuarioByEmail(String email);
}
