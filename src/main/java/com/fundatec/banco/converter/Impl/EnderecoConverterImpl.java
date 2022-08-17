package com.fundatec.banco.converter.Impl;

import com.fundatec.banco.converter.EnderecoConverter;
import com.fundatec.banco.dto.response.EnderecoResponseDto;
import com.fundatec.banco.model.Endereco;
import org.springframework.stereotype.Component;

@Component
public class EnderecoConverterImpl implements EnderecoConverter<Endereco, EnderecoResponseDto> {

    @Override
    public EnderecoResponseDto convert(Endereco endereco) {
        return EnderecoResponseDto.builder()
                .id(endereco.getId())
                .logradouro(endereco.getLogradouro())
                .bairro(endereco.getBairro())
                .cidade(endereco.getCidade())
                .uf(endereco.getUf())
                .build();
    }
}