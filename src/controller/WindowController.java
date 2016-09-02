/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import model.graphRepresentation.Grafo;
import view.MainWindow;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import model.graphAlgorithm.ComparadorVerticesRotulo;
import model.graphRepresentation.Vertice;

/**
 *
 * @author Augustop
 */

public class WindowController {
    private MainWindow mainWindow;
    private XMLController xmlController = new XMLController();
    private Grafo grafoAtual;
    private GraphController graphController;
    
    public WindowController() {
        this.mainWindow = new MainWindow(this);
        this.mainWindow.setSize(1020, 620);
        this.mainWindow.setLocationRelativeTo(null);
        this.mainWindow.setVisible(true);
        this.mainWindow.requestFocusInWindow();
    }
    
    public void iniciarImportacaoXML(){
        try{
            JFileChooser fileChooser = new JFileChooser(JFileChooserController.getLastDirectory());
            FileNameExtensionFilter extensionFilter = new FileNameExtensionFilter("Arquivo XML", "xml");
            fileChooser.setFileFilter(extensionFilter);
            if(fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
                this.grafoAtual = this.xmlController.lerGrafo(fileChooser.getSelectedFile());
                this.limparVertices();
                JFileChooserController.storeLastDirectory(fileChooser);
            }
        }catch(Exception e){
            this.grafoAtual = null;
            this.mainWindow.getGraphPanel().removeAll();
            this.mainWindow.repaint();
            JOptionPane.showMessageDialog(this.mainWindow, "Erro no XML");
            return;
        }
        this.mainWindow.getTextAreaPath().setText("");
        this.prepararComboBox();
        this.graphController = new GraphController(grafoAtual, mainWindow.getGraphPanel());
        this.graphController.desenharGrafo();
        this.mainWindow.repaint();
    }
    
    private void prepararComboBox(){
        PriorityQueue<Vertice> pq = new PriorityQueue<Vertice>(new ComparadorVerticesRotulo());
        for (Vertice v : this.grafoAtual.getArrayVertice()) {
            pq.add(v);
        }
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        DefaultComboBoxModel<String> model2 = new DefaultComboBoxModel<>();
        for (Vertice v : pq) {
            model.addElement(v.getRotulo());
            model2.addElement(v.getRotulo());
        }
        this.mainWindow.getComboBoxVerticeInicial().setModel(model);
        this.mainWindow.getComboBoxVerticeFinal().setModel(model2);
        this.mainWindow.getComboBoxVerticeFinal().setSelectedIndex(1);
    }
    
    private void limparVertices() throws Exception{
        for (int i = 0; i < this.grafoAtual.getArrayVertice().size(); i++) {
            if(this.grafoAtual.getVertice(i).getId() != i){
                throw new Exception();
            }
            for (int j = i + 1; j < this.grafoAtual.getArrayVertice().size(); j++) {
                if(this.grafoAtual.getVertice(i).getRotulo().equals(this.grafoAtual.getVertice(j).getRotulo())){
                    this.grafoAtual.getVertice(i).setRotulo(this.grafoAtual.getVertice(i).getRotulo() + "1");
                    i--;
                    break;
                }
                if(this.grafoAtual.getVertice(i).getId() == this.grafoAtual.getVertice(j).getId()){
                    throw new Exception();
                }
                if(this.grafoAtual.getPeso(i, j) < 0){
                    throw new Exception();
                }
            }
        }
    }
    
    public void iniciarBuscaCaminhoGrafo(){
        if(this.grafoAtual == null){
            return;
        }
        String rotuloA = this.mainWindow.getComboBoxVerticeInicial().getSelectedItem().toString();
        String rotuloB = this.mainWindow.getComboBoxVerticeFinal().getSelectedItem().toString();
        if(rotuloA.equals(rotuloB)){
            JOptionPane.showMessageDialog(mainWindow, "Não é Permitido Escolher o Mesmo Vértice Como Inicial e Final");
            return;
        }
        if(this.mainWindow.getRadioButtonDFS().isSelected()){
            this.grafoAtual.setAlgoritmoBuscaCaminho(Grafo.TipoBusca.DFS);
        }else if(this.mainWindow.getRadioButtonBFS().isSelected()){
            this.grafoAtual.setAlgoritmoBuscaCaminho(Grafo.TipoBusca.BFS);
        }else if(this.mainWindow.getRadioButtonDijkstra().isSelected()){
            if(this.grafoAtual.verificarPesosNegativos()){
                JOptionPane.showMessageDialog(mainWindow, "Pesos negativos não são permitidos no Dijkstra");
                return;
            }
            this.grafoAtual.setAlgoritmoBuscaCaminho(Grafo.TipoBusca.DIJKSTRA);
        }else{
            return;
        }
        this.mainWindow.getTextAreaPath().setText("");
        ArrayList<Vertice> caminho = this.grafoAtual.realizarBusca(this.grafoAtual.getIdVerticePeloRotulo(rotuloA), this.grafoAtual.getIdVerticePeloRotulo(rotuloB));
        if(caminho == null){
            this.mainWindow.getTextAreaPath().append("Caminho Não Existe\n");
            this.graphController.desenharGrafo();
        }else{
            for (Vertice caminho1 : caminho) {
                this.mainWindow.getTextAreaPath().append(caminho1.getRotulo());
                if(caminho1.getId() != caminho.get(caminho.size() - 1).getId()){
                    this.mainWindow.getTextAreaPath().append(" -> ");
                }
            }
            this.graphController.desenharGrafo();
            if(this.mainWindow.getRadioButtonDijkstra().isSelected()){
                this.mainWindow.getTextAreaPath().append("\nCusto do Caminho: " + caminho.get(caminho.size() - 1).getCustoCaminho() + "\n");
            }
        }

        this.grafoAtual.limparCaminho();
    }
}
