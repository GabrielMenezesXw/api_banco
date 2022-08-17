package com.fundatec.banco.controller;

import com.fundatec.banco.converter.Impl.BancoConverterImpl;
import com.fundatec.banco.dto.response.BancoResponseDto;
import com.fundatec.banco.model.Banco;
import com.fundatec.banco.service.GerenciamentoBancoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.StreamSupport;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/bancos")
public class GerenciamentoBancoController {

    @Autowired
    private final GerenciamentoBancoService service;
    @Autowired
    private final BancoConverterImpl converter;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Banco>> findAll() {
        Iterable<Banco> bancoDto = service.findAll();
        List<Banco> banco = StreamSupport.stream(bancoDto.spliterator(), false).toList();
        return ResponseEntity.ok(banco);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public ResponseEntity<BancoResponseDto> findBancoById(@PathVariable("id") Integer id) {
        Banco banco = service.findById(id);
        return ResponseEntity.ok(converter.convert(banco));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<BancoResponseDto> criarNovoBanco(@RequestBody Banco banco) {
        return ResponseEntity.status(HttpStatus.CREATED).body(converter.convert(service.saveBanco(banco)));
    }
}