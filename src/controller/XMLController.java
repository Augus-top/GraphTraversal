/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.graphRepresentation.Grafo;
import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;

/**
 *
 * @author Augustop
 */
public class XMLController {
    
    public Grafo lerGrafo(File file) throws Exception{
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(file);
        doc.getDocumentElement().normalize();
        NodeList nodes = doc.getElementsByTagName("Vertice");
        NodeList grafoInfo = doc.getElementsByTagName("Grafo");

        Node grafoNode = grafoInfo.item(0);
        Element elementGrafo = (Element) grafoNode;
        Grafo grafo = new Grafo(Boolean.parseBoolean(elementGrafo.getAttribute("ponderado")), Boolean.parseBoolean(elementGrafo.getAttribute("dirigido")), nodes.getLength());
        
        if (nodes.getLength() == 0) {
            throw new Exception("Erro no XML");
        } else {
            for (int i = 0; i < nodes.getLength(); i++) {
                Node node = nodes.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    int relId = Integer.parseInt(element.getAttribute("relId"));
                    String rotulo = element.getAttribute("rotulo");
                    int posX = Integer.parseInt(element.getAttribute("posX"));
                    int posY = Integer.parseInt(element.getAttribute("posY"));
                    grafo.addVertice(posX, posY, relId, rotulo.toUpperCase());
                }
            }
            nodes = doc.getElementsByTagName("Aresta");
            for (int i = 0; i < nodes.getLength(); i++) {
                Node node = nodes.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    int idVertice1 = Integer.parseInt(element.getAttribute("idVertice1"));
                    int idVertice2 = Integer.parseInt(element.getAttribute("idVertice2"));
                    double peso = Double.parseDouble(element.getAttribute("peso"));
                    grafo.addAresta(idVertice1, idVertice2, peso);
                }
            }
        }
        return grafo;
    }
    
}
