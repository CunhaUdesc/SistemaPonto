package br.com.sistemaponto.exception;

/**
 * Exceção do Registro Ponto
 *
 * @author Rafael Boing
 * @since 15/06/2026
 */
public class ExceptionLimiteRegistroPonto extends Exception {

    /**
     * Construct
     *
     * @param msg
     */
    public ExceptionLimiteRegistroPonto(String msg) {
        super(msg);
    }
}
