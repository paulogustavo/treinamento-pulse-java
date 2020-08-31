package br.com.pulse.mapper;

import br.com.pulse.model.Filial;
import br.com.pulse.model.ItemPedido;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ItemPedidoMapper {
    void salvar(ItemPedido itemPedido);
}
