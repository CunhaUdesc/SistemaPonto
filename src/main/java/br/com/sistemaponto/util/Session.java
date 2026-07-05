package br.com.sistemaponto.util;
import br.com.sistemaponto.model.ModelUsuario;

/**
 * Sessão do Usuário Logado ao Sistema
 */
public final class Session {

    private static ModelUsuario usuario;

    /**
     * Construct
     */
    private Session() {}

    public static ModelUsuario getUsuario() {
        return usuario;
    }

    public static void setUsuario(ModelUsuario usuarioLogado) {
        usuario = usuarioLogado;
    }

    /**
     * Logout do Sistema
     */
    public static void logout() {
        usuario = null;
    }
}
