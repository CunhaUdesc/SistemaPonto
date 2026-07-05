package br.com.sistemaponto.interfaces;

import br.com.sistemaponto.exception.ExceptionLogin;
import br.com.sistemaponto.exception.ExceptionSistemaPonto;
import java.sql.SQLException;

/**
 * ‘Interface’ de manipulação e consulta de dados no BD
 *
 * @author Vitor Hugo
 * @since 06/06/2026
 */
public interface InterfaceDados {

    /**
     * Salva um novo Registro no Banco de Dados
     *
     * @param obj
     * @throws SQLException
     * @throws ExceptionLogin
     */
    public void salvar(Object obj) throws SQLException, ExceptionLogin, ExceptionSistemaPonto;

    /**
     * Exclui um registro do Banco de Dados
     *
     * @param codigo
     * @throws ExceptionSistemaPonto
     */
    public void excluir(int codigo) throws ExceptionLogin, ExceptionSistemaPonto;

    /**
     * Altera um Registro do Banco de Dados
     * @param obj
     * @throws ExceptionSistemaPonto
     */
    public void alterar(Object obj) throws ExceptionLogin, ExceptionSistemaPonto;

    /**
     * Retorna um objeto de acordo com as informações passadas
     *
     * @param codigo
     * @return Object
     * @throws ExceptionSistemaPonto
     */
    public Object getFromCodigo(int codigo) throws ExceptionLogin, ExceptionSistemaPonto;

}
