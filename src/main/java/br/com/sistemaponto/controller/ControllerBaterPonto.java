package br.com.sistemaponto.controller;

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
        boolean adicionado = false;
        
        try {
            switch(Session.getUsuario().getFuncionario().getRegistroPonto().getIdRegistro()){
                case 1:
                    adicionado = Session.getUsuario().getFuncionario().getRegistroPonto().setRegistroEntrada(viewBaterPonto.getDataAtual());
                    break;                    
                case 3:
                    adicionado = Session.getUsuario().getFuncionario().getRegistroPonto().setRegistroEntradaIntervalo(viewBaterPonto.getDataAtual());
                    break;            
            }
            if (!adicionado) {
                throw new ExceptionLimiteRegistroPonto("Limite de Registros do dia atingido");
            }
            atualizaRegistrosDoDia();
            
            //atualiza botão
            Session.getUsuario().getFuncionario().getRegistroPonto().setBotao(1);
            desabilitaBotao();
            
            //atualiza o idRegistro
            Session.getUsuario().getFuncionario().getRegistroPonto().setIdRegistro(Session.getUsuario().getFuncionario().getRegistroPonto().getIdRegistro()+1);
            
        } catch (ExceptionLimiteRegistroPonto e) {
            viewBaterPonto.apresentaMensagem("Erro: "+e.getMessage());
        }
    }
    
    public void baterPontoSaida(){
        boolean adicionado = false;
        
        try {
            switch(Session.getUsuario().getFuncionario().getRegistroPonto().getIdRegistro()){
                case 2:
                    adicionado = Session.getUsuario().getFuncionario().getRegistroPonto().setRegistroSaida(viewBaterPonto.getDataAtual());
                    break;                    
                case 4:
                    adicionado = Session.getUsuario().getFuncionario().getRegistroPonto().setRegistroSaidaIntervalo(viewBaterPonto.getDataAtual());
                    break;            
            }
            
            if(!adicionado)
                throw new ExceptionLimiteRegistroPonto("Limite de Registros do dia atingido");
            
            atualizaRegistrosDoDia();
            
            //atualiza botão
            Session.getUsuario().getFuncionario().getRegistroPonto().setBotao(0);
            desabilitaBotao();
            
            //atualiza o idRegistro
            Session.getUsuario().getFuncionario().getRegistroPonto().setIdRegistro(Session.getUsuario().getFuncionario().getRegistroPonto().getIdRegistro()+1);
 
        }catch(ExceptionLimiteRegistroPonto e){
            viewBaterPonto.apresentaMensagem("Erro: "+e.getMessage());
        }
    }
    
    public void atualizaRegistrosDoDia(){
        viewBaterPonto.atualizaRegistros(Session.getUsuario().getFuncionario().getRegistroPonto().toString());
    }
}
