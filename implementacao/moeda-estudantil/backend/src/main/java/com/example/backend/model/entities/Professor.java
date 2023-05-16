package com.example.backend.model.entities;

import com.example.backend.model.dto.TransferDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Table(name = "professor")
@Entity
@NoArgsConstructor
public class Professor extends Usuario {

    @Column(name = "cpf")
    private String cpf;

    @Column(name = "departamento")
    private String departamento;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "instituicao_id")

    private Instituicao instituicao;
    @JsonIgnore
    @OneToMany(mappedBy = "professor")
    private List<Transfer> transfers = new ArrayList<>();


    public Professor(String nome, String email, String senha, Integer saldo, String cpf, String departamento, Instituicao instituicao) {
        super(nome, email, senha, saldo);
        this.cpf = cpf;
        this.departamento = departamento;
        this.instituicao = instituicao;
    }

    public List<TransferDTO> getTransfersDTO() {
        List<TransferDTO> transfersDTO = new ArrayList<>();
        for (Transfer transfer : transfers) {
            String nomeAluno = transfer.getAluno().getNome();
            Integer valor = transfer.getValor();
            String descricao = transfer.getDescricao();

            TransferDTO transferDTO = new TransferDTO(id, getNome(), nomeAluno, valor, descricao);
            transfersDTO.add(transferDTO);
        }
        return transfersDTO;
    }

    public void setTransfersDTO(List<TransferDTO> transfersDTO) {
    }

    // ...
}














