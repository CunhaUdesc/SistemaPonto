package br.com.sistemaponto.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe de teste de conexão como o Banco de Dados
 */
public class Conexao {

//    private static final String URL = "jdbc:postgresql://localhost:5434/sistema_ponto_padaria";
    private static final String URL = "jdbc:sqlite:sistemaponto.db";

//    private static final String USER = "postgres";
//    private static final String PASSWORD = "Vitor0209@";

    public static Connection conectar() throws SQLException {

        try {
            InputStream input = InicializadorDataBase.class.getResourceAsStream("/database.sql");
            if (input == null) {
                throw new RuntimeException("Arquivo database.sql não encontrado!");
            }
            String sql = new String(input.readAllBytes());

            return DriverManager.getConnection(URL);
        } catch (IOException ex) {

        }
        return null;
    }
}
