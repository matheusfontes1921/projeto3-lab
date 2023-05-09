package com.example.backend.model.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Instituicao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private UUID id;

    @Column(name = "nome_do_curso")
    private String nomeDoCurso;

    @OneToMany(mappedBy = "instituicao", cascade = CascadeType.ALL)
    private final List<Professor> professores = new ArrayList<>();

    // getters e setters
}