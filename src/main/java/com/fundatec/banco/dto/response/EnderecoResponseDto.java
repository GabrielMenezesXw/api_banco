package com.fundatec.banco.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fundatec.banco.model.enums.Uf;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Builder
@Data
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EnderecoResponseDto {


    private Integer id;
    private String logradouro;
    private String bairro;
    private String cidade;
    private Uf uf;
}