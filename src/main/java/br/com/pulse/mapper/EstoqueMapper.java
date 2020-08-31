package br.com.pulse.mapper;

import br.com.pulse.model.Estoque;
import br.com.pulse.model.Filial;
import br.com.pulse.model.Produto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface EstoqueMapper {

    Estoque recuperarPorId(@Param("id") Long id);

    void salvar(@Param("estoque") Estoque filial);

    void atualizar(@Param("estoque") Estoque filial);

    Estoque recuperarPorProdutoEFilial(@Param("estoque") Produto produto, @Param("filial") Filial filial);

}
