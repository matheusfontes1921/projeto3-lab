package com.example.backend.model.entities;

import com.example.backend.model.entities.users.Empresa;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor

@Getter
@Setter
@Table(name = "tb_vantagem")
public class Vantagem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String nome;
    @Column
    private String descricao;
    @Column
    private String foto;
    @Column
    private Integer custo;
    @ManyToOne
    private Empresa empresa;

    public Vantagem(String nome, String descricao, String foto, Integer custo, Empresa empresa) {
        this.nome = nome;
        this.descricao = descricao;
        this.foto = foto;
        this.custo = custo;
        this.empresa = empresa;
    }
}
