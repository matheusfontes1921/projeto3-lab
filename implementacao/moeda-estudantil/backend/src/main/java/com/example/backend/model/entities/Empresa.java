package com.example.backend.model.entities;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@AllArgsConstructor
@Getter
@Setter
public class Empresa extends Usuario {
    @Override
    public void login() {

    }

    public void setId(UUID id) {
        this.id = id;
    }

}
