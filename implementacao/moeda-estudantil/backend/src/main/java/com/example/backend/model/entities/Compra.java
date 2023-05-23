package com.example.backend.model.entities;

import com.example.backend.model.entities.users.Aluno;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name = "compras")

public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private Aluno aluno;
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "compra_id")
    private final List<Vantagem> vantagens = new ArrayList<>();
}
