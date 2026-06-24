/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.sistemaponto.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author WIN11
 */
public class ModelRegistroPonto {
    private ModelFuncionario funcionario;
    private List<String> registrosDia;
    private List<String> registrosGeral;
    private int botao = 0; //0 para ENTRADA e 1 para
    
    public ModelRegistroPonto(){
        registrosDia = new ArrayList<>();
        registrosGeral = new ArrayList<>();
    }
    
    public void setFuncionario(ModelFuncionario funcionario){
        this.funcionario = funcionario;
    }
    
    public List<String> getRegistrosDia(){
        return registrosDia;
    }
    
    public boolean setRegistrosDia(String registro){
        return registrosDia.add(registro);
    }
    
    public List<String> getRegistrosGeral(){
        return registrosGeral;
    }
    
    public ModelFuncionario getFuncionario(){
        return funcionario;
    }
    
    public boolean addRegistro(String registro){
        if(registrosDia.size()==4)
            return false;
        this.registrosDia.add(registro);
        this.registrosGeral.add(registro);
        return true;
    }
    
    public int getBotao(){
        return botao;
    }
    
    public void setBotao(int botao){
        this.botao = botao;
    }
}
