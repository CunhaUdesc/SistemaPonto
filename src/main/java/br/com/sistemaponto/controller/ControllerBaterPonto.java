package br.com.sistemaponto.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
    
    private ModelRegistroPonto registro;

    /**
     * Construct
     *
     * @param viewBaterPonto
     * @param daoRegistroPonto
     * @param usuario
     */
    public ControllerBaterPonto(ViewBaterPonto viewBaterPonto, DaoRegistroPontoTeste daoRegistroPonto) {
        this.daoRegistroPonto = daoRegistroPonto;
        this.viewBaterPonto = viewBaterPonto;
        this.viewBaterPonto.mostrarTela();
        this.registro = verificaRegistro();
        this.atualizaRegistrosDoDia();
        this.adicionarAcoes();
        this.setLabels();
        this.desabilitaBotao();
    }
    
    public ModelRegistroPonto verificaRegistro(){
        try {
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String data = LocalDate.now().format(formato);
            return daoRegistroPonto.getRegistroPontoDiaFuncionario(data, Session.getUsuario().getFuncionario());
        } catch (ExceptionSistemaPonto e) {
            viewBaterPonto.apresentaMensagem("Erro: "+e.getMessage());
            return null;
        }
    }

    public void desabilitaBotao() {
        if(this.registro.getBotao() == 0){
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
            switch(this.registro.getIdRegistro()){
                case 1:
                    adicionado = this.registro.setRegistroEntrada(viewBaterPonto.getDataAtual());
                    break;
                case 3:
                    adicionado = this.registro.setRegistroEntradaIntervalo(viewBaterPonto.getDataAtual());
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
            viewBaterPonto.apresentaMensagem("Erro: "+e.getMessage());
        }
    }
    
    public void baterPontoSaida(){
        boolean adicionado = false;
        
        try {
            switch(this.registro.getIdRegistro()){
                case 2:
                    adicionado = this.registro.setRegistroSaida(viewBaterPonto.getDataAtual());
                    break;
                case 4:
                    adicionado = this.registro.setRegistroSaidaIntervalo(viewBaterPonto.getDataAtual());
                    break;
            }
            
            if(!adicionado)
                throw new ExceptionLimiteRegistroPonto("Limite de Registros do dia atingido");
            
            atualizaRegistrosDoDia();
            
            //atualiza botão
            this.registro.setBotao(0);
            desabilitaBotao();
            
            //atualiza o idRegistro
            this.registro.setIdRegistro(this.registro.getIdRegistro()+1);
 
        }catch(ExceptionLimiteRegistroPonto e){
            viewBaterPonto.apresentaMensagem("Erro: "+e.getMessage());
        }
    }
    
    public void atualizaRegistrosDoDia(){
        viewBaterPonto.atualizaRegistros(this.registro.toString());
    }
}
