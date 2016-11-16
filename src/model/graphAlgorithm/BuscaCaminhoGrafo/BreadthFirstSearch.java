/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.graphAlgorithm.BuscaCaminhoGrafo;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import model.graphRepresentation.Grafo;
import model.graphRepresentation.Vertice;

/**
 *
 * @author Augustop
 */
public class BreadthFirstSearch extends AlgoritmoBuscaCaminho{
    private Queue<Vertice> filaVertices;
    
    public BreadthFirstSearch(Grafo grafo) {
        super(grafo);
    } 
    
    @Override
    public ArrayList<Vertice> buscarCaminho(int idVerticeA, int idVerticeB) {
        this.caminhoGrafo = null;
        this.filaVertices = new LinkedList<>();
        this.filaVertices.add(this.grafo.getVertice(idVerticeA));
        this.filaVertices.peek().setVisitado(true);
        while(!this.filaVertices.isEmpty()){
            Vertice verticeAtual = this.filaVertices.poll();
            ArrayList<Vertice> verticesVizinhos = this.grafo.getVizinhosNaoVisitadosVertice(verticeAtual);
            if(verticesVizinhos.isEmpty()){
                continue;
            }
            while(!verticesVizinhos.isEmpty()){
                Vertice primeiroVizinho = this.getPrimeiroVizinhoVertice(verticesVizinhos);
                primeiroVizinho.setVerticePai(verticeAtual);
                primeiroVizinho.setVisitado(true);
                if(primeiroVizinho.getId() == idVerticeB){
                    this.definirCaminho(primeiroVizinho);
                    break;
                }
                this.filaVertices.add(primeiroVizinho);
                verticesVizinhos.remove(primeiroVizinho);
            }
        }
        return this.caminhoGrafo;
    }
    
}
