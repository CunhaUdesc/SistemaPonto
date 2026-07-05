package br.com.sistemaponto.controller;

import br.com.sistemaponto.util.Session;
import br.com.sistemaponto.view.ViewRegistrosFuncionario;

/**
 * Controller do Registro de Ponto do Funcionário
 *
 * @author Rafael Boing
 * @since 09/06/2026
 */
public class ControllerRegistrosFuncionario {

    /** @var ViewRegistrossFuncionário */
    ViewRegistrosFuncionario view;

    /**
     * Construct
     *
     * @param viewRegistrosFuncionario
     */
    public ControllerRegistrosFuncionario(ViewRegistrosFuncionario viewRegistrosFuncionario) {
        this.view = viewRegistrosFuncionario;
        this.view.apresentarTela();
        this.adicionarAcoes();
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
        this.view.setLabelNomeFuncionario(Session.getUsuario().getFuncionario().getNome());
    }

    /**
     * Pesquisa
     */
    public void pesquisar() {
        System.out.println(" ");
    }
}
