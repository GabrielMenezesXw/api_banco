package com.fundatec.banco.converter;

import com.fundatec.banco.dto.response.EnderecoResponseDto;
import com.fundatec.banco.model.Endereco;
import org.springframework.stereotype.Component;

@Component
public interface EnderecoConverter<M extends Endereco, R extends EnderecoResponseDto>{

    R convert(M endereco);
}