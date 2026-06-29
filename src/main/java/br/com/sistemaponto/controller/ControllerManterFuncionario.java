package br.com.sistemaponto.controller;

import br.com.sistemaponto.view.ViewCadastroFuncionario;
import br.com.sistemaponto.view.ViewManterFuncionario;

public class ControllerManterFuncionario {
    private ViewManterFuncionario viewManterFuncionario;
    
    public ControllerManterFuncionario(ViewManterFuncionario view){
        this.viewManterFuncionario = view;
        view.apresentarTela();
        adicionarAcoes();
    }

    public void adicionarAcoes(){
        viewManterFuncionario.adcionarAcaoBtnIncluir(e -> incluirFuncionario());
        
        viewManterFuncionario.adcionarAcaoBtnAlterar(e-> alterarFuncionario());
    
        viewManterFuncionario.adcionarAcaoBtnExcluir(e -> excluirFuncionario());
    }

    public void chamarTelaCadastroFuncionario(){
        new ControllerCadastroFuncionario(new ViewCadastroFuncionario());
    }

    public void incluirFuncionario(){
        chamarTelaCadastroFuncionario();
    }

    public void alterarFuncionario(){
        System.out.println("Funcionario Alterado");
    }

    public void excluirFuncionario(){
        System.out.println("Funcionario Excluido");

    }
}
