package com.fundatec.banco.service;

import com.fundatec.banco.exception.ConflictException;
import com.fundatec.banco.exception.ObjectNotFoundException;
import com.fundatec.banco.model.Endereco;
import com.fundatec.banco.repository.EnderecoRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EnderecoService {

    @Autowired
    private final EnderecoRepository repository;

    public Endereco saveEndereco(Endereco endereco) {
        if (repository.findAll().contains(endereco)){
            throw new ConflictException("endereco");
        }
        return repository.save(endereco);
    }

    public List<Endereco> findAll() {
        if(repository.findAll().isEmpty()){
            throw new ObjectNotFoundException("nenhum endereco, o banco de dados está vazio");
        }
        return repository.findAll();
    }

    public Endereco findById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException("endereço"));
    }


}