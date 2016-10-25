/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.graphRepresentation;

import controller.WindowController;
import java.awt.Point;
import java.util.ArrayList;
import model.graphAlgorithm.AlgoritmoBuscaCaminho;
import model.graphAlgorithm.BreadthFirstSearch;
import model.graphAlgorithm.Coloracao;
import model.graphAlgorithm.DepthFirstSearch;
import model.graphAlgorithm.DijkstraSearch;
import model.graphAlgorithm.PlanarityChecker;

/**
 *
 * @author Augustop
 */



public class Grafo {
    private boolean ponderado;
    private boolean dirigido;
    private boolean conexo = false;
    private ArrayList<Vertice> arrayVertice = new ArrayList<>();
    private double matrizAdjacencia[][];
    private double matrizAdjacenciaVelha[][];
    private AlgoritmoBuscaCaminho algoritmoBuscaCaminho;
    private int numeroArestas = 0;
    private boolean threadExecucao = false;
    private WindowController controller;
    
    public enum TipoBusca{DFS, BFS, DIJKSTRA}
    
    public Grafo(boolean ponderado, boolean dirigido, int numeroVertice, WindowController ctr) {
        this.ponderado = ponderado;
        this.dirigido = dirigido;
        matrizAdjacencia = new double[numeroVertice][numeroVertice];
        this.controller = ctr;
    }
    
    public void addVertice(Point point, int id, String rotulo){
        Vertice novoVertice = new Vertice(point, id, rotulo);
        this.arrayVertice.add(novoVertice);
    }
    
    public void addAresta(int idVerticeA, int idVerticeB, double peso){
        this.matrizAdjacencia[idVerticeA][idVerticeB] = peso;
        if(!this.dirigido){
            this.matrizAdjacencia[idVerticeB][idVerticeA] = peso;
        }
        this.numeroArestas++;
    }

    public double verificarAdjacencia(int idVerticeA, int idVerticeB){
        return this.matrizAdjacencia[idVerticeA][idVerticeB];
    }
    
    public ArrayList<Vertice> realizarBusca(int idVerticeA, int idVerticeB){
        return this.algoritmoBuscaCaminho.buscarCaminho(idVerticeA, idVerticeB);
    }
    
    public boolean verificarTodosVerticesVisitados(){
        for (Vertice arrayVertice1 : arrayVertice) {
            if(!arrayVertice1.isVisitado()){
                return false;
            }
        }
        return true;
    }
    
    public void limparCaminho(){
        for (Vertice arrayVertice1 : arrayVertice) {
            arrayVertice1.setVisitado(false);
            arrayVertice1.setVerticePai(null);
            arrayVertice1.setCustoCaminho(-1);
        }
    }
    
    public boolean verificarGrafoConexo(){
        this.algoritmoBuscaCaminho = new BreadthFirstSearch(this);
        for (int i = 0; i < this.arrayVertice.size(); i++) {
            for (int j = i + 1; j < this.arrayVertice.size(); j++) {
                this.limparCaminho();
                if(this.algoritmoBuscaCaminho.buscarCaminho(i, j) == null){
                    return this.conexo;
                }
            }
        }
        this.conexo = true;
        return this.conexo;
    }
    
    public int getIdVerticePeloRotulo(String rotulo){
        for (Vertice arrayVertice1 : arrayVertice) {
            if(arrayVertice1.getRotulo().equals(rotulo)){
                return arrayVertice1.getId();
            }
        }
        return -1;
    }
    
    public double getPeso(int idVerticeA, int idVerticeB){
        return this.matrizAdjacencia[idVerticeA][idVerticeB];
    }
    
    public ArrayList<Vertice> getVizinhosNaoVisitadosVertice(Vertice vertice){
        ArrayList<Vertice> verticeVizinho = new ArrayList<>();
        for (int i = 0; i < this.getNumeroVertices(); i++) {
            if(this.matrizAdjacencia[vertice.getId()][i] > 0 && !this.arrayVertice.get(i).isVisitado()){
                verticeVizinho.add(this.arrayVertice.get(i));
            }
        }
        return verticeVizinho;
    }
    
    public ArrayList<Vertice> getVizinhosVertice(Vertice vertice){
        ArrayList<Vertice> verticeVizinho = new ArrayList<>();
        for (int i = 0; i < this.getNumeroVertices(); i++) {
            if(this.matrizAdjacencia[vertice.getId()][i] > 0){
                verticeVizinho.add(this.arrayVertice.get(i));
            }
        }
        return verticeVizinho;
    }
    
    public boolean verificarPlanaridade(){
        PlanarityChecker pc = new PlanarityChecker(this);
        return pc.definirPlanaridade();
    }
    
    public void realizarColoracao(){
        Thread t = new Thread(new Coloracao(this));
        t.start();
    }
    
    public void finalizarColoracao(int numeroCromatico){
        this.controller.terminarColoracao(numeroCromatico);
    }
    
    public void limparColoracao(){
        for (Vertice v : arrayVertice) {
            v.setCorVertice(null);
        }
    }
    
    public ArrayList<Vertice> getArrayVertice() {
        return arrayVertice;
    }

    public boolean isDirigido() {
        return dirigido;
    }

    public boolean isPonderado() {
        return ponderado;
    }
    
    public int getNumeroVertices(){
        return this.arrayVertice.size();
    }
    
    public ArrayList<Vertice> getCaminho(){
        if(this.algoritmoBuscaCaminho == null){
            return null;
        }
        return this.algoritmoBuscaCaminho.getCaminhoGrafo();
    }
    
    public Vertice getVertice(int idVertice){
        return this.arrayVertice.get(idVertice);
    }

    public double[][] getMatrizAdjacencia() {
        return matrizAdjacencia;
    }
    
    public boolean isConexo() {
        return conexo;
    }
    
    public void setConexo(boolean conexo) {
        this.conexo = conexo;
    }

    public void setAlgoritmoBuscaCaminho(TipoBusca tipoBusca) {
        switch(tipoBusca){
            case DFS:
                this.algoritmoBuscaCaminho = new DepthFirstSearch(this);
            break;
                
            case BFS:
                this.algoritmoBuscaCaminho = new BreadthFirstSearch(this);
            break;
                
            case DIJKSTRA:
                this.algoritmoBuscaCaminho = new DijkstraSearch(this);
            break;
        }
    }
    
    public Vertice getVerticeMaiorGrau(){
        int id = 0;
        int maiorGrau = 0;
        int grauAtual;
        for (Vertice v : arrayVertice) {
            grauAtual = 0;
            for (int i = 0; i < this.getNumeroVertices(); i++) {
                if(this.matrizAdjacencia[v.getId()][i] > 0){
                    grauAtual++;
                }
            }
            if(grauAtual >= maiorGrau){
                if(maiorGrau != 0 && grauAtual == maiorGrau){
                    if(v.getRotulo().compareTo(this.arrayVertice.get(id).getRotulo()) > 0){
                        continue;
                    }
                }
                maiorGrau = grauAtual;
                id = v.getId();
            }
        }
        return this.arrayVertice.get(id);
    }

    public int getNumeroArestas() {
        return numeroArestas;
    }
    
    public void prepararMatrizColoracao(){
        if(!dirigido){
            return;
        }
        this.matrizAdjacenciaVelha = new double[this.arrayVertice.size()][this.arrayVertice.size()];
        for (int i = 0; i < this.arrayVertice.size(); i++) {
            for (int j = 0; j < this.arrayVertice.size(); j++) {
                this.matrizAdjacenciaVelha[i][j] = this.matrizAdjacencia[i][j];
            }
        }
        for (int i = 0; i < this.arrayVertice.size(); i++) {
            for (int j = 0; j < this.arrayVertice.size(); j++) {
                if(this.matrizAdjacencia[i][j] > 0){
                    this.matrizAdjacencia[j][i] = this.matrizAdjacencia[i][j];
                }
            }
        }
    }
    
    public void repararMatrizColoracao(){
        if(!dirigido){
            return;
        }
        this.matrizAdjacencia = this.matrizAdjacenciaVelha;
    }
    
    public void setCaminhoNull(){
        this.algoritmoBuscaCaminho = null;
    }

    public boolean isThreadExecucao() {
        return threadExecucao;
    }

    public void setThreadExecucao(boolean threadExecucao) {
        this.threadExecucao = threadExecucao;
    }

    public WindowController getController() {
        return controller;
    }
}
