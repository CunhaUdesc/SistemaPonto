package br.com.sistemaponto.view;

import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.*;

/**
 * Tela de Bater o Ponto
 *
 * @author Rafael Boing
 */
public class ViewBaterPonto extends JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(ViewBaterPonto.class.getName());
    private DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    
    /**
     * Criação da Tela
     */
    public ViewBaterPonto() {
        this.initComponents();
    }

    /**
     * Seta as Labels da Tela
     *
     * @param nomeFuncionario
     */
    public void setLabels(String nomeFuncionario) {
        //Método para puxar o nome do funcionario + data atual para o registro do ponto
        Timer timer = new Timer(1000, e -> {
        LocalDateTime dataAtual = LocalDateTime.now();
            lbDataHoje.setText(dataAtual.format(formato));
        });
        
        timer.start();
        this.lbNomeFuncionario.setText(nomeFuncionario);
    }

    /**
     * Atualiza os registros da Tela
     *
     * @param registro
     */
    public void atualizaRegistros(String registro) {
        this.txtRegistroPonto.setText(registro);
    }

    /**
     * Retorna o dia atual
     *
     * @return String
     */
    public String getDataAtual(){
        LocalDateTime dataAtual = LocalDateTime.now();
        return dataAtual.format(formato);
    }

    /**
     * Apresenta a tela atual
     */
    public void mostrarTela(){
        this.setVisible(true);
    }

    /**
     * Adiciona a Ação ao Botão da Primeira Entrada
     *
     * @param a
     */
    public void adicionarAcaoBtnEntradaPonto(ActionListener a) {
        this.btnEntrada.addActionListener(a);
    }

    /**
     * Adiciona a Ação ao Botão da Primeira Saída
     *
     * @param a
     */
    public void adicionarAcaoBtnSaidaPonto(ActionListener a) {
        this.btnSaida.addActionListener(a);
    }

    /**
     * Retorna o Botão de Saída
     *
     * @return JButton
     */
    public JButton getBtnSaida() {
        return this.btnSaida;
    }

    /**
     * Retorna o Botão de Entrada
     *
     * @return JButton
     */
    public JButton getBtnEntrada() {
        return this.btnEntrada;
    }

    /**
     * Retorna os Registros Ponto
     *
     * @return String
     */
    public String getRegistrosPonto() {
        return txtRegistroPonto.getText();
    }

    /**
     * Apresenta uma mensagem em Tela
     * @param msg
     */
    public void apresentaMensagem(String msg) {  
        JOptionPane.showMessageDialog(null, msg);
    }

    /**
     * Inicialização dos Componentes
     */
    private void initComponents() {
        this.lbNomeFuncionario = new JLabel();
        this.lbDataHoje        = new JLabel();
        this.jScrollPane2      = new JScrollPane();
        this.txtRegistroPonto  = new JTextArea();
        this.btnEntrada        = new JButton();
        this.btnSaida          = new JButton();

        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        this.lbNomeFuncionario.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        this.lbNomeFuncionario.setText("NomeFuncionario");

        this.lbDataHoje.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        this.lbDataHoje.setText("Hora do Dia");

        this.txtRegistroPonto.setColumns(20);
        this.txtRegistroPonto.setRows(5);
        this.jScrollPane2.setViewportView(this.txtRegistroPonto);

        this.btnEntrada.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        this.btnEntrada.setText("Entrada");

        this.btnSaida.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        this.btnSaida.setText("Saida");

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                        .addComponent(lbNomeFuncionario, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbDataHoje, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, GroupLayout.DEFAULT_SIZE, 239, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnEntrada, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSaida, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbNomeFuncionario)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbDataHoje)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, GroupLayout.PREFERRED_SIZE, 192, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEntrada)
                    .addComponent(btnSaida))
                .addContainerGap())
        );

        pack();
    }

    private JButton btnEntrada;
    private JButton btnSaida;
    private JScrollPane jScrollPane2;
    private JLabel lbDataHoje;
    private JLabel lbNomeFuncionario;
    private JTextArea txtRegistroPonto;
}
