package br.com.sistemaponto.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.Statement;

public class InicializadorDataBase {

    public static void inicializar() {

        try {
            InputStream input = InicializadorDataBase.class.getResourceAsStream("/database.sql");
            String sql = new String(input.readAllBytes());

            Connection conn = Conexao.conectar();
            Statement stmt = conn.createStatement();

            stmt.execute(sql);
            stmt.close();
            conn.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
