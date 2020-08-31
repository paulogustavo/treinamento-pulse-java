package br.com.pulse.service;

import br.com.pulse.model.Estoque;

public interface EstoqueService {
    Estoque recuperarPorId(Long id);

    void salvar(Estoque estoque);

    void atualizar(Estoque estoque);

}
