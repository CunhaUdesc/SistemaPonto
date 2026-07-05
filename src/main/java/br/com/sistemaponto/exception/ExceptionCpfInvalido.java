package br.com.sistemaponto.exception;

/**
 * Exceção de CPF
 *
 * @author Rafael Boing
 */
public class ExceptionCpfInvalido extends Exception {

    /**
     * Construct
     *
     * @param msg
     */
    public ExceptionCpfInvalido(String msg) {
        super(msg);
    }
}
