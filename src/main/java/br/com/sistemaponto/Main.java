package br.com.sistemaponto;

import java.sql.Connection;
import java.util.Scanner;

import br.com.sistemaponto.controller.ControllerLogin;
import br.com.sistemaponto.dao.DaoUsuario;
import br.com.sistemaponto.enumerados.EnumTipoUsuario;
import br.com.sistemaponto.model.ModelFuncionario;
import br.com.sistemaponto.model.ModelRegistroPonto;
import br.com.sistemaponto.model.ModelUsuario;
import br.com.sistemaponto.util.Conexao;
import br.com.sistemaponto.view.ViewLogin;
import java.time.LocalDate;

/**
 * Teste de Conexão
 */
public class Main {
    
    public static final String versao= "V 1.0.0";
    
    public static void main(String[] args) {
        
        DaoUsuario dao = new DaoUsuario();
  //      ControllerLogin controller = new ControllerLogin(dao);
  
        ModelUsuario usuario = new ModelUsuario(1, "Teste", EnumTipoUsuario.ADMINISTRADOR);
        ModelFuncionario funcionario = new ModelFuncionario("Rafael", "123.456.789-00", LocalDate.of(2004, 5, 21), EnumTipoUsuario.ADMINISTRADOR, 
                                                                                                usuario, new ModelRegistroPonto());
        dao.addUsuario(usuario);

                                              
        ViewLogin viewLogin = new ViewLogin();
        ControllerLogin controllerLogin = new ControllerLogin(dao, viewLogin);
        Scanner scn = new Scanner(System.in);
        
        viewLogin.apresentarTela();
        
        //MÉTODO PARA TESTE
        viewLogin.setLogin("1");
        viewLogin.setSenha("Teste");
        viewLogin.clicarBtnEntrar();

        try {
            Connection conn = Conexao.conectar();

            System.out.println("Banco conectado!" + "\n" + "\n");
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
}
