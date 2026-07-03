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
    private int senha;
    private String tipoUsuario;
    private ModelFuncionario funcionario;  //tem q ver como vai ser relacionado o funcionario e o login

    /**
     * Construct
     *
     * @param login
     * @param senha
     */
    public ModelUsuario(int login, int senha, String tipoUsuario, ModelFuncionario funcionario) {
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

    public int getSenha() {
        return senha;
    }

    public void setSenha(int senha) {
        this.senha = senha;
    }
    
    public void setFuncionario(ModelFuncionario funcionario){
        this.funcionario = funcionario;
    }
    
    public ModelFuncionario getFuncionario(){  //teste por hora
        return funcionario;
    }
}

