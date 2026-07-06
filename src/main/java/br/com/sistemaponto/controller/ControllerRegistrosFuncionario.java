package br.com.sistemaponto.controller;

import java.util.List;

import br.com.sistemaponto.dao.DaoRegistroPonto;
import br.com.sistemaponto.exception.ExceptionSistemaPonto;
import br.com.sistemaponto.interfaces.InterfaceDadosRegistroPonto;
import br.com.sistemaponto.model.ModelFuncionario;
import br.com.sistemaponto.model.ModelRegistroPonto;
import br.com.sistemaponto.view.ViewRegistrosFuncionario;

/**
 * Controller do Registro de Ponto do Funcionário
 *
 * @author Rafael Boing
 * @since 09/06/2026
 */
public class ControllerRegistrosFuncionario {

    /** @var ViewRegistrossFuncionário */
    private ViewRegistrosFuncionario view;

    private InterfaceDadosRegistroPonto daoRegistroPonto;

    private ModelFuncionario func;

    /**
     * Construct
     *
     * @param viewRegistrosFuncionario
     */
    public ControllerRegistrosFuncionario(ViewRegistrosFuncionario viewRegistrosFuncionario, ModelFuncionario funcionario) {
        this.func = funcionario;
        this.daoRegistroPonto = new DaoRegistroPonto();
        this.view = viewRegistrosFuncionario;
        this.atualizarTabela();
        this.adicionarAcoes();
        this.view.apresentarTela();
    }

    /**
     * Adiciona as Ações aos Botões da Tela
     */
    public void adicionarAcoes() {
        this.view.acaoBtnPesquisar(e -> this.pesquisar());
    }

    /**
     * Define as Labels da Tela
     */
    public void setLabels() {
        this.view.setLabelNomeFuncionario(func.getNome());
    }

    public void atualizarTabela(){
        int codigo = func.getCodigo();
        try {
            List<ModelRegistroPonto> listaRegistros = ((DaoRegistroPonto) daoRegistroPonto).getRegistrosFromFuncionarioCodigo(codigo);
            this.view.preencherTabela(listaRegistros);
        } catch (ExceptionSistemaPonto e) {
            this.view.apresentaMensagem("Selecione um Funcionario!");
        }
    }
/*/
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
*/
    /**
     * Pesquisa
     */
    public void pesquisar() {
        System.out.println("TESTE");
    }
}
