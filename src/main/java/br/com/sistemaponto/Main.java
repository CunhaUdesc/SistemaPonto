package br.com.sistemaponto;

import java.sql.Connection;

import br.com.sistemaponto.controller.ControllerLogin;
import br.com.sistemaponto.dao.DaoUsuario;
import br.com.sistemaponto.enumerados.EnumTipoUsuario;
import br.com.sistemaponto.model.ModelFuncionarioFixo;
import br.com.sistemaponto.model.ModelUsuario;
import br.com.sistemaponto.util.Conexao;
import br.com.sistemaponto.util.InicializadorDataBase;
import br.com.sistemaponto.view.ViewLogin;

/**
 * Teste de Conexão
 *
 */
 public class Main {
    
    public static final String versao= "V 2.0.0";
    
    public static void main(String[] args) {

        // Criação e população das tabelas
        InicializadorDataBase.inicializar();


        ModelUsuario usuario = new ModelUsuario(1, "Teste", EnumTipoUsuario.ADMINISTRADOR, new ModelFuncionarioFixo("Teste", "TESTE", "FIXO", "02/09/2026", 2000, 40));
        DaoUsuario dao = new DaoUsuario();
        dao.addUsuario(usuario);


        ViewLogin viewLogin = new ViewLogin();
        ControllerLogin controllerLogin = new ControllerLogin(dao, viewLogin);
                
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
