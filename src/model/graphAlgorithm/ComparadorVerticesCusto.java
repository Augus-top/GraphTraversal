/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.graphAlgorithm;

import java.util.Comparator;
import model.graphRepresentation.Vertice;

/**
 *
 * @author 5674867
 */
public class ComparadorVerticesCusto implements Comparator<Vertice>{

    @Override
    public int compare(Vertice a, Vertice b) {
        if(a.getCustoCaminho() > b.getCustoCaminho()){
            return 1;
        }
        if(a.getCustoCaminho() < b.getCustoCaminho()){
            return  -1;
        }
        return 0;
    }
}
