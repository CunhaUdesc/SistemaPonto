package br.com.sistemaponto;

import br.com.sistemaponto.controller.ControllerLogin;
import br.com.sistemaponto.dao.DaoUsuario;
import br.com.sistemaponto.enumerados.EnumTipoUsuario;
import br.com.sistemaponto.exception.ExceptionLogin;
import br.com.sistemaponto.model.ModelUsuario;
import br.com.sistemaponto.util.Conexao;
import br.com.sistemaponto.view.ViewLogin;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * Teste de Conexão
 */
public class Main {

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);

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

        DaoUsuario dao = new DaoUsuario();
        ControllerLogin controller = new ControllerLogin(dao);

        dao.addUsuario(new ModelUsuario(1, "Teste", EnumTipoUsuario.ADMINISTRADOR));

        try {
            dao.salvar(new ModelUsuario(1, "Teste", EnumTipoUsuario.ADMINISTRADOR));

        } catch (ExceptionLogin ex) {
            System.out.println(ex.getMessage());
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        (new ViewLogin(controller)).autenticarLogin(login, senha);

    }
}
