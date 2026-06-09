package br.com.sistemaponto.model;

/**
 * Modelo de Dados do Usuário
 *
 * @author Vitor Hugo
 * @since 05/06/2026
 */
public class ModelUsuario {

    private int codigo;
    private int login;
    private String senha;
    private String tipo;

    /**
     * Construct
     *
     * @param login
     * @param senha
     */
    public ModelUsuario(int login, String senha, String tipo) {
        this.login = login;
        this.senha = senha;
        this.tipo = tipo;
    }

    public String getTipo() {
        return this.tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getLogin() {
        return login;
    }

    public void setLogin(int login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
}

