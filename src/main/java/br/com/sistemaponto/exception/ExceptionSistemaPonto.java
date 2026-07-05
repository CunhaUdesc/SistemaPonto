package br.com.sistemaponto.exception;

/**
 * Exception da consulta de registros no Banco de Dados
 *
 * @author Vitor Hugo da Cunha
 * @since 15/06/2026
 */
public class ExceptionSistemaPonto extends Exception {

    /**
     * Construct
     *
     * @param msg
     */
    public ExceptionSistemaPonto(String msg) {
        super(msg);
    }
}
