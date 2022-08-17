package com.fundatec.banco.dto.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Builder
@Data
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClienteResponseDto {

    private Integer id;
    private String nome;
    private String cpf;
    private EnderecoResponseDto endereco;


}