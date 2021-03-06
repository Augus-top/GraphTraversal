/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import com.alee.laf.WebLookAndFeel;
import controller.WindowController;
import java.awt.Color;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UnsupportedLookAndFeelException;


/**
 *
 * @author Augustop
 */
public class MainWindow extends javax.swing.JFrame {
    private WindowController controller;
    
    /**
     * Creates new form MainWindow
     */
    public MainWindow() {
        initComponents();
    }
    
    public MainWindow(WindowController ctr) {
        this.controller = ctr;
        try {
            javax.swing.UIManager.setLookAndFeel(new WebLookAndFeel());
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
        initComponents();
        this.getContentPane().setBackground(Color.WHITE);
        this.setButtonColor(this.botaoBuscarCaminho, Color.WHITE, Color.WHITE, Color.BLACK, Color.RED, Color.DARK_GRAY, Color.RED);
        this.setButtonColor(this.botaoImportarXML, Color.WHITE, Color.WHITE, Color.BLACK, Color.RED, Color.DARK_GRAY, Color.RED);
        this.setButtonColor(this.botaoIniciarAStar, Color.WHITE, Color.WHITE, Color.BLACK, Color.RED, Color.DARK_GRAY, Color.RED);
        this.setButtonColor(this.botaoColoracao, Color.WHITE, Color.WHITE, Color.BLACK, Color.RED, Color.DARK_GRAY, Color.RED);
        
        URL urlIcon = getClass().getResource("/kancolle_icon.png");
        Image frameIcon = null;
        try {
            frameIcon = ImageIO.read(urlIcon);
        } catch (IOException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.setIconImage(frameIcon.getScaledInstance(32, 32, Image.SCALE_SMOOTH));
        this.repaint();
        this.radioButtonTraveling.setSelected(true);
    }

    private void setButtonColor(com.alee.laf.button.WebButton b, Color fore, Color selFor, Color top, Color topSel, Color bot, Color botSel){
        b.setForeground (fore);
        b.setSelectedForeground (selFor);
        b.setTopBgColor (top);
        b.setTopSelectedBgColor (topSel);
        b.setBottomBgColor (bot);
        b.setBottomSelectedBgColor (botSel);
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        radioButtonAlgorithmGroup = new javax.swing.ButtonGroup();
        labelVerticeInicial = new javax.swing.JLabel();
        comboBoxVerticeInicial = new javax.swing.JComboBox();
        labelVerticeFinal = new javax.swing.JLabel();
        comboBoxVerticeFinal = new javax.swing.JComboBox();
        radioButtonDFS = new javax.swing.JRadioButton();
        radioButtonDijkstra = new javax.swing.JRadioButton();
        radioButtonBFS = new javax.swing.JRadioButton();
        graphPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        textAreaPath = new javax.swing.JTextArea();
        botaoImportarXML = new com.alee.laf.button.WebButton();
        botaoBuscarCaminho = new com.alee.laf.button.WebButton();
        botaoIniciarAStar = new com.alee.laf.button.WebButton();
        botaoColoracao = new com.alee.laf.button.WebButton();
        labelDelay = new javax.swing.JLabel();
        inputDelay = new javax.swing.JTextField();
        radioButtonTraveling = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Caminho em Grafos");
        setResizable(false);

        labelVerticeInicial.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labelVerticeInicial.setText("Vértice Inicial:");

        comboBoxVerticeInicial.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        labelVerticeFinal.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labelVerticeFinal.setText("Vértice Final:");

        comboBoxVerticeFinal.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        comboBoxVerticeFinal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxVerticeFinalActionPerformed(evt);
            }
        });

        radioButtonAlgorithmGroup.add(radioButtonDFS);
        radioButtonDFS.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        radioButtonDFS.setSelected(true);
        radioButtonDFS.setText("DFS");

        radioButtonAlgorithmGroup.add(radioButtonDijkstra);
        radioButtonDijkstra.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        radioButtonDijkstra.setText("Dijkstra");

        radioButtonAlgorithmGroup.add(radioButtonBFS);
        radioButtonBFS.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        radioButtonBFS.setText("BFS");

        graphPanel.setBackground(new java.awt.Color(255, 255, 255));
        graphPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(51, 51, 255), new java.awt.Color(0, 0, 255)));
        graphPanel.setPreferredSize(new java.awt.Dimension(600, 450));
        graphPanel.setLayout(new java.awt.BorderLayout());

        textAreaPath.setEditable(false);
        textAreaPath.setColumns(20);
        textAreaPath.setFont(new java.awt.Font("Courier New", 0, 13)); // NOI18N
        textAreaPath.setRows(5);
        textAreaPath.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(51, 255, 204), new java.awt.Color(0, 255, 204)));
        jScrollPane1.setViewportView(textAreaPath);

        botaoImportarXML.setText("Importar XML");
        botaoImportarXML.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoImportarXMLActionPerformed(evt);
            }
        });

        botaoBuscarCaminho.setText("Buscar Caminho");
        botaoBuscarCaminho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoBuscarCaminhoActionPerformed(evt);
            }
        });

        botaoIniciarAStar.setText("Iniciar A*");
        botaoIniciarAStar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoIniciarAStarActionPerformed(evt);
            }
        });

        botaoColoracao.setText("Coloração");
        botaoColoracao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoColoracaoActionPerformed(evt);
            }
        });

        labelDelay.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labelDelay.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelDelay.setText("Delay (MS):");

        inputDelay.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        inputDelay.setText("300");

        radioButtonAlgorithmGroup.add(radioButtonTraveling);
        radioButtonTraveling.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        radioButtonTraveling.setText("Caixeiro");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelVerticeFinal)
                            .addComponent(labelVerticeInicial))
                        .addGap(36, 36, 36)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(comboBoxVerticeFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboBoxVerticeInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(botaoImportarXML, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(botaoIniciarAStar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(botaoColoracao, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(botaoBuscarCaminho, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelDelay, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(inputDelay, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(radioButtonDijkstra)
                            .addComponent(radioButtonBFS)
                            .addComponent(radioButtonDFS)
                            .addComponent(radioButtonTraveling))))
                .addContainerGap())
            .addComponent(graphPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 1116, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(graphPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(radioButtonDFS)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(radioButtonBFS)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(radioButtonDijkstra))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(14, 14, 14)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(labelDelay)
                                            .addComponent(inputDelay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(radioButtonTraveling)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(labelVerticeInicial)
                                    .addComponent(comboBoxVerticeInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(10, 10, 10)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(labelVerticeFinal)
                                    .addComponent(comboBoxVerticeFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(botaoIniciarAStar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(botaoImportarXML, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(botaoColoracao, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(botaoBuscarCaminho, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {comboBoxVerticeInicial, labelVerticeInicial});

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {comboBoxVerticeFinal, labelVerticeFinal});

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void comboBoxVerticeFinalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxVerticeFinalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboBoxVerticeFinalActionPerformed

    private void botaoImportarXMLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoImportarXMLActionPerformed
        this.controller.iniciarImportacaoXML();
    }//GEN-LAST:event_botaoImportarXMLActionPerformed

    private void botaoBuscarCaminhoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoBuscarCaminhoActionPerformed
        this.controller.iniciarBuscaCaminhoGrafo();
    }//GEN-LAST:event_botaoBuscarCaminhoActionPerformed

    private void botaoIniciarAStarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoIniciarAStarActionPerformed
        this.controller.iniciarAStar();
    }//GEN-LAST:event_botaoIniciarAStarActionPerformed

    private void botaoColoracaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoColoracaoActionPerformed
        this.controller.iniciarColoracao();
    }//GEN-LAST:event_botaoColoracaoActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.alee.laf.button.WebButton botaoBuscarCaminho;
    private com.alee.laf.button.WebButton botaoColoracao;
    private com.alee.laf.button.WebButton botaoImportarXML;
    private com.alee.laf.button.WebButton botaoIniciarAStar;
    private javax.swing.JComboBox comboBoxVerticeFinal;
    private javax.swing.JComboBox comboBoxVerticeInicial;
    private javax.swing.JPanel graphPanel;
    private javax.swing.JTextField inputDelay;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelDelay;
    private javax.swing.JLabel labelVerticeFinal;
    private javax.swing.JLabel labelVerticeInicial;
    private javax.swing.ButtonGroup radioButtonAlgorithmGroup;
    private javax.swing.JRadioButton radioButtonBFS;
    private javax.swing.JRadioButton radioButtonDFS;
    private javax.swing.JRadioButton radioButtonDijkstra;
    private javax.swing.JRadioButton radioButtonTraveling;
    private javax.swing.JTextArea textAreaPath;
    // End of variables declaration//GEN-END:variables


    public ButtonGroup getRadioButtonAlgorithmGroup() {
        return radioButtonAlgorithmGroup;
    }

    public JComboBox getComboBoxVerticeInicial() {
        return comboBoxVerticeInicial;
    }

    public JComboBox getComboBoxVerticeFinal() {
        return comboBoxVerticeFinal;
    }

    public JRadioButton getRadioButtonBFS() {
        return radioButtonBFS;
    }

    public JRadioButton getRadioButtonDFS() {
        return radioButtonDFS;
    }

    public JRadioButton getRadioButtonDijkstra() {
        return radioButtonDijkstra;
    }

    public JPanel getGraphPanel() {
        return graphPanel;
    }

    public JTextArea getTextAreaPath() {
        return textAreaPath;
    }

    public JTextField getInputDelay() {
        return inputDelay;
    }

    public JRadioButton getRadioButtonTraveling() {
        return radioButtonTraveling;
    }
}
