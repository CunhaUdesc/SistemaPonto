/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.sistemaponto.controller;

import br.com.sistemaponto.util.Session;
import br.com.sistemaponto.view.ViewRegistrosFuncionario;

/**
 *
 * @author Lenovo
 */
public class ControllerRegistrosFuncionario {
    ViewRegistrosFuncionario view;
    
    public ControllerRegistrosFuncionario(ViewRegistrosFuncionario viewRegistrosFuncionario){
        this.view = viewRegistrosFuncionario;
        view.apresentarTela();
        adicionarAcoes();
    }
    
    public void adicionarAcoes(){
        view.acaoBtnPesquisar(e -> pesquisar());
    }
    
    public void setLabels(){
        view.setLabelNomeFuncionario(Session.getUsuario().getFuncionario().getNome());
    }
    
    public void pesquisar(){
        System.out.println("");
    }
}
