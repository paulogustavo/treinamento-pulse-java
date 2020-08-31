package br.com.pulse.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Estoque {

    private Long id;

    private Integer quantidade;

    private Produto produto;

    private Filial filial;


}
