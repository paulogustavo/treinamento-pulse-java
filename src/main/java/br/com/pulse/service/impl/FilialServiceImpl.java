package br.com.pulse.service.impl;

import br.com.pulse.mapper.FilialMapper;
import br.com.pulse.model.Filial;
import br.com.pulse.model.exception.CustomException;
import br.com.pulse.service.FilialService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FilialServiceImpl implements FilialService {

    @Autowired
    private final FilialMapper filialMapper;

    public Filial recuperar(Long id){

        if(id == null) throw new CustomException("Informe o id");
        return filialMapper.recuperarPorId(id);
    }

    public void salvar(Filial filial){
        if(filial == null) throw new CustomException("objeto filial nao pode ser igual a nulo");
        if(filial.getId() != null) throw new CustomException("Não eh possivel salvar filial com id diferente de nulo");
        filialMapper.salvar(filial);
    }

    public void atualizar(Filial filial){
        if(filial == null) throw new CustomException("objeto filial nao pode ser igual a nulo");
        if(filial.getId() == null) throw new CustomException("Não eh possivel salvar filial com id nulo");
        filialMapper.atualizar(filial);
    }

}
