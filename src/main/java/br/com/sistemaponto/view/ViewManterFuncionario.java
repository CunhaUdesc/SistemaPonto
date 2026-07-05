package br.com.sistemaponto.view;

import java.awt.event.ActionListener;
import javax.swing.*;
import br.com.sistemaponto.Main;

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
        this.lbVersao.setText(Main.versao);
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
     * Atualiza as informações da tabela
     */
    public void atualizarTabela(){
        //TEM Q FAZER O METODO AINDA
    }

    /**
     * Inicialização dos Componentes
     */
    private void initComponents() {

        jScrollPane1 = new JScrollPane();
        tbFuncionarios = new JTable();
        lbFuncionarios = new JLabel();
        btnAlterar = new JButton();
        btnExcluir = new JButton();
        btnIncluir = new JButton();
        lbVersao = new JLabel();
        btnRegistrosPonto = new JButton();
        cbFiltros = new JComboBox<>();
        txtFiltro = new JTextField();
        btnPesquisar = new JButton();

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        this.tbFuncionarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nome", "CPF", "Data de Nascimento", "Tipo", "Salario"
            }
        ));
        this.jScrollPane1.setViewportView(tbFuncionarios);

        this.lbFuncionarios.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        this.lbFuncionarios.setText("Funcionários");

        this.btnAlterar.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        this.btnAlterar.setText("Alterar");

        this.btnExcluir.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        this.btnExcluir.setText("Excluir");

        this.btnIncluir.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        this.btnIncluir.setText("Incluir");

        this.lbVersao.setText("Versao");

        this.btnRegistrosPonto.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        this.btnRegistrosPonto.setText("Visualizar Registros");

        this.cbFiltros.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        this.cbFiltros.setModel(new DefaultComboBoxModel<>(new String[] { "Nome", "CPF", "Tipo" }));

        this.txtFiltro.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        this.btnPesquisar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        this.btnPesquisar.setText("Pesquisar");

        GroupLayout layout = new GroupLayout(getContentPane());
        this.getContentPane().setLayout(layout);
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
    }

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
}
