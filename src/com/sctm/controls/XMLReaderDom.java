/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sctm.controls;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
 
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
 
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author TonyTheTaylor
 */
 
public class XMLReaderDom {
 
    public static void main(String[] args) {
        String filePath = "sctm.xml";
        File xmlFile = new File(filePath);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;
        try {
            dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();
            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
            NodeList nodeList = doc.getElementsByTagName("controls");
            //now XML is loaded as Document in memory, lets convert it to Object List
            List<ctrlData> empList = new ArrayList<ctrlData>();
            for (int i = 0; i < nodeList.getLength(); i++) {
                empList.add(getctrlData(nodeList.item(i)));
            }
            //lets print ctrlData list information
            for (ctrlData emp : empList) {
                //System.out.println(emp.toString());
            }
        } catch (SAXException | ParserConfigurationException | IOException | NullPointerException e1) {
        	
        	//System.out.println("darn!");
            e1.printStackTrace();
        }
 
    }
 
 
    private static ctrlData getctrlData(Node node) {
        //XMLReaderDOM domReader = new XMLReaderDOM();
        ctrlData emp = new ctrlData();
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element element = (Element) node;
            try{
            emp.setControlId(getTagValue("controlId", element));
            
            emp.setControlNum(Integer.parseInt(getTagValue("controlNumber", element)));
            emp.setControlTitle(getTagValue("controlTitle", element));
            emp.setControlText(getTagValue("controlText", element));
            }catch(NullPointerException e) {
            	//System.out.println("hmph!");
            }
        }
 
        return emp;
        
    }
 
 
    private static String getTagValue(String tag, Element element) {
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = (Node) nodeList.item(0);
        return node.getNodeValue();
        
    }
 
}
