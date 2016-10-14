/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.graphRepresentation;

import controller.WindowController;
import java.awt.Point;
import java.util.ArrayList;
import model.graphAlgorithm.AStar;
import model.graphAlgorithm.AStarStepByStep;
import model.graphAlgorithm.AlgoritmoAStar;

/**
 *
 * @author Augustop
 */
public class MapaEstrela implements Runnable{
    private Vertice mapa[][];
    public static final int BARREIRA = 1;
    public static final int PONTO_INICIAL = 2;
    public static final int PONTO_FINAL = 3;
    private int l;
    private int c;
    private AlgoritmoAStar buscaAStar;
    private Vertice verticeInicial;
    private Vertice verticeFinal;
    private WindowController ctr;
    private boolean threadExecucao = false;
    
    
    
    public MapaEstrela(WindowController ctr) {
        this.ctr = ctr;
    }
    
    @Override
    public void run() {
        this.threadExecucao = true;
        this.ctr.finalizarAStar(this.buscaAStar.buscarCaminho());
        this.threadExecucao = false;
    }
    
    public boolean buscarCaminhoAStar(boolean stepByStep){
        if(stepByStep){
            this.buscaAStar = new AStarStepByStep(mapa, l, c, this);
            this.buscaAStar.addListaAberta(verticeInicial);
            this.buscaAStar.setVerticeFinal(this.verticeFinal);
            return false;
        }else{
            this.buscaAStar = new AStar(this.mapa, l, c, this);
        }
        this.buscaAStar.addListaAberta(verticeInicial);
        this.buscaAStar.setVerticeFinal(this.verticeFinal);
        return this.buscaAStar.buscarCaminho();
    }
    
    public void setTamanhoMapa(int l, int c) {
        this.l = l;
        this.c = c;
        this.mapa = new Vertice[l][c];
        for (int i = 0; i < l; i++) {
            for (int j = 0; j < c; j++) {
                this.mapa[i][j] = new Vertice(new Point(i, j), 1, "A");
            }
        }
    }

    public void limparMapa(){
        for (int i = 0; i < l; i++) {
            for (int j = 0; j < c; j++) {
                if(this.mapa[i][j].getStatusMapa() != Vertice.StatusMapa.BARREIRA && this.mapa[i][j].getStatusMapa() !=  Vertice.StatusMapa.PONTO_INICIAL && this.mapa[i][j].getStatusMapa() !=  Vertice.StatusMapa.PONTO_FINAL){
                    this.mapa[i][j].setStatusMapa( Vertice.StatusMapa.FLOOR);
                }else if(this.mapa[i][j].getStatusMapa() ==  Vertice.StatusMapa.PONTO_INICIAL){
                    this.buscaAStar.addListaAberta(this.mapa[i][j]);
                }
            }
        }
    }
    
    public void setBarreira(int l, int c){
        this.mapa[l][c].setStatusMapa(Vertice.StatusMapa.BARREIRA);
    }
    
    public void setPontoInicial(int l, int c){
        this.mapa[l][c].setStatusMapa(Vertice.StatusMapa.PONTO_INICIAL);
        this.verticeInicial = this.mapa[l][c];
    }
    
    public void setPontoFinal(int l,  int c){
        this.mapa[l][c].setStatusMapa(Vertice.StatusMapa.PONTO_FINAL);
        this.verticeFinal = this.mapa[l][c];
    }

    public Vertice[][] getMapa() {
        return mapa;
    }

    public int getL() {
        return l;
    }

    public int getC() {
        return c;
    }
    
    public ArrayList<Vertice> getCaminhoAStar(){
        return this.buscaAStar.getCaminho();
    }

    public Vertice getVerticeInicial() {
        return verticeInicial;
    }

    public WindowController getCtr() {
        return ctr;
    }

    public boolean isThreadExecucao() {
        return threadExecucao;
    }

    public Vertice getVerticeFinal() {
        return verticeFinal;
    }

}
