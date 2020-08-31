package br.com.pulse.controller;

import br.com.pulse.model.exception.CustomException;
import br.com.pulse.model.Filial;
import br.com.pulse.model.dto.FilialDto;
import br.com.pulse.service.FilialService;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@WebMvcTest
public class FilialControllerTest {

    @InjectMocks
    private FilialController filialController;

    @Mock
    private FilialService filialService;

    //buscar filial nao informando id
    @Test(expected = RuntimeException.class)
    public void buscarFilialSemInformarId_DeveRetornarErro(){
        Long idFilial = null;

        Mockito.when(filialService.recuperar(idFilial)).thenThrow(CustomException.class);
        filialController.buscarFilial(idFilial);
    }

    //salvar filial sem informar descricao - trows exeception
    @Test(expected = NullPointerException.class)
    public void salvarFilialSemInformarDescricao_DeveRetornarErro(){
        Filial filial = Filial.builder().id(1L).build();
        FilialDto filialDto = FilialDto.builder().filial(filial).build();

        Mockito.doThrow(NullPointerException.class).when(filialService).salvar(filialDto.getFilial());
        filialController.salvarFilial(filialDto);
    }

    //atualizar filial sem informar o id - throws exception
    @Test(expected = NullPointerException.class)
    public void atualizarFilialSemInformarId_DeveRetornarErro(){
        Filial filial = Filial.builder().descricao("Mix Mateus Jardim Tropical").build();
        FilialDto filialDto = FilialDto.builder().filial(filial).build();

        Mockito.doThrow(NullPointerException.class).when(filialService).atualizar(filialDto.getFilial());
        filialController.atualizarFilial(filialDto);
    }

}
