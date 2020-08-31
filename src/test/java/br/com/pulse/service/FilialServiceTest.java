package br.com.pulse.service;

import br.com.pulse.model.exception.CustomException;
import br.com.pulse.mapper.FilialMapper;
import br.com.pulse.model.Filial;
import br.com.pulse.model.dto.FilialDto;
import br.com.pulse.service.impl.FilialServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class FilialServiceTest {

    @InjectMocks
    private FilialServiceImpl filialService;

    @Mock
    private FilialMapper filialMapper;

    @Test
    public void salvarFilial_DeveRetornarSucesso() {
        Filial filial = Filial.builder().descricao("Mix Mateus Jardim Tropical").build();
        Filial filialRetorno = Filial.builder().id(1L).descricao("Mix Mateus Jardim Tropical").build();

        Mockito.doNothing().when(filialMapper).salvar(filial);
        Mockito.doReturn(filialRetorno).when(filialMapper).recuperarPorId(filial.getId());

        filialService.salvar(filial);
        Assert.assertEquals(filialRetorno.getId(), filial.getId());
    }

    @Test(expected = CustomException.class)
    public void buscarFilialSemInformarId_DeveLancarException(){

        Filial filial = Filial.builder().descricao("Mix Mateus Jardim Tropical").build();

        filialService.recuperar(filial.getId());
    }

    @Test(expected = CustomException.class)
    public void salvarFilialSemDescricao_DeveLancarException(){
        Filial filial = Filial.builder().id(1L).build();
        Mockito.doNothing().when(filialMapper).salvar(filial);

        filialService.salvar(filial);
    }

    @Test(expected = CustomException.class)
    public void salvarFilialInformandoId_DeveLancarException(){
        Filial filial = Filial.builder()
                .id(1L).descricao("Mix Mateus Forquilha")
                .build();

        Mockito.doNothing().when(filialMapper).salvar(filial);

        filialService.salvar(filial);
    }

    @Test(expected = CustomException.class)
    public void atualizarFilialSemId(){
        Filial filial = Filial.builder()
                .descricao("Mix Mateus Forquilha")
                .build();
        Mockito.doNothing().when(filialMapper).atualizar(filial);

        filialService.atualizar(filial);
    }

}
