package br.com.pulse.model.dto;

import br.com.pulse.model.Filial;
import lombok.*;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FilialDto {

    private Filial filial;

    public Filial getFilial() {
        return filial;
    }

    public void setFilial(Filial filial) {
        this.filial = filial;
    }
}
