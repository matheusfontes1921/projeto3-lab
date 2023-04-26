package com.example.backend.model.entities;

import jakarta.persistence.*;

import java.util.UUID;

@MappedSuperclass
public abstract class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    protected UUID id;
    @Column
    protected String nome;
    @Column
    protected String email;
    @Column
    protected String senha;

    public abstract void login();
}
