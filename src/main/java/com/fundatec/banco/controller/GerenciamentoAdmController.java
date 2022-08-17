package com.fundatec.banco.controller;

import com.fundatec.banco.converter.Impl.ClienteConverterImpl;
import com.fundatec.banco.converter.Impl.ContaConverterImpl;
import com.fundatec.banco.dto.response.ClienteResponseDto;
import com.fundatec.banco.dto.response.ContaResponseDto;
import com.fundatec.banco.model.Conta;
import com.fundatec.banco.model.Cliente;
import com.fundatec.banco.service.GerenciamentoAdmService;
import com.fundatec.banco.service.GerenciamentoClienteService;
import com.fundatec.banco.service.GerenciamentoContaService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/administrativo")
@RequiredArgsConstructor
public class GerenciamentoAdmController {
    @Autowired
    private final GerenciamentoAdmService admService;
    @Autowired
    private final ClienteConverterImpl clienteConverterImpl;
    @Autowired
    private final GerenciamentoClienteService clienteService;
    @Autowired
    private final GerenciamentoContaService contaService;
    @Autowired
    private final ContaConverterImpl contaConverterImpl;

    @PostMapping("/clientes")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ClienteResponseDto> newCliente(@RequestHeader Integer idBanco, @RequestHeader String senha, @RequestBody Cliente cliente) {
        if (admService.verificarCredenciais(idBanco, senha)) {
            Cliente clienteDto = clienteService.saveCliente(cliente);
            return ResponseEntity.ok(clienteConverterImpl.convert(clienteDto));
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    @PostMapping("/contas")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ContaResponseDto> newConta(@RequestHeader Integer idBanco, @RequestHeader String senha, @RequestBody Conta conta) {
        if (admService.verificarCredenciais(idBanco, senha)) {
            admService.verificarCredenciais(idBanco, senha);
            Conta contaDto = contaService.criarConta(conta);
            return ResponseEntity.ok(contaConverterImpl.convert(contaDto));
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }
    @PatchMapping("/contas/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<ContaResponseDto> alterarTipoConta(@PathVariable("id") Integer id, @RequestBody Conta conta){
        admService.alterarTipoConta(id, conta);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}