/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.graphAlgorithm;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.graphRepresentation.MapaEstrela;
import model.graphRepresentation.Vertice;

/**
 *
 * @author Augustop
 */
public class AStarStepByStep extends AlgoritmoAStar{

    public AStarStepByStep(Vertice[][] mapa, int l, int c, MapaEstrela mapaest) {
        super(mapa, l, c, mapaest);
    }
        
    @Override
    public boolean avaliarVizinho(Vertice vizinho, Vertice pai, double novoG) {
        double novoCusto = (10 * (Math.abs(vizinho.getPosicao().x - this.verticeFinal.getPosicao().x) + Math.abs(vizinho.getPosicao().y - this.verticeFinal.getPosicao().y))) + novoG + pai.getCustoG();
        if(vizinho.getStatusMapa() == Vertice.StatusMapa.FLOOR){
            vizinho.setVerticePai(pai);
            vizinho.setCustoG(novoG + pai.getCustoG());
            vizinho.setCustoCaminho(novoCusto);
            vizinho.setStatusMapa(Vertice.StatusMapa.VERTICE_CONSIDERADO);
            this.listaAberta.add(vizinho);
            this.mapaEstrela.getCtr().getMapController().pintarMapa();
            try {
                this.mapaEstrela.getCtr().getMapController().getMapPainel().revalidate();
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(AStarStepByStep.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            return false;
        }
        if(vizinho.getStatusMapa() == Vertice.StatusMapa.VERTICE_CONSIDERADO && novoCusto < vizinho.getCustoCaminho()){
            this.listaAberta.remove(vizinho);
            vizinho.setVerticePai(pai);
            vizinho.setCustoG(novoG + pai.getCustoG());
            vizinho.setCustoCaminho(novoCusto);
            this.listaAberta.add(vizinho);
            this.mapaEstrela.getCtr().getMapController().pintarMapa();
            try {
                this.mapaEstrela.getCtr().getMapController().getMapPainel().revalidate();
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(AStarStepByStep.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            return false;
        }
        if(vizinho.getStatusMapa() == Vertice.StatusMapa.PONTO_FINAL){
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
            verticeAtual.setStatusMapa(Vertice.StatusMapa.VERTICE_VERIFICADO);
            this.mapaEstrela.getCtr().getMapController().pintarMapa();
            this.mapaEstrela.getCtr().getMapController().getMapPainel().revalidate();
            try {
                Thread.sleep(50);
            } catch (InterruptedException ex) {
                Logger.getLogger(AStarStepByStep.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }
    
    private void reproduzirCaminho(){
        this.caminho.add(verticeFinal);
        Vertice atual = verticeFinal.getVerticePai();
        while(atual != null){
           atual.setStatusMapa(Vertice.StatusMapa.CAMINHO_VERTICE);
           if(atual.getVerticePai() == null){
               atual.setStatusMapa(Vertice.StatusMapa.PONTO_INICIAL);
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
