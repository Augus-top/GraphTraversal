/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import com.alee.laf.WebLookAndFeel;
import controller.WindowController;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

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
        buttonXML = new javax.swing.JButton();
        buttonBuscarCaminho = new javax.swing.JButton();
        labelVerticeInicial = new javax.swing.JLabel();
        comboBoxVerticeInicial = new javax.swing.JComboBox();
        labelVerticeFinal = new javax.swing.JLabel();
        comboBoxVerticeFinal = new javax.swing.JComboBox();
        labelAlgoritmo = new javax.swing.JLabel();
        radioButtonDFS = new javax.swing.JRadioButton();
        radioButtonDijkstra = new javax.swing.JRadioButton();
        radioButtonBFS = new javax.swing.JRadioButton();
        graphPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        textAreaPath = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Caminho em Grafos");
        setPreferredSize(new java.awt.Dimension(1020, 620));
        setResizable(false);

        buttonXML.setText("Importar XML");
        buttonXML.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonXMLActionPerformed(evt);
            }
        });

        buttonBuscarCaminho.setText("Buscar Caminho");
        buttonBuscarCaminho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonBuscarCaminhoActionPerformed(evt);
            }
        });

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

        labelAlgoritmo.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        labelAlgoritmo.setText("Algoritmo:");

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
        graphPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(51, 51, 255), null));
        graphPanel.setPreferredSize(new java.awt.Dimension(600, 450));
        graphPanel.setLayout(new java.awt.BorderLayout());

        textAreaPath.setEditable(false);
        textAreaPath.setColumns(20);
        textAreaPath.setRows(5);
        jScrollPane1.setViewportView(textAreaPath);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(buttonXML, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelVerticeFinal)
                            .addComponent(labelVerticeInicial))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(comboBoxVerticeFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboBoxVerticeInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 540, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(labelAlgoritmo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(radioButtonDijkstra)
                            .addComponent(radioButtonBFS)
                            .addComponent(radioButtonDFS))
                        .addContainerGap())
                    .addComponent(buttonBuscarCaminho, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addComponent(graphPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 1020, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(graphPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(labelVerticeInicial)
                                    .addComponent(comboBoxVerticeInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(10, 10, 10)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(labelVerticeFinal)
                                    .addComponent(comboBoxVerticeFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(radioButtonDFS)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(radioButtonBFS)
                                    .addComponent(labelAlgoritmo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(radioButtonDijkstra)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(buttonXML, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                            .addComponent(buttonBuscarCaminho, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 14, Short.MAX_VALUE)))
                .addGap(19, 19, 19))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {comboBoxVerticeInicial, labelVerticeInicial});

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {comboBoxVerticeFinal, labelVerticeFinal});

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonXMLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonXMLActionPerformed
        this.controller.iniciarImportacaoXML();
    }//GEN-LAST:event_buttonXMLActionPerformed

    private void buttonBuscarCaminhoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonBuscarCaminhoActionPerformed
        this.controller.iniciarBuscaCaminhoGrafo();
    }//GEN-LAST:event_buttonBuscarCaminhoActionPerformed

    private void comboBoxVerticeFinalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxVerticeFinalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboBoxVerticeFinalActionPerformed

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
    private javax.swing.JButton buttonBuscarCaminho;
    private javax.swing.JButton buttonXML;
    private javax.swing.JComboBox comboBoxVerticeFinal;
    private javax.swing.JComboBox comboBoxVerticeInicial;
    private javax.swing.JPanel graphPanel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelAlgoritmo;
    private javax.swing.JLabel labelVerticeFinal;
    private javax.swing.JLabel labelVerticeInicial;
    private javax.swing.ButtonGroup radioButtonAlgorithmGroup;
    private javax.swing.JRadioButton radioButtonBFS;
    private javax.swing.JRadioButton radioButtonDFS;
    private javax.swing.JRadioButton radioButtonDijkstra;
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
}
