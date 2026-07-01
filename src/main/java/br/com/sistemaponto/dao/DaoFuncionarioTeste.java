package br.com.sistemaponto.dao;

import br.com.sistemaponto.interfaces.InterfaceFuncionario;
import br.com.sistemaponto.model.ModelFuncionario;
import java.util.HashMap;
import java.util.Map;

public class DaoFuncionarioTeste implements InterfaceFuncionario{
    private Map<Integer, ModelFuncionario> funcionarios;
    
    public DaoFuncionarioTeste(){
        this.funcionarios = new HashMap<>();
    }

    @Override
    public void adicionarFuncionario(ModelFuncionario funcionario) {
        funcionarios.put(funcionario.getId(), funcionario);
    }

    @Override
    public void alterarFuncionario(ModelFuncionario funcionario) {
        funcionarios.put(funcionario.getId(), funcionario);
    }

    @Override
    public void excluirFuncionario(ModelFuncionario funcionario) {
        funcionarios.remove(funcionario.getId());
    }

    @Override
    public Map<Integer, ModelFuncionario> getAllFuncionarios() {
        return this.funcionarios;
    }

    @Override
    public ModelFuncionario getFuncionarioFromCpf(String cpf) {
        for(ModelFuncionario f : funcionarios.values()){
            if(f.getCPF().equals(cpf))
                return f;
        }
        return null;
    }

    @Override
    public ModelFuncionario getFuncionarioFromNome(String nome) {
        for(ModelFuncionario f : funcionarios.values()){
            if(f.getNome().equals(nome))
                return f;
        }
        return null;    }

    @Override
    public Map<Integer, ModelFuncionario> getFuncionarioFromTipo(String tipo) {
        Map<Integer, ModelFuncionario> lista = new HashMap<>();
        
        for(ModelFuncionario f : funcionarios.values()){
            if(f.getTipoFuncionario().equals(tipo))
                lista.put(f.getId(), f);
        }
        return lista;    
    }

    @Override
    public ModelFuncionario getFuncionario(int id) {
        return funcionarios.get(id);
    }

}
