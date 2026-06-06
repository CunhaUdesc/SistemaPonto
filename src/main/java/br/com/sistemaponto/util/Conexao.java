package br.com.sistemaponto.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe de teste de conexão como o Banco de Dados
 */
public class Conexao {

    private static final String URL = "jdbc:postgresql://localhost:5434/sistema_ponto_padaria";

    private static final String USER = "postgres";
    private static final String PASSWORD = "Vitor0209@";

    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
