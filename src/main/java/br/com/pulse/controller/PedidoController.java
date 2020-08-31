package br.com.pulse.controller;

import br.com.pulse.model.Pedido;
import br.com.pulse.model.dto.FilialDto;
import br.com.pulse.model.dto.PedidoDto;
import br.com.pulse.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/pedido")
@RequiredArgsConstructor
public class PedidoController {

    private final PedidoService pedidoService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Pedido> buscar(@PathVariable("id") Long id){
        return new ResponseEntity<>(pedidoService.recuperar(id), HttpStatus.OK);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<HttpStatus> salvar(@Valid @RequestBody PedidoDto filialDto){
        pedidoService.salvarPedido(filialDto.getPedido());
        return new ResponseEntity<>(HttpStatus.OK, HttpStatus.CREATED);
    }

    /*@PostMapping(value = "/processoarPedido/{id}")
    @Transactional
    public ResponseEntity<HttpStatus> processarPedido(@PathVariable("id") Long id){
        pedidoService.processarPedido(id);
        return new ResponseEntity<>(HttpStatus.OK, HttpStatus.CREATED);
    }*/



}
