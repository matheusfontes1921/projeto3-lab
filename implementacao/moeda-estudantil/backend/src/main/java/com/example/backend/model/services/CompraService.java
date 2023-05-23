package com.example.backend.model.services;

import com.example.backend.model.entities.Compra;
import com.example.backend.model.entities.Vantagem;
import com.example.backend.model.repositories.CompraRepository;
import com.example.backend.model.repositories.VantagemRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompraService {
    private final CompraRepository compraRepository;
    private final AlunoService alunoRepository;
    private final EmpresaService empresaRepository;
    private final VantagemRepository vantagemRepository;

    public CompraService(CompraRepository compraRepository, AlunoService alunoRepository, EmpresaService empresaRepository, VantagemRepository vantagemRepository) {
        this.compraRepository = compraRepository;
        this.alunoRepository = alunoRepository;
        this.empresaRepository = empresaRepository;
        this.vantagemRepository = vantagemRepository;
    }

    public Integer valorTotalCompra(Long id) {
        var compra = compraRepository.findById(id).orElseThrow(() -> new RuntimeException("Compra não encontrada"));
        return compra.getVantagens().stream().mapToInt(Vantagem::getCusto).sum();
    }

    public Compra save(Compra compra) {
        return compraRepository.save(compra);
    }

    public ResponseEntity<?> adicionarItem(Compra compra, Vantagem vantagem) {
        compra.getVantagens().add(vantagem);
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
            return compraRepository.save(compra);
        }

    }

    public ResponseEntity<List<Compra>> listarComprasDoAluno(Long idAluno) {
        var aluno = alunoRepository.findById(idAluno).orElseThrow(() -> new RuntimeException("Aluno não encontrado"));
        return ResponseEntity.ok(aluno.getCompraList());
    }

    public Compra findById(Long id) {
        return compraRepository.findById(id).orElseThrow(() -> new RuntimeException("Compra não encontrada"));
    }
}
