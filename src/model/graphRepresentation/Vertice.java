/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.graphRepresentation;

/**
 *
 * @author Augustop
 */
public class Vertice {
    private int posx;
    private int posy;
    private int id;
    private String rotulo;
    private boolean visitado = false; 
    private Vertice verticePai = null;
    
    public Vertice(int posx, int posy, int id, String rotulo) {
        this.posx = posx;
        this.posy = posy;
        this.id = id;
        this.rotulo = rotulo;
    }

    public int getId() {
        return id;
    }

    public int getPosx() {
        return posx;
    }

    public int getPosy() {
        return posy;
    }

    public String getRotulo() {
        return rotulo;
    }

    public Vertice getVerticePai() {
        return verticePai;
    }

    public boolean isVisitado() {
        return visitado;
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
}
