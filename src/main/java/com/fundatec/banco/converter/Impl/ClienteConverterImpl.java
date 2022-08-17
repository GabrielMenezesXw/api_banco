package com.fundatec.banco.converter.Impl;

import com.fundatec.banco.converter.ClienteConverter;
import com.fundatec.banco.dto.request.ClienteRequestDto;
import com.fundatec.banco.dto.response.ClienteResponseDto;
import com.fundatec.banco.dto.response.EnderecoResponseDto;
import com.fundatec.banco.model.Cliente;
import org.springframework.stereotype.Component;

@Component
public class ClienteConverterImpl implements ClienteConverter<Cliente, ClienteResponseDto, ClienteRequestDto> {


    @Override
    public ClienteResponseDto convert(Cliente cliente) {
        return ClienteResponseDto.builder().id(cliente.getId())
                .nome(cliente.getNome())
                .endereco(cliente.getEndereco() != null
                        ? EnderecoResponseDto.builder()
                        .logradouro(cliente.getEndereco().getLogradouro())
                        .bairro(cliente.getEndereco().getBairro())
                        .cidade(cliente.getEndereco().getCidade())
                        .uf(cliente.getEndereco().getUf()).build()
                        : null
                )
                .cpf(cliente.getCpf())
                .build();
    }

    @Override
    public Cliente convert(ClienteRequestDto cliente) {
        return Cliente.builder().nome(cliente.getNome())
                .cpf(cliente.getCpf())
                .dataNascimento(cliente.getDataNascimento())
                .sexo(cliente.getSexo())
                .build();
    }
}