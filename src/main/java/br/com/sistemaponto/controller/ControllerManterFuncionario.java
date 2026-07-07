package br.com.sistemaponto.controller;

import java.util.List;

import br.com.sistemaponto.dao.DaoFuncionario;
import br.com.sistemaponto.dao.DaoRegistroPonto;
import br.com.sistemaponto.exception.ExceptionSistemaPonto;
import br.com.sistemaponto.interfaces.InterfaceDadosRegistroPonto;
import br.com.sistemaponto.model.ModelFuncionario;
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
    private DaoFuncionario daoFuncionario;

    private InterfaceDadosRegistroPonto daoRegistroPonto;

    /**
     * Construct
     *
     * @param view
     */
    public ControllerManterFuncionario(ViewManterFuncionario view) {
        this.viewManterFuncionario = view;
        this.daoFuncionario = new DaoFuncionario();
        this.daoRegistroPonto = new DaoRegistroPonto();
        this.viewManterFuncionario.apresentarTela();
        this.atualizarTabela();
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
        int codigo = this.viewManterFuncionario.getCodigoSelecionadoNaTabela();

        if (codigo < 0) {
            this.viewManterFuncionario.apresentaMensagem("Selecione um Funcionario!");
            return;
        }

        ModelFuncionario funcionario;

        try {
            funcionario = (ModelFuncionario) daoFuncionario.getFromCodigo(codigo);

            if (funcionario == null) {
                viewManterFuncionario.apresentaMensagem("Funcionário não encontrado.");
                return;
            }

            new ControllerRegistrosFuncionario(new ViewRegistrosFuncionario(), funcionario);

        } catch (ExceptionSistemaPonto e) { //TEM Q VER SE É ISSO MESMO
            viewManterFuncionario.apresentaMensagem("Erro: "+e.getMessage());
        }
    }

    /**
     * Chamada da Tela de Cadastro de Funcionário
     */
    public void chamarTelaCadastroFuncionario() {
        new ControllerCadastroFuncionario(new ViewCadastroFuncionario());
        this.atualizarTabela();
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

        System.out.println(codigo);

        if (codigo < 0) {
            this.viewManterFuncionario.apresentaMensagem("Selecione um Funcionario!");
            return;
        }

        ModelFuncionario funcionario;

        try {
            funcionario = (ModelFuncionario) daoFuncionario.getFromCodigo(codigo);

            if (funcionario == null) {
                viewManterFuncionario.apresentaMensagem("Funcionario não encontrado.");
                return;
            }
            
            new ControllerCadastroFuncionario(new ViewCadastroFuncionario(), funcionario);

        } catch (ExceptionSistemaPonto e) { //TEM Q VER SE É ISSO MESMO
            viewManterFuncionario.apresentaMensagem("Erro: "+e.getMessage());
        
        }
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
            this.viewManterFuncionario.apresentaMensagem("Funcionario excluído com sucesso!");
            this.atualizarTabela();

        } catch (Exception e) {
            this.viewManterFuncionario.apresentaMensagem("Erro: " + e.getMessage()); //VERIFICAR SE É ISSO MESMO ESSE EXCEPTION
        }
    }

    /**
     * Pesquisa de Filtros
     */
    public void pesquisarFiltro() {
        String tipoFiltro = viewManterFuncionario.getTipoFiltro();
        String textoFiltro = viewManterFuncionario.getFiltro();

        if(textoFiltro.isEmpty()){
            this.atualizarTabela();
            return;
        }
        try{
            switch (tipoFiltro) {
                case "CPF":
                    ModelFuncionario funcionario =  daoFuncionario.getFuncionarioFromCpf(textoFiltro);
                    this.atualizarTabela(funcionario);
                    break;
                case "Nome":
                    List<ModelFuncionario> listaNome = daoFuncionario.getFuncionariosFromNome(textoFiltro);
                    this.atualizarTabela(listaNome);
                    break;
                case "Tipo":
                    List<ModelFuncionario> listaTipo = daoFuncionario.getFuncionariosFromTipo(textoFiltro);
                    this.atualizarTabela(listaTipo);
                    break;
            }
        } catch (ExceptionSistemaPonto e) {
            viewManterFuncionario.apresentaMensagem("Erro: " + e.getMessage());
        }
    }

    public void atualizarTabela(){
        try {
            List<ModelFuncionario> listaFuncionario = ((DaoFuncionario) daoFuncionario).selectAll();
            this.viewManterFuncionario.preencherTabela(listaFuncionario);
        } catch (ExceptionSistemaPonto e) {
            this.viewManterFuncionario.apresentaMensagem("Selecione um Funcionario!");
        }
    }

    public void atualizarTabela(List<ModelFuncionario> listaFuncionario){ //Para os Filtros
        this.viewManterFuncionario.preencherTabela(listaFuncionario);
    }

    public void atualizarTabela(ModelFuncionario funcionario){ //Para os Filtros
        if (funcionario == null) {
            viewManterFuncionario.apresentaMensagem("Funcionario não encontrado.");
            return;
        }
        this.viewManterFuncionario.preencherTabelaRegistroUnico(funcionario);
    }
}
