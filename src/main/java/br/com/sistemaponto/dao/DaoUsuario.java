package br.com.sistemaponto.dao;

import br.com.sistemaponto.exception.ExceptionLogin;
import br.com.sistemaponto.model.ModelUsuario;
import br.com.sistemaponto.util.Conexao;

import java.sql.SQLException;
import java.util.*;

/**
 * Persistência de Dados do Usuário
 *
 * @author Vitor Hugo
 * @since 05/06/2026
 */
public class DaoUsuario {

    private Set<ModelUsuario> usuarios = new HashSet<ModelUsuario>();

    /**
     * Adiciona um novo usuário à lista
     *
     * @param usu
     */
    public void addUsuario(ModelUsuario usu) {
        this.usuarios.add(usu);
    }

    /**
     * Autentica o Usuário de acordo com o login e senha.
     *
     * @param login
     * @param senha
     * @return ModelUsuario
     */
    public ModelUsuario autenticar(int login, String senha) {
        for (ModelUsuario usu : this.usuarios) {
            if (usu.getLogin() == login) {
                return usu;
            }
        }
        return null;
    }

}
