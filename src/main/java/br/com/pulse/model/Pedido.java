package br.com.pulse.model;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Builder
public class Pedido {

    private Long id;
    private TipoPedido tipoPedido;
    private Filial filial;
    private BigDecimal valorTotal;
    private List<ItemPedido> items;

}