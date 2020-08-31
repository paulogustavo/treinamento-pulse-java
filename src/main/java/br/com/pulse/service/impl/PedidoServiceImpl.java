package br.com.pulse.service.impl;

import br.com.pulse.service.PedidoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import br.com.pulse.model.exception.CustomException;
import br.com.pulse.model.*;
import br.com.pulse.mapper.*;

import java.util.List;

@Service
@AllArgsConstructor
public class PedidoServiceImpl implements PedidoService {

    private final PedidoMapper pedidoMapper;
    private final FilialMapper filialMapper;
    private final ItemPedidoMapper itemPedidoMapper;
    private final EstoqueMapper estoqueMapper;
    private final ProdutoMapper produtoMapper;

    public Pedido recuperar(Long id){

        if(id == null) throw new CustomException("Informe o id");
        return pedidoMapper.recuperarPorId(id);
    }
    //salvar pedido e itens de pedido
    public void salvarPedido(Pedido pedido){
        validaPedido(pedido);
        pedidoMapper.salvar(pedido);
        pedido.getItems().forEach(itemPedido -> {
            itemPedido.setPedido(pedido);
            itemPedidoMapper.salvar(itemPedido);
        });
        processarPedido(pedido.getId());
    }

    private void validaPedido(Pedido pedido){
        if(pedido == null) throw new CustomException("Pedido nulo. Informe o pedido.");
        if(pedido.getId() == null) throw new CustomException("Pedido não pode ter id preenchido");
        if(pedido.getFilial() == null || pedido.getFilial().getId() == null) throw new CustomException("Filial não informada");
        if(pedido.getTipoPedido() == null || pedido.getTipoPedido().getId() == null) throw new CustomException("Tipo de pedido não informado");
        if(pedido.getItems() == null || pedido.getItems().isEmpty())
            throw new CustomException("Informe os itens do pedido!");
        if(pedido.getItems()
                .stream()
                .anyMatch(itemPedido -> itemPedido.getQuantidade() == null || itemPedido.getQuantidade() <= 0))
            throw new CustomException("Existe quantidade inválida no pedido enviado");

        verificaValoresInformadosJaSalvosNoBanco(pedido);
    }

    private void verificaValoresInformadosJaSalvosNoBanco(Pedido pedido){
        if(filialMapper.recuperarPorId(pedido.getFilial().getId()) ==  null){
            throw new CustomException("Filial informada não existe na base");
        }

        if(pedido.getTipoPedido().getId() != TipoPedido.ENTRADA && pedido.getTipoPedido().getId() != TipoPedido.SAIDA){
            throw new CustomException("Tipo de pedido informado não existe na base");
        }

        pedido.getItems().forEach(item -> {
            if(produtoMapper.recuperarPorId(item.getProduto().getId()) == null)
                throw new CustomException("Pelo menos um dos produtos informados não existe na base de dados");
        });

    }

    //processar pedido
    private Pedido validaProcessarPedido(Long idPedido){
        if(idPedido == null) throw new CustomException("Informe o código do pedido");
        Pedido pedido = pedidoMapper.recuperarPorId(idPedido);
        if(pedido == null) throw new CustomException("Pedido não encontrado");
        return pedido;
    }

    public void processarPedido(Long idPedido){
        Pedido pedido = validaProcessarPedido(idPedido);
        //busca qtd de itens de pedido
        List<ItemPedido> listaItensPedido = pedido.getItems();

        //if(listaItensPedido.isEmpty()) throw new CustomException("Nenhum item de pedido adicionado ao pedido");

        //decrementa da base
        listaItensPedido.forEach(item -> {
            if(pedido.getTipoPedido().getId() == TipoPedido.ENTRADA){
                //incrementa
                alteraEstoque(item, true);
            } else if(pedido.getTipoPedido().getId() == TipoPedido.SAIDA){
                //decrementa
                alteraEstoque(item, false);
            } else throw new CustomException("Tipo de pedido inválido");
        });

        pedidoMapper.salvar(pedido);
    }

    //incrementa ou decrementa
    private void alteraEstoque(ItemPedido itemPedido, Boolean incrementar){
        Estoque estoque = estoqueMapper.recuperarPorProdutoEFilial(itemPedido.getProduto(), itemPedido.getPedido().getFilial());
        //if(estoque == null) throw new CustomException("Estoque não encontrado");
        if(incrementar){
            if(estoque == null){
                criaNovoEstoque(itemPedido);
            } else estoque.setQuantidade(itemPedido.getQuantidade() + estoque.getQuantidade());
        } else if(estoque != null){
            if(estoque.getQuantidade() - itemPedido.getQuantidade() >= 0){
                estoque.setQuantidade(estoque.getQuantidade() - itemPedido.getQuantidade());
            } else throw new CustomException("Estoque indisponível");
        } else throw new CustomException("Estoque não encontrado");

        estoqueMapper.salvar(estoque);
    }

    private void criaNovoEstoque(ItemPedido itemPedido){
        Estoque novoEstoque = Estoque.builder()
                .filial(itemPedido.getPedido().getFilial())
                .produto(itemPedido.getProduto())
                .quantidade(itemPedido.getQuantidade())
                .build();
        estoqueMapper.salvar(novoEstoque);
    }

}

