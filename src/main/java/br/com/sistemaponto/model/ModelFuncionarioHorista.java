/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.sistemaponto.model;

/**
 *
 * @author Lenovo
 */
public class ModelFuncionarioHorista extends ModelFuncionario{
    private double valorHora;
    private float horasTrabalhadas;
    
    public ModelFuncionarioHorista(String nome, String CPF, String dataNascimento, String tipoFuncionario, double valorHora) {
        super(nome, CPF, dataNascimento, tipoFuncionario);
        this.valorHora = valorHora;
    }

    public double getValorHora() {
        return valorHora;
    }

    public void setValorHora(double valorHora) {
        this.valorHora = valorHora;
    }

    public float getHorasTrabalhadas() {
        return horasTrabalhadas;
    }

    public void setHorasTrabalhadas(float horasTrabalhadas) {
        this.horasTrabalhadas = horasTrabalhadas;
    }
    
    @Override
    public String toString(){
        return super.toString() + ", Valor p/ Hora: "+valorHora+", Horas Trabalhadas: "+horasTrabalhadas;
    }
    
}
