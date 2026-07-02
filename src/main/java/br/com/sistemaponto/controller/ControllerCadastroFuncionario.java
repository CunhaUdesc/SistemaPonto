/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.sistemaponto.controller;

import java.sql.SQLException;

import br.com.sistemaponto.exception.ExceptionCpfInvalido;
import br.com.sistemaponto.exception.ExceptionDataNascimentoInvalido;
import br.com.sistemaponto.exception.ExceptionLogin;
import br.com.sistemaponto.exception.ExceptionSistemaPonto;
import br.com.sistemaponto.exception.ExceptionValorPorHoraInvalido;
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
    
    public ControllerCadastroFuncionario(ViewCadastroFuncionario viewCadastroFunc){
        this.viewCadastroFunc = viewCadastroFunc;
        viewCadastroFunc.mostrarTela();
        adicionarAcoes();
    }
    
    public void adicionarAcoes(){
        viewCadastroFunc.adcionarAcaoBtnLimpar(e -> limparTela());
        
        viewCadastroFunc.adcionarAcaoBtnSalvar(e -> salvarFuncionario());
    }
    
    public void limparTela(){
        viewCadastroFunc.limparTela();
    }
    
    public void validarCPF(String cpf) throws ExceptionCpfInvalido{
        if(cpf.length() != 11) // tem q continuar a validação
            throw new ExceptionCpfInvalido("CPF Inválido!");
    }
    
    public void validarDataNascimento(String dataNascimento) throws ExceptionDataNascimentoInvalido{
        if(dataNascimento.length() != 10) // tem q continuar a validação
            throw new ExceptionDataNascimentoInvalido("Data de Nascimento Inválida!");
    }
    
    public void validarValorHora(double valorHora) throws ExceptionValorPorHoraInvalido{
        if(valorHora<0)// tem q continuar a validação
            throw new ExceptionValorPorHoraInvalido("Valor invalido!");
    }

    public void criaUsuario(ModelFuncionario funcionario){
        //Criando o usuário e vinculado
        int login = funcionario.getCodigo(); // PRECISO DO CODIGO
        String senha = funcionario.getCPF();
        String perfil = viewCadastroFunc.getPerfilUsuario();
        
        ModelUsuario usuario = new ModelUsuario(login, senha, perfil);
        funcionario.criaUsuario(usuario);
    }
    
    public void salvarFuncionario(){
        try {
            ModelFuncionario funcionario;
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
            }else{
                double salario = Double.parseDouble(viewCadastroFunc.getSalario()); //TEM Q VALIDAR
                float cargaHoraria = Float.parseFloat(viewCadastroFunc.getCargaHoraria());
                funcionario = new ModelFuncionarioFixo(nome, cpf, tipo, dataNascimento, salario, cargaHoraria);
            }
            
            daoFuncionario.salvar(funcionario); //ARRUMAR OS EXCEPTIONS
            criaUsuario(daoFuncionario.getUltimoFuncionario());

        } catch (ExceptionCpfInvalido e) {
            viewCadastroFunc.apresentaMensagem("Erro: "+e.getMessage());

        } catch (ExceptionDataNascimentoInvalido e) {
            viewCadastroFunc.apresentaMensagem("Erro: "+e.getMessage());

        } catch (ExceptionValorPorHoraInvalido e) {
            viewCadastroFunc.apresentaMensagem("Erro: "+e.getMessage());
            
        } catch (SQLException e) {
            viewCadastroFunc.apresentaMensagem("Erro: "+e.getMessage());

        } catch (ExceptionLogin e) {
            viewCadastroFunc.apresentaMensagem("Erro: "+e.getMessage());

        } catch (ExceptionSistemaPonto e) {
            viewCadastroFunc.apresentaMensagem("Erro: "+e.getMessage());
        }
    }

}
