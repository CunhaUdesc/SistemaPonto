/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.sistemaponto.controller;

import br.com.sistemaponto.dao.DaoRegistroPonto;
import br.com.sistemaponto.exception.ExceptionLimiteRegistroPonto;
import br.com.sistemaponto.model.ModelRegistroPonto;
import br.com.sistemaponto.model.ModelUsuario;
import br.com.sistemaponto.view.ViewBaterPonto;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Lenovo
 */
public class ControllerBaterPonto {
    private ModelUsuario usuario;
    private ViewBaterPonto viewBaterPonto;
    private DaoRegistroPonto daoRegistroPonto;
    
    public ControllerBaterPonto(ViewBaterPonto viewBaterPonto, DaoRegistroPonto daoRegistroPonto, ModelUsuario usuario){
        this.usuario = usuario;
        this.daoRegistroPonto = daoRegistroPonto;
        this.viewBaterPonto = viewBaterPonto;
        viewBaterPonto.mostrarTela();
        atualizaRegistrosDoDia();
        adicionarAcoes();
        setLabels();
        desabilitaBotao();
    }
   
    public void desabilitaBotao(){
        //Testando como desabilitar o botão
        if(usuario.getFuncionario().getRegistroPonto().getBotao() == 0){
            viewBaterPonto.getBtnEntrada().setEnabled(true);
            viewBaterPonto.getBtnSaida().setEnabled(false);
            return;
        }
            viewBaterPonto.getBtnEntrada().setEnabled(false);
            viewBaterPonto.getBtnSaida().setEnabled(true);
    }

    public void setLabels(){
        viewBaterPonto.setLabels("NomeTeste");
    }
    
    public void adicionarAcoes(){
        viewBaterPonto.adicionarAcaoBtnEntradaPonto(e -> baterPontoEntrada());
        
        viewBaterPonto.adicionarAcaoBtnSaidaPonto(e -> baterPontoSaida());
    }
    
    public void baterPontoEntrada(){
        
        try{
            boolean adicionado = usuario.getFuncionario().getRegistroPonto().addRegistro("Entrada -- "+viewBaterPonto.getDataAtual());
            
            if(!adicionado)
                throw new ExceptionLimiteRegistroPonto("Limite de Registros do dia atingido");
            
            atualizaRegistrosDoDia();
            usuario.getFuncionario().getRegistroPonto().setBotao(1);
            desabilitaBotao();
            
        }catch(ExceptionLimiteRegistroPonto e){
            viewBaterPonto.apresentaMensagem(e.getMessage());
        }
    }
    
    public void baterPontoSaida(){ //talvez pode tirar, pois o último registro sempre é uma saída
        
        try{
            boolean adicionado = usuario.getFuncionario().getRegistroPonto().addRegistro("Saida -- "+viewBaterPonto.getDataAtual());
            
            if(!adicionado)
                throw new ExceptionLimiteRegistroPonto("Limite de Registros do dia atingido");
            
            atualizaRegistrosDoDia();
            
            usuario.getFuncionario().getRegistroPonto().setBotao(0);
            desabilitaBotao();
 
        }catch(ExceptionLimiteRegistroPonto e){
            viewBaterPonto.apresentaMensagem(e.getMessage());
        }
    }
    
    public void atualizaRegistrosDoDia(){
        List<String> registros = usuario.getFuncionario().getRegistroPonto().getRegistrosDia();
        StringBuilder sb = new StringBuilder();
        for(String r : registros){
            sb.append(r).append("\n");
        }
        viewBaterPonto.atualizaRegistros(sb.toString().trim());
    }
}
