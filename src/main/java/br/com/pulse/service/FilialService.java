package br.com.pulse.service;

import br.com.pulse.model.Filial;

public interface FilialService {
    Filial recuperar(Long id);

    void salvar(Filial filial);

    void atualizar(Filial filial);
}
