package com.fundatec.banco.converter.Impl;

import com.fundatec.banco.converter.ContaConverter;
import com.fundatec.banco.dto.request.ContaRequestDto;
import com.fundatec.banco.dto.response.BancoResponseDto;
import com.fundatec.banco.dto.response.ContaResponseDto;
import com.fundatec.banco.model.Conta;
import org.springframework.stereotype.Component;

@Component
public class ContaConverterImpl implements ContaConverter <Conta, ContaResponseDto, ContaRequestDto> {

    @Override
    public ContaResponseDto convert(Conta conta) {
        return ContaResponseDto.builder().id(conta.getId())
                .cpfTitular(conta.getCpfTitular())
                .banco(conta.getBanco() == null
                        ? null
                        :
                        BancoResponseDto.builder()
                                .id(conta.getBanco().getId())
                                .nome(conta.getBanco().getNome())
                                .build()
                )
                .saldo(conta.getSaldo())
                .build();
    }

    @Override
    public Conta convert(ContaRequestDto conta) {
        return Conta.builder()
                .tipoConta(conta.getTipoConta())
                .senhaAcesso(conta.getSenhaAcesso())
                .build();
    }
}