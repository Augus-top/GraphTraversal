/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.graphAlgorithm;

import java.util.ArrayList;
import java.util.PriorityQueue;
import model.graphRepresentation.MapaEstrela;
import model.graphRepresentation.Vertice;

/**
 *
 * @author Augustop
 */
public class AStar extends AlgoritmoAStar{

    public AStar(Vertice[][] mapa, int l, int c, MapaEstrela mapaest) {
        super(mapa, l, c, mapaest);
    }

    @Override
    public boolean avaliarVizinho(Vertice vizinho, Vertice pai, double novoG){
        double novoCusto = (10 * (Math.abs(vizinho.getPosicao().x - this.verticeFinal.getPosicao().x) + Math.abs(vizinho.getPosicao().y - this.verticeFinal.getPosicao().y))) + novoG + pai.getCustoG();
        if(vizinho.getStatusMapa() == 0){
            vizinho.setVerticePai(pai);
            vizinho.setCustoG(novoG + pai.getCustoG());
            vizinho.setCustoCaminho(novoCusto);
            vizinho.setStatusMapa(4);
            this.listaAberta.add(vizinho);
            return false;
        }
        if(vizinho.getStatusMapa() == 4 && novoCusto < vizinho.getCustoCaminho()){
            this.listaAberta.remove(vizinho);
            vizinho.setVerticePai(pai);
            vizinho.setCustoG(novoG + pai.getCustoG());
            vizinho.setCustoCaminho(novoCusto);
            this.listaAberta.add(vizinho);
            return false;
        }
        if(vizinho.getStatusMapa() == 3){
            vizinho.setVerticePai(pai);
            vizinho.setCustoG(novoG + pai.getCustoG());
            vizinho.setCustoCaminho(novoCusto);
            return true;
        }
        return false;
    }
    
    @Override
    public boolean procurarVizinhos(Vertice verticePai){
        int x = verticePai.getPosicao().x;
        int y = verticePai.getPosicao().y;
        if(x + 1 < l){
            if(avaliarVizinho(mapa[x + 1][y], verticePai, 10)){
                return true;
            }
        }
        if(y + 1 < c){
            if(avaliarVizinho(mapa[x][y + 1], verticePai, 10)){
                return true;
            }
        }
        if(x - 1 >= 0){
            if(avaliarVizinho(mapa[x - 1][y], verticePai, 10)){
                return true;
            }
        }
        if(y - 1 >= 0){
            if(avaliarVizinho(mapa[x][y - 1], verticePai, 10)){
                return true;
            }
        }
        if(x + 1 < l && y + 1 < c){
            if(avaliarVizinho(mapa[x + 1][y + 1], verticePai, 14)){
                return true;
            }
        }
        if(x + 1 < l && y - 1 >= 0){
            if(avaliarVizinho(mapa[x + 1][y - 1], verticePai, 14)){
                return true;
            }
        }
        if(x - 1 >= 0 && y + 1 < c){
            if(avaliarVizinho(mapa[x - 1][y + 1], verticePai, 14)){
                return true;
            }
        }
        if(x - 1 >= 0 && y - 1 >= 0){
            if(avaliarVizinho(mapa[x - 1][y - 1], verticePai, 14)){
                return true;
            }
        }
        return false;
    }
    
    @Override
    public boolean buscarCaminho(){
        this.caminho.clear();
        Vertice verticeAtual;
        while(!this.listaAberta.isEmpty()){
            verticeAtual = this.listaAberta.poll();
            if(procurarVizinhos(verticeAtual)){
                this.reproduzirCaminho();
                return true;
            }
            verticeAtual.setStatusMapa(5);
        }
        return false;
    }
    
    private void reproduzirCaminho(){
        this.caminho.add(verticeFinal);
        Vertice atual = verticeFinal.getVerticePai();
        while(atual != null){
           atual.setStatusMapa(6);
           if(atual.getVerticePai() == null){
               atual.setStatusMapa(2);
               break;
           }else{
               caminho.add(0, atual);
           }
           atual = atual.getVerticePai();
        }
        this.listaAberta.clear();
    }
    
    @Override
    public void addListaAberta(Vertice a){
        this.listaAberta.add(a);
    }

    @Override
    public void setVerticeFinal(Vertice verticeFinal) {
        this.verticeFinal = verticeFinal;
    }

    @Override
    public ArrayList<Vertice> getCaminho() {
        return caminho;
    }

    @Override
    public boolean encontrouCaminho() {
        return this.encontrouCaminho;
    }
}
