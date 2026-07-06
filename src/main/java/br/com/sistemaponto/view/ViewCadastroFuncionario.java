package br.com.sistemaponto.view;

import java.awt.event.ActionListener;
import javax.swing.*;

import br.com.sistemaponto.model.ModelFuncionario;
import br.com.sistemaponto.model.ModelFuncionarioFixo;
import br.com.sistemaponto.model.ModelFuncionarioHorista;

/**
 * Tela do Cadastro de Funcionários
 *
 * @author Rafael Boing
 * @since 15/06/2026
 */
public class ViewCadastroFuncionario extends JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(ViewCadastroFuncionario.class.getName());

    /**
     * Criação da Tela
     */
    public ViewCadastroFuncionario() {
        this.initComponents();
        
        //campos de acordo com o tipo
        configuraAcoes();
        visualizaCamposPorTipoFuncionario();
        //desabilitar botões
        txtSalario.setEnabled(false);
        txtCargaHoraria.setEnabled(false);
    }
    
    public void mostrarTela(){
        setVisible(true);
    }
    
    public String getCodigo(){
        return txtCodigo.getText();
    }
    
    public String getNome(){
        return txtNome.getText();
    }
    
    public String getCpf(){
        return txtCpf.getText();
    }
    
    public String getDataNascimento(){
        return txtDataNascimento.getText();
    }
    
    public String getTipoFuncionario(){
        return (String) cbTipoFuncionario.getSelectedItem();
    }
    
    public String getValorHora(){
        return txtValorHora.getText();
    }
    
    public String getSalario(){
        return txtSalario.getText();
    }
    
    public String getCargaHoraria(){
        return txtCargaHoraria.getText();
    }
    
    public String getPerfilUsuario(){
        return (String) cbTipoUsuario.getSelectedItem();
    }

    public void setCbTipoUsuario(JComboBox<String> cbTipoUsuario) {
        this.cbTipoUsuario = cbTipoUsuario;
    }

    public void setCbTipoFuncionario(JComboBox<String> cbTipoFuncionario) {
        this.cbTipoFuncionario = cbTipoFuncionario;
    }

    public void setTxtCargaHoraria(JTextField txtCargaHoraria) {
        this.txtCargaHoraria = txtCargaHoraria;
    }

    public void setTxtCodigo(JTextField txtCodigo) {
        this.txtCodigo = txtCodigo;
    }

    public void setTxtCpf(JTextField txtCpf) {
        this.txtCpf = txtCpf;
    }

    public void setTxtDataNascimento(JTextField txtDataNascimento) {
        this.txtDataNascimento = txtDataNascimento;
    }

    public void setTxtNome(JTextField txtNome) {
        this.txtNome = txtNome;
    }

    public void setTxtSalario(JTextField txtSalario) {
        this.txtSalario = txtSalario;
    }

    public void setTxtValorHora(JTextField txtValorHora) {
        this.txtValorHora = txtValorHora;
    }

    public void setLbTitulo(String titulo){
        lbTitulo.setText(titulo);
    }
    
    public void limparTela(){
        txtCodigo.setText("");
        txtNome.setText("");
        txtCpf.setText("");
        txtDataNascimento.setText("");
        txtValorHora.setText("");
        txtSalario.setText("");
        txtCargaHoraria.setText("");
    }
    
    public void adcionarAcaoBtnLimpar(ActionListener a){
        btnLimpar.addActionListener(a);
    }
    
    public void adcionarAcaoBtnSalvar(ActionListener a){
        btnSalvar.addActionListener(a);
    }
    
    public void configuraAcoes(){
        cbTipoFuncionario.addActionListener( e -> visualizaCamposPorTipoFuncionario());
    }
    
    public void apresentaMensagem(String msg){
        JOptionPane.showMessageDialog(null, msg);
    }
    
    public void visualizaCamposPorTipoFuncionario(){
        cbTipoFuncionario.addActionListener(e -> {
            String tipo = (String) cbTipoFuncionario.getSelectedItem();

            if ("Horista".equals(tipo)) {
                txtValorHora.setEnabled(true);
                txtSalario.setEnabled(false);
                txtCargaHoraria.setEnabled(false);
            } else {
                txtValorHora.setEnabled(false);
                txtSalario.setEnabled(true);
                txtCargaHoraria.setEnabled(true);
            }
            revalidate();
            repaint();
        });
    }

    public JButton getBtnLimpar(){
        return btnLimpar;
    }

    public JTextField getTxtCodigo(){
        return txtCodigo;
    }

    public void mostraFuncionarioNaTela(ModelFuncionario funcionario){
        String codigo = String.valueOf(funcionario.getCodigo());
        txtCodigo.setText(codigo);
        txtNome.setText(funcionario.getNome());
        txtCpf.setText(funcionario.getCPF());
        txtDataNascimento.setText(funcionario.getDataNascimento());
        cbTipoFuncionario.setSelectedItem(funcionario.getTipoFuncionario());

        if(funcionario.getTipoFuncionario().equals("Horista")){
            ModelFuncionarioHorista func = (ModelFuncionarioHorista) funcionario;
            txtValorHora.setText(String.valueOf(func.getValorHora()));
            
        } else {
            ModelFuncionarioFixo func = (ModelFuncionarioFixo) funcionario;
            txtSalario.setText(String.valueOf(func.getSalarioBase()));
            txtCargaHoraria.setText(String.valueOf(func.getCargaHoraria()));
        }
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbCodigo = new JLabel();
        txtCodigo = new JTextField();
        lbTitulo = new JLabel();
        lbNome = new JLabel();
        txtNome = new JTextField();
        txtCpf = new JTextField();
        lbCpf = new JLabel();
        txtDataNascimento = new JTextField();
        lbDataNascimento = new JLabel();
        btnSalvar = new JButton();
        btnLimpar = new JButton();
        cbTipoFuncionario = new JComboBox<>();
        txtValorHora = new JTextField();
        lbTipoFuncionario = new JLabel();
        lbValorHora = new JLabel();
        lbSalario = new JLabel();
        lbCargaHoraria = new JLabel();
        txtSalario = new JTextField();
        txtCargaHoraria = new JTextField();
        cbTipoUsuario = new JComboBox<>();
        jLabel1 = new JLabel();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        lbCodigo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbCodigo.setText("Codigo");

        txtCodigo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        lbTitulo.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lbTitulo.setText("Cadastro de Funcionario");

        lbNome.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbNome.setText("Nome");

        txtNome.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txtCpf.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtCpf.setToolTipText("");

        lbCpf.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbCpf.setText("Cpf");

        txtDataNascimento.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        lbDataNascimento.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbDataNascimento.setText("Data de Nascimento");

        btnSalvar.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnSalvar.setText("Salvar");

        btnLimpar.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnLimpar.setText("Limpar");

        cbTipoFuncionario.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cbTipoFuncionario.setModel(new DefaultComboBoxModel<>(new String[] { "Horista", "Fixo" }));

        txtValorHora.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        lbTipoFuncionario.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbTipoFuncionario.setText("Tipo");

        lbValorHora.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbValorHora.setText("Valor por Hora");

        lbSalario.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbSalario.setText("Salario");

        lbCargaHoraria.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbCargaHoraria.setText("Carga Horaria");

        txtSalario.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txtCargaHoraria.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        cbTipoUsuario.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cbTipoUsuario.setModel(new DefaultComboBoxModel<>(new String[] { "Usuario", "Adm" }));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Perfil");

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addComponent(lbTitulo, GroupLayout.PREFERRED_SIZE, 282, GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addGroup(GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                .addComponent(lbCodigo, GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE)
                                .addComponent(txtCodigo, GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtNome, GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
                                .addComponent(lbNome, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(GroupLayout.Alignment.LEADING, layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                            .addGroup(GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(btnSalvar, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnLimpar, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE))
                            .addGroup(GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                    .addComponent(lbCpf, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtCpf, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                    .addComponent(lbDataNascimento)
                                    .addComponent(txtDataNascimento, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cbTipoFuncionario, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cbTipoUsuario, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lbTipoFuncionario, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lbSalario)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lbCargaHoraria))
                                    .addComponent(txtValorHora, GroupLayout.Alignment.LEADING)
                                    .addComponent(lbValorHora, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(txtSalario, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtCargaHoraria, GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)))))))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lbTitulo)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lbCodigo)
                    .addComponent(lbNome))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCodigo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lbCpf)
                    .addComponent(lbDataNascimento))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCpf, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDataNascimento, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lbTipoFuncionario)
                    .addComponent(lbValorHora))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(txtValorHora, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbTipoFuncionario, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lbSalario)
                    .addComponent(lbCargaHoraria)
                    .addComponent(jLabel1))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(txtSalario, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbTipoUsuario, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtCargaHoraria, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSalvar)
                    .addComponent(btnLimpar))
                .addGap(20, 20, 20))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JButton btnLimpar;
    private JButton btnSalvar;
    private JComboBox<String> cbTipoFuncionario;
    private JComboBox<String> cbTipoUsuario;
    private JLabel jLabel1;
    private JLabel lbCargaHoraria;
    private JLabel lbCodigo;
    private JLabel lbCpf;
    private JLabel lbDataNascimento;
    private JLabel lbNome;
    private JLabel lbSalario;
    private JLabel lbTipoFuncionario;
    private JLabel lbTitulo;
    private JLabel lbValorHora;
    private JTextField txtCargaHoraria;
    private JTextField txtCodigo;
    private JTextField txtCpf;
    private JTextField txtDataNascimento;
    private JTextField txtNome;
    private JTextField txtSalario;
    private JTextField txtValorHora;
    // End of variables declaration//GEN-END:variables
}
