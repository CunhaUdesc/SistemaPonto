package br.com.sistemaponto.controller;

import java.sql.SQLException;

import br.com.sistemaponto.dao.DaoUsuario;
import br.com.sistemaponto.exception.ExceptionLogin;
import br.com.sistemaponto.model.ModelUsuario;
import br.com.sistemaponto.util.Session;
import br.com.sistemaponto.view.ViewLogin;
import br.com.sistemaponto.view.ViewMenu;

/**
 * Controlador de Login do Sistema Ponto
 *
 * @author Vitor Hugo
 * @since 05/06/2026
 */
public class ControllerLogin {

    private DaoUsuario Dao;
    private ViewLogin viewLogin;

    /**
     * Construct
     *
     * @param dao
     */
    public ControllerLogin(DaoUsuario dao, ViewLogin viewLogin) {
        this.Dao = dao;
        this.viewLogin = viewLogin;
        viewLogin.apresentarTela();
        adicionarAcao();
    }
    
    public void chamarMenu(){
        new ControllerMenu(new ViewMenu());
        this.viewLogin.setVisible(false);
    }
    
        public void adicionarAcao(){
            viewLogin.adcionarAcaoBtnEntrar(a -> autenticarLogin());
        }

    /**
     * Autenticação de Usuário
     *
     * @param cod
     * @param senha
     * @return ModelUsuario
     */
        
       public void autenticarLogin() {
           int login = 0;
           String senha;
           
           try {
               login = Integer.parseInt(viewLogin.getLogin());
           } catch (Exception e) {
                viewLogin.apresentaMensagem("Login Inválido!");
                return;
           }
           try{
               senha = viewLogin.getSenha();
               
               if(senha == null || senha.trim().isEmpty()){
                    viewLogin.apresentaMensagem("Senha incorreta!");
                    return;
               }                
           }catch(Exception e){
               viewLogin.apresentaMensagem("Senha incorreta!");
           }
                ModelUsuario Usuario = Dao.autenticar(login, viewLogin.getSenha());
            
                if (Usuario != null) {
                    Session.setUsuario(Usuario);
                    viewLogin.apresentaMensagem("Bem vindo! ");
                    chamarMenu();
                    return;
                }

                viewLogin.apresentaMensagem("Nenhum Usuário Encontrado!");
        }
       
    public ModelUsuario autenticar(String cod, String senha) throws ExceptionLogin, SQLException {

        try {
            int login = Integer.parseInt(cod);
            ModelUsuario Usuario = Dao.autenticar(login, senha);

            if (Usuario == null) {
                throw new ExceptionLogin("Usuário inválido!");
            }

            return Usuario;

        } catch (NumberFormatException e) {
            throw new ExceptionLogin("Login inválido!");
        }
    }

}
