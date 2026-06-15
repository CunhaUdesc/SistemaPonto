/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.sistemaponto.controller;

import br.com.sistemaponto.view.ViewCadastroFuncionario;

/**
 *
 * @author Lenovo
 */
public class ControllerCadastroFuncionario {
    private ViewCadastroFuncionario viewCadastroFunc;
    
    public ControllerCadastroFuncionario(ViewCadastroFuncionario viewCadastroFunc){
        this.viewCadastroFunc = viewCadastroFunc;
        adicionarAcoes();
    }
    
    public void adicionarAcoes(){
        viewCadastroFunc.adcionarAcaoBtnLimpar(e -> limparTela());
        
        viewCadastroFunc.adcionarAcaoBtnSalvar(e -> salvarFuncionario());
    }
    
    public void limparTela(){
        viewCadastroFunc.limparTela();
    }
    
    public void salvarFuncionario(){
        
    }
}
