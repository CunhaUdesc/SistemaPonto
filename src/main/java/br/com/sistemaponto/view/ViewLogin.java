package br.com.sistemaponto.view;

import br.com.sistemaponto.controller.ControllerLogin;
import br.com.sistemaponto.exception.ExceptionLogin;
import br.com.sistemaponto.model.ModelUsuario;

import javax.swing.*;
import java.sql.SQLException;

/**
 * Tela de Login ao Sistema Ponto
 *
 * @author Vitor Hugo
 * @since 05//06/2026
 */
public class ViewLogin {

    private ControllerLogin Controller;

    /**
     * Construct
     *
     * @param controller
     */
    public ViewLogin(ControllerLogin controller) {
        this.Controller = controller;
    }

    /**
     * Autenticação de login do usuário
     *
     * @param login
     * @param senha
     */
    public void autenticarLogin(String login, String senha) {

        try {
            ModelUsuario usuario = Controller.autenticar(login, senha);

            if (usuario != null) {
                this.apresentaMensagem("Bem vindo! ");
            }

        } catch (ExceptionLogin exc) {
            this.apresentaMensagem(exc.getMessage());
        } catch (SQLException exc) {
            this.apresentaMensagem(exc.getMessage());
        }
    }

    /**
     * Apresenta uma mensagem na Tela
     *
     * @param msg
     */
    public void apresentaMensagem(String msg) {

        System.out.println(msg);
        //JOptionPane.showMessageDialog(null, msg);
    }

}
