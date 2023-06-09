package com.example.backend.model.entities.users;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public abstract class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    protected Long id;
    @Column
    protected String nome;
    @Column
    protected String email;
    @Column
    protected String senha;

    @Column
    protected Integer saldo;

    protected Usuario(String nome, String email, String senha, Integer saldo) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.saldo = saldo;
    }

    public Usuario() {}


    public void transferencia(Integer quantidade) {
        saldo += quantidade;
    }
}
