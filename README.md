# SistemaPonto
Sistema de Controle Ponto para Padaria

=== AFAZERES ===

1. Criar JTable para a consulta de registro ponto -- Rafael
2. Métodos DAO:
   a) DAOUsuario:
     - addUsuario() (insert)
     - excluirUsuario() (delete)
   b) DAOFuncionario:
     - addFuncionario()
     - alterar()
     - excluir()
     - Select():
        - getAllFuncionarios() (ADM)
        - getFuncionarioFromCpf() (FUNCIONARIO, ADM)
        - getFuncionarioFromNome() (ADM)
        - getFuncionarioFromTipo() (ADM)
        - getFuncionariosFromSalario() (ADM) -> Opcional
    c) DAORegistroPonto:
      - addRegistroPonto() (ADM, Funcionário)
      - alterarRegistroPonto() (ADM)
  
3. Usar o DataGrip: com arquivo na raiz do projeto, ex. no Moodle
      - getAllRegistrosPonto()
      - getRegistrosPontoFromFuncionario()
      - getRegistrosPontoFromData() -> Opcional