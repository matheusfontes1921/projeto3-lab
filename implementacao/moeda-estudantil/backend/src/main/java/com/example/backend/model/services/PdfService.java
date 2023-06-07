package com.example.backend.model.services;

import com.example.backend.model.entities.Compra;
import com.example.backend.model.entities.Transfer;
import com.example.backend.model.entities.users.Aluno;
import com.example.backend.model.entities.users.Professor;
import com.example.backend.model.repositories.AlunoRepository;
import com.example.backend.model.repositories.ProfessorRepository;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.OutputStream;
import java.util.List;
import java.util.Optional;

@Service
public class PdfService {

    private final AlunoRepository alunoRepository;
    private final ProfessorRepository professorRepository;

    public PdfService(AlunoRepository alunoRepository, ProfessorRepository professorRepository) {
        this.alunoRepository = alunoRepository;
        this.professorRepository = professorRepository;
    }

    public ResponseEntity<?> PdfGenerator(List<Transfer> transfers, List<Compra> compras, OutputStream outputStream, Long id) {
        Optional<Aluno> aluno = alunoRepository.findById(id);
        if (aluno.isPresent()) {
            PdfWriter writer = new PdfWriter(outputStream);
            PdfDocument pdfDocument = new PdfDocument(writer);
            Document document = new Document(pdfDocument);

            Paragraph title = new Paragraph("Extrato do aluno " + aluno.get().getNome());
            document.add(title);
            document.add(new Paragraph("Ganhos: "));
            for (Transfer transfer : transfers) {
                Paragraph paragraph = new Paragraph("Id: " + transfer.getId() + " / Valor: " + transfer.getValor());
                document.add(paragraph);
            }
            document.add(new Paragraph("Perdas: "));
            for (Compra compra : compras) {
                Paragraph paragraph = new Paragraph("Id: " + compra.getId() + " / Valor: " + compra.getVantagens().get(0).getCusto());
                document.add(paragraph);
            }
            document.close();

        }
        return ResponseEntity.badRequest().body("Aluno não encontrado");
    }
    public ResponseEntity<?> PdfGeneratorProfessor(List<Transfer> transfers, OutputStream outputStream, Long id) {
        Optional<Professor> professor = professorRepository.findById(id);
        if (professor.isPresent()) {
            PdfWriter writer = new PdfWriter(outputStream);
            PdfDocument pdfDocument = new PdfDocument(writer);
            Document document = new Document(pdfDocument);

            Paragraph title = new Paragraph("Extrato do professor " + professor.get().getNome());
            document.add(title);
            document.add(new Paragraph("Ganhos: "));
            document.add(new Paragraph("Ganho inicial de 350!"));
            document.add(new Paragraph("Perdas: "));
            for (Transfer transfer : transfers) {
                Paragraph paragraph = new Paragraph("Id: " + transfer.getId() + " / Valor: " + transfer.getValor() + " / Transferência realizada para o aluno: " + transfer.getAluno().getNome());
                document.add(paragraph);
            }

            document.close();

        }
        return ResponseEntity.badRequest().body("Professor não encontrado");
    }
}
