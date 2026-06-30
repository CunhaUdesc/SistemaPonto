/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package br.com.sistemaponto.view;

import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author WIN11
 */
public class ViewRegistrosFuncionario extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(ViewRegistrosFuncionario.class.getName());

    /**
     * Creates new form ViewRegistrosFuncionario
     */
    public ViewRegistrosFuncionario() {
        initComponents();
    }
    
    public void apresentarTela(){
        setVisible(true);
    }

    public void apresentaMensagem(String msg) {  
        JOptionPane.showMessageDialog(null, msg);
    }
    
    public String getTipoFiltro(){
        return cbFiltros.getSelectedItem().toString();
    }
    
    public String getFiltro(){
        return txtFiltro.getText();
    }
    
    public void acaoBtnPesquisar(ActionListener a){
        btnPesquisar.addActionListener(a);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tbRegistros = new javax.swing.JTable();
        lbRegistros = new javax.swing.JLabel();
        lbNomeFuncionario = new javax.swing.JLabel();
        btnPesquisar = new javax.swing.JButton();
        txtFiltro = new javax.swing.JTextField();
        cbFiltros = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tbRegistros.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tbRegistros);

        lbRegistros.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lbRegistros.setText("Registros Funcionario");

        lbNomeFuncionario.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lbNomeFuncionario.setText("NomeFuncionario");

        btnPesquisar.setText("Pesquisar");

        cbFiltros.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Dia", "Mês", "Ano" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lbRegistros)
                        .addGap(80, 80, 80))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(cbFiltros, 0, 76, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnPesquisar)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbNomeFuncionario)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbRegistros)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(lbNomeFuncionario)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnPesquisar)
                    .addComponent(txtFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbFiltros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnPesquisar;
    private javax.swing.JComboBox<String> cbFiltros;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbNomeFuncionario;
    private javax.swing.JLabel lbRegistros;
    private javax.swing.JTable tbRegistros;
    private javax.swing.JTextField txtFiltro;
    // End of variables declaration//GEN-END:variables
}
