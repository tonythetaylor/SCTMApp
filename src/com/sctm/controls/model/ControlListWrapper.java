/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sctm.controls.model;

//import com.sctm.controls.model.Control;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author TonyTheTaylor
 */
@XmlRootElement(name = "controls")
@XmlAccessorType (XmlAccessType.FIELD)
public class ControlListWrapper {
    
    @XmlElement(name = "control")
    private List<Control> controls = null;

    
    public List<Control> getControls() {
        return controls;
    }

    public void setControls(List<Control> controls) {
        this.controls = controls;
    }
 }