package com.fundatec.banco.service;

import com.fundatec.banco.exception.ConflictException;
import com.fundatec.banco.exception.ObjectNotFoundException;
import com.fundatec.banco.model.Banco;
import com.fundatec.banco.model.Conta;
import com.fundatec.banco.repository.GerenciamentoBancoRepository;
import com.fundatec.banco.repository.GerenciamentoContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class GerenciamentoAdmService {

    private final GerenciamentoBancoRepository bancoRepository;

    private final GerenciamentoContaRepository contaRepository;

    @Autowired
    public GerenciamentoAdmService(GerenciamentoBancoRepository bancoRepository, GerenciamentoContaRepository contaRepository) {
        this.bancoRepository = bancoRepository;
        this.contaRepository = contaRepository;
    }

    public boolean verificarCredenciais(Integer idBanco, String senha) {
        Banco banco = bancoRepository.findById(idBanco).orElseThrow(() -> new ObjectNotFoundException("Banco não encontrado"));
        return Objects.equals(banco.getSenha(), senha);
    }

    public Conta alterarTipoConta(Integer id, Conta conta) {
        if (contaRepository.findAll().contains(conta)){
            throw new ConflictException("conta");
        }
        Optional<Conta> contadto = contaRepository.findById(id);
        return contaRepository.save(conta);
    }


}
