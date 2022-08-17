package com.fundatec.banco.converter;

import com.fundatec.banco.dto.request.ContaRequestDto;
import com.fundatec.banco.dto.response.ContaResponseDto;
import com.fundatec.banco.model.Conta;
import org.springframework.stereotype.Component;

@Component
public interface ContaConverter <M extends Conta, R extends ContaResponseDto, Request extends ContaRequestDto>{


    R convert(M conta);

    M convert(Request conta);
}