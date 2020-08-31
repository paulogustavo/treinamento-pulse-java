package br.com.pulse.controller;

import br.com.pulse.model.Produto;
import br.com.pulse.model.dto.ProdutoDto;
import br.com.pulse.model.exception.CustomException;
import br.com.pulse.service.ProdutoService;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
@WebMvcTest
public class ProdutoControllerTest {

    @InjectMocks
    private ProdutoController produtoController;

    @Mock
    private ProdutoService produtoService;

    //buscar produto nao informando id
    @Test(expected = RuntimeException.class)
    public void buscarSemInformarId_DeveRetornarErro(){
        Long idProduto = null;

        Mockito.when(produtoService.recuperar(idProduto)).thenThrow(CustomException.class);
        produtoController.buscar(idProduto);
    }

    //salvar produto sem informar descricao - trows exeception
    @Test(expected = NullPointerException.class)
    public void salvarSemInformarDescricao_DeveRetornarErro(){
        Produto produto = Produto.builder().id(1L).valorUnitario(BigDecimal.valueOf(2.5)).build();
        ProdutoDto produtoDto = ProdutoDto.builder().produto(produto).build();

        Mockito.doThrow(NullPointerException.class).when(produtoService).salvar(produtoDto.getProduto());
        produtoController.salvar(produtoDto);
    }

    //atualizar produto sem informar o id - throws exception
    @Test(expected = NullPointerException.class)
    public void atualizarSemInformarId_DeveRetornarErro(){
        Produto produto = Produto.builder().descricao("Fécula Amafil 1Kg")
                .valorUnitario(BigDecimal.valueOf(2.7)).build();
        ProdutoDto filialDto = ProdutoDto.builder().produto(produto).build();

        Mockito.doThrow(NullPointerException.class).when(produtoService).atualizar(filialDto.getProduto());
        produtoController.atualizar(filialDto);
    }

}
