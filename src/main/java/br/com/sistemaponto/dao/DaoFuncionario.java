package br.com.sistemaponto.dao;

import br.com.sistemaponto.enumerados.EnumTipoFuncionario;
import br.com.sistemaponto.exception.ExceptionSistemaPonto;
import br.com.sistemaponto.interfaces.InterfaceDados;
import br.com.sistemaponto.model.ModelFuncionario;
import br.com.sistemaponto.model.ModelFuncionarioFixo;
import br.com.sistemaponto.model.ModelFuncionarioHorista;
import br.com.sistemaponto.util.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

/**
 * DAO do Funcionário
 *
 * @package dao
 * @author Vitor Hugo
 * @since 26/06
 */
public class DaoFuncionario implements InterfaceDados {

    // Armazenamento temporário dos funcionários
    private static List<ModelFuncionario> funcionarios;

    /**
     * Construct
     */
    public DaoFuncionario() {
        this.funcionarios = new ArrayList<>();
    }

    /** Filtros a fazer
     * Nome funcionario
     * CPF funcionario
     * Tipo funcionario
     */

    public List<ModelFuncionario> getFuncionariosFromNome(String nome) {
        return new ArrayList<ModelFuncionario>();
    }

    public void getFuncionarioFromCpf(String cpf) {
//        return new ModelFuncionario();
    }

    public List<ModelFuncionario> getFuncionariosFromTipo(String tipo) {
        return new ArrayList<ModelFuncionario>();
    }

    @Override
    public void salvar(Object obj) throws ExceptionSistemaPonto {
        if (!(obj instanceof ModelFuncionario)) {
            throw new ExceptionSistemaPonto("Funcionário inválido!");
        }
        ModelFuncionario Funcionario = (ModelFuncionario) obj;

        String sql = """
            INSERT INTO tbfuncionario (funnome, funcpf, funtipo, fundatanascimento, funcargahoraria, funsalario, funhorastrabalhadas, funvalorhora, usucodigo)
            VALUES (?, ?, ?, ?, ?, ?, ?);
        """;

        try (
                Connection conn = Conexao.conectar();
                PreparedStatement stmt = conn.prepareStatement(sql);
        ) {
            // O código do funcionário é gerado automaticamente pelo Banco de Dados
            stmt.setString(1, Funcionario.getNome());
            stmt.setString(2, Funcionario.getCPF());
            stmt.setString(3, Funcionario.getTipoFuncionario());
            stmt.setString(4, Funcionario.getDataNascimento());
            stmt.setInt(9, Funcionario.getUsuario().getCodigo());

            if (Funcionario.getTipoFuncionario().equals(EnumTipoFuncionario.FIXO)) {
                ModelFuncionarioFixo Fixo = (ModelFuncionarioFixo) Funcionario;
                stmt.setFloat(5, Fixo.getCargaHoraria());
                stmt.setDouble(6, Fixo.getSalarioBase());
            } else {
                ModelFuncionarioHorista Horista = (ModelFuncionarioHorista) Funcionario;
                stmt.setFloat(7, Horista.getHorasTrabalhadas());
                stmt.setDouble(8, Horista.getValorHora());
            }

        } catch (Exception ex) {
            throw new ExceptionSistemaPonto("Funcinário inválido!");
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
                if (src.getString("funtipo").equals(EnumTipoFuncionario.FIXO)) {
                    ModelFuncionarioFixo Funcionario = new ModelFuncionarioFixo(
                        src.getString("funnome"),
                        src.getString("funcpf"),
                        src.getString("funtipo"),
                        src.getString("fundatanacimento"),
                        (new DaoUsuario()).getUsuarioFromCodigo(src.getInt("usucodigo")),
                        src.getDouble("funsalario"),
                        src.getFloat("funcargahoraria")
                    );
                    Funcionario.setCodigo(src.getInt("funcodigo"));
                    return Funcionario;

                } else {
                    ModelFuncionarioHorista Funcionario = new ModelFuncionarioHorista(
                        src.getString("funnome"),
                        src.getString("funcpf"),
                        src.getString("funtipo"),
                        src.getString("fundatanacimento"),
                        (new DaoUsuario()).getUsuarioFromCodigo(src.getInt("usucodigo")),
                        src.getDouble("funvalorhora")
                    );
                    Funcionario.setCodigo(src.getInt("funcodigo"));
                    return Funcionario;
                }
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
        if (!(obj instanceof ModelFuncionario)) {
            throw new ExceptionSistemaPonto("Funcionário inválido!");
        }

        String sql = """
            UPDATE tbfuncionario
               SET funnome           = ?,
                   funcpf            = ?,
                   funtipo           = ?,
                   fundatanascimento = ?,
                   funusuario        = ?,
                   funcargahoraria   = ?,
                   funsalario        = ?,
                   funvalorhora      = ?,
             WHERE id = ?;
        """;

        try (
            Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql);

        ) {
            stmt.setString(1, ((ModelFuncionario) obj).getNome());
            stmt.setString(2, ((ModelFuncionario) obj).getCPF());
            stmt.setString(3, ((ModelFuncionario) obj).getTipoFuncionario());
            stmt.setString(4, ((ModelFuncionario) obj).getDataNascimento());
            stmt.setInt(5, ((ModelFuncionario) obj).getCodigo());

            if (((ModelFuncionario) obj).getTipoFuncionario().equals(EnumTipoFuncionario.FIXO)) {
                ModelFuncionarioFixo Fixo = (ModelFuncionarioFixo) obj;
                stmt.setFloat(6, Fixo.getCargaHoraria());
                stmt.setDouble(7, Fixo.getSalarioBase());

            } else {
                ModelFuncionarioHorista Horista = (ModelFuncionarioHorista) obj;
                stmt.setDouble(8, Horista.getValorHora());
            }
            stmt.executeQuery();
            return;

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
                if (src.getString("funtipo").equals(EnumTipoFuncionario.FIXO)) {
                    ModelFuncionarioFixo Funcionario = new ModelFuncionarioFixo(
                            src.getString("funnome"),
                            src.getString("funcpf"),
                            src.getString("funtipo"),
                            src.getString("fundatanacimento"),
                            (new DaoUsuario()).getUsuarioFromCodigo(src.getInt("usucodigo")),
                            src.getDouble("funsalario"),
                            src.getFloat("funcargahoraria")
                    );
                    Funcionario.setCodigo(src.getInt("funcodigo"));
                    allFuncionarios.add(Funcionario);

                } else {
                    ModelFuncionarioHorista Funcionario = new ModelFuncionarioHorista(
                            src.getString("funnome"),
                            src.getString("funcpf"),
                            src.getString("funtipo"),
                            src.getString("fundatanacimento"),
                            (new DaoUsuario()).getUsuarioFromCodigo(src.getInt("usucodigo")),
                            src.getDouble("funvalorhora")
                    );
                    Funcionario.setCodigo(src.getInt("funcodigo"));
                    allFuncionarios.add(Funcionario);
                }
            }
            return allFuncionarios;

        } catch (Exception ex) {
            throw new ExceptionSistemaPonto("Registro não encontrado!");
        }
    }

    /**
     * Retorna um Funcionário de acordo com o código recebido.
     *
     * @param codigo
     * @return ModelFuncionario
     * @throws ExceptionSistemaPonto
     */
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
                if (src.getString("funtipo").equals(EnumTipoFuncionario.FIXO)) {
                    ModelFuncionarioFixo Funcionario = new ModelFuncionarioFixo(
                            src.getString("funnome"),
                            src.getString("funcpf"),
                            src.getString("funtipo"),
                            src.getString("fundatanacimento"),
                            (new DaoUsuario()).getUsuarioFromCodigo(src.getInt("usucodigo")),
                            src.getDouble("funsalario"),
                            src.getFloat("funcargahoraria")
                    );
                    Funcionario.setCodigo(src.getInt("funcodigo"));
                    return Funcionario;

                } else {
                    ModelFuncionarioHorista Funcionario = new ModelFuncionarioHorista(
                            src.getString("funnome"),
                            src.getString("funcpf"),
                            src.getString("funtipo"),
                            src.getString("fundatanacimento"),
                            (new DaoUsuario()).getUsuarioFromCodigo(src.getInt("usucodigo")),
                            src.getDouble("funvalorhora")
                    );
                    Funcionario.setCodigo(src.getInt("funcodigo"));
                    return Funcionario;
                }
            }
            return null;
        } catch (Exception ex) {
            throw new ExceptionSistemaPonto("Funcionário não encontrado");
        }
    }

}
