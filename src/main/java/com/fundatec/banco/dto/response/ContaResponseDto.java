package com.fundatec.banco.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


import java.math.BigDecimal;
import java.util.List;

@Builder
@Getter
@Setter
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ContaResponseDto {

    private Integer id;
    private String cpfTitular;
    private BancoResponseDto banco;
    private List<MovimentacaoResponseDto> movimentacaoList;
    private BigDecimal saldo;


}