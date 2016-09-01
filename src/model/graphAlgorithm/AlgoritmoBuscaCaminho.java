/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.graphAlgorithm;

import java.util.ArrayList;
import model.graphRepresentation.Grafo;
import model.graphRepresentation.Vertice;

/**
 *
 * @author Augustop
 */
public abstract class AlgoritmoBuscaCaminho {
    protected Grafo grafo;
    protected ArrayList<Vertice> caminhoGrafo;
    
    public AlgoritmoBuscaCaminho(Grafo grafo) {
        this.grafo = grafo;
    }
    
    public abstract ArrayList<Vertice> buscarCaminho(int idVerticeA, int idVerticeB);
    
    public Vertice getPrimeiroVizinhoVertice(ArrayList<Vertice> verticesVizinhos){
        Vertice primeiroVizinho = null;
        for (Vertice verticeVizinho : verticesVizinhos) {
            if(!verticeVizinho.isVisitado()){
                if (primeiroVizinho == null) {
                    primeiroVizinho = verticeVizinho;
                }else if((primeiroVizinho.getRotulo().compareTo(verticeVizinho.getRotulo()) > 0)){
                    primeiroVizinho = verticeVizinho;
                }
            }
        }
        return primeiroVizinho;
    }
    
    public ArrayList<Vertice> definirCaminho(Vertice verticeFinal){
        this.caminhoGrafo = new ArrayList<>();
        Vertice verticeAtual = verticeFinal;
        do{
            this.caminhoGrafo.add(0, verticeAtual);
            verticeAtual = verticeAtual.getVerticePai();
        }while(verticeAtual != null);
        return this.caminhoGrafo;
    }

    public ArrayList<Vertice> getCaminhoGrafo() {
        return caminhoGrafo;
    }
}
