/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.sistemaponto.controller;

import br.com.sistemaponto.dao.DaoRegistroPontoTeste;
import br.com.sistemaponto.view.ViewBaterPonto;
import br.com.sistemaponto.view.ViewManterFuncionario;
import br.com.sistemaponto.view.ViewManterRegistroPonto;
import br.com.sistemaponto.view.ViewMenu;

/**
 * @author Rafael
 * @since 09/06/2026
 */
public class ControllerMenu {
    private ViewMenu viewMenu;
    
    public ControllerMenu(ViewMenu viewMenu){
        this.viewMenu = viewMenu;
        viewMenu.apresentarTela();
        adicionarAcoes();
    }
    
    public void adicionarAcoes(){
        //Adicionado funções dos botões
        viewMenu.adcionarAcaoBtnBaterPonto(e -> chamarTelaBaterPonto());
        
        viewMenu.adcionarAcaoBtnManterControlePonto(e -> abrirTelaManterControlePonto());
        
        viewMenu.adcionarAcaoBtnManterFuncionario(e -> abrirTelaManterFuncionario());
    }
    
    public void chamarTelaBaterPonto(){
        new ControllerBaterPonto(new ViewBaterPonto(), new DaoRegistroPontoTeste());
    }
    
    public void abrirTelaManterControlePonto(){
        new ControllerManterRegistroPonto(new ViewManterRegistroPonto());
    }
    
    public void abrirTelaManterFuncionario(){
        new ControllerManterFuncionario(new ViewManterFuncionario());
    }
}
