/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.graphRepresentation;

import java.awt.Color;
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
    private Color corVertice;
    
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

    public Color getCorVertice() {
        return corVertice;
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

    public void setCorVertice(Color corVertice) {
        this.corVertice = corVertice;
    }
    
    public void setCorVertice(int corVertice) {
        switch(corVertice){
            case 1:
                this.corVertice = Color.RED;
            break;
            case 2:
                this.corVertice = Color.BLUE;
            break;
            case 3:
                this.corVertice = Color.YELLOW;
            break;
            case 4:
                this.corVertice = Color.GREEN;
            break;
            case 5:
                this.corVertice = Color.ORANGE;
            break;
            case 6:
                this.corVertice = Color.PINK;
            break;
            case 7:
                this.corVertice = new Color(167, 50 ,255);
            break;
            case 8:
                this.corVertice = Color.CYAN;
            break;
            case 9:
                this.corVertice = Color.WHITE;
            break;
            case 10:
                this.corVertice = Color.LIGHT_GRAY;
            break;
            case 11:
                this.corVertice = new Color(73, 26, 26);
            break;
            default:
                this.corVertice = new Color(173, 249, 97);
            break;
        }
    }
}
