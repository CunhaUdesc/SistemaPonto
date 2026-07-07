package br.com.sistemaponto.controller;

import java.util.Set;

import br.com.sistemaponto.dao.DaoRegistroPonto;
import br.com.sistemaponto.exception.ExceptionSistemaPonto;
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

    private DaoRegistroPonto daoRegistroPonto;

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
        this.setLabels();
        this.atualizarTabela();
        this.adicionarAcoes();
        this.view.apresentarTela();
    }

    /**
     * Adiciona as Ações aos Botões da Tela
     */
    public void adicionarAcoes() {
        this.view.acaoBtnPesquisar(e -> this.pesquisarFiltro());
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
            Set<ModelRegistroPonto> listaRegistros = daoRegistroPonto.getRegistrosFromFuncionarioCodigo(codigo);
            this.view.preencherTabela(listaRegistros);
        } catch (ExceptionSistemaPonto e) {
            this.view.apresentaMensagem("Selecione um Funcionario!");
        }
    }

    public void atualizarTabela(Set<ModelRegistroPonto> registros){
        this.view.preencherTabela(registros);
    }

    public void pesquisarFiltro() {
        String tipoFiltro = view.getTipoFiltro();
        String textoFiltro = view.getFiltro();

        if(textoFiltro.isEmpty()){
            this.atualizarTabela();
            return;
        }
        try{
            switch (tipoFiltro) {
                case "Dia":
                    if(textoFiltro.length()==10){
                        Set<ModelRegistroPonto> setRegistros =  daoRegistroPonto.getRegistrosFromDia(textoFiltro);
                        this.atualizarTabela(setRegistros);
                        break;
                    }
                    this.view.apresentaMensagem("Favor digite uma data válida!");
                    break;
                case "Ano":
                    Set<ModelRegistroPonto> registros = daoRegistroPonto.getRegistrosFromAno(textoFiltro);
                    this.atualizarTabela(registros);
                    break;
            }
        } catch (ExceptionSistemaPonto e) {
            view.apresentaMensagem("Erro: " + e.getMessage());
        }
    }
}
