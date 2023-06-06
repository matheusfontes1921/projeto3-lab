package com.example.backend.model.services;

import com.example.backend.model.entities.Compra;
import com.example.backend.model.entities.Vantagem;
import com.example.backend.model.entities.users.Aluno;
import com.example.backend.model.repositories.AlunoRepository;
import com.example.backend.model.repositories.CompraRepository;
import com.example.backend.model.repositories.VantagemRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompraService {
    private final CompraRepository compraRepository;
    private final AlunoRepository alunoRepository;
    private final EmpresaService empresaRepository;
    private final VantagemRepository vantagemRepository;
    private final EmailService emailService;

    public CompraService(CompraRepository compraRepository, AlunoRepository alunoRepository, EmpresaService empresaRepository, VantagemRepository vantagemRepository, EmailService emailService) {
        this.compraRepository = compraRepository;
        this.alunoRepository = alunoRepository;
        this.empresaRepository = empresaRepository;
        this.vantagemRepository = vantagemRepository;
        this.emailService = emailService;
    }

    public Integer valorTotalCompra(Long id) {
        var compra = compraRepository.findById(id).orElseThrow(() -> new RuntimeException("Compra não encontrada"));
        return compra.getVantagens().stream().mapToInt(Vantagem::getCusto).sum();
    }

    public Compra save(Compra compra) {
        return compraRepository.save(compra);
    }

    public ResponseEntity<?> adicionarItem(Compra compra, Vantagem vantagem) {
        compra.addVantagem(vantagem);
        vantagem.getEmpresa().getListaDeVantagens().remove(vantagem);
        empresaRepository.salvarEmpresa(vantagem.getEmpresa());
        compraRepository.save(compra);
        vantagemRepository.save(vantagem);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<?> removerItem(Compra compra, Vantagem vantagem) {
        compra.getVantagens().remove(vantagem);
        vantagem.getEmpresa().getListaDeVantagens().add(vantagem);
        empresaRepository.salvarEmpresa(vantagem.getEmpresa());
        compraRepository.save(compra);
        vantagemRepository.save(vantagem);
        return ResponseEntity.noContent().build();
    }

    public Compra finalizar(Long id) {
        var compra = compraRepository.findById(id).orElseThrow(() -> new RuntimeException("Compra não encontrada"));
        if (compra.getVantagens().isEmpty()) {
            throw new RuntimeException("Compra vazia");
        }
        if (compra.getAluno().getSaldo() < valorTotalCompra(compra.getId())) {
            throw new RuntimeException("Saldo insuficiente");
        } else {

            compra.getAluno().setSaldo(compra.getAluno().getSaldo() - valorTotalCompra(compra.getId()));
            compra.getAluno().getCompraList().add(compra);
            compraRepository.save(compra);
            var conteudoEmail = dadosEmail(compra);
            emailService.enviarEmail(compra.getAluno().getEmail(), "Compra realizada no SITE", conteudoEmail);
            emailService.enviarEmail(compra.getVantagens().get(0).getEmpresa().getEmail(), "Compra realizada no SITE", conteudoEmail);
            return compraRepository.saveAndFlush(compra);
        }

    }

    private String dadosEmail(Compra compra) {
        List<String> empresas = compra.getVantagens().stream().map(v -> v.getEmpresa().getNome()).toList();
        return "Foi feita uma compra na loja" + empresas +
                "no valor de" + valorTotalCompra(compra.getId()) + "\n\n" + "ID DA COMPRA :" + compra.getId() + "\n\n" + compra.getAluno().getNome() + "\nOBRIGADO POR COMPRAR NO SITE";


    }

    public ResponseEntity<List<Compra>> listarComprasDoAluno(Long idAluno) {
        var aluno = alunoRepository.findById(idAluno).orElseThrow(() -> new RuntimeException("Aluno não encontrado"));
        return ResponseEntity.ok(aluno.getCompraList());
    }

    public Compra findById(Long id) {
        return compraRepository.findById(id).orElseThrow(() -> new RuntimeException("Compra não encontrada"));
    }

    public Compra iniciarCompra(Long idAluno) {
        Aluno aluno = alunoRepository.findById(idAluno).orElseThrow(() -> new RuntimeException("Aluno não encontrado"));
        Compra compra = new Compra(aluno);
        aluno.getCompraList().add(compra);
        alunoRepository.save(aluno);
        compraRepository.save(compra);

        return compra;
    }
}
