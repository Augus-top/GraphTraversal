/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.graphRepresentation;

import java.util.ArrayList;
import model.graphAlgorithm.AlgoritmoBuscaCaminho;
import model.graphAlgorithm.BreadthFirstSearch;
import model.graphAlgorithm.DepthFirstSearch;
import model.graphAlgorithm.DijkstraSearch;

/**
 *
 * @author Augustop
 */



public class Grafo {
    private boolean ponderado;
    private boolean dirigido;
    private boolean conexo;
    private ArrayList<Vertice> arrayVertice = new ArrayList<>();
    private double matrizAdjacencia[][];
    private AlgoritmoBuscaCaminho algoritmoBuscaCaminho;
    
    public enum TipoBusca{DFS, BFS, DIJKSTRA}
    
    public Grafo(boolean ponderado, boolean dirigido, int numeroVertice) {
        this.ponderado = ponderado;
        this.dirigido = dirigido;
        matrizAdjacencia = new double[numeroVertice][numeroVertice];
    }
    
    public void addVertice(int posx, int posY, int id, String rotulo){
        Vertice novoVertice = new Vertice(posx, posY, id, rotulo);
        this.arrayVertice.add(novoVertice);
    }
    
    public void addAresta(int idVerticeA, int idVerticeB, double peso){
        this.matrizAdjacencia[idVerticeA][idVerticeB] = peso;
    }

    public double verificarAdjacencia(int idVerticeA, int idVerticeB){
        return this.matrizAdjacencia[idVerticeA][idVerticeB];
    }
    
    public ArrayList<Vertice> realizarBusca(int idVerticeA, int idVerticeB){
        return this.algoritmoBuscaCaminho.buscarCaminho(idVerticeA, idVerticeB);
    }
    
    public void limparCaminho(){
        for (Vertice arrayVertice1 : arrayVertice) {
            arrayVertice1.setVisitado(false);
            arrayVertice1.setVerticePai(null);
        }
    }
    
    public int getIdVerticePeloRotulo(String rotulo){
        for (Vertice arrayVertice1 : arrayVertice) {
            if(arrayVertice1.getRotulo().equals(rotulo)){
                return arrayVertice1.getId();
            }
        }
        return -1;
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
    
    public Vertice getVertice(int idVertice){
        return this.arrayVertice.get(idVertice);
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
}
