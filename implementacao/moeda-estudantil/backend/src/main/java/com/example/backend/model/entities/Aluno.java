package com.example.backend.model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="aluno")
public class Aluno extends Usuario {

    @Column(name = "cpf")
    private String cpf;

    @Column(name = "rg")
    private String rg;

    @Column(name = "endereco")
    private String endereco;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "instituicao_id")
    private Instituicao instituicao;

    @Column(name = "curso")
    private String curso;

    public Aluno(String nome, String email, String senha, Integer saldo, String cpf, String rg, String endereco, Instituicao instituicao, String curso) {
        super(nome, email, senha, saldo);
        this.cpf = cpf;
        this.rg = rg;
        this.endereco = endereco;
        this.instituicao = instituicao;
        this.curso = curso;
    }

    @Override
    public void login() {

    }
}