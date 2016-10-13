/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.graphRepresentation;

import java.awt.Point;
import java.util.ArrayList;
import model.graphAlgorithm.AStar;

/**
 *
 * @author Augustop
 */
public class MapaEstrela {
    private Vertice mapa[][];
    public static final int BARREIRA = 1;
    public static final int PONTO_INICIAL = 2;
    public static final int PONTO_FINAL = 3;
    private int l;
    private int c;
    private AStar buscaAStar;
    private Vertice verticeInicial;
    
    public boolean buscarCaminhoAStar(){    
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
        this.buscaAStar = new AStar(this.mapa, l, c);
    }

    public void limparMapa(){
        for (int i = 0; i < l; i++) {
            for (int j = 0; j < c; j++) {
                if(this.mapa[i][j].getStatusMapa() != BARREIRA && this.mapa[i][j].getStatusMapa() != PONTO_INICIAL && this.mapa[i][j].getStatusMapa() != PONTO_FINAL){
                    this.mapa[i][j].setStatusMapa(0);
                }else if(this.mapa[i][j].getStatusMapa() == PONTO_INICIAL){
                    this.buscaAStar.addListaAberta(this.mapa[i][j]);
                }
            }
        }
    }
    
    public void setBarreira(int l, int c){
        this.mapa[l][c].setStatusMapa(BARREIRA);
    }
    
    public void setPontoInicial(int l, int c){
        this.mapa[l][c].setStatusMapa(PONTO_INICIAL);
        this.buscaAStar.addListaAberta(this.mapa[l][c]);
        this.verticeInicial = this.mapa[l][c];
    }
    
    public void setPontoFinal(int l,  int c){
        this.mapa[l][c].setStatusMapa(PONTO_FINAL);
        this.buscaAStar.setVerticeFinal(this.mapa[l][c]);
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
    
    
}
