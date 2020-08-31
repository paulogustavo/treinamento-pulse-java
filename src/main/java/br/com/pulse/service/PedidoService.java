package br.com.pulse.service;

import br.com.pulse.model.Filial;
import br.com.pulse.model.Pedido;

public interface PedidoService {

    Pedido recuperar(Long id);

    void salvarPedido(Pedido pedido);
}
