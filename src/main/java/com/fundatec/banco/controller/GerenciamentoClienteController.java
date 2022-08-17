package com.fundatec.banco.controller;

import com.fundatec.banco.converter.Impl.ClienteConverterImpl;
import com.fundatec.banco.dto.request.ClienteRequestDto;
import com.fundatec.banco.dto.response.ClienteResponseDto;
import com.fundatec.banco.model.Cliente;
import com.fundatec.banco.service.GerenciamentoClienteService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/v1/clientes")
@AllArgsConstructor
public class GerenciamentoClienteController {
    @Autowired
    private final GerenciamentoClienteService service;
    @Autowired
    private final ClienteConverterImpl converter;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public ResponseEntity<ClienteResponseDto> findClienteById(@PathVariable("id") Integer id){
        Cliente cliente = service.findById(id);
        return ResponseEntity.ok(converter.convert(cliente));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<ClienteResponseDto>> findAll(){
        Iterable<Cliente> cliente = service.findAll();
        List<ClienteResponseDto> clienteResponseDto = StreamSupport.stream(cliente.spliterator(), false)
                .map(converter::convert)
                .toList();
        return ResponseEntity.ok(clienteResponseDto);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ClienteResponseDto> criarCliente(@RequestBody ClienteRequestDto clienteRequestDto) {
        Cliente cliente =service.criarNovoCliente(converter.convert(clienteRequestDto),clienteRequestDto.getIdEndereco());
        return ResponseEntity.status(HttpStatus.CREATED).body(converter.convert(cliente));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBancoById(@PathVariable("id") Integer id){service.deleteById(id);}
}