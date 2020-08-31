package br.com.pulse.service;

import br.com.pulse.model.Produto;
import br.com.pulse.utils.Validator;

public interface ProdutoService {
    Produto recuperar(Long id);

    void salvar(Produto produto);

    void atualizar(Produto produto);

    <T> void validar(Validator<T> validator, T produto);
}
