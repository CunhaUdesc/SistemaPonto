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
public class ModelFuncionario {
    private int id;
    private String nome;
    private String CPF;
    private String tipoFuncionario;
    private float cargahoraria;
    private double salario;
    private ModelUsuario Usuario;
    private ModelRegistroPonto Registro;

    //Verificar se tem mais atributos
    
    private static int geraCodigo = 1;

    public ModelFuncionario(String nome, String CPF, String tipoFuncionario, int cargaHoraria, double salario, ModelUsuario usuario) {
        this.nome = nome;
        this.CPF = CPF;
        this.tipoFuncionario = tipoFuncionario;
        this.cargahoraria = cargaHoraria;
        this.Usuario = usuario;
        usuario.setFuncionario(this);
    }

    public int getId() {
        return id;
    }

    public ModelFuncionario setId(int id) {
        this.id = id;
        return this;
    }

    public String getNome() {
        return nome;
    }

    public String getCPF() {
        return CPF;
    }

    public String getTipoFuncionario() {
        return tipoFuncionario;
    }

    public ModelFuncionario setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public ModelFuncionario setCPF(String CPF) {
        this.CPF = CPF;
        return this;
    }

    public ModelFuncionario setTipo(String tipoFuncionario) {
        this.tipoFuncionario = tipoFuncionario;
        return this;
    }
    
    public ModelRegistroPonto getRegistroPonto(){
        return this.Registro;
    }

    public double getSalario() {
        return salario;
    }

    public ModelFuncionario setSalario(double salario) {
        this.salario = salario;
        return this;
    }

    public float getCargahoraria() {
        return cargahoraria;
    }

    public ModelFuncionario setCargahoraria(float cargahoraria) {
        this.cargahoraria = cargahoraria;
        return this;
    }

    public ModelUsuario getUsuario() {
        return Usuario;
    }

    public ModelFuncionario setUsuario(ModelUsuario usuario) {
        Usuario = usuario;
        return this;
    }
}
