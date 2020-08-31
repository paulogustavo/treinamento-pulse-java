package br.com.pulse.controller;

import br.com.pulse.model.*;
import br.com.pulse.model.dto.PedidoDto;
import br.com.pulse.model.exception.CustomException;
import br.com.pulse.service.PedidoService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@WebMvcTest
public class PedidoControllerTest {

    @InjectMocks
    private PedidoController pedidoController;

    @Mock
    private PedidoService pedidoService;

    List<ItemPedido> itens;

    @Before
    public void init(){
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

        Pedido pedido = Pedido.builder()
                .tipoPedido(TipoPedido.builder().id(TipoPedido.ENTRADA).build())
                .filial(Filial.builder().id(1L).build())
                .valorTotal(BigDecimal.valueOf(32.5))
                .items(itens)
                .build();

        /*Pedido pedidoRetornoService = Pedido.builder()
                .tipoPedido(TipoPedido.builder().id(TipoPedido.ENTRADA).build())
                .filial(Filial.builder().id(1L).build())
                .items(itens)
                .valorTotal(BigDecimal.valueOf(32.5))
                .build();*/

        PedidoDto pedidoDto = PedidoDto.builder().pedido(pedido).build();

        Mockito.doNothing().when(pedidoService).salvarPedido(pedidoDto.getPedido());
        ResponseEntity<HttpStatus> responseEntity = pedidoController.salvar(pedidoDto);
        Assert.assertEquals(200, responseEntity.getStatusCode().value());
    }

    @Test(expected = CustomException.class)
    public void salvarSemInformarItens_DeveLancarException(){
        Pedido pedido = Pedido.builder()
                .tipoPedido(TipoPedido.builder().id(TipoPedido.ENTRADA).build())
                .filial(Filial.builder().id(1L).build())
                .valorTotal(BigDecimal.valueOf(32.5))
                .build();

        PedidoDto pedidoDto = PedidoDto.builder().pedido(pedido).build();

        Mockito.doThrow(CustomException.class).when(pedidoService).salvarPedido(pedidoDto.getPedido());
        pedidoController.salvar(pedidoDto);
    }

    @Test(expected = CustomException.class)
    public void salvarInformandoId_DeveLancarException(){
        Pedido pedido = Pedido.builder()
                .id(1L)
                .build();

        PedidoDto pedidoDto = PedidoDto.builder().pedido(pedido).build();

        Mockito.doThrow(CustomException.class).when(pedidoService).salvarPedido(pedidoDto.getPedido());
        pedidoController.salvar(pedidoDto);
    }




}
