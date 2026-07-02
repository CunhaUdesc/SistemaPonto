/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package br.com.sistemaponto.view;

import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import br.com.sistemaponto.Main;

/**
 *
 * @author WIN11
 */
public class ViewManterFuncionario extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(ViewManterFuncionario.class.getName());

    /**
     * Creates new form ViewManterFuncionario
     */
    public ViewManterFuncionario() {
        initComponents();
        lbVersao.setText(Main.versao);
    }

    public void apresentarTela(){
        setVisible(true);
    }

    public void apresentaMensagem(String msg) {  
        JOptionPane.showMessageDialog(null, msg);
    }
    
    public void adcionarAcaoBtnIncluir(ActionListener a){
        btnIncluir.addActionListener(a);
    }
    
    public void adcionarAcaoBtnAlterar(ActionListener a){
        btnAlterar.addActionListener(a);
    }
    
    public void adcionarAcaoBtnExcluir(ActionListener a){
        btnExcluir.addActionListener(a);
    }
    
    public void adicionarAcaoBtnVisualizarRegistros(ActionListener a){
        btnRegistrosPonto.addActionListener(a);
    }
    
    public void adicionarAcaoBtnPesquisar(ActionListener a){
        btnPesquisar.addActionListener(a);
    }
    
    public String getTipoFiltro(){
        return (String) cbFiltros.getSelectedItem();
    }
    
    public String getFiltro(){
        return txtFiltro.getText();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tbFuncionarios = new javax.swing.JTable();
        lbFuncionarios = new javax.swing.JLabel();
        btnAlterar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnIncluir = new javax.swing.JButton();
        lbVersao = new javax.swing.JLabel();
        btnRegistrosPonto = new javax.swing.JButton();
        cbFiltros = new javax.swing.JComboBox<>();
        txtFiltro = new javax.swing.JTextField();
        btnPesquisar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tbFuncionarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nome", "CPF", "Data de Nascimento", "Tipo", "Salario"
            }
        ));
        jScrollPane1.setViewportView(tbFuncionarios);

        lbFuncionarios.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lbFuncionarios.setText("Funcionários");

        btnAlterar.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnAlterar.setText("Alterar");

        btnExcluir.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnExcluir.setText("Excluir");

        btnIncluir.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnIncluir.setText("Incluir");

        lbVersao.setText("Versao");

        btnRegistrosPonto.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnRegistrosPonto.setText("Visualizar Registros");

        cbFiltros.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cbFiltros.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nome", "CPF", "Tipo" }));

        txtFiltro.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        btnPesquisar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnPesquisar.setText("Pesquisar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lbFuncionarios)
                .addGap(232, 232, 232))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbVersao, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 94, Short.MAX_VALUE)
                        .addComponent(btnIncluir)
                        .addGap(18, 18, 18)
                        .addComponent(btnExcluir)
                        .addGap(18, 18, 18)
                        .addComponent(btnAlterar)
                        .addGap(18, 18, 18)
                        .addComponent(btnRegistrosPonto, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cbFiltros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtFiltro)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbFuncionarios)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnPesquisar)
                    .addComponent(txtFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbFiltros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnAlterar)
                        .addComponent(btnExcluir)
                        .addComponent(btnIncluir)
                        .addComponent(btnRegistrosPonto))
                    .addComponent(lbVersao, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAlterar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnIncluir;
    private javax.swing.JButton btnPesquisar;
    private javax.swing.JButton btnRegistrosPonto;
    private javax.swing.JComboBox<String> cbFiltros;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbFuncionarios;
    private javax.swing.JLabel lbVersao;
    private javax.swing.JTable tbFuncionarios;
    private javax.swing.JTextField txtFiltro;
    // End of variables declaration//GEN-END:variables
}
