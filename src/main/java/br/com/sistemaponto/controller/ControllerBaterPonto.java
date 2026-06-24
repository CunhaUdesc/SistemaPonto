package br.com.sistemaponto.controller;

import br.com.sistemaponto.dao.*;
import br.com.sistemaponto.exception.*;
import br.com.sistemaponto.model.*;
import br.com.sistemaponto.view.*;
import java.util.*;

/**
 * Controller da Batida de Ponto
 *
 * @author Rafael
 * @since 15/06/2026
 */
public class ControllerBaterPonto {

    /** @var ModelUsuario */
    private ModelUsuario usuario;

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
    public ControllerBaterPonto(ViewBaterPonto viewBaterPonto, DaoRegistroPonto daoRegistroPonto, ModelUsuario usuario) {
        this.usuario = usuario;
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
        if(usuario.getFuncionario().getRegistroPonto().getBotao() == 0){
            viewBaterPonto.getBtnEntrada().setEnabled(true);
            viewBaterPonto.getBtnSaida().setEnabled(false);
            return;
        }
            viewBaterPonto.getBtnEntrada().setEnabled(false);
            viewBaterPonto.getBtnSaida().setEnabled(true);
    }

    public void setLabels(){
        viewBaterPonto.setLabels(usuario.getFuncionario().getNome());
    }
    
    public void adicionarAcoes(){
        viewBaterPonto.adicionarAcaoBtnEntradaPonto(e -> baterPontoEntrada());
        
        viewBaterPonto.adicionarAcaoBtnSaidaPonto(e -> baterPontoSaida());
    }
    
    public void baterPontoEntrada() {
        
        try {
            boolean adicionado = usuario.getFuncionario().getRegistroPonto().addRegistro("Entrada -- " + viewBaterPonto.getDataAtual());
            
            if (!adicionado) {
                throw new ExceptionLimiteRegistroPonto("Limite de Registros do dia atingido");
            }
            atualizaRegistrosDoDia();
            usuario.getFuncionario().getRegistroPonto().setBotao(1);
            desabilitaBotao();
            
        } catch (ExceptionLimiteRegistroPonto e) {
            viewBaterPonto.apresentaMensagem(e.getMessage());
        }
    }
    
    public void baterPontoSaida(){ //talvez pode tirar, pois o último registro sempre é uma saída
        
        try{
            boolean adicionado = usuario.getFuncionario().getRegistroPonto().addRegistro("Saida -- "+viewBaterPonto.getDataAtual());
            
            if(!adicionado)
                throw new ExceptionLimiteRegistroPonto("Limite de Registros do dia atingido");
            
            atualizaRegistrosDoDia();
            
            usuario.getFuncionario().getRegistroPonto().setBotao(0);
            desabilitaBotao();
 
        }catch(ExceptionLimiteRegistroPonto e){
            viewBaterPonto.apresentaMensagem(e.getMessage());
        }
    }
    
    public void atualizaRegistrosDoDia(){
        List<String> registros = usuario.getFuncionario().getRegistroPonto().getRegistrosDia();
        StringBuilder sb = new StringBuilder();
        for(String r : registros){
            sb.append(r).append("\n");
        }
        viewBaterPonto.atualizaRegistros(sb.toString().trim());
    }
}
