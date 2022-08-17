package com.fundatec.banco.controller;

import com.fundatec.banco.converter.Impl.MovimentacaoConverterImpl;
import com.fundatec.banco.dto.response.MovimentacaoResponseDto;
import com.fundatec.banco.model.Movimentacao;
import com.fundatec.banco.service.MovimentacaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("v1/movimentacoes")
@RequiredArgsConstructor
public class MovimentacaoController{

    @Autowired
    private final MovimentacaoService service;
    @Autowired
    private final MovimentacaoConverterImpl converter;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public ResponseEntity<MovimentacaoResponseDto> findMovimentacaoById(@RequestHeader Integer idBanco, @RequestHeader Integer idConta, @PathVariable("id") Integer id){
        Movimentacao movimentacao = service.findById(id);
        return ResponseEntity.ok(converter.convert(movimentacao));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<MovimentacaoResponseDto>> findAll(@RequestHeader Integer idBanco, @RequestHeader Integer idConta){
        Iterable<Movimentacao> movimentacoes = service.verificarMovimentacoesPorConta(idBanco,idConta);
        List<MovimentacaoResponseDto> movimentacaoResponseDtos = StreamSupport.stream(movimentacoes.spliterator(), false)
                .map(converter::convert)
                .toList();
        return ResponseEntity.ok(movimentacaoResponseDtos);
    }

}