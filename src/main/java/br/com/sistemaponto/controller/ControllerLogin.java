package br.com.sistemaponto.controller;

import br.com.sistemaponto.dao.DaoUsuario;
import br.com.sistemaponto.exception.ExceptionLogin;
import br.com.sistemaponto.model.ModelUsuario;
import br.com.sistemaponto.util.Conexao;
import br.com.sistemaponto.view.ViewLogin;

import java.sql.SQLException;

/**
 * Controlador de Login do Sistema Ponto
 *
 * @author Vitor Hugo
 * @since 05/06/2026
 */
public class ControllerLogin {

    private DaoUsuario Dao;

    /**
     * Construct
     *
     * @param dao
     */
    public ControllerLogin(DaoUsuario dao) {
        this.Dao = dao;
    }

    /**
     * Autenticação de Usuário
     *
     * @param cod
     * @param senha
     * @return ModelUsuario
     */
    public ModelUsuario autenticar(String cod, String senha) throws ExceptionLogin, SQLException {

        try {
            int login = Integer.parseInt(cod);
            ModelUsuario Usuario = Dao.autenticar(login, senha);

            if (Usuario == null) {
                throw new ExceptionLogin("Usuário inválido!");
            }

            return Usuario;

        } catch (NumberFormatException e) {
            throw new ExceptionLogin("Login inválido!");
        }
    }

}
