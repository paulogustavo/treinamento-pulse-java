package br.com.pulse.service.impl;

import br.com.pulse.mapper.ProdutoMapper;
import br.com.pulse.model.Filial;
import br.com.pulse.model.Produto;
import br.com.pulse.model.exception.CustomException;
import br.com.pulse.service.FilialService;
import br.com.pulse.service.ProdutoService;
import br.com.pulse.utils.DescricaoValidator;
import br.com.pulse.utils.Validator;
import br.com.pulse.utils.ValorUnitarioValidator;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProdutoServiceImpl implements ProdutoService {

    @Autowired
    private final ProdutoMapper produtoMapper;

    public Produto recuperar(Long id){

        if(id == null) throw new CustomException("Informe o id");
        return produtoMapper.recuperarPorId(id);
    }

    public void salvar(Produto produto){
        if(produto == null) throw new CustomException("objeto produto nao pode ser igual a nulo");
        if(produto.getId() != null) throw new CustomException("Não eh possivel salvar produto com id diferente de nulo");

        validar(new DescricaoValidator(), produto.getDescricao());
        validar(new ValorUnitarioValidator(), produto.getValorUnitario());

        produtoMapper.salvar(produto);
    }

    public void atualizar(Produto produto){
        if(produto == null) throw new CustomException("objeto produto nao pode ser igual a nulo");
        if(produto.getId() == null) throw new CustomException("Não eh possivel atualizar produto com id nulo");
        produtoMapper.atualizar(produto);
    }

    @Override
    public <T> void validar(Validator<T> validator, T objeto) {
        try{
            validator.valida(objeto);
        } catch (Exception e){
            String msg = "Não foi possível salvar o produto: " + e.getMessage();
            System.err.println(msg);
            throw new RuntimeException(msg);
        }
    }

}
