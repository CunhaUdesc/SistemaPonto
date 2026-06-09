package br.com.sistemaponto.interfaces;

import br.com.sistemaponto.exception.ExceptionLogin;

import java.sql.SQLException;
import java.util.*;

/**
 * Interface de manipulação e consulta de dados no BD
 *
 * @author Vitor Hugo
 * @since 06/06/2026
 */
public interface InterfaceDados {

    public void salvar(Object obj) throws SQLException, ExceptionLogin;

    public void excluir(Object obj);

    public void alterar(Object obj);

    public List<Iterator> select(Object obj);
}
