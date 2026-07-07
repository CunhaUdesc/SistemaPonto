package br.com.sistemaponto.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import br.com.sistemaponto.exception.ExceptionSistemaPonto;
import br.com.sistemaponto.interfaces.InterfaceDadosRegistroPonto;
import br.com.sistemaponto.model.ModelFuncionario;
import br.com.sistemaponto.model.ModelRegistroPonto;
import br.com.sistemaponto.util.Conexao;

/**
 * DAO do Registro de Ponto
 *
 * @author Rafael Boing
 * @since 03/07/2026
 */
public class DaoRegistroPonto implements InterfaceDadosRegistroPonto {

    /**
     * Construct
     */
    public DaoRegistroPonto() {

    }

    @Override
    public void salvarRegistro(ModelRegistroPonto registro) throws ExceptionSistemaPonto {

        if (registro.getCodigo() == 0) {
            inserirRegistro(registro);
        } else {
            atualizarRegistro(registro);
        }
    }

    private void inserirRegistro(ModelRegistroPonto registro) throws ExceptionSistemaPonto {

        String sql = """
            INSERT INTO tbregistroponto
            (
                regdataregistro,
                regentrada,
                funcodigo
            )
            VALUES
            (
                CURRENT_DATE,
                ?,
                ?
            )
        """;

        try (
            Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(
                sql,
                Statement.RETURN_GENERATED_KEYS
            )
        ) {

            stmt.setString(1, registro.getRegistroEntrada());
            stmt.setInt(2, registro.getFuncionario().getCodigo());

            stmt.executeUpdate();

            ResultSet src = stmt.getGeneratedKeys();

            if (src.next()) {
                registro.setCodigo(src.getInt(1));
            }

        } catch (Exception e) {
            throw new ExceptionSistemaPonto(e.getMessage());
        }
    }

    private void atualizarRegistro(ModelRegistroPonto registro)throws ExceptionSistemaPonto {

        String sql = """
            UPDATE tbregistroponto
            SET regentrada = ?,
                regsaidaintervalo = ?,
                regvoltaintervalo = ?,
                regsaidafinal = ?
            WHERE regcodigo = ?
        """;


        try (
            Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {

            stmt.setString(1, registro.getRegistroEntrada());
            stmt.setString(2, registro.getRegistroSaida());
            stmt.setString(3, registro.getRegistroEntradaIntervalo());
            stmt.setString(4, registro.getRegistroSaidaIntervalo());
            stmt.setInt(5, registro.getCodigo());

            stmt.executeUpdate();

        } catch (Exception e) {
            throw new ExceptionSistemaPonto(e.getMessage());
        }
    }

    /**
     * Retorna o Registro Ponto do Funcionário para determinado dia
     *
     * @param data
     * @param func
     * @return ModelRegistroPonto
     */
    public ModelRegistroPonto getRegistroPontoDiaFuncionario(String data, ModelFuncionario func)
            throws ExceptionSistemaPonto {

        String sql = """
            SELECT *
            FROM tbregistroponto
            WHERE regdataregistro = ?
            AND funcodigo = ?
        """;

        try (
            Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql);
        ) {

            stmt.setString(1, data);
            stmt.setInt(2, func.getCodigo());

            ResultSet src = stmt.executeQuery();

            if (src.next()) {
                return preencheModelo(src);
            }

            return null;

        } catch (Exception ex) {
            throw new ExceptionSistemaPonto(ex.getMessage());
        }
    }

    /**
     * Retorna os registros de determinada data
     *
     * @param data
     * @return List<ModelRegistroPonto>
     */
    public List<ModelRegistroPonto> getRegistrosFromData(String data, String data2) throws ExceptionSistemaPonto {
            List<ModelRegistroPonto> allRegistros = new ArrayList<>();

            String sql = """
                SELECT *
                  FROM tbregistroponto
            """;
        if (!data2.isEmpty()) {
            sql += "\n WHERE regdataregistro BETWEEN ? and ?";

        } else {
            sql += "\n WHERE regdataregistro = ?";
        }

        try (
            Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql);
        ) {
            stmt.setString(1, data);
            if (!data2.isEmpty()) {
                stmt.setString(2, data2);
            }
            return this.getRegistros(stmt);

        } catch (Exception ex) {
            throw new ExceptionSistemaPonto(ex.getMessage());
        }
    }

    /**
     * Preenche um modelo de dados com base em uma linha do Banco de Dados.
     *
     * @return ModelRegistroPonto
     */
    private ModelRegistroPonto preencheModelo(ResultSet src) throws ExceptionSistemaPonto {

        try {
            ModelRegistroPonto Registro = new ModelRegistroPonto();
            Registro.setCodigo(src.getInt("regcodigo"));
            Registro.setDiaAtual(src.getString("regdataregistro"));
            Registro.setRegistroEntrada(this.getDataHoraFormatada(src.getString("regentrada")));
            Registro.setRegistroSaida(this.getDataHoraFormatada(src.getString("regsaidaintervalo")));
            Registro.setRegistroEntradaIntervalo(this.getDataHoraFormatada(src.getString("regvoltaintervalo")));
            Registro.setRegistroSaidaIntervalo(this.getDataHoraFormatada(src.getString("regsaidafinal")));
            Registro.setFuncionario((new DaoFuncionario()).getFromCodigo(src.getInt("funcodigo")));


            return Registro;
        } catch (SQLException ex) {
            throw new ExceptionSistemaPonto(ex.getMessage());
        }
    }

    /**
     * Retorna os Registro Ponto de acordo com o Nome do Funcionário.
     *
     * @param nome
     * @return List<ModelRegistroPonto>
     */
    public List<ModelRegistroPonto> getRegistrosFromFuncionarioNome(String nome) throws ExceptionSistemaPonto {
        String sql = """
            SELECT *
              FROM tbregistroponto
             WHERE regnome = ?;
        """;

        try (
            Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql);
        ) {
            stmt.setString(1, nome);
            return this.getRegistros(stmt);

        } catch (Exception ex) {
            throw new ExceptionSistemaPonto(ex.getMessage());
        }
    }

    /**
     * Retorna os registros de acordo com um PreparedStatement.
     *
     * @param stmt
     * @return List<ModelRegistroPonto>
     * @throws ExceptionSistemaPonto
     */
    public List<ModelRegistroPonto> getRegistros(PreparedStatement stmt) throws ExceptionSistemaPonto {
        List<ModelRegistroPonto> allRegistros = new ArrayList<>();

        try {
            ResultSet src = stmt.executeQuery();
            while (src.next()) {
                allRegistros.add(preencheModelo(src));
            }
            return allRegistros;

        } catch (Exception ex) {
            throw new ExceptionSistemaPonto(ex.getMessage());
        }
    }

    /**
     * Retorna os registros com base no código do Funcionário
     *
     * @param codigo
     * @return List<ModelRegistroPonto>
     */
    public Set<ModelRegistroPonto> getRegistrosFromFuncionarioCodigo(int codigo) throws ExceptionSistemaPonto {
        String sql = """
            SELECT *
              FROM tbregistroponto
             WHERE funcodigo = ?;
        """;

        try (
                Connection conn = Conexao.conectar();
                PreparedStatement stmt = conn.prepareStatement(sql);
        ) {
            stmt.setInt(1, codigo);
            return new HashSet<>(this.getRegistros(stmt));
        } catch (Exception ex) {
            throw new ExceptionSistemaPonto(ex.getMessage());
        }
    }

    public List<ModelRegistroPonto> getRegistrosFromFuncionarioCpf(String cpf) throws ExceptionSistemaPonto {
        String sql = """
            SELECT * 
              FROM tbregistroponto
              JOIN tbfuncionario
             USING (funcodigo) 
             WHERE funcpf = ?;
        """;

        try (
                Connection conn = Conexao.conectar();
                PreparedStatement stmt = conn.prepareStatement(sql);
        ) {
            stmt.setString(1, cpf);
            return this.getRegistros(stmt);

        } catch (Exception ex) {
            throw new ExceptionSistemaPonto(ex.getMessage());
        }
    }

    /**
     * Retorna todos os Registros Ponto
     *
     * @return List<ModelRegistroPonto>
     */
    public List<ModelRegistroPonto> selectAll() throws ExceptionSistemaPonto {
        List<ModelRegistroPonto> allRegistros = new ArrayList<>();

        String sql = """
            SELECT *
              FROM tbregistroponto
        """;

        try (
            Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql);
        ) {
            ResultSet src = stmt.executeQuery();

            while (src.next()) {
                allRegistros.add(preencheModelo(src));
            }
            return allRegistros;

        } catch (Exception ex) {
            throw new ExceptionSistemaPonto(ex.getMessage());
        }
    }

    /**
     * Retorna a DataHora formatada no formato dd/mm/aa hh:mm:ss
     *
     * @param timestamp
     * @return String
     */
    private String getDataHoraFormatada(String timestamp) {

        if(timestamp == null)
            return null;

        LocalDateTime dataHora =
            LocalDateTime.parse(
                timestamp,
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
            );

        return dataHora.format(
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")
        );
    }

    public Set<ModelRegistroPonto> getRegistrosFromDia(String dia) throws ExceptionSistemaPonto {

        DateTimeFormatter formatoEntrada = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dataBanco = LocalDate.parse(dia, formatoEntrada).toString();

        String sql = """
            SELECT *
            FROM tbregistroponto
            WHERE regdataregistro = ?
        """;

        try (
            Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql);
        ) {

            stmt.setString(1, dataBanco);

            return new HashSet<>(this.getRegistros(stmt));

        } catch (Exception ex) {
            throw new ExceptionSistemaPonto(ex.getMessage());
        }
    }

    public Set<ModelRegistroPonto> getRegistrosFromAno(String ano) throws ExceptionSistemaPonto {
        String sql = """
            SELECT *
            FROM tbregistroponto
            WHERE regdataregistro BETWEEN ? AND ?
        """;

        try (
            Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql);
        ) {

            stmt.setString(1, ano + "-01-01");
            stmt.setString(2, ano + "-12-31");

            return new LinkedHashSet<>(this.getRegistros(stmt));

        } catch (Exception ex) {
            throw new ExceptionSistemaPonto(ex.getMessage());
        }
    }
}
