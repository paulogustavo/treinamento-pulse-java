package br.com.pulse.service;

import br.com.pulse.mapper.ProdutoMapper;
import br.com.pulse.model.Produto;
import br.com.pulse.model.exception.CustomException;
import br.com.pulse.service.impl.ProdutoServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;

@RunWith(MockitoJUnitRunner.class)
public class ProdutoServiceTest {

    @InjectMocks
    private ProdutoServiceImpl produtoService;

    @Mock
    private ProdutoMapper produtoMapper;

    @Test
    public void salvarProduto_DeveRetornarSucesso() {
        Produto produto = Produto.builder().descricao("Fécula Amafil 1Kg").build();
        Produto produtoRetorno = Produto.builder().id(1L).descricao("Fécula Amafil 1Kg").build();

        Mockito.doNothing().when(produtoMapper).salvar(produto);
        Mockito.doReturn(produtoRetorno).when(produtoMapper.recuperarPorId(1L));

        produtoService.salvar(produto);
        Assert.assertEquals(1L, (long) produtoRetorno.getId());
    }

    @Test(expected = CustomException.class)
    public void buscarSemInformarId_DeveLancarExcecao(){

        Produto produto = Produto.builder().descricao("Fécula Amafil 1Kg").build();

        produtoService.recuperar(produto.getId());
    }

    @Test(expected = CustomException.class)
    public void salvarSemDescricao_DeveLancarException(){

        Produto produto = Produto.builder().valorUnitario(BigDecimal.valueOf(2.5)).build();
        Mockito.doNothing().when(produtoMapper).salvar(produto);
        produtoService.salvar(produto);
    }

    @Test(expected = CustomException.class)
    public void salvarValorUnitario_DeveLancarException(){
        Produto produto = Produto.builder().descricao("Fécula Amafil 1Kg").build();
        Mockito.doNothing().when(produtoMapper).salvar(produto);

        produtoService.salvar(produto);
    }

    @Test(expected = CustomException.class)
    public void salvarInformandoId_DeveLancarException(){
        Produto produto = Produto.builder().id(1L).descricao("Fécula Amafil 1Kg")
                .valorUnitario(BigDecimal.valueOf(3.5)).build();
        Mockito.doNothing().when(produtoMapper).salvar(produto);
        produtoService.salvar(produto);
    }

    @Test(expected = CustomException.class)
    public void atualizarSemId_DeveLancarException(){
        Produto produto = Produto.builder().descricao("Fécula Amafil 1Kg").build();
        Mockito.doNothing().when(produtoMapper).atualizar(produto);

        produtoService.atualizar(produto);
    }


}
