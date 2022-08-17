package com.fundatec.banco.dto.request;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Builder
@Data
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClienteRequestDto {

    private String nome;
    private String cpf;
    private Integer idEndereco;
    private String sexo;
    private LocalDate dataNascimento;

}