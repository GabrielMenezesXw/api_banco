package com.fundatec.banco.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MovimentacaoResponseDto {

    private Integer id;
    private BigDecimal valor;
    private LocalDateTime dataMovimentacao;
}