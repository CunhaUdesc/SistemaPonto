package br.com.sistemaponto.model;

/**
 *  Modelo de Dados do Funcionário Fixo
 *
 * @author Rafael Boing
 */
public class ModelFuncionarioFixo extends ModelFuncionario {
    private double salarioBase;
    private float cargaHoraria;

    /**
     * Construct
     *
     * @param nome
     * @param CPF
     * @param tipoFuncionario
     * @param dataNascimento
     * @param salarioBase
     * @param cargaHoraria
     */
    public ModelFuncionarioFixo(String nome, String CPF, String tipoFuncionario, String dataNascimento, double salarioBase, float cargaHoraria) {
        super(nome, CPF, tipoFuncionario, dataNascimento);
        this.salarioBase = salarioBase;
        this.cargaHoraria = cargaHoraria;
    }

    public double getSalarioBase() {
        return salarioBase;
    }

    public void setSalarioBase(double salarioBase) {
        this.salarioBase = salarioBase;
    }

    public float getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(float cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }
    
    @Override
    public String toString(){
        return super.toString() + ", Salario: "+salarioBase+", Carga Horaria: "+cargaHoraria;
    }
}
