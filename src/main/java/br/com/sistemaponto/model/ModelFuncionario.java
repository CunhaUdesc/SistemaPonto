package br.com.sistemaponto.model;

/**
 * Modelo de Dados do Funcionário
 *
 * @author Vitor Hugo da Cunha
 * @since 04/06/2026
 */
public abstract class ModelFuncionario {
    private int codigo;
    private String nome;
    private String CPF;
    private String tipoFuncionario;
    private String dataNascimento;

    /**
     * Construct para adicionar um novo Funcionário
     *
     * @param nome
     * @param CPF
     * @param tipoFuncionario
     * @param dataNascimento
     */
    public ModelFuncionario(String nome, String CPF, String tipoFuncionario, String dataNascimento) {
        this.nome = nome;
        this.CPF = CPF;
        this.tipoFuncionario = tipoFuncionario;
        this.dataNascimento = dataNascimento;
    }

    /**
     * Construct para buscar funcionários
     * 
     * @param codigo
     * @param nome
     * @param CPF
     * @param tipoFuncionario
     * @param dataNascimento
     */
    public ModelFuncionario(int codigo, String nome, String CPF, String tipoFuncionario, String dataNascimento){
        this.codigo = codigo;
        this.nome = nome;
        this.CPF = CPF;
        this.tipoFuncionario = tipoFuncionario;
        this.dataNascimento = dataNascimento;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public String getCPF() {
        return CPF;
    }

    public String getTipoFuncionario() {
        return tipoFuncionario;
    }
    
    public String getDataNascimento() {
        return dataNascimento;
    }
    
    public ModelFuncionario setCodigo(int codigo) {
        this.codigo = codigo;
        return this;
    }

    public ModelFuncionario setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public ModelFuncionario setCPF(String CPF) {
        this.CPF = CPF;
        return this;
    }

    public ModelFuncionario setTipo(String tipoFuncionario) {
        this.tipoFuncionario = tipoFuncionario;
        return this;
    }
    
    public ModelFuncionario setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
        return this;
    }
    
    @Override
    public String toString(){
        return "Codigo: "+codigo+", Nome: "+nome+", CPF: "+CPF+", Tipo: "+tipoFuncionario;
    }
}
