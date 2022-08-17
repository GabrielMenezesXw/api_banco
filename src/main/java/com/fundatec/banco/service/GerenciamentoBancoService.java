package com.fundatec.banco.service;

import com.fundatec.banco.exception.ConflictException;
import com.fundatec.banco.exception.ObjectNotFoundException;
import com.fundatec.banco.model.Banco;
import com.fundatec.banco.model.Conta;
import com.fundatec.banco.repository.GerenciamentoBancoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GerenciamentoBancoService {

    @Autowired
    private final GerenciamentoBancoRepository repository;

    public GerenciamentoBancoService(GerenciamentoBancoRepository repository) {
        this.repository = repository;
    }

    public Banco saveBanco(Banco banco) {
        if (repository.findAll().contains(banco)){
            throw new ConflictException("banco");
        }
        return repository.save(banco);
    }

    public List<Banco> findAll() {
        if(repository.findAll().isEmpty()){
            throw new ObjectNotFoundException("nenhum banco, o banco de dados está vazio");
        }
        return repository.findAll();
    }

    public Banco findById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Banco"));
    }

    public Conta getContaById(Banco banco, Integer contaId){
        List<Conta> contas = banco.getContas();

        return contas.stream()
                .filter(conta1 -> contaId.equals(conta1.getId()))
                .findFirst()
                .orElseThrow(()->new ObjectNotFoundException("Conta"));
    }

}