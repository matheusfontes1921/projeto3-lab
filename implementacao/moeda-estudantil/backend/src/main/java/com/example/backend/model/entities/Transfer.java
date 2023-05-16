package com.example.backend.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "transfers")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Transfer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_transacao")
    private  Long id;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "professor_id", referencedColumnName = "id")
    private Professor professor;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "aluno_id", referencedColumnName = "id")
    private Aluno aluno;

    @Column
    private Integer valor;
    @Column
    private String descricao;

    public Transfer(Professor professor, Aluno aluno, Integer valor, String descricao) {
        this.professor = professor;
        this.aluno = aluno;
        this.valor = valor;
        this.descricao = descricao;
    }
}
