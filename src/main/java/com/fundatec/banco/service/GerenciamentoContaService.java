package com.fundatec.banco.service;

import com.fundatec.banco.exception.ConflictException;
import com.fundatec.banco.exception.NotAllowedException;
import com.fundatec.banco.exception.ObjectNotFoundException;
import com.fundatec.banco.model.Banco;
import com.fundatec.banco.model.Conta;
import com.fundatec.banco.model.enums.StatusConta;
import com.fundatec.banco.model.Cliente;
import com.fundatec.banco.repository.GerenciamentoClienteRepository;
import com.fundatec.banco.repository.GerenciamentoContaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class GerenciamentoContaService {


    private final GerenciamentoContaRepository repository;
    private final GerenciamentoClienteRepository clienteRepository;
    private final GerenciamentoClienteService clienteService;
    private final GerenciamentoBancoService bancoService;


    public List<Conta> findAll() {
        if(repository.findAll().isEmpty()){
            throw new ObjectNotFoundException("nenhuma conta, o banco de dados está vazio");
        }
        return repository.findAll();
    }

    public Conta criarNovaConta(Conta conta, Integer idCliente, Integer idBanco) {
        Banco banco = bancoService.findById(idBanco);
        Cliente cliente = clienteService.findById(idCliente);
        String cfpTitular = cliente.getCpf();
        conta.setBanco(banco);
        conta.setCpfTitular(cfpTitular);
        conta.setSaldo(BigDecimal.ZERO);
        conta.setStatus(StatusConta.ATIVA);

        return repository.save(conta);
    }
    public Conta ativarConta(Integer id) throws RuntimeException {
        Conta conta = findById(id);
        if (conta.getStatus().equals(StatusConta.ATIVA)){
            throw new NotAllowedException("já está ativa");
        }
        conta.setStatus(StatusConta.ATIVA);
        return repository.save(conta);
    }

    public Conta desativarConta(Integer id){
        Conta conta = findById(id);
        if (conta.getStatus().equals(StatusConta.INATIVA)){
            throw new NotAllowedException("já está inativa");
        }
        conta.setStatus(StatusConta.INATIVA);
        return repository.save(conta);
    }

    public Conta criarConta(Conta conta) {
        if (repository.findAll().contains(conta)){
            throw new ConflictException("conta");
        }
        return repository.save(conta);
    }

    public Conta findById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Cliente"));
    }

    public Conta findByCpf(String cpfTitular) {
        return repository.findByCpfTitular(cpfTitular).orElseThrow(() -> new ObjectNotFoundException("Cliente"));
    }

    public Conta setTitular(Integer idConta, Integer idCliente) {
        Conta conta = findById(idConta);
        if (conta.getCpfTitular() == null) {
            Cliente cliente = clienteRepository.findById(idCliente).orElseThrow(() -> new ObjectNotFoundException("Cliente"));
            conta.setCpfTitular(cliente.getCpf());
            return conta;
        }
        throw new NotAllowedException("A conta em questão já possui um titular.");
    }

    public Conta alterarStatus(Integer id) {
        try {
            Conta conta = findById(id);
            if (conta.getStatus() == StatusConta.INATIVA) {
                return ativarConta(id);
            }
            return desativarConta(id);
        }
        catch(ObjectNotFoundException e){
            throw new ObjectNotFoundException("conta");
        }
    }

    public boolean verificarCredenciais(Integer idCliente, Integer idConta, String senha) {
        Cliente cliente = clienteRepository.findById(idCliente).orElseThrow(() -> new ObjectNotFoundException("cliente não encontrado"));
        Conta conta = repository.findById(idConta).orElseThrow(() -> new ObjectNotFoundException("Conta não encontrada"));
        if(Objects.equals(cliente.getCpf(), conta.getCpfTitular()) && Objects.equals(conta.getSenhaAcesso(), senha)) {
            return true;
        }
        return false;
    }
}