/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.sistemaponto.interfaces;

import br.com.sistemaponto.exception.ExceptionSistemaPonto;
import br.com.sistemaponto.model.ModelRegistroPonto;
import java.util.List;

/**
 *  Interface para o DAO de Registro Ponto
 *
 * @author Rafael Boing
 * @since 03/07/2026
 */
public interface InterfaceDadosRegistroPonto {

    public void salvarRegistro(ModelRegistroPonto registro) throws ExceptionSistemaPonto;

    public List<ModelRegistroPonto> selectAll() throws ExceptionSistemaPonto;
}
