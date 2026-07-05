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

    /**
     * Salva um novo registro no Banco de Dados
     *
     * @param registro
     * @throws ExceptionSistemaPonto
     */
    public void salvarRegistro(ModelRegistroPonto registro) throws ExceptionSistemaPonto;

    /**
     * Seleciona todos os Registros
     *
     * @return List<ModelRegistroPonto>
     * @throws ExceptionSistemaPonto
     */
    public List<ModelRegistroPonto> selectAll() throws ExceptionSistemaPonto;
}
