/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import model.graphRepresentation.MapaEstrela;
import model.graphRepresentation.Vertice;

/**
 *
 * @author Augustop
 */
public class MapController {
    private JPanel mapPainel;
    private MapaEstrela mapa;
    private WindowController windowController;

    public MapController(JPanel mapPainel, WindowController windowController) {
        this.mapPainel = mapPainel;
        this.windowController = windowController;
    }

    public void pintarMapa(){
        this.mapPainel.removeAll();
        Vertice[][] m = this.mapa.getMapa();
        this.mapPainel.setLayout(new GridLayout(this.mapa.getL(), this.mapa.getC()));
        Dimension squareSize = new Dimension((this.mapPainel.getHeight() - 5) / this.mapa.getL(), (this.mapPainel.getHeight() - 5) / this.mapa.getC());
        for (int i = 0; i < this.mapa.getL(); i++) {
            for (int j = 0; j < this.mapa.getC(); j++) {
                JPanel square = new JPanel();
                square.setBorder(new LineBorder(Color.BLACK, 2));
                square.setPreferredSize(squareSize);
                square.setPreferredSize(squareSize);
                switch(m[i][j].getStatusMapa()){
                    case 0:
                        square.setBackground(Color.WHITE);
                    break;
                    case 1:
                        square.setBackground(Color.BLACK);
                    break;
                    case 2:
                        square.setBackground(Color.GREEN);
                    break;
                    case 3:
                        square.setBackground(Color.RED);
                    break;
                }
                this.mapPainel.add(square);
            }
        }
        this.mapPainel.revalidate();
    }
    
    public void setMapa(MapaEstrela mapaEstrela) {
        this.mapa = mapaEstrela;
    }
}
