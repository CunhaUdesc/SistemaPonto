/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.sistemaponto.controller;

import java.sql.SQLException;

import br.com.sistemaponto.exception.ExceptionCargaHoraria;
import br.com.sistemaponto.exception.ExceptionCpfInvalido;
import br.com.sistemaponto.exception.ExceptionDataNascimentoInvalido;
import br.com.sistemaponto.exception.ExceptionLogin;
import br.com.sistemaponto.exception.ExceptionSistemaPonto;
import br.com.sistemaponto.exception.ExceptionValorInvalido;
import br.com.sistemaponto.interfaces.InterfaceDados;
import br.com.sistemaponto.model.ModelFuncionario;
import br.com.sistemaponto.model.ModelFuncionarioFixo;
import br.com.sistemaponto.model.ModelFuncionarioHorista;
import br.com.sistemaponto.model.ModelUsuario;
import br.com.sistemaponto.view.ViewCadastroFuncionario;

/**
 *
 * @author Lenovo
 */
public class ControllerCadastroFuncionario {
    private ViewCadastroFuncionario viewCadastroFunc;
    private InterfaceDados daoFuncionario;
    
    //QUANDO FOR ADICIONAR UM FUNCIONARIO
    public ControllerCadastroFuncionario(ViewCadastroFuncionario viewCadastroFunc){
        this.viewCadastroFunc = viewCadastroFunc;
        viewCadastroFunc.mostrarTela();
        viewCadastroFunc.getBtnLimpar().setEnabled(true);
        adicionarAcoes();
    }

    //QUANDO FOR ALTERAR UM FUNCIONARIO
    public ControllerCadastroFuncionario(ViewCadastroFuncionario viewCadastroFunc, int codigoFuncionario){
        this.viewCadastroFunc = viewCadastroFunc;
        viewCadastroFunc.mostrarTela();
        adicionarAcoes();
    }
    
    public void adicionarAcoes(){
        viewCadastroFunc.adcionarAcaoBtnLimpar(e -> limparTela());
        
        viewCadastroFunc.adcionarAcaoBtnSalvar(e -> salvarFuncionario());
    }

    //======================================VALIDAÇÕES==============================================
    
    public void validarCPF(String cpf) throws ExceptionCpfInvalido{
        if(cpf == null || cpf.length() != 11){
            throw new ExceptionCpfInvalido("CPF invalido!");
        }

        for(int i = 0; i < cpf.length(); i++){
            if(!Character.isDigit(cpf.charAt(i))){
                throw new ExceptionCpfInvalido("CPF invalido!");
            }
        }
    }
    
    public void validarDataNascimento(String dataNascimento) throws ExceptionDataNascimentoInvalido{
        if(dataNascimento.length() != 10)
            throw new ExceptionDataNascimentoInvalido("Data de Nascimento Inválida!");

        if(dataNascimento.charAt(2) != '/' || dataNascimento.charAt(5) != '/')
            throw new ExceptionDataNascimentoInvalido("Data de Nascimento Inválida!");
        
        String diaString = dataNascimento.substring(0,2);
        String mesString = dataNascimento.substring(3,5);
        String anoString = dataNascimento.substring(6,10);

        for(int i=0;i<diaString.length();i++){
            if(!Character.isDigit(diaString.charAt(i)) || !Character.isDigit(mesString.charAt(i)))
                throw new ExceptionDataNascimentoInvalido("Data de Nascimento Inválida!");
        }

        for(int i=0;i<anoString.length();i++){
            if(!Character.isDigit(anoString.charAt(i)))
                throw new ExceptionDataNascimentoInvalido("Data de Nascimento Inválida!");
        }

        int dia = Integer.parseInt(diaString);
        int mes = Integer.parseInt(mesString);
        int ano = Integer.parseInt(anoString);

        if(mes<1 || mes>12)
            throw new ExceptionDataNascimentoInvalido("Data de Nascimento Inválida!");
        if(dia<1 || dia>31)
            throw new ExceptionDataNascimentoInvalido("Data de Nascimento Inválida!");
        if(ano<1920 || ano>2026)
            throw new ExceptionDataNascimentoInvalido("Data de Nascimento Inválida!");
    }
    
    public void validarValorHora(double valorHora) throws ExceptionValorInvalido{
        if(valorHora<0)
            throw new ExceptionValorInvalido("Valor invalido!");
    }

    public void validarSalario(double salario) throws ExceptionValorInvalido{
        if(salario<1621)
            throw new ExceptionValorInvalido("Valor invalido!");
    }

    public void validarCargaHoraria(float cargaHoraria) throws ExceptionCargaHoraria{
        if(cargaHoraria<30 || cargaHoraria>44)
            throw new ExceptionCargaHoraria("Carga Horaria nao permitida!");
    }

    public int criaLogin(String cpf){
        return Integer.parseInt(cpf.substring(0,3));
    }

    //=========================METODOS USUARIO================================

    public int criaSenha(String dataNascimento){
        String diaString = dataNascimento.substring(0,2);
        String mesString = dataNascimento.substring(3,5);

        return Integer.parseInt(diaString+mesString);
    }

    //===========================FUNÇÕES BOTÕES================================
    
    public void salvarFuncionario(){
        try {
            ModelFuncionario funcionario;
            ModelUsuario usuario;

            String nome = viewCadastroFunc.getNome();

            String cpf = viewCadastroFunc.getCpf();
            validarCPF(cpf);

            String tipo = viewCadastroFunc.getTipoFuncionario();

            String dataNascimento = viewCadastroFunc.getDataNascimento();
            validarDataNascimento(dataNascimento);
            
            if(viewCadastroFunc.getTipoFuncionario().equals("Horista")){
                double valorHora = Double.parseDouble(viewCadastroFunc.getValorHora());
                validarValorHora(valorHora);
                
                funcionario = new ModelFuncionarioHorista(nome, cpf, dataNascimento, tipo, valorHora);

            } else {
                double salario = Double.parseDouble(viewCadastroFunc.getSalario());
                validarSalario(salario);
                float cargaHoraria = Float.parseFloat(viewCadastroFunc.getCargaHoraria());
                validarCargaHoraria(cargaHoraria);
                funcionario = new ModelFuncionarioFixo(nome, cpf, tipo, dataNascimento, salario, cargaHoraria);
                
            }

            //Criando o usuário
            int login = criaLogin(cpf);
            int senha = criaSenha(dataNascimento);
            String perfil = viewCadastroFunc.getPerfilUsuario();

            usuario = new ModelUsuario(login, senha, perfil, funcionario);
            daoFuncionario.salvar(funcionario); //ARRUMAR OS EXCEPTIONS

        } catch (ExceptionCpfInvalido e) {
            viewCadastroFunc.apresentaMensagem("Erro: "+e.getMessage());

        } catch (ExceptionDataNascimentoInvalido e) {
            viewCadastroFunc.apresentaMensagem("Erro: "+e.getMessage());

        } catch (ExceptionValorInvalido e) {
            viewCadastroFunc.apresentaMensagem("Erro: "+e.getMessage());

        } catch (ExceptionCargaHoraria e) {
            viewCadastroFunc.apresentaMensagem("Erro: "+e.getMessage());

        } catch (SQLException e) {
            viewCadastroFunc.apresentaMensagem("Erro: "+e.getMessage());

        } catch (ExceptionLogin e) {
            viewCadastroFunc.apresentaMensagem("Erro: "+e.getMessage());

        } catch (ExceptionSistemaPonto e) {
            viewCadastroFunc.apresentaMensagem("Erro: "+e.getMessage());
            
        }
    }

    public void alterarFuncionario(){
        viewCadastroFunc.getBtnLimpar().setEnabled(false);
    }

    public void limparTela(){
        viewCadastroFunc.limparTela();
    }

}
