package com.example.backend.model.repositories;

import com.example.backend.model.entities.Aluno;
import com.example.backend.model.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AlunoRepository extends JpaRepository<Aluno,Long> {

    Aluno findUsuarioByEmail(String email);
}
