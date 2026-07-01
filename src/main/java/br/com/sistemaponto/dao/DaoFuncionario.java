package br.com.sistemaponto.dao;

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
 * @package dao
 * @author Vitor Hugo
 * @since 26/06
 */
public class DaoFuncionario implements InterfaceDados {

    // Armazenamento temporário dos funcionários
    private static List<ModelFuncionario> funcionarios;

    /** Filtros a fazer
     * Nome funcionario
     * CPF funcionario
     * Tipo funcionario
     * /

    /**
     * Construct
     */
    public DaoFuncionario() {
        this.funcionarios = new ArrayList<>();
    }

    @Override
    public void salvar(Object obj) {
        ModelFuncionario funcionario = (ModelFuncionario) obj;
        String sql = """
            INSERT INTO tbfuncionario (funnome, funcpf, funtipo, funcargahoraria, funsalario, usucodigo, regcodigo)
            VALUES (?, ?, ?, ?, ?, ?, ?); 
        """;

        try (
                Connection conn = Conexao.conectar();
                PreparedStatement stmt = conn.prepareStatement(sql);
        ) {
            // O código do funcionário é gerado automaticamente pelo Banco de Dados
            stmt.setString(1, funcionario.getNome());
            stmt.setString(2, funcionario.getCPF());
            stmt.setString(3, funcionario.getTipoFuncionario());
//            stmt.setFloat(4, funcionario.getCargahoraria());
//            stmt.setDouble(5, funcionario.getSalario());
            stmt.setInt(6, funcionario.getUsuario().getCodigo());

        } catch (Exception ex) {

        }
    }

    @Override
    public ModelFuncionario selectFromCodigo(int codigo) throws ExceptionSistemaPonto {
        String sql = """
            SELECT * 
              FROM tbfuncionario 
             WHERE funcodigo = ?;                
        """;

        try (
            Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql);
        ) {
            stmt.setInt(1, codigo);
            ResultSet src = stmt.executeQuery();

            if (src.next()) {
                ModelFuncionario Funcionario = new ModelFuncionario(
                        src.getString("funnome"),
                        src.getString("funcpf"),
                        src.getString("funtipo"),
//                        src.getInt("funcargahoraria"),
//                        src.getDouble("funsalario"),
                        src.getString("fundatanacimento"),
                        (new DaoUsuario()).getUsuarioFromCodigo(src.getInt("usucodigo"))
                );
                Funcionario.setCodigo(src.getInt("funcodigo"));
                return Funcionario;
            }
            return null;
        } catch (Exception ex) {
            throw new ExceptionSistemaPonto("Funcionário não encontrado!");
        }
    }

    @Override
    public void excluir(int codigo) throws ExceptionSistemaPonto {
        String sql = """
            DELETE 
              FROM tbfuncionario
             WHERE funcodigo = ?;
        """;

        try (
            Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql);
        ) {
            stmt.executeQuery();

        } catch (Exception ex) {
            throw new ExceptionSistemaPonto("Funcionário não encontrado!");
        }
    }

    @Override
    public void alterar(Object obj) throws ExceptionSistemaPonto {
        String sql = """
            UPDATE tbfuncionario
               SET nome     = ?,
                   cpf      = ?,
                   telefone = ?,
                   endereco = ?
             WHERE id = ?;
        """;

        try (
            Connection conn = Conexao.conectar();

        ) {

        } catch (Exception ex) {
            throw new ExceptionSistemaPonto("Dados inválidos");
        }
    }

    @Override
    public List<ModelFuncionario> selectAll() throws ExceptionSistemaPonto {
        List<ModelFuncionario> allFuncionarios = new ArrayList<>();

        String sql = "SELECT * FROM tbfuncionario";

        try (
            Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql);
        ) {
            ResultSet src = stmt.executeQuery();

            while (src.next()) {
                ModelFuncionario Funcionario = new ModelFuncionario (
                    src.getString("funnome"),
                    src.getString("funcpf"),
                    src.getString("funtipo"),
//                    src.getInt("funcargahoraria"),
//                    src.getDouble("funsalario"),
                    src.getString("fundatanacimento"),
                    (new DaoUsuario()).getUsuarioFromCodigo(src.getInt("usucodigo"))
                );
                Funcionario.setCodigo(src.getInt("funcodigo"));
                allFuncionarios.add(Funcionario);
            }
            return allFuncionarios;
        } catch (SQLException ex) {
            throw new ExceptionSistemaPonto("Registro não encontrado!");
        }
    }

    public ModelFuncionario getFuncionarioFromCodigo(int codigo) throws ExceptionSistemaPonto {
        String sql = """
            SELECT * 
              FROM tbfuncionario
             WHERE funcodigo = ?;
        """;

        try (
            Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql);
        ) {
            stmt.setInt(1, codigo);
            ResultSet src = stmt.executeQuery();

            if (src.next()) {
                ModelFuncionario Funcionario = new ModelFuncionario (
                        src.getString("funnome"),
                        src.getString("funcpf"),
                        src.getString("funtipo"),
//                        src.getInt("funcargahoraria"),
//                        src.getDouble("funsalario"),
                        src.getString("fundatanacimento"),
                        (new DaoUsuario()).getUsuarioFromCodigo(src.getInt("usucodigo"))
                );
                Funcionario.setCodigo(codigo);
                return Funcionario;
            }
            return null;
        } catch (Exception ex) {
            throw new ExceptionSistemaPonto("Funcionário não encontrado");
        }
    }

}
