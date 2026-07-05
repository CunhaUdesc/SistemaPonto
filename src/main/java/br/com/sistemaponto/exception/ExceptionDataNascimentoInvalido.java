package br.com.sistemaponto.exception;

/**
 * Exceção da Data de Nascimento
 *
 * @author Rafel Boing
 * @since 15/06/2026
 */
public class ExceptionDataNascimentoInvalido extends Exception {

    /**
     * Construct
     *
     * @param msg
     */
    public ExceptionDataNascimentoInvalido(String msg) {
        super(msg);
    }
}
