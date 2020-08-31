package br.com.pulse.utils;

public class DescricaoValidator implements Validator<String> {
    @Override
    public void valida(String descricao) throws ValidatorException {
        if(descricao == null || descricao.trim().equals("")){
            throw new ValidatorException("Informe a senha");
        }
    }
}
