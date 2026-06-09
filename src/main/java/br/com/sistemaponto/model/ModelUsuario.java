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
    private int tipo;

    /**
     * Construct
     *
     * @param login
     * @param senha
     */
    public ModelUsuario(int login, String senha, int tipo) {
        this.login = login;
        this.senha = senha;
        this.tipo = tipo;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
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

