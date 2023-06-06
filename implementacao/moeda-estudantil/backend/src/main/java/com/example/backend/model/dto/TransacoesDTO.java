package com.example.backend.model.dto;

import com.example.backend.model.entities.Compra;
import com.example.backend.model.entities.Transfer;

import java.util.List;

public record TransacoesDTO(List<Transfer> transferencias, List<Compra> compras) {
}
