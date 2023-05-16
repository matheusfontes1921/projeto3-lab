package com.example.backend.model.entities;

import java.util.ArrayList;
import java.util.List;

import com.example.backend.model.entities.users.Professor;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="instituicao")
@Entity
public class Instituicao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nome_do_curso")
    private String nomeDoCurso;

    @OneToMany(mappedBy = "instituicao")
    @JsonIgnore
    private final List<Professor> professores = new ArrayList<>();

    // getters e setters
}