package br.com.sistemaponto;

import java.sql.Connection;
import java.util.Scanner;

import br.com.sistemaponto.controller.ControllerLogin;
import br.com.sistemaponto.dao.DaoUsuario;
import br.com.sistemaponto.enumerados.EnumTipoUsuario;
import br.com.sistemaponto.model.ModelUsuario;
import br.com.sistemaponto.util.Conexao;
import br.com.sistemaponto.view.ViewLogin;

/**
 * Teste de Conexão
 */
public class Main {
    
    public static final String versao= "V 1.0.0";

    public static void main(String[] args) {
        
        DaoUsuario dao = new DaoUsuario();
  //      ControllerLogin controller = new ControllerLogin(dao);

        dao.addUsuario(new ModelUsuario(1, "Teste", EnumTipoUsuario.ADMINISTRADOR));
               
        ViewLogin viewLogin = new ViewLogin();
        ControllerLogin controllerLogin = new ControllerLogin(dao, viewLogin);
        Scanner scn = new Scanner(System.in);
        
        viewLogin.apresentarTela();

        try {
            Connection conn = Conexao.conectar();

            System.out.println("Banco conectado!" + "\n" + "\n");
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Login:\n");
        String login = scn.nextLine();
        System.out.println("Senha:");
        String senha = scn.nextLine();

        //Teste
    }
}
