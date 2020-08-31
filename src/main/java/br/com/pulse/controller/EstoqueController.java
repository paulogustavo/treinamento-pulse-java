package br.com.pulse.controller;

import br.com.pulse.model.Estoque;
import br.com.pulse.model.Filial;
import br.com.pulse.model.dto.EstoqueDto;
import br.com.pulse.model.dto.FilialDto;
import br.com.pulse.service.EstoqueService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/estoque")
@RequiredArgsConstructor
public class EstoqueController {

    private final EstoqueService estoqueService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Estoque> buscar(@PathVariable("id") Long id){
        return new ResponseEntity<>(estoqueService.recuperarPorId(id), HttpStatus.OK);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<HttpStatus> salvar(@Valid @RequestBody EstoqueDto estoqueDto){
        estoqueService.salvar(estoqueDto.getEstoque());
        return new ResponseEntity<>(HttpStatus.OK, HttpStatus.CREATED);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<HttpStatus> atualizar(@Valid @RequestBody EstoqueDto estoqueDto){
        estoqueService.atualizar(estoqueDto.getEstoque());
        return new ResponseEntity<>(HttpStatus.OK, HttpStatus.CREATED);
    }



}
