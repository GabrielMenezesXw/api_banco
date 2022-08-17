package com.fundatec.banco.model;

import com.fasterxml.jackson.annotation.*;
import com.fundatec.banco.exception.NotAllowedException;
import com.fundatec.banco.model.enums.StatusConta;
import com.fundatec.banco.model.enums.TipoConta;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;


@Entity
@Getter
@Setter
@Builder
@Inheritance(strategy = InheritanceType.JOINED)
@AllArgsConstructor
@NoArgsConstructor
public class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "conta_id")
    private Integer id;

    @Column(name = "cpf_titular", nullable = true, unique = true)
    private String cpfTitular;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JsonBackReference(value = "banco_conta")
    @JoinColumn(name = "banco_id", referencedColumnName = "banco_id")
    private Banco banco;
    @Column(name = "saldo")
    private BigDecimal saldo;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusConta status;

    @Column(name = "senha")
    private String senhaAcesso;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_conta")
    private TipoConta tipoConta;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "contaAcesso")
    @JsonManagedReference
    private List<Movimentacao> movimentacoes;

    @Column
    LocalDate dataUltimaMovimentacao;

    public Conta(Integer id, String cpfTitular, Banco banco, BigDecimal saldo, StatusConta status, String senhaAcesso, TipoConta tipoConta, List<Movimentacao> movimentacoes) {
        this.id = id;
        this.cpfTitular = cpfTitular;
        this.banco = banco;
        this.saldo = saldo;
        this.status = status;
        this.senhaAcesso = senhaAcesso;
        this.tipoConta = tipoConta;
        this.movimentacoes = movimentacoes;
    }

    public boolean checarStatus(StatusConta status){
        return StatusConta.INATIVA != status;
    }

    public void depositar(BigDecimal valor) {
        if (valor == null || valor.compareTo(BigDecimal.valueOf(0.0)) == 0 || valor.compareTo(BigDecimal.valueOf(0.0)) < 0) {
            throw new IllegalArgumentException("valor inválido, logo não pode ser depositado");
        } else {
            saldo = saldo.add(valor);
        }
    }

    public void sacar(BigDecimal valor) throws RuntimeException {
        if (valor == null || valor.compareTo(BigDecimal.ZERO) == 0 || valor.compareTo(BigDecimal.valueOf(0.0)) < 0) {
            throw new IllegalArgumentException("valor inválido, logo não pode ser sacado");
        } else if (valor.compareTo(saldo) > 0 && !this.tipoConta.equals(TipoConta.ESPECIAL)) {
            throw new NotAllowedException("o valor maior é que seu saldo");
        } else if (getSaldo().compareTo(BigDecimal.ZERO) == 0) {
            throw new NotAllowedException("pois seu saldo está zerado");
        } else {
            saldo = saldo.subtract(valor);
        }
    }
    public void gerarSaldoDiario(){
        if(this.tipoConta.equals(TipoConta.POUPANCA)){
            LocalDate dataMovimentacao = LocalDate.now();
            long diferencaTempo = DAYS.between(dataMovimentacao, dataUltimaMovimentacao);
            BigDecimal saldoAtual = this.saldo.multiply(BigDecimal.valueOf(this.tipoConta.getValor()));
            saldo.add(saldoAtual.multiply(BigDecimal.valueOf(diferencaTempo)));
            dataUltimaMovimentacao = dataMovimentacao;
        }
    }

    @PrePersist
    public void preInsert(){
        if(this.dataUltimaMovimentacao == null){
            this.dataUltimaMovimentacao = LocalDate.now();
        }
    }


}
