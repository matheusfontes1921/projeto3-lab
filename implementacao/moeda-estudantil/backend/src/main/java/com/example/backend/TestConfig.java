package com.example.backend;

import com.example.backend.model.entities.Aluno;
import com.example.backend.model.entities.Instituicao;
import com.example.backend.model.entities.Professor;
import com.example.backend.model.repositories.AlunoRepository;
import com.example.backend.model.repositories.EmpresaRepository;
import com.example.backend.model.repositories.ProfessorRepository;
import org.springframework.boot.CommandLineRunner;

public class TestConfig implements CommandLineRunner {
    ProfessorRepository professorRepository;
    AlunoRepository alunoRepository;
    EmpresaRepository empresaRepository;

    public TestConfig(ProfessorRepository professorRepository, AlunoRepository alunoRepository, EmpresaRepository empresaRepository) {
        this.professorRepository = professorRepository;
        this.alunoRepository = alunoRepository;
        this.empresaRepository = empresaRepository;
    }

    @Override
    public void run(String... args) {
        Instituicao i1 = new Instituicao(null, "Puc");
        Professor professor = new Professor("Joao", "joao@gmail.com", "123", 350, "616161616", "ICEI", i1);
        Aluno aluno1 = new Aluno("Claudio", "claudio@gmail.com", "1234", 0, "154898-31", "1711546", "rua 6x1 Vespasiano", i1, "Enhenharia De Software");
    }
}
