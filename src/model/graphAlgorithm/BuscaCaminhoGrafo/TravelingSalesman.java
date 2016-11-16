/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.graphAlgorithm.BuscaCaminhoGrafo;

import java.util.ArrayList;
import model.graphRepresentation.Grafo;
import model.graphRepresentation.Vertice;

/**
 *
 * @author Augustop
 */
public class TravelingSalesman extends AlgoritmoBuscaCaminho{
    private double custoCaminho = 0;
    
    public TravelingSalesman(Grafo grafo) {
        super(grafo);
    }
    
    private void encontrarSubCicloInicial(){
        int indice1 = 0;
        int indice2 = 0;
        double[][] matrizGrafo = this.grafo.getMatrizAdjacencia();
        double menorCusto = Integer.MAX_VALUE;
        double novoCusto;
        for (int i = 0; i < this.grafo.getNumeroVertices(); i++) {
            for (int j = 0; j < this.grafo.getNumeroVertices(); j++) {
                if(matrizGrafo[i][j] > -1 && matrizGrafo[j][i] > -1){
                    novoCusto = matrizGrafo[i][j];
                    if(menorCusto > novoCusto){
                        menorCusto = novoCusto;
                        indice1 = i;
                        indice2 = j;
                    }
                }
            }
        }
        this.custoCaminho = menorCusto * 2;
        this.grafo.getVertice(indice2).setCustoCaminho(menorCusto);
        this.grafo.getVertice(indice1).setCustoCaminho(menorCusto);
        this.grafo.getVertice(indice1).setVisitado(true);
        this.grafo.getVertice(indice2).setVisitado(true);
        this.caminhoGrafo.add(this.grafo.getVertice(indice1));
        this.caminhoGrafo.add(this.grafo.getVertice(indice2));
        this.caminhoGrafo.add(this.grafo.getVertice(indice1));
    } 
    
    @Override
    public ArrayList<Vertice> buscarCaminho(int idVerticeA, int idVerticeB) {
        this.caminhoGrafo = new ArrayList<>();
        this.encontrarSubCicloInicial();
        double custoCorrente;
        double novoCusto;
        int indice1 = 0;
        int indice2 = 0;
        double[][] matrizGrafo = this.grafo.getMatrizAdjacencia();
        do{
            custoCorrente = Integer.MAX_VALUE;
            for (int i = 0; i < this.caminhoGrafo.size() - 1; i++) {
                for (Vertice v : this.grafo.getArrayVertice()) {
                    if(v.isVisitado()){
                        continue;
                    }
                    if(matrizGrafo[this.caminhoGrafo.get(i).getId()][v.getId()] > -1 && matrizGrafo[v.getId()][this.caminhoGrafo.get(i + 1).getId()] > -1){
                        novoCusto = matrizGrafo[this.caminhoGrafo.get(i).getId()][v.getId()] + matrizGrafo[v.getId()][this.caminhoGrafo.get(i + 1).getId()] - this.caminhoGrafo.get(i + 1).getCustoCaminho();
                        if(custoCorrente > novoCusto){
                            custoCorrente = novoCusto;
                            indice1 = i;
                            indice2 = v.getId();
                        }
                    }
                }
            }
            if(custoCorrente == Integer.MAX_VALUE){
                this.caminhoGrafo = null;
                return this.caminhoGrafo;
            }
            Vertice novoVerticeCaminho = this.grafo.getVertice(indice2);
            novoVerticeCaminho.setVisitado(true);
            novoVerticeCaminho.setCustoCaminho(matrizGrafo[this.caminhoGrafo.get(indice1).getId()][indice2]);
            Vertice verticeProximoCaminho = this.grafo.getVertice(this.caminhoGrafo.get(indice1 + 1).getId());
            verticeProximoCaminho.setCustoCaminho(matrizGrafo[indice2][verticeProximoCaminho.getId()]);
            this.caminhoGrafo.add(indice1 + 1, novoVerticeCaminho);
            this.custoCaminho += custoCorrente;
            System.out.println(this.custoCaminho);
//            for (Vertice v : this.caminhoGrafo) {
//                System.out.print(v.getRotulo() + " -> ");
//            }
//            break;
        }while(!this.grafo.verificarTodosVerticesVisitados());
        this.caminhoGrafo.get(this.caminhoGrafo.size() - 1).setCustoCaminho(this.custoCaminho);
        return this.caminhoGrafo;
    }
    
}
