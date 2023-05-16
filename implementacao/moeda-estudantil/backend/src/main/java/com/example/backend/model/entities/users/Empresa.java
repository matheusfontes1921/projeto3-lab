package com.example.backend.model.entities.users;

import com.example.backend.model.entities.Vantagem;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@Getter
@Setter
@Table(name = "tb_empresa")
public class Empresa extends Usuario {
    public Empresa(String nome, String email, String senha, Integer saldo) {
        super(nome, email, senha, saldo);

    }

    @JsonIgnore
    @Setter(AccessLevel.NONE)
    @OneToMany(mappedBy = "empresa")
    private List<Vantagem> listaDeVantagens = new ArrayList<>();

    public Empresa() {

    }
}
