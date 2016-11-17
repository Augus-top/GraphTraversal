/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.graphAlgorithm.BuscaCaminhoGrafo;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.graphRepresentation.Grafo;
import model.graphRepresentation.Vertice;

/**
 *
 * @author Augustop
 */
public class TravelingSalesman extends AlgoritmoBuscaCaminho implements Runnable{
    private double custoCaminho = 0;
    private int delay;
    
    public TravelingSalesman(Grafo grafo, int delay) {
        super(grafo);
        this.delay = delay;
    }

    @Override
    public void run() {
        this.grafo.setThreadExecucao(true);
        this.buscarCaminhoMenorEncargo();
        this.grafo.setThreadExecucao(false);
        this.grafo.finalizarCaixeiro();
    }
    
        
    @Override
    public ArrayList<Vertice> buscarCaminho(int idVerticeA, int idVerticeB) {
        Thread t = new Thread(this);
        t.start();
        return null;
    }
    
    private void requisitarPinturaSalesman(){
        try {
            this.grafo.getController().pintarSalesman(this.custoCaminho);
            Thread.sleep(this.delay);
        }catch (InterruptedException ex) {
            Logger.getLogger(TravelingSalesman.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        this.grafo.getVertice(indice1).setCorVertice(2);
        this.grafo.getVertice(indice2).setCorVertice(2);
        this.grafo.getVertice(indice1).setVisitado(true);
        this.grafo.getVertice(indice2).setVisitado(true);
        this.caminhoGrafo.add(this.grafo.getVertice(indice1));
        this.caminhoGrafo.add(this.grafo.getVertice(indice2));
        this.caminhoGrafo.add(this.grafo.getVertice(indice1));
    } 
    
    public ArrayList<Vertice> buscarCaminhoMenorEncargo(){
        this.caminhoGrafo = new ArrayList<>();
        this.encontrarSubCicloInicial();
        if(this.caminhoGrafo.get(0).getId() == this.caminhoGrafo.get(1).getId()){
            this.caminhoGrafo.clear();
            this.grafo.limparCaminho();
            this.grafo.limparColoracao();
            return this.greedySearchOriented();
        }
        this.requisitarPinturaSalesman();
        double custoCorrente;
        double novoCusto;
        int indice1 = 0;
        int indice2 = -1;
        double[][] matrizGrafo = this.grafo.getMatrizAdjacencia();
        do{
            custoCorrente = Integer.MAX_VALUE;
            for (int i = 0; i < this.caminhoGrafo.size() - 1; i++) {
                for (Vertice v : this.grafo.getArrayVertice()) {
                    if(v.isVisitado()){
                        continue;
                    }
                    else if(matrizGrafo[this.caminhoGrafo.get(i).getId()][v.getId()] > -1 && matrizGrafo[v.getId()][this.caminhoGrafo.get(i + 1).getId()] > -1){
                        novoCusto = matrizGrafo[this.caminhoGrafo.get(i).getId()][v.getId()] + matrizGrafo[v.getId()][this.caminhoGrafo.get(i + 1).getId()] - this.caminhoGrafo.get(i + 1).getCustoCaminho();
                        if(custoCorrente > novoCusto){
                            custoCorrente = novoCusto;
                            indice1 = i;
                            if(indice2 != v.getId()){
                                indice2 = v.getId();
                                v.setCorVertice(1);
                                this.requisitarPinturaSalesman();
                                v.setCorVertice(null);
                            }
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
            novoVerticeCaminho.setCorVertice(2);
            this.requisitarPinturaSalesman();
        }while(!this.grafo.verificarTodosVerticesVisitados());
        this.caminhoGrafo.get(this.caminhoGrafo.size() - 1).setCustoCaminho(this.custoCaminho);
        return this.caminhoGrafo;
    }
    
    private ArrayList<Vertice> greedySearchOriented(){
        this.custoCaminho = 0;
        Vertice v = this.grafo.getArrayVertice().get(0);
        v.setVisitado(true);
        v.setCorVertice(2);
        Vertice novoVertice;
        double custoAtual;
        this.caminhoGrafo.add(v);
        this.requisitarPinturaSalesman();
        ArrayList<Vertice> vizinhos;
        double[][] matrizGrafo = this.grafo.getMatrizAdjacencia();
        do {            
            vizinhos = this.grafo.getVizinhosNaoVisitadosVertice(v);
            novoVertice = null;
            custoAtual = Integer.MAX_VALUE;
            for (Vertice vizinho : vizinhos) {
                if(custoAtual > matrizGrafo[v.getId()][vizinho.getId()]){
                    custoAtual = matrizGrafo[v.getId()][vizinho.getId()];
                    novoVertice = vizinho;
                }
            }
            if(novoVertice == null){
                this.caminhoGrafo = null;
                return this.caminhoGrafo;
            }
            novoVertice.setVisitado(true);
            novoVertice.setCorVertice(2);
            this.custoCaminho += matrizGrafo[v.getId()][novoVertice.getId()];
            this.caminhoGrafo.add(novoVertice);
            v = novoVertice;
            this.requisitarPinturaSalesman();
        } while (!this.grafo.verificarTodosVerticesVisitados());
        Vertice first = this.caminhoGrafo.get(0);
        Vertice last = this.caminhoGrafo.get(this.caminhoGrafo.size() - 1);
        if(matrizGrafo[last.getId()][first.getId()] == -1){
            this.caminhoGrafo = null;
            return this.caminhoGrafo;
        }
        this.custoCaminho += matrizGrafo[last.getId()][first.getId()];
        this.caminhoGrafo.get(0).setCustoCaminho(this.custoCaminho);
        this.caminhoGrafo.add(this.caminhoGrafo.get(0));
        return this.caminhoGrafo;
    }
}
