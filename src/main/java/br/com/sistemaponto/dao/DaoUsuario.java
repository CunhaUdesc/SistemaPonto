package br.com.sistemaponto.dao;

import br.com.sistemaponto.exception.ExceptionLogin;
import br.com.sistemaponto.interfaces.InterfaceDados;
import br.com.sistemaponto.model.ModelUsuario;
import br.com.sistemaponto.util.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
    
    public DaoUsuario() {
        this.usuarios = new HashSet<ModelUsuario>();
    }

    @Override
    public List<Iterator> select(Object usuario) {
        // Poderá receber o usuário com alguma informação, para assim consultar no BD de acordo com
        // as informações preenchidas no modelo.

        return List.of();
    }

    @Override
    public void salvar(Object obj) throws SQLException, ExceptionLogin {
        ModelUsuario Usuario = (ModelUsuario)obj;
        String sql = """
            INSERT INTO tbusuario
            (login, senha, perfil)
            VALUES (?, ?, ?)
        """;

        try {
            Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1, Usuario.getLogin());
            stmt.setString(2, Usuario.getSenha());
            stmt.setString(3, Usuario.getTipo());

            stmt.executeUpdate();

        } catch (SQLException ex) {
            throw new ExceptionLogin(ex.getMessage());
        }
    }

    @Override
    public void alterar(Object usuario) {}

    @Override
    public void excluir(Object usuario) {
        ModelUsuario Usuario = (ModelUsuario) usuario;
        String sql = """
            DELETE 
              FROM tbusuario
             WHERE usucodigo = ? 
        """;

        try {
            Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1, Usuario.getCodigo());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    /**
     * Retorna o usuário de acordo com o código
     *
     * @param codigo
     * @return
     */
    public ModelUsuario getUsuarioFromCodigo(int codigo) {
        return new ModelUsuario(0, "", "");
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public List<Iterator> selectAll() {
        return new ArrayList<>();
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
