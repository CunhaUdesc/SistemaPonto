package br.com.sistemaponto.view;

import br.com.sistemaponto.Main;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * Tela do Menu Principal
 *
 * @author Rafael
 * @since 09/06/2026
 */
public class ViewMenu extends JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(ViewMenu.class.getName());

    /**
     * Criação da Tela do Menu
     */
    public ViewMenu() {
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
     * Apresenta uma mensagem na Tela
     *
     * @param msg
     */
    public void apresentaMensagem(String msg) {  
        JOptionPane.showMessageDialog(null, msg);
    }

    /**
     * Adiciona a ação do Botão de Manter Funcionário
     *
     * @param a
     */
    public void adcionarAcaoBtnManterFuncionario(ActionListener a) {
        this.btnManterFuncionario.addActionListener(a);
    }

    /**
     * Adiciona a ação do Botão de Bater Ponto
     *
     * @param a
     */
    public void adcionarAcaoBtnBaterPonto(ActionListener a) {
        this.btnBaterPonto.addActionListener(a);
    }

    /**
     * Inicialização dos componentes da Tela
     */
    private void initComponents() {

        lbTitulo = new JLabel();
        btnManterFuncionario = new JButton();
        btnBaterPonto = new JButton();
        lbVersao = new JLabel();

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        this.lbTitulo.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        this.lbTitulo.setText("Sistema Ponto - Menu Inicial");

        this.btnManterFuncionario.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        this.btnManterFuncionario.setText("Cadastro");

        this.btnBaterPonto.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        this.btnBaterPonto.setText("Bater Ponto");

        this.lbVersao.setText("Versao");

        GroupLayout layout = new GroupLayout(getContentPane());
        this.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(143, 143, 143)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnManterFuncionario, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnBaterPonto, GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lbVersao, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(138, Short.MAX_VALUE))
            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 56, Short.MAX_VALUE)
                .addComponent(lbTitulo)
                .addGap(54, 54, 54))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(lbTitulo)
                .addGap(39, 39, 39)
                .addComponent(btnManterFuncionario)
                .addGap(18, 18, 18)
                .addComponent(btnBaterPonto)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                .addComponent(lbVersao))
        );

        pack();
    }

    private JButton btnBaterPonto;
    private JButton btnManterFuncionario;
    private JLabel lbTitulo;
    private JLabel lbVersao;
}
