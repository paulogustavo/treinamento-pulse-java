package br.com.pulse.model;

import lombok.Data;
import lombok.Builder;

@Data
@Builder
public class TipoPedido {

    public static final long ENTRADA = 1L;
    public static final long SAIDA = 2L;

    private Long id;
    private String descricao;

}
