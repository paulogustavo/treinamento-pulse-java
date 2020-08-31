package br.com.pulse.mapper;

import br.com.pulse.model.Filial;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface FilialMapper {
    Filial recuperarPorId(@Param("id") Long id);

    void salvar(@Param("filial") Filial filial);

    void atualizar(@Param("filial") Filial filial);
}
