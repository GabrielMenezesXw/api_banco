package com.fundatec.banco.model.contas;

import com.fundatec.banco.exception.NotAllowedException;
import com.fundatec.banco.model.Conta;
import com.fundatec.banco.model.enums.StatusConta;
import com.fundatec.banco.model.enums.TipoConta;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

class ContaTest {

    private Conta conta;
    private static final Logger logger = Logger.getLogger(Conta.class.getName());

    @BeforeEach
    void init() {
        conta = new Conta(1, "12345678",null, BigDecimal.valueOf(0), StatusConta.ATIVA,
                "cloud", TipoConta.SIMPLES, null );
    }

    @Test
    void deveDepositarCorretamente() {
        logger.info(conta.getSaldo().toString());
        conta.depositar(BigDecimal.valueOf(16));
        logger.info(conta.getSaldo().toString());
        assertEquals(BigDecimal.valueOf(16), conta.getSaldo());
    }

    @Test
    void deveSacarCorretamente() {
        logger.info(conta.getSaldo().toString());
        conta.depositar(BigDecimal.valueOf(20));
        logger.info(conta.getSaldo().toString());
        conta.sacar(BigDecimal.valueOf(16));
        logger.info(conta.getSaldo().toString());
        assertEquals(BigDecimal.valueOf(4), conta.getSaldo());
    }

    @Test
    void deveDarErroAoDepositarNulo() {
        logger.info(conta.getSaldo().toString());
        assertThrows(IllegalArgumentException.class, () -> conta.depositar(null));
        logger.info(conta.getSaldo().toString());
    }

    @Test
    void deveDarErroAoDepositarNegativo() {
        logger.info(conta.getSaldo().toString());
        assertThrows(IllegalArgumentException.class, () -> conta.depositar(BigDecimal.valueOf(-1)));
        logger.info(conta.getSaldo().toString());
    }

    @Test
    void deveDarErroAoSacarValorMaiorQueSaldo() {
        conta.depositar(BigDecimal.valueOf(20));
        logger.info(conta.getSaldo().toString());
        assertThrows(NotAllowedException.class, () -> conta.sacar(BigDecimal.valueOf(777)));
        logger.info(conta.getSaldo().toString());
    }

    @Test
    void deveDarErroAoSacarNulo() {
        logger.info(conta.getSaldo().toString());
        assertThrows(IllegalArgumentException.class, () -> conta.sacar(null));
        logger.info(conta.getSaldo().toString());
    }

    @Test
    void deveDarErroAoSacarZero() {
        logger.info(conta.getSaldo().toString());
        assertThrows(IllegalArgumentException.class, () -> conta.depositar(BigDecimal.ZERO));
        logger.info(conta.getSaldo().toString());
    }

    @Test
    void deveDarErroAoSacarPoisSaldoNulo() {
        logger.info(conta.getSaldo().toString());
        assertThrows(NotAllowedException.class, () -> conta.sacar(BigDecimal.ONE));
        logger.info(conta.getSaldo().toString());
    }

    @Test
    void deveDarErroAoSacarValorNegativo() {
        conta.depositar(BigDecimal.valueOf(20));
        logger.info(conta.getSaldo().toString());
        assertThrows(IllegalArgumentException.class, () -> conta.sacar(BigDecimal.valueOf(-1)));
        logger.info(conta.getSaldo().toString());
    }

    @Test
    void deveRetornarVerdadeiroAoChecarContaPoisContaAtiva(){
        conta.setStatus(StatusConta.ATIVA);
        assertTrue(conta.checarStatus(conta.getStatus()));
        logger.info(conta.getStatus().toString());
    }

    @Test
    void deveRetornarFalseAoChecarContaPoisContaInativa(){
        conta.setStatus(StatusConta.INATIVA);
        assertFalse(conta.checarStatus(conta.getStatus()));
        logger.info(conta.getStatus().toString());
    }
}