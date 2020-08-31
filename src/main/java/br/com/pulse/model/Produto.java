package br.com.pulse.model;

import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;

@Data
@Builder
public class Produto {

    private Long id;
    private String descricao;
    private BigDecimal valorUnitario;

}
