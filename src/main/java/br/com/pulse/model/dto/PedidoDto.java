package br.com.pulse.model.dto;

import br.com.pulse.model.Pedido;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PedidoDto {

    private Pedido pedido;

}
