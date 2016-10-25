/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.graphAlgorithm;

import java.awt.Color;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.graphRepresentation.Grafo;
import model.graphRepresentation.Vertice;

/**
 *
 * @author Augustop
 */
public class Coloracao implements Runnable{
    private Grafo grafo;
    private int numeroCromatico = 1;
    private int ultimaCorSaturacao;
    
    public Coloracao(Grafo grafo) {
        this.grafo = grafo;
    }

    @Override
    public void run(){
        this.grafo.setThreadExecucao(true);
        this.definirColoracao();
        this.grafo.setThreadExecucao(false);
        this.grafo.finalizarColoracao(this.numeroCromatico);
    }
    
    private boolean verificarCorVizinhaVisitada(ArrayList<Color> cores, Color corVizinho){
        for (Color cor : cores) {
            if(corVizinho.getRGB() == cor.getRGB()){
                return true;
            }
        }
        return false;
    }
    
    private Vertice selecionarVerticeMaiorGrauSaturacao(){
        int maiorGrau = 0;
        int grauAtual;
        int id = -1;
        ArrayList<Vertice> vizinhos;
        ArrayList<Color> cores = new ArrayList<>();
        for (Vertice v : this.grafo.getArrayVertice()) {
            if(v.getCorVertice() != null){
                continue;
            }
            cores.clear();
            grauAtual = 0;
            vizinhos = this.grafo.getVizinhosVertice(v);
            for (Vertice vizinho : vizinhos) {
                if(vizinho.getCorVertice() == null){
                    continue;
                }
                if(!this.verificarCorVizinhaVisitada(cores, vizinho.getCorVertice())){
                    grauAtual++;
                    cores.add(vizinho.getCorVertice());
                }
            }
            if(grauAtual >= maiorGrau){
                if(maiorGrau != 0 && grauAtual == maiorGrau){
                    if(v.getRotulo().compareTo(this.grafo.getVertice(id).getRotulo()) > 0){
                        continue;
                    }
                }
                maiorGrau = grauAtual;
                id = v.getId();
            }
        }
        if(id == -1){
            return null;
        }else{
            this.ultimaCorSaturacao = maiorGrau;
            return this.grafo.getVertice(id);
        }
    }
    
    private void definirNovaCor(Vertice verticeAtual){
        boolean corValida;
        ArrayList<Vertice> vizinhos = this.grafo.getVizinhosVertice(verticeAtual);
        for(int i = 1; i <= numeroCromatico; i++){
            verticeAtual.setCorVertice(i);
            try {
                this.grafo.getController().getGraphController().desenharGrafo();
                Thread.sleep(300);
            } catch (InterruptedException ex) {
                Logger.getLogger(Coloracao.class.getName()).log(Level.SEVERE, null, ex);
            }
            corValida = true;
            for (Vertice vizinho : vizinhos) {
                if(vizinho.getCorVertice() == null){
                    continue;
                }
                if(vizinho.getCorVertice().getRGB() == verticeAtual.getCorVertice().getRGB()){
                    corValida = false;
                    break;
                }
            }
            if(corValida){
                break;
            }
        }
    }
    
    public int definirColoracao(){
        this.grafo.getVerticeMaiorGrau().setCorVertice(1);
        try {
            this.grafo.getController().getGraphController().desenharGrafo();
            Thread.sleep(300);
        } catch (InterruptedException ex) {
            Logger.getLogger(Coloracao.class.getName()).log(Level.SEVERE, null, ex);
        }
        Vertice verticeAtual = null;
        while(true){
            verticeAtual = this.selecionarVerticeMaiorGrauSaturacao();
            if(verticeAtual == null){
                break;
            }
            if(ultimaCorSaturacao == numeroCromatico){
                numeroCromatico++;
                verticeAtual.setCorVertice(this.numeroCromatico);
                try {
                    this.grafo.getController().getGraphController().desenharGrafo();
                    Thread.sleep(400);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Coloracao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                this.definirNovaCor(verticeAtual);
            }
        }
        return numeroCromatico;
    }

    public int getNumeroCromatico() {
        return numeroCromatico;
    }

}
