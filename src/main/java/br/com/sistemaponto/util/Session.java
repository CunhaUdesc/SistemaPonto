package br.com.sistemaponto.util;
import br.com.sistemaponto.model.ModelUsuario;

public final class Session {

    private static ModelUsuario usuario;

    private Session(){

    }

    public static ModelUsuario getUsuario() {
        return usuario;
    }

    public static void setUsuario(ModelUsuario usuarioLogado) {
        usuario = usuarioLogado;
    }

    public static void logout() {
        usuario = null;
    }
}
