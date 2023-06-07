package com.example.backend.controllers;

import com.example.backend.model.entities.Compra;
import com.example.backend.model.entities.Transfer;
import com.example.backend.model.entities.users.Aluno;
import com.example.backend.model.repositories.AlunoRepository;
import com.example.backend.model.services.PdfService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/extrato")
@CrossOrigin
public class PdfController {
    private final PdfService pdfService;
    private final AlunoRepository alunoRepository;

    public PdfController(PdfService pdfService, AlunoRepository alunoRepository) {
        this.pdfService = pdfService;
        this.alunoRepository = alunoRepository;
    }

    @GetMapping("/pdf/{id}")
    public void generateExtratoPdfHandler(HttpServletResponse response, @PathVariable Long id) throws IOException {
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=extrato.pdf");
        Optional<Aluno> aluno = alunoRepository.findById(id);
        if (aluno.isPresent()) {
            var alunoObj = aluno.get();
            List<Transfer> transfers = alunoObj.getTransfers();
            List<Compra> compras = aluno.get().getCompraList();
            pdfService.PdfGenerator(transfers, compras, response.getOutputStream(), id);
            response.flushBuffer();
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}
