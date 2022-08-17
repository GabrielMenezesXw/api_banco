package com.fundatec.banco.model.enums;

public enum TipoConta {
    SIMPLES(1), ESPECIAL(1), POUPANCA(1.03);

    double valor;

    TipoConta(double valor) {
        this.valor = valor;
    }

    public double getValor() {
        return valor;
    }
}