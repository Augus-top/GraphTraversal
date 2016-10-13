/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import javax.swing.JPanel;
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
                Double n = m[i][j].getCustoCaminho();
                String custo = Integer.toString(n.intValue());
                JPanel square = new JPanel();
                switch(m[i][j].getStatusMapa()){
                    case 0:
                        square.setBackground(Color.WHITE);
                    break;
                    case 1:
                        square.setBackground(Color.BLACK);
                    break;
                    case 2:
                        square = new JPanel(){
                            @Override
                            protected void paintComponent(Graphics g) {
                                super.paintComponent(g);
                                g.setColor(Color.WHITE);
                                g.setFont(new Font("Ariel", Font.PLAIN, squareSize.height - 5));
                                int fontWidth = g.getFontMetrics().stringWidth("I");
                                int fontHeight = g.getFontMetrics().getHeight() - g.getFontMetrics().getDescent();
                                g.drawString("I", squareSize.width - (fontWidth / 2), squareSize.height / 2 + 1 + (fontHeight / 2));
                            }
                        };
                        square.setBackground(new Color(170, 23, 255));
                    break;
                    case 3:
                        square = new JPanel(){
                            @Override
                            protected void paintComponent(Graphics g) {
                                super.paintComponent(g);
                                g.setColor(Color.WHITE);
                                g.setFont(new Font("Ariel", Font.PLAIN, squareSize.height - 5));
                                int fontWidth = g.getFontMetrics().stringWidth("F");
                                int fontHeight = g.getFontMetrics().getHeight() - g.getFontMetrics().getDescent();
                                g.drawString("F", squareSize.width - (fontWidth / 2), squareSize.height / 2 + 1 + (fontHeight / 2));
                            }
                        };
                        square.setBackground(Color.RED);
                    break;
                    case 4:
                        square = new JPanel(){
                            @Override
                            protected void paintComponent(Graphics g) {
                                super.paintComponent(g);
                                g.setColor(Color.WHITE);
                                g.setFont(new Font("Ariel", Font.PLAIN, squareSize.height - 5));
                                int fontWidth = g.getFontMetrics().stringWidth(custo);
                                int fontHeight = g.getFontMetrics().getHeight() - g.getFontMetrics().getDescent();
                                g.drawString(custo, squareSize.width - (fontWidth / 2), squareSize.height / 2 + 1 + (fontHeight / 2));
                            }
                        };
                        square.setBackground(Color.GRAY);
                    break;
                    case 5:
                        square = new JPanel(){
                            @Override
                            protected void paintComponent(Graphics g) {
                                super.paintComponent(g);
                                g.setColor(Color.WHITE);
                                g.setFont(new Font("Ariel", Font.PLAIN, squareSize.height - 5));
                                int fontWidth = g.getFontMetrics().stringWidth(custo);
                                int fontHeight = g.getFontMetrics().getHeight() - g.getFontMetrics().getDescent();
                                g.drawString(custo, squareSize.width - (fontWidth / 2), squareSize.height / 2 + 1 + (fontHeight / 2));
                            }
                        };
                        square.setBackground(new Color(23, 150, 255));
                    break;
                    case 6:
                        square = new JPanel(){
                            @Override
                            protected void paintComponent(Graphics g) {
                                super.paintComponent(g);
                                g.setColor(Color.WHITE);
                                g.setFont(new Font("Ariel", Font.PLAIN, squareSize.height - 5));
                                int fontWidth = g.getFontMetrics().stringWidth(custo);
                                int fontHeight = g.getFontMetrics().getHeight() - g.getFontMetrics().getDescent();
                                g.drawString(custo, squareSize.width - (fontWidth / 2), squareSize.height / 2 + 1 + (fontHeight / 2));
                            }
                        };
                        square.setBackground(Color.GREEN);
                    break;
                }
                square.setBorder(new LineBorder(Color.BLACK, 2));
                square.setPreferredSize(squareSize);
                square.setPreferredSize(squareSize);
                this.mapPainel.add(square);
            }
        }
    }

    public MapaEstrela getMapa() {
        return mapa;
    }
    
    public void setMapa(MapaEstrela mapaEstrela) {
        this.mapa = mapaEstrela;
    }
}
