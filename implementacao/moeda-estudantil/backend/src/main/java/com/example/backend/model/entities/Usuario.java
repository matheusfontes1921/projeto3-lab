package com.example.backend.model.entities;

import jakarta.persistence.*;

import java.util.UUID;

@Inheritance
@Table(name = "tb_usuarios")
@Entity
public abstract class Usuario {
    @Id
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
