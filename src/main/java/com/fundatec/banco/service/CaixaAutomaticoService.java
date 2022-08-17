package com.fundatec.banco.service;

import com.fundatec.banco.exception.NotAllowedException;
import com.fundatec.banco.exception.ObjectNotFoundException;
import com.fundatec.banco.model.Movimentacao;
import com.fundatec.banco.model.Conta;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class CaixaAutomaticoService {

    private final MovimentacaoService service;

    public BigDecimal consultarSaldo(Conta conta) {
        if(!conta.checarStatus(conta.getStatus())){
            throw new NotAllowedException("a conta em questão está inativa");
        }else if (Objects.isNull(conta.getId())){
            throw new ObjectNotFoundException("conta, reveja a informação");
        }
        return conta.getSaldo();
    }

    public List<Movimentacao> consultarExtrato(Conta conta) {
        if(!conta.checarStatus(conta.getStatus())){
            throw new NotAllowedException("a conta em questão está inativa");
        }else if (Objects.isNull(conta.getId())){
            throw new ObjectNotFoundException("conta, reveja a informação");
        }
        return conta.getMovimentacoes();
    }

    public void depositar(Conta conta, BigDecimal valor) {
        conta.gerarSaldoDiario();
        if(!conta.checarStatus(conta.getStatus())){
            throw new NotAllowedException("a conta em questão está inativa");
        }else if (Objects.isNull(conta.getId())){
            throw new ObjectNotFoundException("conta, reveja a informação");
        }
        Movimentacao novaMovimentacao = gerarDadosParaMovimentacao(conta, valor);
        BigDecimal saldo = conta.getSaldo();
        conta.setSaldo(saldo.add(valor));
        addMovimentacao(novaMovimentacao);
    }

    public void sacar(Conta conta, BigDecimal valor) {
        conta.gerarSaldoDiario();
        if(!conta.checarStatus(conta.getStatus())){
            throw new NotAllowedException("a conta em questão está inativa");
        }else if (Objects.isNull(conta.getId())){
            throw new ObjectNotFoundException("conta, reveja a informação");
        }
        Movimentacao novaMovimentacao = gerarDadosParaMovimentacao(conta, valor);
        BigDecimal saldo = conta.getSaldo();
        conta.setSaldo(saldo.subtract(valor));
        addMovimentacao(novaMovimentacao);
    }

    public void addMovimentacao(Movimentacao novaMovimentacao) {
        service.saveMovimentacao(Movimentacao.builder()
                .id(novaMovimentacao.getId())
                .contaAcesso(novaMovimentacao.getContaAcesso())
                .valor(novaMovimentacao.getValor())
                .dataMovimentacao(novaMovimentacao.getDataMovimentacao())
                .build());
    }

    public Movimentacao transferir(Conta contaOrigem, Conta contaDestino, BigDecimal valor) {
        if( !contaOrigem.checarStatus(contaOrigem.getStatus()) || !contaDestino.checarStatus(contaDestino.getStatus()) ){
            throw new NotAllowedException("operação não permitida, pois uma das contas está inativa");
        }else if (Objects.isNull(contaOrigem.getId()) || Objects.isNull(contaDestino.getId()) ){
            throw new ObjectNotFoundException("umas das contas, reveja a informação");
        }
        Movimentacao novaMovimentacao = gerarDadosParaMovimentacao(contaOrigem,valor);
        contaOrigem.gerarSaldoDiario();
        contaOrigem.sacar(valor);
        contaDestino.gerarSaldoDiario();
        contaDestino.depositar(valor);
        return service.saveMovimentacao(Movimentacao
                .builder()
                .id(novaMovimentacao.getId())
                .valor(novaMovimentacao.getValor())
                .contaAcesso(novaMovimentacao.getContaAcesso())
                .dataMovimentacao(novaMovimentacao.getDataMovimentacao())
                .build());
    }

    public Movimentacao gerarDadosParaMovimentacao(Conta conta, BigDecimal valor){
        Movimentacao movimentacao = new Movimentacao();
        movimentacao.setValor(valor);
        movimentacao.setContaAcesso(conta);
        movimentacao.setDataMovimentacao(LocalDateTime.now());
        return movimentacao;
    }
}