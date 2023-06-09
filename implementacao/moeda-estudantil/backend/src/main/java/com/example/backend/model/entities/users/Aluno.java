package com.example.backend.model.entities.users;

import com.example.backend.model.entities.Compra;
import com.example.backend.model.entities.Instituicao;
import com.example.backend.model.entities.Transfer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "aluno")
public class Aluno extends Usuario {
    @Column(name = "cpf")
    private String cpf;

    @Column(name = "rg")
    private String rg;

    @Column(name = "endereco")
    private String endereco;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "instituicao_id")
    private Instituicao instituicao;

    @Column(name = "curso")
    private String curso;

    @OneToMany(mappedBy = "aluno")
    private final List<Transfer> transfers = new ArrayList<>();

    @OneToMany(mappedBy = "aluno")
    @JsonManagedReference
    private final List<Compra> compraList = new ArrayList<>();

    public Aluno(String nome, String email, String senha, Integer saldo, String cpf, String rg, String endereco, Instituicao instituicao, String curso) {
        super(nome, email, senha, saldo);
        this.cpf = cpf;
        this.rg = rg;
        this.endereco = endereco;
        this.instituicao = instituicao;
        this.curso = curso;
    }
}