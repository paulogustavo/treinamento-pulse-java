package br.com.pulse.service.impl;

import br.com.pulse.mapper.EstoqueMapper;
import br.com.pulse.model.Estoque;
import br.com.pulse.model.Filial;
import br.com.pulse.model.Produto;
import br.com.pulse.model.exception.CustomException;
import br.com.pulse.service.EstoqueService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EstoqueServiceImpl implements EstoqueService {

    @Autowired
    private final EstoqueMapper estoqueMapper;

    public Estoque recuperarPorId(Long id){

        if(id == null) throw new CustomException("Informe o id");
        return estoqueMapper.recuperarPorId(id);
    }

    public void salvar(Estoque estoque){
        if(estoque == null) throw new CustomException("objeto estoque nao pode ser igual a nulo");
        if(estoque.getId() != null) throw new CustomException("Não eh possivel salvar filial com id diferente de nulo");
        estoqueMapper.salvar(estoque);
    }

    public void atualizar(Estoque estoque){
        if(estoque == null) throw new CustomException("objeto estoque nao pode ser igual a nulo");
        if(estoque.getId() == null) throw new CustomException("Não eh possivel salvar filial com id nulo");
        estoqueMapper.atualizar(estoque);
    }

    public Estoque recuperarPorProdutoEFilial(Produto produto, Filial filial){
        return estoqueMapper.recuperarPorProdutoEFilial(produto, filial);
    }

}
