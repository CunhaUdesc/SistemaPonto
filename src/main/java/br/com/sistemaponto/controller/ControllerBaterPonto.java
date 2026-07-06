package br.com.sistemaponto.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import br.com.sistemaponto.dao.DaoRegistroPonto;
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
    private DaoRegistroPonto daoRegistroPonto;

    /** @var ModelRegistroPonto */
    private ModelRegistroPonto registro;

    /**
     * Construct
     *
     * @param viewBaterPonto
     * @param daoRegistroPonto
     */
    public ControllerBaterPonto(ViewBaterPonto viewBaterPonto, DaoRegistroPonto daoRegistroPonto) {
        this.daoRegistroPonto = daoRegistroPonto;
        this.viewBaterPonto = viewBaterPonto;
        this.viewBaterPonto.mostrarTela();
        this.registro = verificaRegistro();
        this.atualizaRegistrosDoDia();
        this.adicionarAcoes();
        this.setLabels();
        this.desabilitaBotao();
    }

    /**
     * Realiza a verificação do Registro Ponto
     *
     * @return ModelRegistroPonto
     */
    public ModelRegistroPonto verificaRegistro(){
        try {
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String data = LocalDate.now().format(formato);

            for (ModelRegistroPonto ponto : this.daoRegistroPonto.getRegistroPontoDiaFuncionario(data, Session.getUsuario().getFuncionario())) {
                return ponto;
            }

        } catch (ExceptionSistemaPonto e) {
            this.viewBaterPonto.apresentaMensagem("Erro: "+e.getMessage());
        }
        return null;
    }

    /**
     * Desabilita o Botão 'Bater Ponto'
     */
    public void desabilitaBotao() {
        if(this.registro.getBotao() == 0){
            this.viewBaterPonto.getBtnEntrada().setEnabled(true);
            this.viewBaterPonto.getBtnSaida().setEnabled(false);
            return;
        }
            this.viewBaterPonto.getBtnEntrada().setEnabled(false);
            this.viewBaterPonto.getBtnSaida().setEnabled(true);
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
            atualizaRegistrosDoDia();
            
            //atualiza botão
            this.registro.setBotao(1);
            desabilitaBotao();
            
            //atualiza o idRegistro
            this.registro.setIdRegistro(this.registro.getIdRegistro()+1);
            
        } catch (ExceptionLimiteRegistroPonto e) {
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

            this.atualizaRegistrosDoDia();
            
            //atualiza botão
            this.registro.setBotao(0);
            this.desabilitaBotao();
            
            //atualiza o idRegistro
            this.registro.setIdRegistro(this.registro.getIdRegistro()+1);
 
        } catch(ExceptionLimiteRegistroPonto e) {
            this.viewBaterPonto.apresentaMensagem("Erro: "+e.getMessage());
        }
    }

    /**
     * Atualiza o Registro dos Pontos do dia
     */
    public void atualizaRegistrosDoDia(){
        this.viewBaterPonto.atualizaRegistros(this.registro.toString());
    }
}
