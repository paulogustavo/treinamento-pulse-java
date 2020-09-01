package br.com.pulse.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Estoque {

    private Long id;

    private Integer quantidade;

    private Produto produto;

    private Filial filial;


}
