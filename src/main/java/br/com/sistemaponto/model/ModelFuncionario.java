/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.sistemaponto.model;

import br.com.sistemaponto.enumerados.EnumTipoUsuario;
import java.time.LocalDate;

/**
 *
 * @author Lenovo
 */
public class ModelFuncionario {
    private int id;
    private String nome;
    private String CPF;
    private LocalDate dataNascimento;
    private String tipo;
    private ModelUsuario usuario;
    private ModelRegistroPonto registros;
    
    //Verificar se tem mais atributos
    
    private static int geraCodigo = 1;

    public ModelFuncionario(String nome, String CPF, LocalDate dataNascimento, String tipo
                                            ,ModelUsuario usuario, ModelRegistroPonto registros) {
        this.id = geraCodigo++;
        this.nome = nome;
        this.CPF = CPF;
        this.dataNascimento = dataNascimento;
        this.tipo = tipo;
        this.usuario = usuario;
        usuario.setFuncionario(this);
        this.registros = registros;
        registros.setFuncionario(this);
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCPF() {
        return CPF;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public String getTipo() {
        return tipo;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    public ModelRegistroPonto getRegistroPonto(){
        return registros;
    }

}
