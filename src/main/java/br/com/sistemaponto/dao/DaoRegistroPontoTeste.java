/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.sistemaponto.dao;

import java.util.HashMap;
import java.util.Map;

import br.com.sistemaponto.interfaces.InterfaceDadosRegistroPonto;
import br.com.sistemaponto.model.ModelRegistroPonto;

/**
 *
 * @author WIN11
 */
public class DaoRegistroPontoTeste implements InterfaceDadosRegistroPonto{
    private static Map<String, ModelRegistroPonto> registros;
    
    public DaoRegistroPontoTeste(){
        this.registros = new HashMap<>();
    }

    @Override
    public boolean salvarRegistro(ModelRegistroPonto registro) {
        try{
            registros.put(registro.getFuncionario().getNome(), registro);
            return true;
        } catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Map<String, ModelRegistroPonto> buscarRegistros() {
        return registros;
    }
}
