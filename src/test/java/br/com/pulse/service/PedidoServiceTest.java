package br.com.pulse.service;

import br.com.pulse.mapper.EstoqueMapper;
import br.com.pulse.mapper.PedidoMapper;
import br.com.pulse.model.*;
import br.com.pulse.model.exception.CustomException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class PedidoServiceTest {

    @InjectMocks
    private PedidoService pedidoService;

    @Mock
    private PedidoMapper pedidoMapper;

    private Filial filial;
    private TipoPedido tipoPedidoEntrada;
    private TipoPedido tipoPedidoSaida;
    private List<ItemPedido> itens;

    @Before
    public void init(){
        filial = Filial.builder().id(1L).descricao("Mix Mateus Forquilha").build();
        tipoPedidoEntrada = TipoPedido.builder().id(1L).descricao("ENTRADA").build();
        tipoPedidoSaida = TipoPedido.builder().id(2L).descricao("SAIDA").build();
        itens = new ArrayList<>();
        Produto produto = Produto.builder()
                .id(1L)
                .valorUnitario(BigDecimal.valueOf(3.5))
                .descricao("Biscoito Racheiro")
                .build();

        Produto produto2 = Produto.builder()
                .id(2L)
                .valorUnitario(BigDecimal.valueOf(8.5))
                .descricao("Batata Pringles")
                .build();

        ItemPedido item1 = ItemPedido.builder()
                .produto(produto)
                .pedido(Pedido.builder().id(1L).build())
                .quantidade(2)
                .build();

        ItemPedido item2 = ItemPedido.builder()
                .produto(produto2)
                .pedido(Pedido.builder().id(1L).build())
                .quantidade(3)
                .build();

        itens.add(item1);
        itens.add(item2);
    }

    @Test
    public void salvarPedido_DeveRetornarSucesso(){
        Pedido pedido = Pedido.builder().filial(filial).items(itens).tipoPedido(tipoPedidoEntrada).build();
        Pedido pedidoRetorno = Pedido.builder().filial(filial).items(itens).tipoPedido(tipoPedidoEntrada).build();

        Mockito.doNothing().when(pedidoMapper).salvar(pedido);
        Mockito.doReturn(pedidoRetorno).when(pedidoMapper).recuperarPorId(pedido.getId());

        pedidoService.salvarPedido(pedido);
        Assert.assertEquals(1L, (long) pedidoRetorno.getId());
    }

    @Test(expected = CustomException.class)
    public void salvarSemInformarItens_DeveLancarException(){
        Pedido pedido = Pedido.builder().filial(filial).tipoPedido(tipoPedidoSaida).build();
        Mockito.doThrow(CustomException.class).when(pedidoMapper).salvar(pedido);
        pedidoService.salvarPedido(pedido);
    }

    @Test(expected = CustomException.class)
    public void salvarInformandoId_DeveLancarException(){
        Pedido pedido = Pedido.builder().id(1L).filial(filial).items(itens).tipoPedido(tipoPedidoSaida).build();
        Mockito.doThrow(CustomException.class).when(pedidoMapper).salvar(pedido);
        pedidoService.salvarPedido(pedido);
    }

}
