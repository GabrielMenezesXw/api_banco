package com.fundatec.banco.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fundatec.banco.model.enums.TipoOperacao;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Builder
@Getter
@Setter
@Table(name = "tb_movimentacoes")
@NoArgsConstructor
@AllArgsConstructor
public class Movimentacao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "conta_id")
    @JsonBackReference
    private Conta contaAcesso;

    @Column(name = "operacao")
    private TipoOperacao tipoOperacao;

    @Column(name = "valor", nullable = false)
    private BigDecimal valor;

    @Column(name = "data_movimentacao", nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dataMovimentacao;

    public Movimentacao(Conta contaAcesso, TipoOperacao tipoOperacao, BigDecimal valor, LocalDateTime dataMovimentacao) {
        this.contaAcesso = contaAcesso;
        this.tipoOperacao = tipoOperacao;
        this.valor = valor;
        this.dataMovimentacao = dataMovimentacao;
    }

    @Override
    public String toString() {
        return "Movimentacao{" +
                "id=" + id +
                ", contaAcesso=" + contaAcesso +
                ", valor=" + valor +
                ", dataMovimentacao=" + dataMovimentacao +
                '}';
    }
}

