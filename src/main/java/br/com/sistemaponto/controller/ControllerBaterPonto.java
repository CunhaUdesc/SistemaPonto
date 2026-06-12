/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.sistemaponto.controller;

import br.com.sistemaponto.view.ViewBaterPonto;

/**
 *
 * @author Lenovo
 */
public class ControllerBaterPonto {
    private ViewBaterPonto viewBaterPonto;
    
    public ControllerBaterPonto(ViewBaterPonto viewBaterPonto){
        this.viewBaterPonto = viewBaterPonto;
        viewBaterPonto.mostrarTela();
        adicionarAcoes();
        setLabels();
        desabilitaBotao();
    }
    
    public void desabilitaBotao(){
        //Testando como desabilitar o botão
        if(viewBaterPonto.getRegistrosPonto().trim().isEmpty());
            viewBaterPonto.getBtnSaida().setEnabled(false);
    }
    
    public void setLabels(){
        viewBaterPonto.setLabels("NomeTeste");
    }
    
    public void adicionarAcoes(){
        viewBaterPonto.adicionarAcaoBtnEntradaPonto(e -> baterPontoEntrada());
        
        viewBaterPonto.adicionarAcaoBtnSaidaPonto(e -> baterPontoSaida());
    }
    
    public void baterPontoEntrada(){
        System.out.println("Entrada Registrada!" + viewBaterPonto.getDataAtual());
    }
    
    public void baterPontoSaida(){
        System.out.println("Saida Registrada!"  + viewBaterPonto.getDataAtual());
    }
}
