package com.fundatec.banco.converter.Impl;

import com.fundatec.banco.converter.MovimentacaoConverter;
import com.fundatec.banco.dto.response.MovimentacaoResponseDto;
import com.fundatec.banco.model.Movimentacao;
import org.springframework.stereotype.Component;


@Component
public class MovimentacaoConverterImpl implements MovimentacaoConverter<Movimentacao, MovimentacaoResponseDto> {

    @Override
    public MovimentacaoResponseDto convert(Movimentacao movimentacao) {
        return MovimentacaoResponseDto.builder()
                .id(movimentacao.getId())
                .dataMovimentacao(movimentacao.getDataMovimentacao())
                .valor(movimentacao.getValor())
                .build();
    }
}