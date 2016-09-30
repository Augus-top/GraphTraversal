/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.alee.extended.image.DisplayType;
import com.alee.extended.image.WebImage;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import model.graphRepresentation.Grafo;
import model.graphRepresentation.Vertice;

/**
 *
 * @author Augustop
 */
public class GraphController {
    private Grafo grafo;
    private JPanel graphPanel;
    private int radiusVertice = 40; 

    public GraphController(Grafo grafo, JPanel graphPanel) {
        this.grafo = grafo;
        this.graphPanel = graphPanel;
    }

    public void desenharGrafo(){
        this.graphPanel.removeAll();
        Point minPoint = this.getMaxMinPoint(-1);
        Point maxPoint = this.getMaxMinPoint(1);
        BufferedImage graphImage = new BufferedImage(maxPoint.x, maxPoint.y, BufferedImage.TYPE_4BYTE_ABGR);
        Graphics2D g = (Graphics2D) graphImage.getGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setStroke(new BasicStroke(3.0f));
        this.prepararVertices(g);
        graphImage = graphImage.getSubimage(minPoint.x, minPoint.y, maxPoint.x - minPoint.x, maxPoint.y - minPoint.y);
        WebImage wi = new WebImage(graphImage);
        wi.setDisplayType(DisplayType.fitComponent);
        this.graphPanel.add(wi);
        this.graphPanel.revalidate();
    }
    
    private void prepararVertices(Graphics2D g){
        double matriz[][] = this.grafo.getMatrizAdjacencia();
        g.setColor(Color.BLACK);
        Point v1;
        Point v2;
        for (int i = 0; i < matriz[0].length; i++) {
            for (int j = 0; j < matriz.length; j++) {
                if(matriz[i][j] != 0){
                    v1 = this.grafo.getVertice(i).getPosicao();
                    v2 = this.grafo.getVertice(j).getPosicao();
                    g.drawLine(v1.x, v1.y, v2.x, v2.y);
                    if(this.grafo.isDirigido()){
                        this.pintarArcos(g, v2, v1);
                    }
                    if(this.grafo.isPonderado()){
                        this.pintarPesos(g, v1, v2, matriz[i][j]);
                    }
                }
            }
        }
        if(this.grafo.getCaminho() != null && this.grafo.getCaminho().size() > 1){
            for (int i = 0; i < this.grafo.getCaminho().size() - 1; i++) {
                g.setColor(Color.red);
                v1 = this.grafo.getCaminho().get(i).getPosicao();
                v2 = this.grafo.getCaminho().get(i + 1).getPosicao();
                g.drawLine(v1.x, v1.y, v2.x, v2.y);
                if(this.grafo.isDirigido()){
                    this.pintarArcos(g, v2, v1);
                }
                if(this.grafo.isPonderado()){
                    this.pintarPesos(g, v1, v2, matriz[this.grafo.getCaminho().get(i).getId()][this.grafo.getCaminho().get(i + 1).getId()]);
                }
            }
        }
        for (Vertice v: this.grafo.getArrayVertice()) {
            g.setColor(Color.YELLOW);
            g.fillOval(v.getPosicao().x - this.radiusVertice / 2, v.getPosicao().y - this.radiusVertice / 2, this.radiusVertice, this.radiusVertice);
            g.setColor(Color.BLACK);
            g.drawOval(v.getPosicao().x - this.radiusVertice / 2, v.getPosicao().y - this.radiusVertice / 2, this.radiusVertice, this.radiusVertice);
            g.setColor(Color.BLACK);
            int fontWidth = g.getFontMetrics().stringWidth(v.getRotulo());
            int fontHeight = g.getFontMetrics().getHeight() - g.getFontMetrics().getDescent();
            g.drawString(v.getRotulo(),v.getPosicao().x - (fontWidth / 2), v.getPosicao().y + (fontHeight / 2));
        }
    }
    
    private Point getMaxMinPoint(int maxMin){
        Point point = new Point();
        point.x = this.grafo.getVertice(0).getPosicao().x;
        point.y = this.grafo.getVertice(0).getPosicao().y;
        for (Vertice v : this.grafo.getArrayVertice()) {
            if(v.getPosicao().x * maxMin > point.x * maxMin){
                point.x = v.getPosicao().x;
            }
            if(v.getPosicao().y * maxMin > point.y * maxMin){
                point.y = v.getPosicao().y;
            }
        }
        point.x += 50 * maxMin;
        point.y += 50 * maxMin;
        if(point.x < 0){
            point.x = 0;
        }
        if(point.y < 0){
            point.y = 0;
        }
        return point;
    }
    
    private void pintarArcos(Graphics2D g, Point v1, Point v2){
        double theta = Math.atan2(v1.y - v2.y, v1.x - v2.x);
        double inX = v1.x - this.radiusVertice / 2 * Math.cos(theta);
        double inY = v1.y - this.radiusVertice / 2 * Math.sin(theta);
        double rho = theta + Math.toRadians(15);
        for (int i = 0; i < 2; i++) {
            g.draw(new Line2D.Double(inX, inY, inX - 20 * Math.cos(rho), inY - 20 * Math.sin(rho)));
            rho = theta - Math.toRadians(15);
        }
    }
    
    private void pintarPesos(Graphics2D g, Point v1, Point v2, double peso){
        Point centroVertice = new Point();
        centroVertice.x = (v1.x > v2.x)? v2.x + ((v1.x - v2.x) / 2) : v1.x + ((v2.x - v1.x) / 2);
        centroVertice.y = (v1.y > v2.y)? v2.y + ((v1.y - v2.y) / 2) : v1.y + ((v2.y - v1.y) / 2);
        int fontWidth = g.getFontMetrics().stringWidth(Double.toString(peso));
        int fontHeight = g.getFontMetrics().getHeight() - g.getFontMetrics().getDescent();
        g.setColor(Color.WHITE);
        g.fillOval(centroVertice.x - 12, centroVertice.y - 12, fontWidth + 10, fontHeight + 10);
        g.setColor(Color.BLACK);
        g.drawString(Double.toString(peso), centroVertice.x - 8, centroVertice.y + fontHeight - 8);
    }
    
    public void setGrafo(Grafo grafo) {
        this.grafo = grafo;
    }
}
