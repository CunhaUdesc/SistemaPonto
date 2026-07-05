package br.com.sistemaponto.exception;

/**
 * Exceção de Valor Inválido
 *
 * @author Rafael Boing
 * @since 20/06/2026
 */
public class ExceptionValorInvalido extends Exception {

    /**
     * Construct
     * 
     * @param msg
     */
    public ExceptionValorInvalido(String msg) {
        super(msg);
    }
}
