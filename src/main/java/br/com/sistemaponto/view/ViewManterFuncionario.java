/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

package br.com.sistemaponto.view;

import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.table.DefaultTableModel;

import br.com.sistemaponto.Main;
import br.com.sistemaponto.model.ModelFuncionario;
import br.com.sistemaponto.model.ModelFuncionarioFixo;
import br.com.sistemaponto.model.ModelFuncionarioHorista;

/**
 * Tela de Manter os Funcionários
 *
 * @author Rafael Boing
 * @since 15/06/2026
 */
public class ViewManterFuncionario extends JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(ViewManterFuncionario.class.getName());

    /**
     * Criação da Tela de Manter Funcionário
     */
    public ViewManterFuncionario() {
        this.initComponents();
        this.initTabela();
        this.lbVersao.setText(Main.versao);
    }

    public void initTabela(){
        // Ajuste das larguras das colunas
        tbFuncionarios.getColumnModel().getColumn(0).setPreferredWidth(70);   // Código
        tbFuncionarios.getColumnModel().getColumn(1).setPreferredWidth(180);  // Nome
        tbFuncionarios.getColumnModel().getColumn(2).setPreferredWidth(120);  // CPF
        tbFuncionarios.getColumnModel().getColumn(3).setPreferredWidth(140);  // Data de Nascimento
        tbFuncionarios.getColumnModel().getColumn(4).setPreferredWidth(80);   // Tipo
        tbFuncionarios.getColumnModel().getColumn(5).setPreferredWidth(100);  // Salário
        tbFuncionarios.getColumnModel().getColumn(6).setPreferredWidth(100);  // Carga Horária
        tbFuncionarios.getColumnModel().getColumn(7).setPreferredWidth(100);  // Valor Hora
    }

    /**
     * Apresenta a Tela atual
     */
    public void apresentarTela() {
        setVisible(true);
    }

    /**
     * Apresenta uma mensagem na tela
     *
     * @param msg
     */
    public void apresentaMensagem(String msg) {
        JOptionPane.showMessageDialog(null, msg);
    }

    /**
     * Adiciona uma ação no botão de Incluir
     *
     * @param a
     */
    public void adcionarAcaoBtnIncluir(ActionListener a) {
        this.btnIncluir.addActionListener(a);
    }

    /**
     * Adiciona uma ação no botão de alterar
     *
     * @param a
     */
    public void adcionarAcaoBtnAlterar(ActionListener a) {
        this.btnAlterar.addActionListener(a);
    }

    /**
     * Adiciona uma ação no botão de excluir
     *
     * @param a
     */
    public void adcionarAcaoBtnExcluir(ActionListener a) {
        this.btnExcluir.addActionListener(a);
    }

    /**
     * Adiciona uma ação no botão de visualizar os registros
     * @param a
     */
    public void adicionarAcaoBtnVisualizarRegistros(ActionListener a) {
        this.btnRegistrosPonto.addActionListener(a);
    }

    /**
     * Adiciona uma ação no botão de pesquisar
     *
     * @param a
     */
    public void adicionarAcaoBtnPesquisar(ActionListener a) {
        this.btnPesquisar.addActionListener(a);
    }

    /**
     * Retorna o tipo de filtro
     *
     * @return String
     */
    public String getTipoFiltro(){
        return (String) this.cbFiltros.getSelectedItem();
    }

    /**
     * Retorna o valor do filtro da Tela
     *
     * @return String
     */
    public String getFiltro() {
        return this.txtFiltro.getText();
    }

    /**
     * Retorna o código do registro selecionado na tabela
     *
     * @return int
     */
    public int getCodigoSelecionadoNaTabela() {
        int linha = this.tbFuncionarios.getSelectedRow();

        if(linha == -1) {
            return -1;
        }
        return (Integer) this.tbFuncionarios.getValueAt(linha, 0);
    }

    /**
     * Atualiza as informações da tabela para o ADM
     */
    public void preencherTabela(List<ModelFuncionario> listaFuncionarios){
        DefaultTableModel model = (DefaultTableModel) tbFuncionarios.getModel();
        model.setRowCount(0);

        for (ModelFuncionario func : listaFuncionarios) {

            Object salario = "";
            Object cargaHoraria = "";
            Object valorHora = "";



            if (func instanceof ModelFuncionarioFixo) {
                ModelFuncionarioFixo fixo = (ModelFuncionarioFixo) func;

                Double salarioBase = fixo.getSalarioBase();
                salario = salarioBase != null ? salarioBase : 0.0;

                Float horas = fixo.getCargaHoraria();
                cargaHoraria = horas != null ? horas : 0.0;

            } else 
                if (func instanceof ModelFuncionarioHorista) {
                ModelFuncionarioHorista horista = (ModelFuncionarioHorista) func;

                Double valor = horista.getValorHora();
                valorHora = valor != null ? valor : 0.0;
            }

            model.addRow(new Object[]{
                func.getCodigo(),
                func.getNome(),
                func.getCPF(),
                func.getDataNascimento(),
                func.getTipoFuncionario(),
                salario,
                cargaHoraria,
                valorHora
            });
        }
    }

    public void preencherTabelaRegistroUnico(ModelFuncionario funcionario){
        DefaultTableModel model = (DefaultTableModel) tbFuncionarios.getModel();
        model.setRowCount(0);

        Object salario = "";
        Object cargaHoraria = "";
        Object valorHora = "";

        if (funcionario instanceof ModelFuncionarioFixo) {
            ModelFuncionarioFixo fixo = (ModelFuncionarioFixo) funcionario;

            salario = fixo.getSalarioBase();
            cargaHoraria = fixo.getCargaHoraria();

        } else if (funcionario instanceof ModelFuncionarioHorista) {
            ModelFuncionarioHorista horista = (ModelFuncionarioHorista) funcionario;

            valorHora = horista.getValorHora();
        }

        model.addRow(new Object[]{
            funcionario.getCodigo(),
            funcionario.getNome(),
            funcionario.getCPF(),
            funcionario.getDataNascimento(),
            funcionario.getTipoFuncionario(),
            salario,
            cargaHoraria,
            valorHora
        });
    }
    
    @SuppressWarnings("unchecked")
    /**
     * Inicialização dos Componentes
     */
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

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        tbFuncionarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
            },
            new String [] {
                "Codigo", "Nome", "CPF", "Data de Nascimento",
                "Tipo", "Salario", "Carga Horaria", "Valor Hora"
            }
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });
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
        cbFiltros.setModel(new DefaultComboBoxModel<>(new String[] { "Nome", "CPF", "Tipo" }));

        txtFiltro.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        btnPesquisar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnPesquisar.setText("Pesquisar");

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lbFuncionarios)
                .addGap(232, 232, 232))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbVersao, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 94, Short.MAX_VALUE)
                        .addComponent(btnIncluir)
                        .addGap(18, 18, 18)
                        .addComponent(btnExcluir)
                        .addGap(18, 18, 18)
                        .addComponent(btnAlterar)
                        .addGap(18, 18, 18)
                        .addComponent(btnRegistrosPonto, GroupLayout.PREFERRED_SIZE, 164, GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cbFiltros, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtFiltro)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnPesquisar, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbFuncionarios)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(btnPesquisar)
                    .addComponent(txtFiltro, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbFiltros, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 193, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(btnAlterar)
                        .addComponent(btnExcluir)
                        .addComponent(btnIncluir)
                        .addComponent(btnRegistrosPonto))
                    .addComponent(lbVersao, GroupLayout.Alignment.TRAILING))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JButton btnAlterar;
    private JButton btnExcluir;
    private JButton btnIncluir;
    private JButton btnPesquisar;
    private JButton btnRegistrosPonto;
    private JComboBox<String> cbFiltros;
    private JScrollPane jScrollPane1;
    private JLabel lbFuncionarios;
    private JLabel lbVersao;
    private JTable tbFuncionarios;
    private JTextField txtFiltro;
    // End of variables declaration//GEN-END:variables
}
