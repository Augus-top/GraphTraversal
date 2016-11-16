/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.graphAlgorithm.BuscaCaminhoGrafo;

import java.util.ArrayList;
import java.util.Stack;
import model.graphRepresentation.Grafo;
import model.graphRepresentation.Vertice;

/**
 *
 * @author Augustop
 */
public class DepthFirstSearch extends AlgoritmoBuscaCaminho{
    private Stack<Vertice> stackVertices;

    public DepthFirstSearch(Grafo grafo) {
        super(grafo);
    }
    
    @Override
    public ArrayList<Vertice> buscarCaminho(int idVerticeA, int idVerticeB) {
        this.caminhoGrafo = null;
        this.stackVertices = new Stack<>();
        this.stackVertices.push(this.grafo.getVertice(idVerticeA));
        while(!this.stackVertices.empty()){
            Vertice verticeAtual = this.stackVertices.peek();
            verticeAtual.setVisitado(true);
            ArrayList<Vertice> verticesVizinhos = this.grafo.getVizinhosNaoVisitadosVertice(verticeAtual);
            if(verticesVizinhos.isEmpty()){
                this.stackVertices.pop();
                continue;
            }
            Vertice primeiroVizinho = this.getPrimeiroVizinhoVertice(verticesVizinhos);
            primeiroVizinho.setVerticePai(verticeAtual);
            if(primeiroVizinho.getId() == idVerticeB){
                this.definirCaminho(primeiroVizinho);
                break;
            }else{    
                this.stackVertices.push(primeiroVizinho);
            }
        }
        return this.caminhoGrafo;
    }
    
}
