/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.sistemaponto.model;

import java.time.LocalDate;

/**
 *
 * @author Lenovo
 */
public class ModelFuncionarioHorista extends ModelFuncionario{
    private float valorHora;
    private float horasTrabalhadas;
    
    public ModelFuncionarioHorista(String nome, String CPF, LocalDate dataNascimento, String tipoFuncionario,
                                                        ModelUsuario usuario, ModelRegistroPonto registros, float valorHora,float horasTrabalhadas) {
        super(nome, CPF, dataNascimento, tipoFuncionario, usuario, registros);
        this.valorHora = valorHora;
        this.horasTrabalhadas = horasTrabalhadas;
    }

    public float getValorHora() {
        return valorHora;
    }

    public void setValorHora(float valorHora) {
        this.valorHora = valorHora;
    }

    public float getHorasTrabalhadas() {
        return horasTrabalhadas;
    }

    public void setHorasTrabalhadas(float horasTrabalhadas) {
        this.horasTrabalhadas = horasTrabalhadas;
    }
    
}
