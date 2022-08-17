package com.fundatec.banco.converter;

import com.fundatec.banco.dto.request.ClienteRequestDto;
import com.fundatec.banco.dto.response.ClienteResponseDto;
import com.fundatec.banco.model.Cliente;
import org.springframework.stereotype.Component;

@Component
public interface ClienteConverter <M extends Cliente, R extends ClienteResponseDto, Request extends ClienteRequestDto> {

    R convert(M cliente);

    M convert(Request cliente);

}