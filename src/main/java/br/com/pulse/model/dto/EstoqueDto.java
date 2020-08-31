package br.com.pulse.model.dto;

import br.com.pulse.model.Estoque;
import br.com.pulse.model.Filial;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EstoqueDto {

    private Estoque estoque;

    public Estoque getEstoque() {
        return estoque;
    }

    public void setEstoque(Estoque estoque) {
        this.estoque = estoque;
    }
}
