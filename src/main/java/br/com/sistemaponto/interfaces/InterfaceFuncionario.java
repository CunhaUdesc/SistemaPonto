/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.sistemaponto.interfaces;

import br.com.sistemaponto.model.ModelFuncionario;
import java.util.Map;

/**
 *
 * @author Lenovo
 */
public interface InterfaceFuncionario {
    public void adicionarFuncionario(ModelFuncionario funcionario); // ADM
    public void alterarFuncionario(ModelFuncionario funcionario); // ADM
    public void excluirFuncionario(ModelFuncionario funcionario); // ADM
    public ModelFuncionario getFuncionario(int id); // ADM -- FUNCIONARIO
    public Map<Integer, ModelFuncionario> getAllFuncionarios(); // ADM
    public ModelFuncionario getFuncionarioFromCpf(String cpf); // ADM -- FUNCIONARIO
    public ModelFuncionario getFuncionarioFromNome(String nome); // ADM
    public Map<Integer, ModelFuncionario> getFuncionarioFromTipo(String tipo);  // ADM
    //public Map<String, ModelFuncionario> getFuncionariosFromSalario(); ---- opcional
}
