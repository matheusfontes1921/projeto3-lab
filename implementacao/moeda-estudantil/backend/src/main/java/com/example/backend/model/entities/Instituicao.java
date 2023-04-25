package com.example.backend.model.entities;
import java.util.List;

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
    private Long id;

    @Column(name = "nome_do_curso")
    private String nomeDoCurso;

    @OneToMany(mappedBy = "instituicao", cascade = CascadeType.ALL)
    private List<Professor> professores;

    // getters e setters
}