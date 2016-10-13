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
public abstract class AlgoritmoAStar {
    protected Vertice mapa[][];
    protected PriorityQueue<Vertice> listaAberta;
    protected Vertice verticeFinal;
    protected int l;
    protected int c;
    protected ArrayList<Vertice> caminho = new ArrayList<Vertice>();
    protected boolean encontrouCaminho = false;
    protected MapaEstrela mapaEstrela;
    
    public AlgoritmoAStar(Vertice[][] mapa, int l, int c, MapaEstrela mapaest) {
        this.mapa = mapa;
        this.l = l;
        this.c = c;
        this.listaAberta = new PriorityQueue<>(new ComparadorVerticesCusto());
        this.mapaEstrela = mapaest;
    }
    
    public abstract boolean avaliarVizinho(Vertice vizinho, Vertice pai, double novoG);
    
    public abstract boolean procurarVizinhos(Vertice verticePai);
    
    public abstract boolean buscarCaminho();
    
    public abstract void addListaAberta(Vertice a);

    public abstract void setVerticeFinal(Vertice verticeFinal);

    public abstract ArrayList<Vertice> getCaminho();
 
    public abstract boolean encontrouCaminho();
}
