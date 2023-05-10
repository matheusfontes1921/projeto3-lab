package com.example.backend.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "transfers")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Transfer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_transacao")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "professor_id",referencedColumnName = "id")
    Professor professor;

    @ManyToOne
    @JoinColumn(name = "aluno_id",referencedColumnName = "id")
    Aluno aluno;
}
