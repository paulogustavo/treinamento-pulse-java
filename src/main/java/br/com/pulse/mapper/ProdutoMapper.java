package br.com.pulse.mapper;

import br.com.pulse.model.Filial;
import br.com.pulse.model.Produto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ProdutoMapper {
    Produto recuperarPorId(@Param("id") Long id);

    void salvar(@Param("produto") Produto produto);

    void atualizar(@Param("produto") Produto produto);
}
