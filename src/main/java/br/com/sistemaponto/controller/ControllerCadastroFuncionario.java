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
    
    public void salvarFuncionario(){
        try {
            ModelFuncionario funcionario;
            int codigo = 101; // PRECISO DE UM METODO PRA TRAZER O CÓDIGO DO BANCO DE DADOS -- getNovoCodigo()
            String nome = viewCadastroFunc.getNome();

            String cpf = viewCadastroFunc.getCpf();
            validarCPF(cpf);

            String tipo = viewCadastroFunc.getTipoFuncionario();

            String dataNascimento = viewCadastroFunc.getDataNascimento();
            validarDataNascimento(dataNascimento);
            
             //Criando o usuário
            int login = codigo; // VAI SER O CODIGO DO FUNCIONARIO
            String senha = cpf;
            String perfil = viewCadastroFunc.getPerfilUsuario();
            ModelUsuario usuario = new ModelUsuario(login, senha, tipo);
            
            if(viewCadastroFunc.getTipoFuncionario().equals("Horista")){
                double valorHora = Double.parseDouble(viewCadastroFunc.getValorHora());
                validarValorHora(valorHora);
                
                funcionario = new ModelFuncionarioHorista(nome, cpf, dataNascimento, tipo, usuario, valorHora);
            }else{
                double salario = Double.parseDouble(viewCadastroFunc.getSalario()); //TEM Q VALIDAR
                double cargaHoraria = Double.parseDouble(viewCadastroFunc.getCargaHoraria());
                funcionario = new ModelFuncionarioFixo(nome, cpf, tipo, dataNascimento, usuario, salario, (float) cargaHoraria); //CARGA HORARIA DEVIA SER FLOAT????
            }
            
            daoFuncionario.salvar(funcionario); //ARRUMAR OS EXCEPTIONS

        } catch (ExceptionCpfInvalido e) {
            viewCadastroFunc.apresentaMensagem("Erro: "+e.getMessage());

        } catch (ExceptionDataNascimentoInvalido e) {
            viewCadastroFunc.apresentaMensagem("Erro: "+e.getMessage());

        } catch (ExceptionValorPorHoraInvalido e) {
            viewCadastroFunc.apresentaMensagem("Erro: "+e.getMessage());
        } catch (SQLException ex) {
            System.getLogger(ControllerCadastroFuncionario.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } catch (ExceptionLogin ex) {
            System.getLogger(ControllerCadastroFuncionario.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } catch (ExceptionSistemaPonto ex) {
            System.getLogger(ControllerCadastroFuncionario.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
}
