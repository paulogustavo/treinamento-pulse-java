package br.com.pulse.controller;

import br.com.pulse.model.Filial;
import br.com.pulse.model.dto.FilialDto;
import br.com.pulse.service.FilialService;
import com.netflix.ribbon.proxy.annotation.Http;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/filial")
@RequiredArgsConstructor
public class FilialController {

    private final FilialService filialService;

    @GetMapping(value = "/id_filial/{id}")
    public ResponseEntity<Filial> buscarFilial(@PathVariable("id") Long id){
        return new ResponseEntity<>(filialService.recuperar(id), HttpStatus.OK);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<HttpStatus> salvarFilial(@Valid @RequestBody FilialDto filialDto){
        filialService.salvar(filialDto.getFilial());
        return new ResponseEntity<>(HttpStatus.OK, HttpStatus.CREATED);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<HttpStatus> atualizarFilial(@Valid @RequestBody FilialDto filialDto){
        filialService.atualizar(filialDto.getFilial());
        return new ResponseEntity<>(HttpStatus.OK, HttpStatus.CREATED);
    }



}
