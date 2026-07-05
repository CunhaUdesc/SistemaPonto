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
    private String tipoUsuario;
    private ModelFuncionario funcionario;  //tem q ver como vai ser relacionado o funcionario e o login

    /**
     * Construct
     *
     * @param login
     * @param senha
     * @param tipoUsuario
     * @param funcionario
     */
    public ModelUsuario(int login, String senha, String tipoUsuario, ModelFuncionario funcionario) {
        this.login = login;
        this.senha = senha;
        this.tipoUsuario = tipoUsuario;
        this.funcionario = funcionario;
    }

    public String getTipo() {
        return this.tipoUsuario;
    }

    public void setTipo(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
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
    
    public void setFuncionario(ModelFuncionario funcionario){
        this.funcionario = funcionario;
    }
    
    public ModelFuncionario getFuncionario(){  //teste por hora
        return funcionario;
    }
}

