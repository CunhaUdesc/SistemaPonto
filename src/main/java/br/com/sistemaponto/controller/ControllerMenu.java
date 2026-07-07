package br.com.sistemaponto.controller;

import br.com.sistemaponto.dao.DaoRegistroPontoTeste;
import br.com.sistemaponto.view.ViewBaterPonto;
import br.com.sistemaponto.view.ViewManterFuncionario;
import br.com.sistemaponto.view.ViewMenu;

/**
 * Controller do Menu Principal
 *
 * @author Rafael
 * @since 09/06/2026
 */
public class ControllerMenu {

    /** @var ViewMenu */
    private ViewMenu viewMenu;

    /**
     * Construct
     *
     * @param viewMenu
     */
    public ControllerMenu(ViewMenu viewMenu) {
        this.viewMenu = viewMenu;
        viewMenu.apresentarTela();
        this.adicionarAcoes();
    }

    /**
     * Adiciona as Ações dos Botões da tela
     */
    public void adicionarAcoes() {
        //Adicionado funções dos botões
        this.viewMenu.adcionarAcaoBtnBaterPonto(e -> this.chamarTelaBaterPonto());

        this.viewMenu.adcionarAcaoBtnManterFuncionario(e -> this.abrirTelaManterFuncionario());
    }

    /**
     * Chamada da tela de bater ponto
     */
    public void chamarTelaBaterPonto() {
        new ControllerBaterPonto(new ViewBaterPonto(), new DaoRegistroPontoTeste());
    }

    /**
     * Abre a tela de manter funcionários
     */
    public void abrirTelaManterFuncionario() {
        new ControllerManterFuncionario(new ViewManterFuncionario());
    }
}