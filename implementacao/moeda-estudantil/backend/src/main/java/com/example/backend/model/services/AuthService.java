package com.example.backend.model.services;

import com.example.backend.model.dto.LoginDto;
import com.example.backend.model.entities.Aluno;
import com.example.backend.model.entities.Empresa;
import com.example.backend.model.entities.Professor;
import com.example.backend.model.repositories.AlunoRepository;
import com.example.backend.model.repositories.EmpresaRepository;
import com.example.backend.model.repositories.ProfessorRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AuthService {
    private final AlunoRepository alunoRepository;
    private final EmpresaRepository empresaRepository;
    private final ProfessorRepository professorRepository;

    public AuthService(AlunoRepository alunoRepository, EmpresaRepository empresaRepository, ProfessorRepository professorRepository) {
        this.alunoRepository = alunoRepository;
        this.empresaRepository = empresaRepository;
        this.professorRepository = professorRepository;
    }

    public ResponseEntity<?> authUser(LoginDto loginDto) {
        Aluno existingAluno = alunoRepository.findUsuarioByEmail(loginDto.getEmail());
        Empresa existingProfessor = empresaRepository.findUsuarioByEmail(loginDto.getEmail());
        Professor existingEmpresa = professorRepository.findUsuarioByEmail(loginDto.getEmail());

        String senha = loginDto.getPassword();

        if(existingAluno != null && existingAluno.getSenha().equals(senha)) {
            return ResponseEntity.ok(existingAluno);
        } else if(existingProfessor != null && existingProfessor.getSenha().equals(senha)) {
            return ResponseEntity.ok(existingProfessor);
        } else if(existingEmpresa != null && existingEmpresa.getSenha().equals(senha)) {
            return ResponseEntity.ok(existingEmpresa);
        } else {
            return ResponseEntity.badRequest().body("Email ou senha incorretos");
        }
    }
}
