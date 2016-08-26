/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.graphAlgorithm;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;
import model.graphRepresentation.Grafo;
import model.graphRepresentation.Vertice;

/**
 *
 * @author Augustop
 */
public class DijkstraSearch extends AlgoritmoBuscaCaminho{
    private Queue<Vertice> listaAberta;
    
    public DijkstraSearch(Grafo grafo) {
        super(grafo);
    }
    
    @Override
    public ArrayList<Vertice> buscarCaminho(int idVerticeA, int idVerticeB) {
        this.caminhoGrafo = null;
        this.listaAberta = new PriorityQueue(new ComparadorVerticesCusto());
        this.listaAberta.add(this.grafo.getVertice(idVerticeA));
        this.listaAberta.peek().setCustoCaminho(0);
        while(!listaAberta.isEmpty()){
            Vertice verticeAtual = this.listaAberta.poll();
            verticeAtual.setVisitado(true);
            ArrayList<Vertice> vizinhos = this.grafo.getVizinhosVertice(verticeAtual);
            for (Vertice vizinho : vizinhos) {
                if(vizinho.getCustoCaminho() == -1){
                    vizinho.setCustoCaminho(verticeAtual.getCustoCaminho() + this.grafo.getPeso(verticeAtual.getId(), vizinho.getId()));
                    vizinho.setVerticePai(verticeAtual);
                }else if(vizinho.getCustoCaminho() > verticeAtual.getCustoCaminho() + vizinho.getCustoCaminho()){
                    vizinho.setCustoCaminho(verticeAtual.getCustoCaminho() + this.grafo.getPeso(verticeAtual.getId(), vizinho.getId()));
                    vizinho.setVerticePai(verticeAtual);
                }
                if(vizinho.getId() == idVerticeB){
                    this.definirCaminho(vizinho);
                    return this.caminhoGrafo;
                }
            }
        }
        return this.caminhoGrafo;
    }
    
}
