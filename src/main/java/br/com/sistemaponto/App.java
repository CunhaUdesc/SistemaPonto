package br.com.sistemaponto;

import br.com.sistemaponto.dao.*;
import br.com.sistemaponto.model.*;
import br.com.sistemaponto.util.*;

import java.sql.Connection;

public class App {

    public static void main(String[] args) {

//        /* Teste de conexão com o Banco de Dados */
//        try {
//            Connection conn = Conexao.conectar();
//
//            System.out.println("Conectado!");
//
//        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
//            System.out.println("\n Trace: \n");
//            System.out.println(ex.getStackTrace());
//        }
        try {
//            InicializadorDataBase.inicializar();

            DaoFuncionario DAO = new DaoFuncionario();
            for (ModelFuncionario f : DAO.selectAll()) {
                System.out.println(f.toString() + "\n");
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }


    }
}
