package br.com.sistemaponto.interfaces;

import br.com.sistemaponto.exception.ExceptionLogin;
import br.com.sistemaponto.exception.ExceptionSistemaPonto;
import br.com.sistemaponto.model.ModelFuncionario;
import java.sql.SQLException;
import java.util.*;

/**
 * Interface de manipulação e consulta de dados no BD
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
    public void salvar(Object obj) throws SQLException, ExceptionLogin;

    /**
     * Exclui um registro do Banco de Dados
     *
     * @param codigo
     */
    public void excluir(int codigo) throws ExceptionSistemaPonto;

    /**
     * Altera um Registro do Banco de Dados
     * @param obj
     */
    public void alterar(Object obj);

    /**
     * Retorna um objeto de acordo com as informações passadas
     *
     * @param codigo
     * @return
     * @throws ExceptionSistemaPonto
     */
    public ModelFuncionario selectFromCodigo(int codigo) throws ExceptionSistemaPonto;

    /**
     * Retorna uma lista com todos os registros da tabela
     *
     * @return
     * @throws ExceptionSistemaPonto
     */
    public List<ModelFuncionario> selectAll() throws ExceptionSistemaPonto;
}
