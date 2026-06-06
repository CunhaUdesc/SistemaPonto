package br.com.sistemaponto.model;

/**
 * Modelo de Dados da Sessão do Usuário
 *
 * @author Vitor Hugo
 * @since 06/06/2026
 */
public class ModelSession {

    private ModelUsuario Usuario;

    /**
     * Construct
     *
     * @param Usu
     */
    public ModelSession(ModelUsuario Usu) {
        this.Usuario = Usu;
    }

    /**
     * Retorna o Usuário da Sessão atual
     *
     * @return ModelUsuario
     */
    public ModelUsuario getUsuario() {
        return this.Usuario;
    }
}
