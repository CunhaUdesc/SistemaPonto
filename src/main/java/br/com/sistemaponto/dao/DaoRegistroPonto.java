package br.com.sistemaponto.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    private static Map<String, ModelRegistroPonto> registros;

    /**
     * Construct
     */
    public DaoRegistroPonto() {
        this.registros = new HashMap<>();
    }

    @Override
    public void salvarRegistro(ModelRegistroPonto registro) throws ExceptionSistemaPonto {
        String sql = """
            INSERT INTO tbregistroponto (regdataregistro, regentrada, regsaidaintervalo, regvoltaintervalo, regsaidafinal, funcodigo)
            VALUES (?, ?, ?, ?, ?, ?);
        """;

        try (
            Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql);
        ) {
            stmt.setString(1, registro.getDiaAtual());
            stmt.setString(2, registro.getRegistroEntrada());
            stmt.setString(3, registro.getRegistroSaidaIntervalo());
            stmt.setString(4, registro.getRegistroEntradaIntervalo());
            stmt.setString(5, registro.getRegistroSaida());
            stmt.setInt(6, registro.getFuncionario().getCodigo());

        } catch(Exception e){
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
    public List<ModelRegistroPonto> getRegistroPontoDiaFuncionario(String data, ModelFuncionario func) throws ExceptionSistemaPonto {
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
            return this.getRegistros(stmt);

        } catch (Exception ex) {
            throw new ExceptionSistemaPonto("Nenhum registro encontrado!");
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
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");


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
    public List<ModelRegistroPonto> getRegistrosFromFuncionarioCodigo(int codigo) throws ExceptionSistemaPonto {
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
            return this.getRegistros(stmt);

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
        LocalDateTime dataHora = LocalDateTime.parse(timestamp, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        return dataHora.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
    }
}
