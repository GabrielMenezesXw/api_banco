package com.fundatec.banco.converter;

import com.fundatec.banco.dto.response.MovimentacaoResponseDto;
import com.fundatec.banco.model.Movimentacao;
import org.springframework.stereotype.Component;

@Component
public interface MovimentacaoConverter <M extends Movimentacao, R extends MovimentacaoResponseDto>{

    R convert(M movimentacao);

}