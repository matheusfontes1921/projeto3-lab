package com.example.backend.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Empresa extends Usuario {
    @Id
    private UUID id;

    @Column(name = "nome_empresa")
    private String nome;
    @Column(name = "email_empresa")
    private String email;
    @JsonIgnore
    @Column(name = "senha_empresa")
    private String senha;


    @Override
    public void login() {

    }


}
