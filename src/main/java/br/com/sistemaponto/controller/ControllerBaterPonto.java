package br.com.sistemaponto.controller;

import java.util.List;

import br.com.sistemaponto.dao.DaoRegistroPonto;
import br.com.sistemaponto.exception.ExceptionLimiteRegistroPonto;
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

    /**
     * Construct
     *
     * @param viewBaterPonto
     * @param daoRegistroPonto
     * @param usuario
     */
    public ControllerBaterPonto(ViewBaterPonto viewBaterPonto, DaoRegistroPonto daoRegistroPonto) {
        this.daoRegistroPonto = daoRegistroPonto;
        this.viewBaterPonto = viewBaterPonto;
        this.viewBaterPonto.mostrarTela();
        this.atualizaRegistrosDoDia();
        this.adicionarAcoes();
        this.setLabels();
        this.desabilitaBotao();
    }

    /**
     * Desabilita o botão
     */
    public void desabilitaBotao() {
        //Testando como desabilitar o botão
        if(Session.getUsuario().getFuncionario().getRegistroPonto().getBotao() == 0){
            viewBaterPonto.getBtnEntrada().setEnabled(true);
            viewBaterPonto.getBtnSaida().setEnabled(false);
            return;
        }
            viewBaterPonto.getBtnEntrada().setEnabled(false);
            viewBaterPonto.getBtnSaida().setEnabled(true);
    }

    public void setLabels(){
        viewBaterPonto.setLabels(Session.getUsuario().getFuncionario().getNome());
    }
    
    public void adicionarAcoes(){
        viewBaterPonto.adicionarAcaoBtnEntradaPonto(e -> baterPontoEntrada());
        
        viewBaterPonto.adicionarAcaoBtnSaidaPonto(e -> baterPontoSaida());
    }
    
    public void baterPontoEntrada() {
        
        try {
            boolean adicionado = Session.getUsuario().getFuncionario().getRegistroPonto().addRegistro("Entrada -- " + viewBaterPonto.getDataAtual());
            
            if (!adicionado) {
                throw new ExceptionLimiteRegistroPonto("Limite de Registros do dia atingido");
            }
            atualizaRegistrosDoDia();
            Session.getUsuario().getFuncionario().getRegistroPonto().setBotao(1);
            desabilitaBotao();
            
        } catch (ExceptionLimiteRegistroPonto e) {
            viewBaterPonto.apresentaMensagem(e.getMessage());
        }
    }
    
    public void baterPontoSaida(){ //talvez pode tirar, pois o último registro sempre é uma saída
        
        try{
            boolean adicionado = Session.getUsuario().getFuncionario().getRegistroPonto().addRegistro("Saida -- "+viewBaterPonto.getDataAtual());
            
            if(!adicionado)
                throw new ExceptionLimiteRegistroPonto("Limite de Registros do dia atingido");
            
            atualizaRegistrosDoDia();
            
            Session.getUsuario().getFuncionario().getRegistroPonto().setBotao(0);
            desabilitaBotao();
 
        }catch(ExceptionLimiteRegistroPonto e){
            viewBaterPonto.apresentaMensagem(e.getMessage());
        }
    }
    
    public void atualizaRegistrosDoDia(){
        List<String> registros = Session.getUsuario().getFuncionario().getRegistroPonto().getRegistrosDia();
        StringBuilder sb = new StringBuilder();
        for(String r : registros){
            sb.append(r).append("\n");
        }
        viewBaterPonto.atualizaRegistros(sb.toString().trim());
    }
}
