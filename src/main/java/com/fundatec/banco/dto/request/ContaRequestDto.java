package com.fundatec.banco.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fundatec.banco.model.enums.TipoConta;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Builder
@Data
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ContaRequestDto {

    private Integer idPessoaTitular;
    private Integer idBanco;
    private TipoConta tipoConta;
    private String senhaAcesso;

}