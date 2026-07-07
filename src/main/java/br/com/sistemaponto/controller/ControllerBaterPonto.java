package br.com.sistemaponto.controller;

import java.time.LocalDate;

import br.com.sistemaponto.dao.DaoRegistroPontoTeste;
import br.com.sistemaponto.exception.ExceptionLimiteRegistroPonto;
import br.com.sistemaponto.exception.ExceptionSistemaPonto;
import br.com.sistemaponto.model.ModelRegistroPonto;
import br.com.sistemaponto.util.Session;
import br.com.sistemaponto.view.ViewBaterPonto;

/**
 * Controller da Batida de Ponto
 *
 * @author Rafael
 * @since 15/06/2026
 */
public class ControllerBaterPonto {

    /** @var ViewBaterPonto */
    private ViewBaterPonto viewBaterPonto;

    /** @var DaoRegistroPonto */
    private DaoRegistroPontoTeste daoRegistroPonto;

    /** @var ModelRegistroPonto */
    private ModelRegistroPonto registro;

    /**
     * Construct
     *
     * @param viewBaterPonto
     * @param daoRegistroPonto
     */
    public ControllerBaterPonto(ViewBaterPonto viewBaterPonto, DaoRegistroPontoTeste daoRegistroPonto) {
        this.daoRegistroPonto = daoRegistroPonto;
        this.viewBaterPonto = viewBaterPonto;

        this.carregarRegistroDia();

        this.setLabels();
        this.atualizaRegistrosDoDia();
        this.adicionarAcoes();
        this.desabilitaBotao();
        this.viewBaterPonto.mostrarTela();
    }

    public ModelRegistroPonto verificaRegistro(){
        try {
            String data = LocalDate.now().toString();

            ModelRegistroPonto ponto = this.daoRegistroPonto.getRegistroPontoDiaFuncionario(data, Session.getUsuario().getFuncionario());
            return ponto;

        } catch (ExceptionSistemaPonto e) {
            this.viewBaterPonto.apresentaMensagem("Erro: "+e.getMessage());
        }
        return null;
    }

    private void carregarRegistroDia() {

        this.registro = this.verificaRegistro();

        if (this.registro == null) {

            this.registro = new ModelRegistroPonto();
            this.registro.setFuncionario(Session.getUsuario().getFuncionario());

            this.registro.setDiaAtual(LocalDate.now().toString());

            this.registro.setIdRegistro(1);
            this.registro.setBotao(0);

        } else {
            this.registro.atualizarProximoRegistro();
        }
    }

    /**
     * Desabilita o Botão 'Bater Ponto'
     */
    public void desabilitaBotao() {

        if( this.registro.getBotao() == 4){
            this.viewBaterPonto.getBtnEntrada().setEnabled(false);
            this.viewBaterPonto.getBtnSaida().setEnabled(false);
        } else if(this.registro.getBotao() == 0){
            this.viewBaterPonto.getBtnEntrada().setEnabled(true);
            this.viewBaterPonto.getBtnSaida().setEnabled(false);

        } else {
            this.viewBaterPonto.getBtnEntrada().setEnabled(false);
            this.viewBaterPonto.getBtnSaida().setEnabled(true);
        }
    }

    /**
     * Adiciona o nome do funcionário atual na view
     */
    public void setLabels(){
        this.viewBaterPonto.setLabels(Session.getUsuario().getFuncionario().getNome());
    }

    /**
     * Adiciona as ações aos botões
     */
    public void adicionarAcoes(){
        this.viewBaterPonto.adicionarAcaoBtnEntradaPonto(e -> this.baterPontoEntrada());
        this.viewBaterPonto.adicionarAcaoBtnSaidaPonto(e -> this.baterPontoSaida());
    }

    /**
     * Registro de Ponto da Primeira Entrada
     */
    public void baterPontoEntrada() {
        boolean adicionado = false;
        
        try {
            switch (this.registro.getIdRegistro()) {
                case 1:
                    adicionado = this.registro.setRegistroEntrada(this.viewBaterPonto.getDataAtual());
                    break;
                case 3:
                    adicionado = this.registro.setRegistroEntradaIntervalo(this.viewBaterPonto.getDataAtual());
                    break;
            }
            if (!adicionado) {
                throw new ExceptionLimiteRegistroPonto("Limite de Registros do dia atingido");
            }

            daoRegistroPonto.salvarRegistro(this.registro);

            this.registro.atualizarProximoRegistro();

            this.atualizaRegistrosDoDia();
            this.desabilitaBotao();
            
        } catch (ExceptionLimiteRegistroPonto e) {
            this.viewBaterPonto.apresentaMensagem("Erro: "+e.getMessage());

        } catch (ExceptionSistemaPonto e) {
            this.viewBaterPonto.apresentaMensagem("Erro: "+e.getMessage());
        }
    }

    /**
     * Registro de ponto da primeira saída
     */
    public void baterPontoSaida(){
        boolean adicionado = false;
        
        try {
            switch(this.registro.getIdRegistro()){
                case 2:
                    adicionado = this.registro.setRegistroSaida(this.viewBaterPonto.getDataAtual());
                    break;
                case 4:
                    adicionado = this.registro.setRegistroSaidaIntervalo(this.viewBaterPonto.getDataAtual());
                    break;
            }
            
            if(!adicionado)
                throw new ExceptionLimiteRegistroPonto("Limite de Registros do dia atingido");

            daoRegistroPonto.salvarRegistro(this.registro);

            this.registro.atualizarProximoRegistro();

            this.atualizaRegistrosDoDia();
            this.desabilitaBotao();

        } catch(ExceptionLimiteRegistroPonto e) {
            this.viewBaterPonto.apresentaMensagem("Erro: "+e.getMessage());

        } catch (ExceptionSistemaPonto e) {
            this.viewBaterPonto.apresentaMensagem("Erro: "+e.getMessage());
        }
    }

    /**
     * Atualiza o Registro dos Pontos do dia
     */
    public void atualizaRegistrosDoDia(){
        this.viewBaterPonto.atualizaRegistros(this.registro);
    }
}
