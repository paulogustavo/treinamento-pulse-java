package br.com.pulse.service;

import br.com.pulse.mapper.EstoqueMapper;
import br.com.pulse.model.Estoque;
import br.com.pulse.model.Filial;
import br.com.pulse.model.Produto;
import br.com.pulse.model.dto.EstoqueDto;
import br.com.pulse.model.dto.FilialDto;
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

@RunWith(MockitoJUnitRunner.class)
public class EstoqueServiceTest {

    @InjectMocks
    private EstoqueService estoqueService;

    @Mock
    private EstoqueMapper estoqueMapper;

    private Filial filial;
    private Produto produto;

    @Before
    public void init(){
        filial = Filial.builder().id(1L).descricao("Mix Mateus Forquilha").build();
        produto = Produto.builder()
                .id(1L)
                .descricao("Iphone 11 Pro Max")
                .valorUnitario(BigDecimal.valueOf(3000))
                .build();
    }

    @Test
    public void salvar_DeveRetornarSucesso() {
        Estoque estoque = Estoque.builder().filial(filial).produto(produto).quantidade(40).build();
        Estoque estoqueRetorno = Estoque.builder().id(1L).filial(filial).produto(produto).quantidade(40).build();

        Mockito.doNothing().when(estoqueMapper).salvar(estoque);
        Mockito.doReturn(estoqueRetorno).when(estoqueMapper).recuperarPorId(estoque.getId());

        estoqueService.salvar(estoque);
        Assert.assertEquals(1L, (long) estoqueRetorno.getId());
    }

    @Test(expected = CustomException.class)
    public void buscarSemInformarId_DeveLancarException(){

        Estoque estoque = Estoque.builder().build();

        estoqueService.recuperarPorId(estoque.getId());
    }

    @Test(expected = CustomException.class)
    public void salvarInformandoId_DeveLancarException(){
        Estoque estoque = Estoque.builder().id(1L).build();
        Mockito.doThrow(CustomException.class).when(estoqueMapper).salvar(estoque);

        estoqueService.salvar(estoque);
    }

    @Test(expected = CustomException.class)
    public void salvarSemInfomarFilial_DeveLancarException(){
        Estoque estoque = Estoque.builder().produto(produto).quantidade(40).build();

        Mockito.doNothing().when(estoqueMapper).salvar(estoque);

        estoqueService.salvar(estoque);
    }

    @Test(expected = CustomException.class)
    public void salvarSemInfomarProduto_DeveLancarException(){
        Estoque estoque = Estoque.builder().filial(filial).quantidade(40).build();

        Mockito.doNothing().when(estoqueMapper).salvar(estoque);

        estoqueService.salvar(estoque);
    }

    @Test(expected = CustomException.class)
    public void salvarSemInfomarQuantidade_DeveLancarException(){
        Estoque estoque = Estoque.builder().filial(filial).produto(produto).build();

        Mockito.doNothing().when(estoqueMapper).salvar(estoque);

        estoqueService.salvar(estoque);
    }

    @Test(expected = CustomException.class)
    public void salvarInfomarQuantidadeNegativa_DeveLancarException(){
        Estoque estoque = Estoque.builder().filial(filial).produto(produto).quantidade(-1).build();

        Mockito.doNothing().when(estoqueMapper).salvar(estoque);

        estoqueService.salvar(estoque);
    }

    @Test(expected = CustomException.class)
    public void salvarInfomarQuantidadeZero_DeveLancarException(){
        Estoque estoque = Estoque.builder().filial(filial).produto(produto).quantidade(0).build();

        Mockito.doNothing().when(estoqueMapper).salvar(estoque);

        estoqueService.salvar(estoque);
    }

}
