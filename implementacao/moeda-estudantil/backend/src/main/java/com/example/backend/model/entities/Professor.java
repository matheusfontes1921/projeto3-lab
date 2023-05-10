package com.example.backend.model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Table(name = "professor")
@Entity
public class   Professor extends Usuario {

    @Column(name = "cpf")
    private String cpf;

    @Column(name = "departamento")
    private String departamento;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "instituicao_id")

    private Instituicao instituicao;

    @OneToMany(mappedBy = "professor")
    private List<Transfer> transfers = new ArrayList<>();


    @Override
    public void login() {

    }

    public Professor(String nome, String email, String senha, Integer saldo, String cpf, String departamento, Instituicao instituicao) {
        super(nome, email, senha, saldo);
        this.cpf = cpf;
        this.departamento = departamento;
        this.instituicao = instituicao;
    }

    public Professor() {
        super();
    }


}
