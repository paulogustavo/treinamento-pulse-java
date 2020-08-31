package br.com.pulse.utils;

public interface Validator<T> {
    void valida(T objeto) throws ValidatorException;
}
