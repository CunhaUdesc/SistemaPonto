package br.com.sistemaponto.controller;

import br.com.sistemaponto.interfaces.InterfaceDados;
import br.com.sistemaponto.view.ViewCadastroFuncionario;
import br.com.sistemaponto.view.ViewManterFuncionario;
import br.com.sistemaponto.view.ViewRegistrosFuncionario;

/**
 * Controlador de Manter o Funcionário
 *
 * @author Rafael Boing
 * @since 04/07/2026
 */
public class ControllerManterFuncionario {

    /** @var ViewManterFuncionario */
    private ViewManterFuncionario viewManterFuncionario;
    /** @var InterfaceDados */
    private InterfaceDados daoFuncionario;

    /**
     * Construct
     *
     * @param view
     */
    public ControllerManterFuncionario(ViewManterFuncionario view){
        this.viewManterFuncionario = view;
        this.viewManterFuncionario.apresentarTela();
        this.viewManterFuncionario.atualizarTabela();
        this.adicionarAcoes();
    }

    /**
     * Adiciona as ações aos botões
     */
    public void adicionarAcoes() {
        this.viewManterFuncionario.adcionarAcaoBtnIncluir(e -> this.incluirFuncionario());

        this.viewManterFuncionario.adcionarAcaoBtnAlterar(e-> this.alterarFuncionario());

        this.viewManterFuncionario.adcionarAcaoBtnExcluir(e -> this.excluirFuncionario());

        this.viewManterFuncionario.adicionarAcaoBtnVisualizarRegistros(e -> this.chamarTelaRegistrosPontoFuncionario());

        this.viewManterFuncionario.adicionarAcaoBtnPesquisar(e -> this.pesquisarFiltro());

    }

    /**
     * Chamada da Tela de Registros Ponto
     */
    public void chamarTelaRegistrosPontoFuncionario() {
        new ControllerRegistrosFuncionario(new ViewRegistrosFuncionario());
    }

    /**
     * Chamada da Tela de Cadastro de Funcionário
     */
    public void chamarTelaCadastroFuncionario() {
        new ControllerCadastroFuncionario(new ViewCadastroFuncionario());
    }

    /**
     * Inclusão do Funcionário
     */
    public void incluirFuncionario() {
        this.chamarTelaCadastroFuncionario();
    }

    /**
     * Alteração do Funcionário
     */
    public void alterarFuncionario() {
        int codigo = this.viewManterFuncionario.getCodigoSelecionadoNaTabela();

        if (codigo < 0) {
            this.viewManterFuncionario.apresentaMensagem("Selecione um Funcionario!"); //CONTINUAR O METODO NO CADASTRO
            return;
        }
        new ControllerCadastroFuncionario(new ViewCadastroFuncionario(), codigo);
    }

    /**
     * Exclusão do Funcionário
     */
    public void excluirFuncionario() {
        try {
            int codigo = this.viewManterFuncionario.getCodigoSelecionadoNaTabela();

            if (codigo < 0) {
                this.viewManterFuncionario.apresentaMensagem("Selecione um Funcionario!");
                return;
            }
            this.daoFuncionario.excluir(codigo);
            this.viewManterFuncionario.atualizarTabela();

        } catch (Exception e) {
            this.viewManterFuncionario.apresentaMensagem("Erro: " + e.getMessage()); //VERIFICAR SE É ISSO MESMO ESSE EXCEPTION
        }
    }

    /**
     * Pesquisa de Filtros
     */
    public void pesquisarFiltro() {
        System.out.println("Filtro selecionado");
    }
}
