package br.com.sistemaponto.util;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.Statement;

public class InicializadorDataBase {

    public static void inicializar() {

        try {
            /* Busca o arquivo 'database.sql' dentro da pasta resource */
            InputStream input = InicializadorDataBase.class.getResourceAsStream("/database.sql");
            if (input == null) {
                throw new IllegalStateException("Arquivo 'database.sql não encontrado.'");
            }
            System.out.println("Arquivo encontrado \n");

            try (
                /* Abre uma conexão e executa o SQL */
                Connection conn = Conexao.conectar();
                Statement stmt = conn.createStatement();
            ) {

            /* Lê o arquivo e transforma em String */
            String sql = new String(input.readAllBytes(), StandardCharsets.UTF_8);
            String[] comandos = sql.split(";");
            System.out.println("Arquivo lido");

            for (String comando : comandos) {
                if (comando.trim().isEmpty()) {
                    continue;
                }
                System.out.println("Executando comando: " + comando);
                stmt.execute(comando);
            }

            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

}
