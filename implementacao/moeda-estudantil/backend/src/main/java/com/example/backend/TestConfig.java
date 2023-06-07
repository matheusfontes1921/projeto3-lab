package com.example.backend;

import com.example.backend.model.entities.users.Aluno;
import com.example.backend.model.entities.Instituicao;
import com.example.backend.model.entities.users.Empresa;
import com.example.backend.model.entities.users.Professor;
import com.example.backend.model.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {
    private final ProfessorRepository professorRepository;
    private final AlunoRepository alunoRepository;
    private final EmpresaRepository empresaRepository;
    private final InstituicaoRepository instituicaoRepository;
    private final VantagemRepository vantagemRepository;

    public TestConfig(ProfessorRepository professorRepository, AlunoRepository alunoRepository, EmpresaRepository empresaRepository, InstituicaoRepository instituicaoRepository, VantagemRepository vantagemRepository) {
        this.professorRepository = professorRepository;
        this.alunoRepository = alunoRepository;
        this.empresaRepository = empresaRepository;
        this.instituicaoRepository = instituicaoRepository;
        this.vantagemRepository = vantagemRepository;
    }

    @Override
    public void run(String... args) {
        Instituicao i1 = new Instituicao(null, "Puc");
        Professor professor = new Professor("Joao", "joao@gmail.com", "123", 350, "616161616", "ICEI", i1);
        Aluno aluno1 = new Aluno("Claudio", "lucassr614@gmail.com", "1234", 0, "154898-31", "1711546", "rua 6x1 Vespasiano", i1, "Enhenharia De Software");
        Empresa empresa = new Empresa("SUPERMECADOS BH", "matheusfontes1921@gmail.com", "1921", 0);
        Aluno aluno2 = new Aluno("Marlon", "marlon1921@gmail.com", "1921", 0, "51818181", "1711546", "rua 6x1", i1, "Enhenharia De Software");


        empresaRepository.save(empresa);
        instituicaoRepository.save(i1);
        professorRepository.save(professor);
//        alunoRepository.save(aluno1);
        alunoRepository.saveAll(Arrays.asList(aluno1,aluno2));

    }
}
