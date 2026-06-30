package br.com.sistemaponto;

import br.com.sistemaponto.util.Conexao;

import java.sql.Connection;

public class App {

    public static void main(String[] args) {

        /* Teste de conexão com o Banco de Dados */
        try {
            Connection conn = Conexao.conectar();

            System.out.println("Conectado!");

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            System.out.println("\n Trace: \n");
            System.out.println(ex.getStackTrace());
        }

    }
}
