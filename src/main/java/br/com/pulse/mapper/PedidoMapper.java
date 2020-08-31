package br.com.pulse.mapper;

import br.com.pulse.model.Filial;
import br.com.pulse.model.ItemPedido;
import br.com.pulse.model.Pedido;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PedidoMapper {

    Pedido recuperarPorId(@Param("id") Long id);

    int salvar(@Param("pedido") Pedido pedido);

    void atualizar(@Param("pedido") Pedido pedido);

    List<ItemPedido> buscarItensPedido(@Param("pedido") Pedido pedido);

}
