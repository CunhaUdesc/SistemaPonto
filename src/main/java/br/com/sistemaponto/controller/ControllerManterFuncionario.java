package br.com.sistemaponto.controller;

import br.com.sistemaponto.interfaces.InterfaceDados;
import br.com.sistemaponto.view.ViewCadastroFuncionario;
import br.com.sistemaponto.view.ViewManterFuncionario;
import br.com.sistemaponto.view.ViewRegistrosFuncionario;

public class ControllerManterFuncionario {
    private ViewManterFuncionario viewManterFuncionario;
    private InterfaceDados daoFuncionario;
    
    public ControllerManterFuncionario(ViewManterFuncionario view){
        this.viewManterFuncionario = view;
        viewManterFuncionario.apresentarTela();
        viewManterFuncionario.atualizarTabela();
        adicionarAcoes();
    }

    public void adicionarAcoes(){
        viewManterFuncionario.adcionarAcaoBtnIncluir(e -> incluirFuncionario());
        
        viewManterFuncionario.adcionarAcaoBtnAlterar(e-> alterarFuncionario());
    
        viewManterFuncionario.adcionarAcaoBtnExcluir(e -> excluirFuncionario());
        
        viewManterFuncionario.adicionarAcaoBtnVisualizarRegistros(e -> chamarTelaRegistrosPontoFuncionario());
        
        viewManterFuncionario.adicionarAcaoBtnPesquisar(e -> pesquisarFiltro());

    }
    
    public void chamarTelaRegistrosPontoFuncionario(){
        new ControllerRegistrosFuncionario(new ViewRegistrosFuncionario());
    }

    public void chamarTelaCadastroFuncionario(){
        new ControllerCadastroFuncionario(new ViewCadastroFuncionario());
    }
    
    public void incluirFuncionario(){
        chamarTelaCadastroFuncionario();
    }

    public void alterarFuncionario(){
        int codigo = viewManterFuncionario.getCodigoSelecionadoNaTabela();

        if(codigo<0){
            viewManterFuncionario.apresentaMensagem("Selecione um Funcionario!"); //CONTINUAR O METODO NO CADASTRO
            return;
        }
        new ControllerCadastroFuncionario(new ViewCadastroFuncionario(), codigo);
    }

    public void excluirFuncionario(){
        try {
            int codigo = viewManterFuncionario.getCodigoSelecionadoNaTabela();

            if(codigo<0){
                viewManterFuncionario.apresentaMensagem("Selecione um Funcionario!");
                return;
            }
            daoFuncionario.excluir(codigo);
            viewManterFuncionario.atualizarTabela();

        } catch (Exception e) {
            viewManterFuncionario.apresentaMensagem("Erro: "+e.getMessage()); //VERIFICAR SE É ISSO MESMO ESSE EXCEPTION
        }
    }
    
    public void pesquisarFiltro(){
        System.out.println("Filtro selecionado");
    }
}
