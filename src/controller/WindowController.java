/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import java.util.PriorityQueue;
import com.bethecoder.ascii_table.ASCIITable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import model.graphRepresentation.Grafo;
import view.MainWindow;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import model.graphAlgorithm.ComparadorVerticesRotulo;
import model.graphRepresentation.MapaEstrela;
import model.graphRepresentation.Vertice;
import view.AStarWindow;

/**
 *
 * @author Augustop
 */

public class WindowController {
    private MainWindow mainWindow;
    private XMLController xmlController = new XMLController(this);
    private Grafo grafoAtual;
    private GraphController graphController;
    private AStarWindow starWindow;
    private MapController mapController;
    
    public WindowController() {
        this.mainWindow = new MainWindow(this);
        this.mainWindow.setSize(this.mainWindow.getPreferredSize());
        this.mainWindow.setLocationRelativeTo(null);
        this.mainWindow.setVisible(true);
        this.mainWindow.requestFocusInWindow();
    }
    
    public void iniciarImportacaoXML(){
        if(this.grafoAtual != null && this.grafoAtual.isThreadExecucao()){
            return;
        }
        try{
            JFileChooser fileChooser = new JFileChooser(JFileChooserController.getLastDirectory());
            FileNameExtensionFilter extensionFilter = new FileNameExtensionFilter("Arquivo XML", "xml");
            fileChooser.setFileFilter(extensionFilter);
            if(fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
                this.grafoAtual = this.xmlController.lerGrafo(fileChooser.getSelectedFile());
                this.limparVertices();
                JFileChooserController.storeLastDirectory(fileChooser);
            }else{
                return;
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
            }
        }
    }
    
    public void iniciarBuscaCaminhoGrafo(){
        if(this.grafoAtual == null || this.grafoAtual.isThreadExecucao()){
            return;
        }
        String rotuloA = this.mainWindow.getComboBoxVerticeInicial().getSelectedItem().toString();
        String rotuloB = this.mainWindow.getComboBoxVerticeFinal().getSelectedItem().toString();
        if(rotuloA.equals(rotuloB)){
            JOptionPane.showMessageDialog(mainWindow, "Não é Permitido Escolher o Mesmo Vértice Como Inicial e Final");
            return;
        }
        if(this.mainWindow.getRadioButtonDFS().isSelected()){
            this.grafoAtual.setAlgoritmoBuscaCaminho(Grafo.TipoBusca.DFS, 0);
        }else if(this.mainWindow.getRadioButtonBFS().isSelected()){
            this.grafoAtual.setAlgoritmoBuscaCaminho(Grafo.TipoBusca.BFS, 0);
        }else if(this.mainWindow.getRadioButtonDijkstra().isSelected()){
            this.grafoAtual.setAlgoritmoBuscaCaminho(Grafo.TipoBusca.DIJKSTRA, 0);
        }else if(this.mainWindow.getRadioButtonTraveling().isSelected()){
            if(!this.grafoAtual.verificarGrafoConexo()){
                this.mainWindow.getTextAreaPath().setText("Grafo não conexo! Não é possível executar o Caixeiro Viajante");
                this.grafoAtual.limparCaminho();
                return;
            }else if(!this.grafoAtual.isPonderado()){
                this.mainWindow.getTextAreaPath().setText("Grafo não é ponderado! Não é possível executar o Caixeiro Viajante");
                return;
            }
//            this.grafoAtual.setAlgoritmoBuscaCaminho(Grafo.TipoBusca.TSP);
            this.iniciarSalesman();
            return;
        }else{
            return;
        }
        this.mainWindow.getTextAreaPath().setText("");
        ArrayList<Vertice> caminho = this.grafoAtual.realizarBusca(this.grafoAtual.getIdVerticePeloRotulo(rotuloA), this.grafoAtual.getIdVerticePeloRotulo(rotuloB));
        this.finalizarBuscaCaminho(caminho);
    }
    
    private void iniciarSalesman(){
        int delay;
        try{
            delay = Integer.parseInt(this.mainWindow.getInputDelay().getText());
            if(delay < 1 || delay > 9999){
                delay = 300;
            }
        }catch(Exception e){
            delay = 300;
        }
        this.grafoAtual.setAlgoritmoBuscaCaminho(Grafo.TipoBusca.TSP, delay);
        this.grafoAtual.realizarBusca(0, 0);
        this.mainWindow.getTextAreaPath().setText("");
    }
    
    public void pintarSalesman(double custoCaminho){
        this.mainWindow.getTextAreaPath().setText("");
        ArrayList<Vertice> caminho = this.grafoAtual.getCaminho();
        for (int i = 0; i < caminho.size(); i++) {
            this.mainWindow.getTextAreaPath().append(caminho.get(i).getRotulo());
            if(i != caminho.size() - 1){
                this.mainWindow.getTextAreaPath().append(" -> ");
            }
        }
        this.mainWindow.getTextAreaPath().append("\nCusto total do caminho: " + custoCaminho);
        this.graphController.desenharGrafo();
    }
    
    public void finalizarSalesman(){
        this.mainWindow.getTextAreaPath().setText("Finalizado!\n\n");
        ArrayList<Vertice> caminho = this.grafoAtual.getCaminho();
        this.finalizarBuscaCaminho(caminho);
    }
    
    private void finalizarBuscaCaminho(ArrayList<Vertice> caminho){
        if(caminho == null){
            
            if(this.mainWindow.getRadioButtonTraveling().isSelected()){
                if(!this.grafoAtual.verificarGrafoCompleto()){
                    this.mainWindow.getTextAreaPath().setText("Falha na heurística pelo grafo não ser completo!");
                    this.grafoAtual.limparCaminho();
                    this.grafoAtual.limparColoracao();
                    return;
                }else{
                    this.mainWindow.getTextAreaPath().append("Caminho Não Existe\n");
                }
            }else{
                this.mainWindow.getTextAreaPath().append("Caminho Não Existe\n");
            }
        }else{
            for (int i = 0; i < caminho.size(); i++) {
                this.mainWindow.getTextAreaPath().append(caminho.get(i).getRotulo());
                if(i != caminho.size() - 1){
                    this.mainWindow.getTextAreaPath().append(" -> ");
                }
            }
            if(this.mainWindow.getRadioButtonTraveling().isSelected()){
                this.mainWindow.getTextAreaPath().append("\nCusto total do caminho: " + caminho.get(caminho.size() - 1).getCustoCaminho());
            }
        }
        if(this.mainWindow.getRadioButtonDijkstra().isSelected()){
            pintarTabelaDijkstra();
        }
        this.graphController.desenharGrafo();
        if(!this.mainWindow.getRadioButtonTraveling().isSelected()){
            this.definirGrafoConexo();
        }
        this.grafoAtual.limparCaminho();
        this.grafoAtual.limparColoracao();
    }
    
    
    private void pintarTabelaDijkstra(){
        String[] header = new String[grafoAtual.getNumeroVertices() + 1];
        String[][] dados = new String[2][grafoAtual.getNumeroVertices() + 1];
        header[0] = "Vértices";
        dados[0][0] = "Estimativas";
        dados[1][0] = "Precedentes";
        for (int i = 0; i < grafoAtual.getNumeroVertices(); i++) {
            header[i + 1] = grafoAtual.getArrayVertice().get(i).getRotulo();
            dados[0][i + 1] = Double.toString(grafoAtual.getArrayVertice().get(i).getCustoCaminho());
            if(grafoAtual.getArrayVertice().get(i).getVerticePai() == null){
                dados[1][i + 1] = "--";
            }else{
                dados[1][i + 1] = grafoAtual.getArrayVertice().get(i).getVerticePai().getRotulo();
            }
        }
        String tabela = ASCIITable.getInstance().getTable(header, dados);
        this.mainWindow.getTextAreaPath().append("\n\n" + tabela);
    }
    
    private void definirGrafoConexo(){
        if(this.grafoAtual.isConexo()){
            this.mainWindow.getTextAreaPath().append("\nO Grafo é conexo");
        }else{
            if(this.grafoAtual.verificarGrafoConexo()){
                this.mainWindow.getTextAreaPath().append("\nO Grafo é conexo");
            }else{
                this.mainWindow.getTextAreaPath().append("\nO Grafo não é conexo");
            }
        }
    }
    
    public void iniciarAStar(){
        if(this.grafoAtual != null && this.grafoAtual.isThreadExecucao()){
            return;
        }
        this.starWindow = new AStarWindow(this);
        this.mapController = new MapController(this.starWindow.getMapPanel(), this);
        this.starWindow.setSize(1100, 620);
        this.starWindow.setLocationRelativeTo(null);
        this.starWindow.setVisible(true);
        this.mainWindow.setVisible(false);
    }
    
    public void iniciarImportacaoXMLAStar(){
        if(this.mapController.getMapa() != null && this.mapController.getMapa().isThreadExecucao()){
            return;
        }
        MapaEstrela novoMapa = null;
        try{
            JFileChooser fileChooser = new JFileChooser(JFileChooserController.getLastDirectory());
            FileNameExtensionFilter extensionFilter = new FileNameExtensionFilter("Arquivo XML", "xml");
            fileChooser.setFileFilter(extensionFilter);
            if(fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
                novoMapa = this.xmlController.construirMapa(fileChooser.getSelectedFile());
                this.mapController.setMapa(novoMapa);
                this.mapController.pintarMapa();
                this.starWindow.repaint();
                this.starWindow.getMapPanel().revalidate();
                JFileChooserController.storeLastDirectory(fileChooser);
            }else{
                return;
            }
        }catch(Exception e){
            this.starWindow.getMapPanel().removeAll();
            this.starWindow.repaint();
            JOptionPane.showMessageDialog(this.starWindow, "Erro no XML");
            return;
        }
        this.starWindow.getTextAreaPath().setText("");
    }
    
    public void iniciarBuscaAStar(boolean stepByStep){
        if(this.mapController.getMapa().isThreadExecucao()){
            return;
        }
        this.starWindow.getTextAreaPath().setText("");
        if(stepByStep == true){
            this.mapController.getMapa().buscarCaminhoAStar(stepByStep);
            Thread t = new Thread(this.mapController.getMapa());
            t.start();
        }else{
            this.finalizarAStar(this.mapController.getMapa().buscarCaminhoAStar(stepByStep));
        }

    }
    
    public void finalizarAStar(boolean encontrouCaminho){
        if(encontrouCaminho){
            ArrayList<Vertice> caminho = this.mapController.getMapa().getCaminhoAStar();
            String[] header = new String[caminho.size() + 1];
            String[][] dados = new String[4][caminho.size() + 1];
            header[0] = "Passo ";
            dados[0][0] = "Posição";
            dados[1][0] = "Valor F";
            dados[2][0] = "Valor G";
            dados[3][0] = "Valor H";
            
            for (int i = 0; i < caminho.size(); i++) {
                header[i + 1] = Integer.toString(i + 1);
                dados[0][i + 1] = "" + caminho.get(i).getPosicao().x + ", " + caminho.get(i).getPosicao().y;
                dados[1][i + 1] = "" + caminho.get(i).getCustoCaminho();
                dados[2][i + 1] = "" + caminho.get(i).getCustoG();
                dados[3][i + 1] = "" + (caminho.get(i).getCustoCaminho() - caminho.get(i).getCustoG());
            }
            String tabela = ASCIITable.getInstance().getTable(header, dados);
            this.starWindow.getTextAreaPath().append(tabela);
        }else{
            this.starWindow.getTextAreaPath().setText("Caminho não Existe");
        }
        this.mapController.getMapa().getVerticeInicial().setStatusMapa(Vertice.StatusMapa.PONTO_INICIAL);
        this.mapController.pintarMapa();
        this.starWindow.getMapPanel().revalidate();
        this.mapController.getMapa().limparMapa();
    }
    
    public void iniciarColoracao(){
        if(this.grafoAtual == null || this.grafoAtual.isThreadExecucao()){
            return;
        }
        int delay;
        try{
            delay = Integer.parseInt(this.mainWindow.getInputDelay().getText());
            if(delay < 1 || delay > 9999){
                delay = 300;
            }
        }catch(Exception e){
            delay = 300;
        }
        this.mainWindow.getTextAreaPath().setText("");
        this.grafoAtual.setCaminhoNull();
        this.grafoAtual.prepararMatrizColoracao();
        this.grafoAtual.realizarColoracao(delay);
    }
    
    public void terminarColoracao(int numeroCromatico){
        this.grafoAtual.repararMatrizColoracao();
        this.mainWindow.getTextAreaPath().setText("Número Cromático: " + numeroCromatico);
        if(numeroCromatico > 12){
            this.mainWindow.getTextAreaPath().append("\nNúmero ultrapassou o limite de cores disponíveis (12)");
        }
        this.graphController.desenharGrafo();
        this.grafoAtual.limparColoracao();
    }
    
    public void retornarTelaPrincipal(){
        if(this.mapController.getMapa() != null && this.mapController.getMapa().isThreadExecucao()){
            return;
        }
        this.mainWindow.setVisible(true);
        this.starWindow.setVisible(false);
    }

    public MapController getMapController() {
        return mapController;
    }

    public GraphController getGraphController() {
        return graphController;
    }
}
