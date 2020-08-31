package br.com.pulse.utils;

import java.math.BigDecimal;

public class ValorUnitarioValidator implements Validator<BigDecimal> {

    @Override
    public void valida(BigDecimal objeto) throws ValidatorException {
        if(objeto == null || objeto.compareTo(BigDecimal.valueOf(0)) <= 0){
            throw new ValidatorException("O valor unitário não pode ser nulo");
        }
    }
}
