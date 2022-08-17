package com.fundatec.banco.service;


import com.fundatec.banco.exception.ConflictException;
import com.fundatec.banco.exception.ObjectNotFoundException;
import com.fundatec.banco.model.Endereco;
import com.fundatec.banco.model.Cliente;
import com.fundatec.banco.repository.GerenciamentoClienteRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GerenciamentoClienteService {

    @Autowired
    private final GerenciamentoClienteRepository repository;
    @Autowired
    private final EnderecoService enderecoService;


    public Cliente findById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Cliente"));
    }


    public Iterable<Cliente> findAll() {
        if(repository.findAll().isEmpty()){
            throw new ObjectNotFoundException("nenhum cliente, o banco de dados está vazio");
        }
        return repository.findAll();
    }

    public Cliente saveCliente(Cliente cliente) {
        if (repository.findAll().contains(cliente)){
            throw new ConflictException("cliente");
        }
        return repository.save(cliente);
    }

    public void deleteById(Integer id) {
        if (!repository.findAll().contains(id)){
            throw new ObjectNotFoundException("cliente");
        }
        repository.deleteById(id);
    }

    public Cliente criarNovoCliente(Cliente cliente, Integer idEndereco) {
        if(!repository.findAll().contains(cliente)){
            throw new ObjectNotFoundException("cliente");
        }
        if(!repository.findAll().contains(idEndereco)){
            throw new ObjectNotFoundException("endereco");
        }
        Endereco endereco = enderecoService.findById(idEndereco);
        cliente.setEndereco(endereco);
        try {
            return repository.save(cliente);
        } catch (ConflictException e){
            throw new ConflictException("cliente");
        }
    }
}