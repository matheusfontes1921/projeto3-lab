package com.example.backend.model.entities;

import com.example.backend.model.entities.users.Aluno;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "compras")
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference
    private Aluno aluno;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "compra_id")
    private final List<Vantagem> vantagens = new ArrayList<>();

    public Compra(Aluno aluno) {
        this.aluno = aluno;
    }

    public void addVantagem(Vantagem vantagem) {
        this.vantagens.add(vantagem);
    }
}
