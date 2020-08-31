package br.com.pulse.controller;

import br.com.pulse.model.Estoque;
import br.com.pulse.model.Filial;
import br.com.pulse.model.Produto;
import br.com.pulse.model.dto.EstoqueDto;
import br.com.pulse.model.dto.FilialDto;
import br.com.pulse.model.exception.CustomException;
import br.com.pulse.service.EstoqueService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

@SpringBootTest
@WebMvcTest
public class EstoqueControllerTest {

    @InjectMocks
    private EstoqueController estoqueController;

    @Mock
    private EstoqueService estoqueService;

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

    @Test(expected = RuntimeException.class)
    public void buscarSemInformarId_DeveRetornarErro(){
        Long idFilial = null;

        Mockito.when(estoqueService.recuperarPorId(idFilial)).thenThrow(CustomException.class);
        estoqueController.buscar(idFilial);
    }

    @Test
    public void buscarPorId_DeveRetornarSucesso(){
        Estoque estoque = Estoque.builder().filial(filial).produto(produto).quantidade(40).build();

        Mockito.doReturn(estoque).when(estoqueService).recuperarPorId(1L);
        ResponseEntity<Estoque> response = estoqueController.buscar(1L);
        Assert.assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    public void salvarEstoque_DeveRetornarSucesso(){
        Estoque estoque = Estoque.builder().filial(filial).produto(produto).quantidade(40).build();
        EstoqueDto estoqueDto = EstoqueDto.builder().estoque(estoque).build();

        Mockito.doNothing().when(estoqueService).salvar(estoqueDto.getEstoque());
        ResponseEntity<HttpStatus> response = estoqueController.salvar(estoqueDto);
        Assert.assertEquals(200, response.getStatusCodeValue());
    }


}
