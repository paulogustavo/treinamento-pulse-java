package br.com.pulse.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ItemPedido {

    private Long id;
    private Pedido pedido;
    private Produto produto;
    private Integer quantidade;

}
