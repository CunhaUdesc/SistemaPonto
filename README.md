# SistemaPonto
Sistema de Controle Ponto para Padaria

=== AFAZERES ===

1. Criar JTable para a consulta de funcionários e consulta de registro ponto;
2. Ajustar telar extrar para não fecharem o sistema todo;
3. Implementar classe:
public final class Session {

    private static Usuario usuario;

    private Session() {}

    public static Usuario getUsuario() {
        return usuario;
    }

    public static void setUsuario(Usuario usuario) {
        Session.usuario = usuario;
    }

    public static void logout() {
        usuario = null;
    }
}
Em qualquer lugar da aplicação: Usuario usuario = Session.getUsuario();

4. Métodos DAO:
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
  
5. Usar o DataGrip: com arquivo na raiz do projeto, ex. no Moodle
      - getAllRegistrosPonto()
      - getRegistrosPontoFromFuncionario()
      - getRegistrosPontoFromData() -> Opcional
      - 
