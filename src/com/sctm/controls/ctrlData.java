/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sctm.controls;

import com.sctm.controls.model.Control;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author TonyTheTaylor
 */
//@XmlRootElement(name = "document")
//@XmlAccessorType (XmlAccessType.FIELD)
public class ctrlData  {

    private String controlId;
    private String controlText;
    private int controlNum;
    private String controlTitle;
    
    public String getControlId() {
        return controlId;
    }
    public void setControlId(String controlId) {
        this.controlId = controlId;
    }
    public String getControlText() {
        return controlText;
    }
    public void setControlText(String controlText) {
        this.controlText = controlText;
    }
    public int getControlNum() {
        return controlNum;
    }
    public void setControlNum(int controlNum) {
        this.controlNum = controlNum;
    }
    public String getControlTitle() {
        return controlTitle;
    }
    public void setControlTitle(String controlTitle) {
        this.controlTitle = controlTitle;
    }
     
    @Override
    public String toString() {
        return this.controlId ;
    }
     
}