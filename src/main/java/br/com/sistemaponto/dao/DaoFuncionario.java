package br.com.sistemaponto.dao;

import br.com.sistemaponto.enumerados.EnumTipoFuncionario;
import br.com.sistemaponto.exception.ExceptionSistemaPonto;
import br.com.sistemaponto.interfaces.InterfaceDados;
import br.com.sistemaponto.model.ModelFuncionario;
import br.com.sistemaponto.model.ModelFuncionarioFixo;
import br.com.sistemaponto.model.ModelFuncionarioHorista;
import br.com.sistemaponto.util.Conexao;

import javax.lang.model.type.NullType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
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

    /**
     * Retorna os Funcionário de acordo com o tipo
     *
     * @param tipo
     * @return List<ModelFuncionario>
     */
    public List<ModelFuncionario> getFuncionariosFromTipo(String tipo) throws ExceptionSistemaPonto {
        List<ModelFuncionario> allFuncionarios = new ArrayList<>();

        String sql = """
            SELECT *
              FROM tbfuncionario
             WHERE funtipo LIKE ?;
        """;

        try (
            Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql);
        ) {
            stmt.setString(1, "%"+tipo+"%");
            ResultSet src = stmt.executeQuery();

            while (src.next()) {
                if (src.getString("funtipo").equals(EnumTipoFuncionario.FIXO)) {

                    ModelFuncionarioFixo Funcionario = new ModelFuncionarioFixo(
                            src.getString("funnome"),
                            src.getString("funcpf"),
                            src.getString("funtipo"),
                            src.getString("fundatanacimento"),
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
                            src.getDouble("funvalorhora")
                    );
                    Funcionario.setCodigo(src.getInt("funcodigo"));
                    allFuncionarios.add(Funcionario);
                }
                return allFuncionarios;
            }
            return null;

        } catch (Exception ex) {
            throw new ExceptionSistemaPonto(ex.getMessage());
        }
    }

    /**
     * Retorna o Funcionário de acordo com o nome
     *
     * @param nome
     * @return List<ModelFuncionario>
     */
    public List<ModelFuncionario> getFuncionariosFromNome(String nome) throws ExceptionSistemaPonto {
        List<ModelFuncionario> allFuncionarios = new ArrayList<>();

        String sql = """
             SELECT * 
               FROM tbfuncionario
              WHERE funnome LIKE ?;
        """;

        try (
            Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql);
        ) {
            stmt.setString(1, "%"+nome+"%");
            ResultSet src = stmt.executeQuery();

            while (src.next()) {
                if (src.getString("funtipo").equals(EnumTipoFuncionario.FIXO)) {

                    ModelFuncionarioFixo Funcionario = new ModelFuncionarioFixo(
                        src.getString("funnome"),
                        src.getString("funcpf"),
                        src.getString("funtipo"),
                        src.getString("fundatanacimento"),
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
                        src.getDouble("funvalorhora")
                    );
                    Funcionario.setCodigo(src.getInt("funcodigo"));
                    allFuncionarios.add(Funcionario);
                }
                return allFuncionarios;
            }
            return null;

        } catch (Exception ex) {
            throw new ExceptionSistemaPonto(ex.getMessage());
        }
    }

    /**
     * Retorna um Funcionário de acordo com o CPF
     *
     * @param cpf
     * @return ModelFuncionario
     * @throws ExceptionSistemaPonto
     */
    public ModelFuncionario getFuncionarioFromCpf(String cpf) throws ExceptionSistemaPonto {
        String sql = """
            SELECT *
              FROM tbfuncionario
             WHERE funcpf = ?;
        """;

        try (
            Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql);
        ) {
            stmt.setString(1, cpf);
            ResultSet src = stmt.executeQuery();

            if (src.next()) {
                if (src.getString("funtipo").equals(EnumTipoFuncionario.FIXO)) {
                    ModelFuncionarioFixo fixo = new ModelFuncionarioFixo(
                        src.getString("funnome"),
                        src.getString("funcpf"),
                        src.getString("funtipo"),
                        src.getString("fundatanascimento"),
                        src.getDouble("funsalario"),
                        src.getFloat("funcargahoraria")
                    );
                    return fixo;
                } else {
                    ModelFuncionarioHorista horista = new ModelFuncionarioHorista(
                            src.getString("funnome"),
                            src.getString("funcpf"),
                            src.getString("funtipo"),
                            src.getString("fundatanascimento"),
                            src.getDouble("funvalorhora")
                    );
                    return horista;
                }
             }
            return null;

        } catch (Exception ex) {
            throw new ExceptionSistemaPonto(ex.getMessage());
        }
    }

    @Override
    public ModelFuncionario getFromCodigo(int codigo) throws ExceptionSistemaPonto {
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
    public void salvar(Object obj) throws ExceptionSistemaPonto {
        if (!(obj instanceof ModelFuncionario)) {
            throw new ExceptionSistemaPonto("Funcionário inválido!");
        }
        ModelFuncionario Funcionario = (ModelFuncionario) obj;

        String sql = """
            INSERT INTO tbfuncionario (funnome, funcpf, funtipo, fundatanascimento, funcargahoraria, funsalario, funhorastrabalhadas, funvalorhora)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?); 
        """;

        try (
                Connection conn = Conexao.conectar();
                PreparedStatement stmt = conn.prepareStatement(sql);
        ) {
            stmt.setString(1, Funcionario.getNome());
            stmt.setString(2, Funcionario.getCPF());
            stmt.setString(3, Funcionario.getTipoFuncionario());
            stmt.setString(4, Funcionario.getDataNascimento());

            if (Funcionario.getTipoFuncionario().equals(EnumTipoFuncionario.FIXO)) {
                ModelFuncionarioFixo Fixo = (ModelFuncionarioFixo) Funcionario;
                stmt.setFloat(5, Fixo.getCargaHoraria());
                stmt.setDouble(6, Fixo.getSalarioBase());
                stmt.setNull(7, Types.NULL);

            } else {
                ModelFuncionarioHorista Horista = (ModelFuncionarioHorista) Funcionario;
                stmt.setNull(5, Types.NULL);
                stmt.setNull(6, Types.NULL);
                stmt.setDouble(7, Horista.getValorHora());
            }
            stmt.executeUpdate();
        } catch (Exception ex) {
            throw new ExceptionSistemaPonto("Funcinário inválido!");
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
            stmt.executeUpdate();

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
                   funcargahoraria   = ?,
                   funsalario        = ?,
                   funvalorhora      = ?
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

            if (((ModelFuncionario) obj).getTipoFuncionario().equals(EnumTipoFuncionario.FIXO)) {
                ModelFuncionarioFixo Fixo = (ModelFuncionarioFixo) obj;
                stmt.setFloat(5, Fixo.getCargaHoraria());
                stmt.setDouble(6, Fixo.getSalarioBase());
                stmt.setNull(7, Types.NULL);

            } else {
                ModelFuncionarioHorista Horista = (ModelFuncionarioHorista) obj;
                stmt.setNull(5, Types.NULL);
                stmt.setNull(6, Types.NULL);
                stmt.setDouble(7, Horista.getValorHora());
            }
            stmt.executeUpdate();
            return;

        } catch (Exception ex) {
            throw new ExceptionSistemaPonto("Dados inválidos");
        }
    }

    /**
     * Retorna todos os Funcionários cadastrados
     *
     * @return List<ModelFuncionario>
     * @throws ExceptionSistemaPonto
     */
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
//                            (new DaoUsuario()).getUsuarioFromCodigo(src.getInt("usucodigo")),
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
//                            (new DaoUsuario()).getUsuarioFromCodigo(src.getInt("usucodigo")),
                            src.getDouble("funvalorhora")
                    );
                    Funcionario.setCodigo(src.getInt("funcodigo"));
                    allFuncionarios.add(Funcionario);
                }
            }
            return allFuncionarios;

        } catch (Exception ex) {
            throw new ExceptionSistemaPonto(ex.getMessage());
        }
    }
}
