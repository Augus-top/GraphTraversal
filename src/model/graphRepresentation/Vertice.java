/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.graphRepresentation;

import java.awt.Point;

/**
 *
 * @author Augustop
 */
public class Vertice {
    private Point posicao;
    private int id;
    private String rotulo;
    private boolean visitado = false; 
    private Vertice verticePai = null;
    private double custoCaminho = -1;
    private double custoG = 0;
    public enum StatusMapa{FLOOR, BARREIRA, PONTO_INICIAL, PONTO_FINAL, VERTICE_CONSIDERADO, VERTICE_VERIFICADO, CAMINHO_VERTICE};
    private StatusMapa statusMapa = StatusMapa.FLOOR;
    
    public Vertice(Point point, int id, String rotulo) {
        this.posicao = point;
        this.id = id;
        this.rotulo = rotulo;
    }

    public int getId() {
        return id;
    }

    public Point getPosicao() {
        return posicao;
    }
    
    public String getRotulo() {
        return rotulo;
    }

    public Vertice getVerticePai() {
        return verticePai;
    }

    public double getCustoCaminho() {
        return custoCaminho;
    }

    public double getCustoG() {
        return custoG;
    }
    
    public boolean isVisitado() {
        return visitado;
    }

    public StatusMapa getStatusMapa() {
        return statusMapa;
    }
    
    public void setPosicao(Point posicao) {
        this.posicao = posicao;
    }
    
    public void setVisitado(boolean visitado) {
        this.visitado = visitado;
    }

    public void setVerticePai(Vertice verticePai) {
        this.verticePai = verticePai;
    }

    public void setRotulo(String rotulo) {
        this.rotulo = rotulo;
    }

    public void setCustoCaminho(double custoCaminho) {
        this.custoCaminho = custoCaminho;
    }

    public void setStatusMapa(StatusMapa statusMapa) {
        this.statusMapa = statusMapa;
    }

    public void setCustoG(double custoG) {
        this.custoG = custoG;
    }
    
    
}
