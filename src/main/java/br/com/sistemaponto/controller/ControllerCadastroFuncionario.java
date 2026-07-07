package br.com.sistemaponto.controller;

import java.sql.SQLException;

import br.com.sistemaponto.dao.DaoFuncionario;
import br.com.sistemaponto.dao.DaoUsuario;
import br.com.sistemaponto.exception.ExceptionCargaHoraria;
import br.com.sistemaponto.exception.ExceptionCpfInvalido;
import br.com.sistemaponto.exception.ExceptionDataNascimentoInvalido;
import br.com.sistemaponto.exception.ExceptionLogin;
import br.com.sistemaponto.exception.ExceptionSistemaPonto;
import br.com.sistemaponto.exception.ExceptionValorInvalido;
import br.com.sistemaponto.interfaces.InterfaceDados;
import br.com.sistemaponto.model.ModelFuncionario;
import br.com.sistemaponto.model.ModelFuncionarioFixo;
import br.com.sistemaponto.model.ModelFuncionarioHorista;
import br.com.sistemaponto.model.ModelUsuario;
import br.com.sistemaponto.view.ViewCadastroFuncionario;

/**
 *  Controlador do Cadastro de Funcionário
 *
 * @author Rafael Boing
 * @since 04/07/2026
 */
public class ControllerCadastroFuncionario {

    /** @var ViewCadastroFuncionario */
    private ViewCadastroFuncionario viewCadastroFunc;
    /** @var InterfaceDados */
    private InterfaceDados daoFuncionario;

    private InterfaceDados daoUsuario;

    private ModelFuncionario funcionarioAlteracao;

    private ModelUsuario usuarioAlteracao;

    private int acao; //0 Para quando for novo funcionario e 1 para quando for alteração

    /**
     * Construct para Adicionar um Funcionário
     *
     * @param viewCadastroFunc
     */
    public ControllerCadastroFuncionario(ViewCadastroFuncionario viewCadastroFunc) {
        this.acao = 0;
        this.funcionarioAlteracao = null;
        this.viewCadastroFunc = viewCadastroFunc;
        this.daoFuncionario = new DaoFuncionario();
        this.daoUsuario = new DaoUsuario();

        viewCadastroFunc.mostrarTela();
        viewCadastroFunc.setLbTitulo("Cadastro de Funcionario");
        viewCadastroFunc.getBtnLimpar().setEnabled(true);
        this.adicionarAcoes();
    }

    /**
     * Construct para Alterar um Funcionário
     *
     * @param viewCadastroFunc
     */
    public ControllerCadastroFuncionario(ViewCadastroFuncionario viewCadastroFunc, ModelFuncionario funcionario) {
        this.acao = 1;
        this.funcionarioAlteracao = funcionario;
        this.viewCadastroFunc = viewCadastroFunc;
        this.daoFuncionario = new DaoFuncionario();
        this.daoUsuario = new DaoUsuario();

        viewCadastroFunc.mostrarTela();
        viewCadastroFunc.setLbTitulo("Alterar Funcionario");
        viewCadastroFunc.getBtnLimpar().setEnabled(false);
        viewCadastroFunc.mostraFuncionarioNaTela(funcionario);

        this.adicionarAcoes();
    }

    /**
     * Adiciona as Ações aos Botões
     */
    public void adicionarAcoes() {
        this.viewCadastroFunc.adcionarAcaoBtnLimpar(e -> this.limparTela());
        this.viewCadastroFunc.adcionarAcaoBtnSalvar(e -> this.salvarFuncionario());
    }

    //======================================VALIDAÇÕES==============================================

    /**
     * Validação de CPF do Funcionário
     *
     * @param cpf
     * @throws ExceptionCpfInvalido
     */
    public void validarCPF(String cpf) throws ExceptionCpfInvalido {
        if (cpf == null || cpf.length() != 11) {
            throw new ExceptionCpfInvalido("CPF invalido!");
        }

        for (int i = 0; i < cpf.length(); i++){
            if (!Character.isDigit(cpf.charAt(i))) {
                throw new ExceptionCpfInvalido("CPF invalido!");
            }
        }
    }

    /**
     * Validação da Data de Nascimento do Funcionário
     *
     * @param dataNascimento
     * @throws ExceptionDataNascimentoInvalido
     */
    public void validarDataNascimento(String dataNascimento) throws ExceptionDataNascimentoInvalido {
        if (dataNascimento.length() != 10) {
            throw new ExceptionDataNascimentoInvalido("Data de Nascimento Inválida!");
        }

        if (dataNascimento.charAt(2) != '/' || dataNascimento.charAt(5) != '/')
            throw new ExceptionDataNascimentoInvalido("Data de Nascimento Inválida!");
        
        String diaString = dataNascimento.substring(0,2);
        String mesString = dataNascimento.substring(3,5);
        String anoString = dataNascimento.substring(6,10);

        for (int i = 0; i < diaString.length(); i++) {
            if (!Character.isDigit(diaString.charAt(i)) || !Character.isDigit(mesString.charAt(i))) {
                throw new ExceptionDataNascimentoInvalido("Data de Nascimento Inválida!");
            }
        }

        for (int i = 0; i < anoString.length(); i++) {
            if(!Character.isDigit(anoString.charAt(i))) {
                throw new ExceptionDataNascimentoInvalido("Data de Nascimento Inválida!");
            }
        }

        int dia = Integer.parseInt(diaString);
        int mes = Integer.parseInt(mesString);
        int ano = Integer.parseInt(anoString);

        if (mes < 1 || mes > 12) {
            throw new ExceptionDataNascimentoInvalido("Data de Nascimento Inválida!");
        }
        if (dia < 1 || dia > 31) {
            throw new ExceptionDataNascimentoInvalido("Data de Nascimento Inválida!");
        }
        if (ano < 1920 || ano > 2026) {
            throw new ExceptionDataNascimentoInvalido("Data de Nascimento Inválida!");
        }
    }

    /**
     * Validação do Valor por Hora do Funcionário
     *
     * @param valorHora
     * @throws ExceptionValorInvalido
     */
    public void validarValorHora(double valorHora) throws ExceptionValorInvalido {
        if (valorHora < 0) {
            throw new ExceptionValorInvalido("Valor invalido!");
        }
    }

    /**
     * Validação do salário do Funcionário
     *
     * @param salario
     * @throws ExceptionValorInvalido
     */
    public void validarSalario(double salario) throws ExceptionValorInvalido {
        if (salario < 1621) {
            throw new ExceptionValorInvalido("Valor invalido!");
        }
    }

    /**
     * Validação da carga horária informada
     *
     * @param cargaHoraria
     * @throws ExceptionCargaHoraria
     */
    public void validarCargaHoraria(float cargaHoraria) throws ExceptionCargaHoraria {
        if (cargaHoraria < 30 || cargaHoraria > 44) {
            throw new ExceptionCargaHoraria("Carga Horaria nao permitida!");
        }
    }

        //=========================METODOS USUARIO================================
    /**
     * Cria o login do Usuário
     *
     * @param cpf
     * @return int
     */
    public int criaLogin(String cpf) {
        return Integer.parseInt(cpf.substring(0,3));
    }

    /**
     * Cria a senha do Usuário
     *
     * @param dataNascimento
     * @return String
     */
    public String criaSenha(String dataNascimento) {
        String diaString = dataNascimento.substring(0,2);
        String mesString = dataNascimento.substring(3,5);

        return diaString + mesString;
//        return Integer.parseInt(diaString+mesString);
    }

    //===========================FUNÇÕES BOTÕES================================

    /**
     * Salva um novo Funcionário
     */
    public void salvarFuncionario() {
        if(acao == 0){
            try {
                ModelFuncionario funcionario;
                ModelUsuario usuario;

                String nome = this.viewCadastroFunc.getNome();

                String cpf = this.viewCadastroFunc.getCpf();
                this.validarCPF(cpf);

                String tipo = this.viewCadastroFunc.getTipoFuncionario();
                String tipoFunc = tipo.toUpperCase();

                String dataNascimento = this.viewCadastroFunc.getDataNascimento();
                this.validarDataNascimento(dataNascimento);

                if (this.viewCadastroFunc.getTipoFuncionario().equalsIgnoreCase("Horista")) {
                    double valorHora = Double.parseDouble(this.viewCadastroFunc.getValorHora());
                    this.validarValorHora(valorHora);

                    funcionario = new ModelFuncionarioHorista(nome, cpf, dataNascimento, tipoFunc, valorHora);

                } else {
                    double salario = Double.parseDouble(viewCadastroFunc.getSalario());
                    this.validarSalario(salario);
                    float cargaHoraria = Float.parseFloat(viewCadastroFunc.getCargaHoraria());
                    this.validarCargaHoraria(cargaHoraria);
                    funcionario = new ModelFuncionarioFixo(nome, cpf, tipoFunc, dataNascimento, salario, cargaHoraria);

                }

                //Criando o usuário
                int login = this.criaLogin(cpf);
                String senha = this.criaSenha(dataNascimento);
                String perfil = this.viewCadastroFunc.getPerfilUsuario();

                usuario = new ModelUsuario(login, senha, perfil, funcionario);
                this.daoFuncionario.salvar(funcionario); //ARRUMAR OS EXCEPTIONS
                this.daoUsuario.salvar(usuario);

            } catch (ExceptionCpfInvalido e) {
                this.viewCadastroFunc.apresentaMensagem("Erro: " + e.getMessage());

            } catch (ExceptionDataNascimentoInvalido e) {
                this.viewCadastroFunc.apresentaMensagem("Erro: " + e.getMessage());

            } catch (ExceptionValorInvalido e) {
                this.viewCadastroFunc.apresentaMensagem("Erro: " + e.getMessage());

            } catch (ExceptionCargaHoraria e) {
                this.viewCadastroFunc.apresentaMensagem("Erro: " + e.getMessage());

            } catch (SQLException e) {
                this.viewCadastroFunc.apresentaMensagem("Erro: " + e.getMessage());

            } catch (ExceptionLogin e) {
                this.viewCadastroFunc.apresentaMensagem("Erro: " + e.getMessage());

            } catch (ExceptionSistemaPonto e) {
                this.viewCadastroFunc.apresentaMensagem("Erro: " + e.getMessage());

            }
        } else {
            this.alterarFuncionario();
        }

    }

    /**
     * Realiza a alteração do Funcionário
     */
    public void alterarFuncionario() {
        try{
            funcionarioAlteracao.setNome(this.viewCadastroFunc.getNome());

            String cpf = this.viewCadastroFunc.getCpf();
            this.validarCPF(cpf);
            funcionarioAlteracao.setCPF(cpf);

            funcionarioAlteracao.setTipo(this.viewCadastroFunc.getTipoFuncionario());

            String dataNascimento = this.viewCadastroFunc.getDataNascimento();
            this.validarDataNascimento(dataNascimento);
            funcionarioAlteracao.setDataNascimento(dataNascimento);

            if (this.viewCadastroFunc.getTipoFuncionario().equalsIgnoreCase("Horista")) {
                ModelFuncionarioHorista func = (ModelFuncionarioHorista) funcionarioAlteracao;

                double valorHora = Double.parseDouble(this.viewCadastroFunc.getValorHora());
                this.validarValorHora(valorHora);
                func.setValorHora(valorHora);

            } else {
                ModelFuncionarioFixo func = (ModelFuncionarioFixo) funcionarioAlteracao;

                double salario = Double.parseDouble(viewCadastroFunc.getSalario());
                this.validarSalario(salario);
                func.setSalarioBase(salario);

                float cargaHoraria = Float.parseFloat(viewCadastroFunc.getCargaHoraria());
                this.validarCargaHoraria(cargaHoraria);
                func.setCargaHoraria(cargaHoraria);

            }

                //Criando o usuário
                int login = this.criaLogin(cpf);
                String senha = this.criaSenha(dataNascimento);
                String perfil = this.viewCadastroFunc.getPerfilUsuario();

                daoUsuario = new DaoUsuario();
                int cod = Integer.parseInt(viewCadastroFunc.getCodigo());
                usuarioAlteracao = (ModelUsuario) daoUsuario.getFromCodigo(cod);

                usuarioAlteracao.setLogin(login);
                usuarioAlteracao.setSenha(senha);
                usuarioAlteracao.setTipo(perfil);

                daoUsuario.alterar(usuarioAlteracao);
                daoFuncionario.alterar(funcionarioAlteracao);

            } catch (ExceptionCpfInvalido e) {
                this.viewCadastroFunc.apresentaMensagem("Erro: " + e.getMessage());

            } catch (ExceptionDataNascimentoInvalido e) {
                this.viewCadastroFunc.apresentaMensagem("Erro: " + e.getMessage());

            } catch (ExceptionValorInvalido e) {
                this.viewCadastroFunc.apresentaMensagem("Erro: " + e.getMessage());

            } catch (ExceptionCargaHoraria e) {
                this.viewCadastroFunc.apresentaMensagem("Erro: " + e.getMessage());
                
            } catch (ExceptionLogin e) {
                this.viewCadastroFunc.apresentaMensagem("Erro: " + e.getMessage());

            } catch (ExceptionSistemaPonto e) {
                this.viewCadastroFunc.apresentaMensagem("Erro: " + e.getMessage());

            }
        }

    /**
     * Realiza a limpeza da tela
     */
    public void limparTela(){
        this.viewCadastroFunc.limparTela();
    }
}
