/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sctm.controls.model;


/**
 *
 * @author TonyTheTaylor
 */

//import java.time.LocalDate;

import com.sctm.controls.ctrlData;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Control {
    private final StringProperty controlID;
    private final IntegerProperty controlNum;
    private final StringProperty controlTitle;
    private final StringProperty controlText;
    private final StringProperty controlNarrative;
    private final StringProperty confModerate;
    private final StringProperty confLow1;
    private final StringProperty confLow2;
    
      /**
     * Default constructor.
     */ 
    public Control() {

        this(null, null, null, null, null, null, null, null);
    }
    
        /**
     * Constructor with some initial data.
     * 
     * @param controlNum
     * @param controlID
     * @param controlTitle
     * @param controlText
     * @param controlNarrative
     * @param confModerate
     * @param confLow1
     * @param confLow2
     */
    public Control(Integer controlNum, String controlID, String controlTitle, String controlText, String controlNarrative, String confModerate, String confLow1, String confLow2) {
        if(controlNum != null){ //had to add a condition to check if the controlNum is not null. Without this, a null pointer exception occurs when the edit button is clicked.
        this.controlNum = new SimpleIntegerProperty(controlNum); 
    } else{
          this.controlNum = new SimpleIntegerProperty();  
        }
        this.controlID = new SimpleStringProperty(controlID);
        this.controlTitle = new SimpleStringProperty(controlTitle);
        this.controlText = new SimpleStringProperty(controlText);
        this.controlNarrative = new SimpleStringProperty(controlNarrative);
        this.confModerate = new SimpleStringProperty(confModerate);
        this.confLow1 = new SimpleStringProperty(confLow1);
        this.confLow2 = new SimpleStringProperty(confLow2);
    }
    
    //@XmlElement   
    public String getControlID() {
        return controlID.get();
    }

    public void setControlID(String controlID) {
        this.controlID.set(controlID);
    }

    public StringProperty controlIDProperty() {
        return controlID;
    }
    
    //@XmlElement  
    public String getControlTitle() {
        return controlTitle.get();
    }

    public void setControlTitle(String controlTitle) {
        this.controlTitle.set(controlTitle);
    }

    public StringProperty controlTitleProperty() {
        return controlTitle;
    }
    
    public int getControlNum() {
        return controlNum.get();
    }

    public void setControlNum(int controlNum) {
        this.controlNum.set(controlNum);
    }

    public IntegerProperty controlNumProperty() {
        return controlNum;
    }
    
    public String getControlText() {
        return controlText.get();
    }

    public void setControlText(String controlText) {
        this.controlText.set(controlText);
    }

    public StringProperty controlTextProperty() {
        return controlText;
    }
    
    public String getControlNarrative() {
        return controlNarrative.get();
    }

    public void setControlNarrative(String controlNarrative) {
        this.controlNarrative.set(controlNarrative);
    }

    public StringProperty controlNarrativeProperty() {
        return controlNarrative;
    }
    
    public String getConfModerate() {
        return confModerate.get();
    }

    public void setConfModerate(String confModerate) {
        this.confModerate.set(confModerate);
    }

    public StringProperty confModerateProperty() {
        return confModerate;
    }
    
    public String getConfLow1() {
        return confLow1.get();
    }

    public void setConfLow1(String confLow1) {
        this.confLow1.set(confLow1);
    }

    public StringProperty confLow1Property() {
        return confLow1;
    }
    
    public String getConfLow2() {
        return confLow1.get();
    }

    public void setConfLow2(String confLow2) {
        this.confLow2.set(confLow2);
    }

    public StringProperty confLow2Property() {
        return confLow2;
    }
}