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
public class PlanarityChecker {
    private Grafo grafo;

    public PlanarityChecker(Grafo grafo) {
        this.grafo = grafo;
    }

    public boolean percorrerCiclo(int i, int tamanhoCiclo ,Vertice atual, Vertice objetivo){
//        System.out.println("Testing " + atual.getRotulo() + " with i at " + i);
        if(i == tamanhoCiclo){
            return atual.getRotulo().equals(objetivo.getRotulo());
        }
        for (Vertice v : this.grafo.getVizinhosVertice(atual)) {
            if(percorrerCiclo(i + 1, tamanhoCiclo, v, objetivo)){
                return true;
            }
        }
        return false;
    }
    
    public boolean verificarCiclo(int tamanhoCiclo){
        ArrayList<Vertice> listaVertice = this.grafo.getArrayVertice();
        for (Vertice v : listaVertice) {
            if(this.percorrerCiclo(0, tamanhoCiclo, v, v)){
                return true;
            }
        }
        return false;
    }
    
    public boolean definirPlanaridade(){
        if(this.grafo.getNumeroVertices() < 3){
            return true;
        }
        if(this.grafo.getNumeroArestas() <= 3 * this.grafo.getNumeroVertices() - 6){
            if(this.verificarCiclo(3)){
                return true;
            }else{
                return (this.grafo.getNumeroArestas() <= 2 * this.grafo.getNumeroVertices() - 4);
            }
        }
        return false;
    }
    
}
