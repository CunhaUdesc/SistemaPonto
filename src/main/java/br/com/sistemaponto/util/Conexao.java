package br.com.sistemaponto.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Classe de conexão com o Banco de Dados
 *
 * @author Vitor Hugo da Cunha
 * @since 06/06/2026
 */
public class Conexao {

    /* URL de conexão com SQLite */
    private static final String URL = "jdbc:sqlite:database/sistemaponto.db";

    /**
     * Conexão com o SQLite
     *
     * @return Connection
     * @throws Exception
     * @throws SQLException
     */
    public static Connection conectar() throws Exception, SQLException {

        try {
          Connection conn = DriverManager.getConnection(URL);

            try (Statement stmt = conn.createStatement()) {
                stmt.execute("PRAGMA foreign_keys = ON;");
            }

            System.out.println("Conectado em:");
            System.out.println(conn.getMetaData().getURL());

            return conn;
        } catch (SQLException ex) {
            throw new SQLException(ex.getMessage());

        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }
}
