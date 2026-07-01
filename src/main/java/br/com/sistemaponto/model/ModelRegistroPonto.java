/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.sistemaponto.model;

import java.time.LocalDate;

/**
 *
 * @author WIN11
 */
public class ModelRegistroPonto {
    private int codigo;
    private ModelFuncionario funcionario;
    private LocalDate diaAtual;
    private String registroEntrada;
    private String registroSaida;
    private String registroEntradaIntervalo;
    private String registroSaidaIntervalo;
    
    private float horasDia;
    
    private int botao = 0; //0 para ENTRADA e 1 para
    private int idRegistro = 1; // 1 a 4 para definir qual registro salvar

    public ModelRegistroPonto(){
    }

    public int getCodigo() {
        return codigo;
    }

    public LocalDate getDiaAtual() {
        return diaAtual;
    }

    public String getRegistroEntrada() {
        return registroEntrada;
    }

    public String getRegistroSaida() {
        return registroSaida;
    }

    public String getRegistroEntradaIntervalo() {
        return registroEntradaIntervalo;
    }

    public String getRegistroSaidaIntervalo() {
        return registroSaidaIntervalo;
    }
    
    public ModelFuncionario getFuncionario(){
        return funcionario;
    }
    
    public int getBotao(){
        return botao;
    }

    public int getIdRegistro() {
        return idRegistro;
    }

    public void setDiaAtual(LocalDate diaAtual) {
        this.diaAtual = diaAtual;
    }

    public boolean setRegistroEntrada(String registroEntrada) {
        if(idRegistro == 5)
            return false;
        this.registroEntrada = registroEntrada;
        return true;
    }

    public boolean setRegistroSaida(String registroSaida) {
        if(idRegistro == 5)
            return false;
        this.registroSaida = registroSaida;
        return true;
    }

    public boolean setRegistroEntradaIntervalo(String registroEntradaIntervalo) {
        if(idRegistro == 5)
            return false;
        this.registroEntradaIntervalo = registroEntradaIntervalo;
        return true;
    }

    public boolean setRegistroSaidaIntervalo(String registroSaidaIntervalo) {
        if(idRegistro == 5)
            return false;
        this.registroSaidaIntervalo = registroSaidaIntervalo;
        return true;
    }

    public void setFuncionario(ModelFuncionario funcionario){
        this.funcionario = funcionario;
    }
    
    public void setBotao(int botao){
        this.botao = botao;
    }

    public void setIdRegistro(int idRegistro) {
        this.idRegistro = idRegistro;
    }
    
    @Override
    public String toString(){

        StringBuilder s = new StringBuilder();

        if(getRegistroEntrada() != null){
            s.append(getRegistroEntrada()).append("\n");
        }

        if(getRegistroSaida() != null){
            s.append(getRegistroSaida()).append("\n");
        }

        if(getRegistroEntradaIntervalo() != null){
            s.append(getRegistroEntradaIntervalo()).append("\n");
        }

        if(getRegistroSaidaIntervalo() != null){
            s.append(getRegistroSaidaIntervalo()).append("\n");
        }

        return s.toString().trim();
    }
}
