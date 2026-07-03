/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.sistemaponto.model;

/**
 *
 * @author Lenovo
 */
public abstract class ModelFuncionario {
    private int codigo;
    private String nome;
    private String CPF;
    private String tipoFuncionario;
    private String dataNascimento;

    //Construtor para adicionar novo funcionário
    public ModelFuncionario(String nome, String CPF, String tipoFuncionario, String dataNascimento) {
        this.nome = nome;
        this.CPF = CPF;
        this.tipoFuncionario = tipoFuncionario;
        this.dataNascimento = dataNascimento;
    }

    //Construtor para buscar funcionario e mostrar na tela
    public ModelFuncionario(int codigo, String nome, String CPF, String tipoFuncionario, String dataNascimento){
        this.codigo = codigo;
        this.nome = nome;
        this.CPF = CPF;
        this.tipoFuncionario = tipoFuncionario;
        this.dataNascimento = dataNascimento;
    }

    public int getCodigo() {
        return codigo;
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
    
    public String getDataNascimento() {
        return dataNascimento;
    }
    
    public ModelFuncionario setCodigo(int codigo) {
        this.codigo = codigo;
        return this;
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
    
    public ModelFuncionario setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
        return this;
    }
    
    @Override
    public String toString(){
        return "Codigo: "+codigo+", Nome: "+nome+", CPF: "+CPF+", Tipo: "+tipoFuncionario;
    }
}
