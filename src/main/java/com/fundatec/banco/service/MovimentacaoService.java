package com.fundatec.banco.service;

import com.fundatec.banco.exception.ObjectNotFoundException;
import com.fundatec.banco.model.Banco;
import com.fundatec.banco.model.Movimentacao;
import com.fundatec.banco.model.Conta;
import com.fundatec.banco.repository.GerenciamentoBancoRepository;
import com.fundatec.banco.repository.MovimentacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovimentacaoService {

    private final MovimentacaoRepository repository;
    private final GerenciamentoBancoRepository bancoRepository;
    private final GerenciamentoBancoService bancoService;

    @Autowired
    public MovimentacaoService(MovimentacaoRepository repository, GerenciamentoBancoRepository bancoRepository, GerenciamentoBancoService bancoService) {
        this.repository = repository;
        this.bancoRepository = bancoRepository;
        this.bancoService = bancoService;
    }

    public Movimentacao findById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Movimentação"));
    }

    public List<Movimentacao> findAllMovimentacoes() {
        if(repository.findAll().isEmpty()){
            throw new ObjectNotFoundException("nenhuma movimentação, o banco de dados está vazio");
        }
        return repository.findAll();
    }

    public Movimentacao saveMovimentacao(Movimentacao movimentacao) {
        return repository.save(movimentacao);
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    public List<Movimentacao> verificarMovimentacoesPorConta(Integer idBanco, Integer idConta) {
        Banco banco = bancoRepository.findById(idBanco).orElseThrow(() -> new ObjectNotFoundException("Banco não encontrado"));
        Conta conta = bancoService.getContaById(banco, idConta);
        return conta.getMovimentacoes();
    }
}