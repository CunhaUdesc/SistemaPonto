package br.com.sistemaponto.model;

import java.util.Objects;

/**
 * Modelo de Dados do Registro de Ponto
 *
 * @author Vitor Hugo da Cunha
 * @since 06/06/2026
 */
public class ModelRegistroPonto implements Comparable<ModelRegistroPonto>{

    private int codigo;
    private ModelFuncionario funcionario;
    private String diaAtual;
    private String registroEntrada;
    private String registroSaida;
    private String registroEntradaIntervalo;
    private String registroSaidaIntervalo;
    private float horasDia;

    private int botao = 0; //0 para ENTRADA e 1 para
    private int idRegistro = 1; // 1 a 4 para definir qual registro salvar

    /**
     * Construct
     */
    public ModelRegistroPonto() {}

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getDiaAtual() {
        return diaAtual;
    }

    public String getRegistroEntrada() {
        return registroEntrada;
    }

    public String getRegistroSaida() {
        return registroSaida;
    }

    public String getRegistroEntradaIntervalo() {
        return registroEntradaIntervalo;
    }

    public String getRegistroSaidaIntervalo() {
        return registroSaidaIntervalo;
    }
    
    public ModelFuncionario getFuncionario(){
        return funcionario;
    }
    
    public int getBotao(){
        return botao;
    }

    public int getIdRegistro() {
        return idRegistro;
    }

    public void setDiaAtual(String diaAtual) {
        this.diaAtual = diaAtual;
    }

    public boolean setRegistroEntrada(String registroEntrada) {
        if(idRegistro == 5)
            return false;
        this.registroEntrada = registroEntrada;
        return true;
    }

    public boolean setRegistroSaida(String registroSaida) {
        if(idRegistro == 5)
            return false;
        this.registroSaida = registroSaida;
        return true;
    }

    public boolean setRegistroEntradaIntervalo(String registroEntradaIntervalo) {
        if(idRegistro == 5)
            return false;
        this.registroEntradaIntervalo = registroEntradaIntervalo;
        return true;
    }

    public boolean setRegistroSaidaIntervalo(String registroSaidaIntervalo) {
        if(idRegistro == 5)
            return false;
        this.registroSaidaIntervalo = registroSaidaIntervalo;
        return true;
    }

    public void setFuncionario(ModelFuncionario funcionario){
        this.funcionario = funcionario;
    }
    
    public void setBotao(int botao){
        this.botao = botao;
    }

    public void setIdRegistro(int idRegistro) {
        this.idRegistro = idRegistro;
    }

    public void atualizarProximoRegistro() {

        if (this.registroEntrada == null) {
            idRegistro = 1; // Entrada
            botao = 1;

        } else if (this.registroSaida == null) {
            idRegistro = 2; // Saída para intervalo
            botao = 0;

        } else if (this.registroEntradaIntervalo == null) {
            idRegistro = 3; // Volta intervalo
            botao = 1;

        } else if (this.registroSaidaIntervalo == null) {
            idRegistro = 4; // Saída final
            botao = 0;

        } else {
            idRegistro = 5; // Dia encerrado
        }
    }
    
    @Override
    public String toString(){

        StringBuilder s = new StringBuilder();

        if(getRegistroEntrada() != null){
            s.append(getRegistroEntrada()).append("\n");
        }

        if(getRegistroSaida() != null){
            s.append(getRegistroSaida()).append("\n");
        }

        if(getRegistroEntradaIntervalo() != null){
            s.append(getRegistroEntradaIntervalo()).append("\n");
        }

        if(getRegistroSaidaIntervalo() != null){
            s.append(getRegistroSaidaIntervalo()).append("\n");
        }

        return s.toString().trim();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + this.codigo;
        hash = 61 * hash + Objects.hashCode(this.diaAtual);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ModelRegistroPonto other = (ModelRegistroPonto) obj;
        if (this.codigo != other.codigo) {
            return false;
        }
        return Objects.equals(this.diaAtual, other.diaAtual);
    }

    @Override
    public int compareTo(ModelRegistroPonto outro) {
        return Integer.compare(this.codigo, outro.codigo);
    }
}
