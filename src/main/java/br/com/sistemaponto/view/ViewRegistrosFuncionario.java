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
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

import br.com.sistemaponto.model.ModelRegistroPonto;

/**
 * Tela dos Registros de Ponto do Funcionário
 *
 * @author Rafael Boing
 * @since 15/06/2026
 */
public class ViewRegistrosFuncionario extends JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(ViewRegistrosFuncionario.class.getName());

    /**
     * Criaçao da Tela de Registro dos Funcionários
     */
    public ViewRegistrosFuncionario() {
        this.initComponents();
    }

    /**
     * Aprenseta a Tela
     */
    public void apresentarTela(){
        setVisible(true);
    }

    /**
     * Seta label do Nome do Funcionário
     *
     * @param nome
     */
    public void setLabelNomeFuncionario(String nome) {
        this.lbNomeFuncionario.setText(nome);
    }

    /**
     * Apresenta uma mensagem em Tela
     *
     * @param msg
     */
    public void apresentaMensagem(String msg) {
        JOptionPane.showMessageDialog(null, msg);
    }

    /**
     * Retorna o filtro de Tipo
     *
     * @return String
     */
    public String getTipoFiltro(){
        return cbFiltros.getSelectedItem().toString();
    }

    /**
     * Retorna o valor do Filtro
     *
     * @return String
     */
    public String getFiltro(){
        return txtFiltro.getText();
    }
    
    public void preencherTabela(List<ModelRegistroPonto> registros){
        DefaultTableModel model = (DefaultTableModel) tbRegistros.getModel();
        model.setRowCount(0);

        for(ModelRegistroPonto registroPonto : registros){
            model.addRow(new Object[]{
                registroPonto.getCodigo(),
                registroPonto.getDiaAtual(),
                mudaRegistroPonto(registroPonto.getRegistroEntrada()),
                mudaRegistroPonto(registroPonto.getRegistroSaida()),
                mudaRegistroPonto(registroPonto.getRegistroEntradaIntervalo()),
                mudaRegistroPonto(registroPonto.getRegistroSaidaIntervalo())
            });
        }
    }

    public String mudaRegistroPonto(String registroPonto) {
        if (registroPonto == null) {
            return "";
        }

        return registroPonto.substring(11, 19);
    }

    /**
     * Adiciona ação no botão de Pesquisar
     *
     * @param a
     */
    public void acaoBtnPesquisar(ActionListener a){
        this.btnPesquisar.addActionListener(a);
    }

    /**
     * Inicializador de Componentes
     */
    private void initComponents() {

        jScrollPane1 = new JScrollPane();
        tbRegistros = new JTable();
        lbRegistros = new JLabel();
        lbNomeFuncionario = new JLabel();
        btnPesquisar = new JButton();
        txtFiltro = new JTextField();
        cbFiltros = new JComboBox<>();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        tbRegistros.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Entrada", "Saida", "Entrada Intervalo", "Saida Intervalo"
            }
        ));
        jScrollPane1.setViewportView(tbRegistros);

        lbRegistros.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lbRegistros.setText("Registros Funcionario");

        lbNomeFuncionario.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lbNomeFuncionario.setText("NomeFuncionario");

        btnPesquisar.setText("Pesquisar");

        cbFiltros.setModel(new DefaultComboBoxModel<>(new String[] { "Dia", "Mês", "Ano" }));

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbNomeFuncionario)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cbFiltros, 0, 97, Short.MAX_VALUE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtFiltro, GroupLayout.PREFERRED_SIZE, 209, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnPesquisar, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())))
            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbRegistros, GroupLayout.PREFERRED_SIZE, 229, GroupLayout.PREFERRED_SIZE)
                .addGap(90, 90, 90))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addComponent(lbRegistros)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbNomeFuncionario)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(btnPesquisar)
                    .addComponent(txtFiltro, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbFiltros, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 219, GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }

    private JButton btnPesquisar;
    private JComboBox<String> cbFiltros;
    private JScrollPane jScrollPane1;
    private JLabel lbNomeFuncionario;
    private JLabel lbRegistros;
    private JTable tbRegistros;
    private JTextField txtFiltro;
}
