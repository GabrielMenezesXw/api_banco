package com.fundatec.banco.converter;

import com.fundatec.banco.dto.response.BancoResponseDto;
import com.fundatec.banco.model.Banco;
import org.springframework.stereotype.Component;

@Component
public interface BancoConverter  <M extends Banco, R extends BancoResponseDto> {

    R convert(M banco);
}