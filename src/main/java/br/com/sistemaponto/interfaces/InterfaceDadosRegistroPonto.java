/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.sistemaponto.interfaces;

import br.com.sistemaponto.model.ModelRegistroPonto;
import java.util.Map;

/**
 *
 * @author WIN11
 */
public interface InterfaceDadosRegistroPonto {
    public boolean salvarRegistro(ModelRegistroPonto registro);
    public Map<String, ModelRegistroPonto> buscarRegistros();
}
