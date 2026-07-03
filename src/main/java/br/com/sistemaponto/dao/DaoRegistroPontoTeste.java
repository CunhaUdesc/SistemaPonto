/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.sistemaponto.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import br.com.sistemaponto.exception.ExceptionSistemaPonto;
import br.com.sistemaponto.interfaces.InterfaceDadosRegistroPonto;
import br.com.sistemaponto.model.ModelFuncionario;
import br.com.sistemaponto.model.ModelRegistroPonto;
import br.com.sistemaponto.util.Conexao;

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

    /**
     * Retorna o Registro Ponto do Funcionário para determinado dia
     *
     * @param data
     * @return
     */
    public ModelRegistroPonto getRegistroPontoDiaFuncionario(String data, ModelFuncionario func) throws ExceptionSistemaPonto {
        String sql = """
            SELECT * 
              FROM tbregistroponto 
             WHERE regdataregistro = ?
               AND funcodigo = ? 
        """;
        
        try (
            Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql);
        ) {
            stmt.setString(1, data);
            stmt.setInt(2, func.getCodigo());
            ResultSet rst = stmt.executeQuery();

            ModelRegistroPonto registro = new ModelRegistroPonto();
            if (rst.next()) {
                registro.setFuncionario((new DaoFuncionario()).getFuncionarioFromCodigo(rst.getInt("funcodigo")));
            }
            return registro;

        } catch (Exception ex) {
            throw new ExceptionSistemaPonto("Nenhum registro encontrado!");
        }
    }

    /**
     * Filtros
     *
     * Data do Registro
     */

    @Override
    public Map<String, ModelRegistroPonto> buscarRegistros() {
        return registros;
    }
}
