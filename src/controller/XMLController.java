/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.Point;
import model.graphRepresentation.Grafo;
import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import model.graphRepresentation.MapaEstrela;
import model.graphRepresentation.Vertice;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

/**
 *
 * @author Augustop
 */
public class XMLController {
    private WindowController windowController;

    public XMLController(WindowController windowController) {
        this.windowController = windowController;
    }
    
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
                    grafo.addVertice(new Point(posX, posY), relId, rotulo.toUpperCase());
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
    
    public MapaEstrela construirMapa(File file) throws Exception{
        MapaEstrela novoMapa = new MapaEstrela(this.windowController);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(file);
        doc.getDocumentElement().normalize();
        try{
            NodeList nodes = doc.getElementsByTagName("LINHAS");
            Node node = nodes.item(0);
            int linha = Integer.parseInt(node.getTextContent());
            nodes = doc.getElementsByTagName("COLUNAS");
            node = nodes.item(0);
            int coluna = Integer.parseInt(node.getTextContent());
            novoMapa.setTamanhoMapa(linha, coluna);
            nodes = doc.getElementsByTagName("INICIAL");
            node = nodes.item(0);
            String ponto = node.getTextContent();
            String[] pos = ponto.split(",");
            novoMapa.setPontoInicial(Integer.parseInt(pos[0]) - 1, Integer.parseInt(pos[1]) - 1);
            nodes = doc.getElementsByTagName("FINAL");
            node = nodes.item(0);
            ponto = node.getTextContent();
            pos = ponto.split(",");
            novoMapa.setPontoFinal(Integer.parseInt(pos[0]) - 1, Integer.parseInt(pos[1]) - 1);
            nodes = doc.getElementsByTagName("MURO");
            for (int i = 0; i < nodes.getLength(); i++) {
                node = nodes.item(i);
                ponto = node.getTextContent();
                pos = ponto.split(",");
                novoMapa.setBarreira(Integer.parseInt(pos[0]) - 1, Integer.parseInt(pos[1]) - 1);
            }
        }catch(Exception ex){
            System.err.println(ex);
        }
        return novoMapa;
    }
    
}
