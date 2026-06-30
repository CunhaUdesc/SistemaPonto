package br.com.sistemaponto.exception;

/**
 * Exception da consulta de registros no Banco de Dados
 */
public class ExceptionSistemaPonto extends Exception {

    /**
     * Construct
     * @param msg
     */
    public ExceptionSistemaPonto(String msg) {
        super(msg);
    }
}
