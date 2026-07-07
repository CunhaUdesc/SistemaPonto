package br.com.sistemaponto.dao;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import br.com.sistemaponto.exception.ExceptionSistemaPonto;
import br.com.sistemaponto.model.ModelFuncionario;
import br.com.sistemaponto.model.ModelRegistroPonto;

public class DaoRegistroPontoTeste {

    private static final Map<Integer, ModelRegistroPonto> registros = new HashMap<>();
    private int proximoCodigo = 1;

    public void salvarRegistro(ModelRegistroPonto registro) throws ExceptionSistemaPonto {

        if (registro.getCodigo() == 0) {
            registro.setCodigo(proximoCodigo++);
        }

        registros.put(registro.getCodigo(), registro);
    }

    public ModelRegistroPonto getRegistroPontoDiaFuncionario(String data, ModelFuncionario funcionario) throws ExceptionSistemaPonto {

        for (ModelRegistroPonto registro : registros.values()) {

            if (registro.getFuncionario().equals(funcionario) && LocalDate.parse(registro.getDiaAtual()).toString().equals(data)) {
                return registro;
            }
        }

        return null;
    }

    public Map<Integer, ModelRegistroPonto> getRegistros() {
        return registros;
    }
}
