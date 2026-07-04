package br.com.sistemaponto.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Classe de teste de conexão como o Banco de Dados
 */
public class Conexao {

    /* URL de conexão com SQLite */
    private static final String URL = "jdbc:sqlite:database/sistemaponto.db";

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
