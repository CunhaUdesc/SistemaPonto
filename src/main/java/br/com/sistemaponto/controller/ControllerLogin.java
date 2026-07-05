package br.com.sistemaponto.controller;

import java.sql.SQLException;
import br.com.sistemaponto.dao.DaoUsuario;
import br.com.sistemaponto.exception.ExceptionLogin;
import br.com.sistemaponto.model.ModelUsuario;
import br.com.sistemaponto.util.Session;
import br.com.sistemaponto.view.ViewLogin;
import br.com.sistemaponto.view.ViewMenu;

/**
 * Controlador de Login do Sistema Ponto
 *
 * @author Vitor Hugo
 * @since 05/06/2026
 */
public class ControllerLogin {

    /** DaoUsuario */
    private DaoUsuario Dao;
    /** ViewLogin */
    private ViewLogin viewLogin;

    /**
     * Construct
     *
     * @param dao
     * @param viewLogin
     */
    public ControllerLogin(DaoUsuario dao, ViewLogin viewLogin) {
        this.Dao = dao;
        this.viewLogin = viewLogin;
        this.viewLogin.apresentarTela();
        this.adicionarAcoes();
    }

    /**
     * Chamada do menu
     */
    public void chamarMenu() {
        new ControllerMenu(new ViewMenu());
        this.viewLogin.setVisible(false);
    }

    /**
     * Adiciona as Ações aos Botões
     */
    public void adicionarAcoes() {
        this.viewLogin.adcionarAcaoBtnEntrar(a -> this.autenticarLogin());
    }

    /**
     * Autenticação de Usuário
     *
     * @param cod
     * @param senha
     * @return ModelUsuario
     */
     public void autenticarLogin() {
        int login = 0;
        String senha;
           
        try {
            login = Integer.parseInt(viewLogin.getLogin());

        } catch (Exception e) {
            this.viewLogin.apresentaMensagem("Login Inválido!");
            return;
        }
        try {
            senha = this.viewLogin.getSenha();
               
            if (senha == null || senha.trim().isEmpty()) {
                this.viewLogin.apresentaMensagem("Senha incorreta!");
                    return;
            }

        } catch(Exception e) {
            this.viewLogin.apresentaMensagem("Senha incorreta!");
        }
        ModelUsuario Usuario = Dao.autenticar(login, viewLogin.getSenha());

        if (Usuario != null) {
            Session.setUsuario(Usuario);
            this.viewLogin.apresentaMensagem("Bem vindo! ");
            this.chamarMenu();
            return;
        }
        this.viewLogin.apresentaMensagem("Nenhum Usuário Encontrado!");
     }

    /**
     * Autenticação do Usuário
     *
     * @param cod
     * @param senha
     * @return ModelUsuario
     * @throws ExceptionLogin
     * @throws SQLException
     */
    public ModelUsuario autenticar(String cod, String senha) throws ExceptionLogin, SQLException {

        try {
            int login = Integer.parseInt(cod);
            ModelUsuario Usuario = this.Dao.autenticar(login, senha);

            if (Usuario == null) {
                throw new ExceptionLogin("Usuário inválido!");
            }
            return Usuario;

        } catch (NumberFormatException e) {
            throw new ExceptionLogin("Login inválido!");
        }
    }

}
