package br.com.sistemaponto.dao;

import br.com.sistemaponto.exception.ExceptionLogin;
import br.com.sistemaponto.exception.ExceptionSistemaPonto;
import br.com.sistemaponto.interfaces.InterfaceDados;
import br.com.sistemaponto.model.ModelFuncionario;
import br.com.sistemaponto.model.ModelUsuario;
import br.com.sistemaponto.util.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * Persistência de Dados do Usuário
 *
 * @author Vitor Hugo
 * @since 05/06/2026
 */
public class DaoUsuario implements InterfaceDados {

    private static Set<ModelUsuario> usuarios;

    /**
     * Construct
     */
    public DaoUsuario() {
        this.usuarios = new HashSet<ModelUsuario>();
    }

    @Override
    public void salvar(Object obj) throws ExceptionLogin {
        if (!(obj instanceof ModelUsuario)) {
            throw new ExceptionLogin("Usuário inválido!");
        }
        ModelUsuario Usuario = (ModelUsuario)obj;
        String sql = """
            INSERT INTO tbusuario
            (usulogin, ususenha, usutipo, funcodigo)
            VALUES (?, ?, ?, ?);
        """;

        try {
            Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1, Usuario.getLogin());
            stmt.setString(2, Usuario.getSenha());
            stmt.setString(3, Usuario.getTipo());
            stmt.setInt(4, Usuario.getFuncionario().getCodigo());

            stmt.executeUpdate();

        } catch (Exception ex) {
            throw new ExceptionLogin(ex.getMessage());
        }
    }

    @Override
    public void alterar(Object usuario) {}

    @Override
    public void excluir(int codigo) throws ExceptionSistemaPonto {
        String sql = """
            DELETE 
              FROM tbusuario
             WHERE usucodigo = ? 
        """;

        try {
            Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1, codigo);
            stmt.executeUpdate();

        } catch (Exception e) {
            throw new ExceptionSistemaPonto("Usuário não encontrado!");
        }
    }

    @Override
    public ModelUsuario getFromCodigo(int codigo) throws ExceptionLogin {
        String sql = """
            SELECT * 
              FROM tbusuario
             WHERE usucodigo = ?;
        """;

        try (
                Connection conn = Conexao.conectar();
                PreparedStatement stmt = conn.prepareStatement(sql);
        ) {
            stmt.setInt(1, codigo);
            ResultSet src = stmt.executeQuery();

            if (src.next()) {
                ModelUsuario Usuario = new ModelUsuario(
                    src.getInt("usulogin"),
                    src.getString("ususenha"),
                    src.getString("usutipo"),
                    (new DaoFuncionario()).getFromCodigo(src.getInt("funcodigo"))
                );
                Usuario.setCodigo(src.getInt("usucodigo"));
                return Usuario;
            }
            return null;

        } catch (Exception ex) {
            throw new ExceptionLogin("Nenhum funcionário encontrado!");
        }
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    /**
     * Adiciona um novo usuário à lista
     *
     * @param usu
     */
    public void addUsuario(ModelUsuario usu) {
        usuarios.add(usu);
    }

    /**
     * Autentica o Usuário de acordo com o login e senha.
     *
     * @param login
     * @param senha
     * @return ModelUsuario
     */
    public ModelUsuario autenticar(int login, String senha) {
        for (ModelUsuario usu : usuarios) {
            if (usu.getLogin() == login && usu.getSenha().equals(senha)) {
                return usu;
            }
        }
        return null;
    }
}
