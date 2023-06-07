package com.example.backend.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransferDTO {
    private Long id;
    private String nomeProfessor;
    private String nomeAluno;
    private Integer valor;
    private String descricao;

    public TransferDTO(Long id, String nomeProfessor, String nomeAluno, Integer valor, String descricao) {
        this.id = id;
        this.nomeProfessor = nomeProfessor;
        this.nomeAluno = nomeAluno;
        this.valor = valor;
        this.descricao = descricao;
    }
    public TransferDTO(){}

    // getters e setters
}

