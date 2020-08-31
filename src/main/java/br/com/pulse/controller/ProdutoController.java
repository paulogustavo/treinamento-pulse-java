package br.com.pulse.controller;

import br.com.pulse.model.Produto;
import br.com.pulse.model.dto.ProdutoDto;
import br.com.pulse.service.ProdutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/produdto")
@RequiredArgsConstructor
public class ProdutoController {

    private final ProdutoService produtoService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Produto> buscar(@PathVariable("id") Long id){
        return new ResponseEntity<>(produtoService.recuperar(id), HttpStatus.OK);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<HttpStatus> salvar(@Valid @RequestBody ProdutoDto produtoDto){
        produtoService.salvar(produtoDto.getProduto());
        return new ResponseEntity<>(HttpStatus.OK, HttpStatus.CREATED);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<HttpStatus> atualizar(@Valid @RequestBody ProdutoDto produtoDto){
        produtoService.atualizar(produtoDto.getProduto());
        return new ResponseEntity<>(HttpStatus.OK, HttpStatus.CREATED);
    }



}
