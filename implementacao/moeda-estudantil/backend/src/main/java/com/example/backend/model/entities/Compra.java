package com.example.backend.model.entities;

import com.example.backend.model.entities.users.Aluno;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;

@Entity
@Getter
@Table(name = "compras")

public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    @ManyToOne(fetch = FetchType.EAGER)
    private Aluno aluno;
    @Column
    @OneToMany(mappedBy = "compra")
    private List<Vantagem> vantagens;
}
