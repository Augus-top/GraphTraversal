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
 * @author Augustop
 */
public class ComparadorVerticesRotulo implements Comparator<Vertice>{

    @Override
    public int compare(Vertice a, Vertice b) {
        return a.getRotulo().compareTo(b.getRotulo());
    }
}
