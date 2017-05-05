/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sctm.controls.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import com.sctm.controls.model.Control;
import com.sctm.controls.ctrlData;
import javafx.scene.control.TextArea;

/**
 *Dialog to edit details of a control.
 * @author TonyTheTaylor
 */
public class ControlEditDialogController {

    @FXML
    private TextField ctrlNumField; //changed control to ctrl
    @FXML
    private TextField ctrlIDField;
    @FXML
    private TextField ctrlTitleField;
    @FXML
    private TextArea ctrlTextArea;
    @FXML
    private TextArea narrativeTextArea;
    @FXML
    private TextField confModerateField;
    @FXML
    private TextField confLow1Field;
    @FXML
    private TextField confLow2Field;
      
    private Stage dialogStage;
    private Control control;
    private boolean okClicked = false;

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    }

    /**
     * Sets the stage of this dialog.
     * 
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
     * Sets the control to be edited in the dialog.
     * 
     * @param control
     */
    public void setControl(Control control) {
        this.control = control;

        ctrlNumField.setText(Integer.toString(control.getControlNum()));
        ctrlIDField.setText(control.getControlID());
        ctrlTitleField.setText(control.getControlTitle());
        ctrlTextArea.setText(control.getControlText());
        narrativeTextArea.setText(control.getControlNarrative());
        confModerateField.setText(control.getConfModerate());
        confLow1Field.setText(control.getConfLow1());
        confLow2Field.setText(control.getConfLow2());
    }

    /**
     * Returns true if the user clicked OK, false otherwise.
     * 
     * @return
     */
    public boolean isOkClicked() {
        return okClicked;
    }

    /**
     * Called when the user clicks ok.
     */
    @FXML
    private void handleOk() {
        if (isInputValid()) {
            control.setControlNum(Integer.parseInt(ctrlNumField.getText()));
            control.setControlID(ctrlIDField.getText());
            control.setControlTitle(ctrlTitleField.getText());
            control.setControlText(ctrlTextArea.getText());
            control.setControlNarrative(narrativeTextArea.getText());
            control.setConfModerate(confModerateField.getText());
            control.setConfLow1(confLow1Field.getText());
            control.setConfLow2(confLow2Field.getText());

            okClicked = true;
            dialogStage.close();
        }
    }

    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    /**
     * Validates the user input in the text fields.
     * 
     * @return true if the input is valid
     */
    private boolean isInputValid() {
        String errorMessage = "";
        if (ctrlNumField.getText() == null || ctrlNumField.getText().length() == 0) {
        
            errorMessage += "Not a valid control number!\n"; 
        } else {
            // try to parse the postal code into an int.
            try {
                Integer.parseInt(ctrlNumField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "No valid postal code (must be an integer)!\n"; 
            }
        }
        if (ctrlIDField.getText() == null || ctrlIDField.getText().length() == 0) {
            errorMessage += "Not a valid ID!\n"; 
        }
        if (ctrlTitleField.getText() == null || ctrlTitleField.getText().length() == 0) {
            errorMessage += "Not a valid title!\n"; 
        }

        if (ctrlTextArea.getText() == null || ctrlTextArea.getText().length() == 0) {
            errorMessage += "Not a valid description!\n"; 
        } 
        if (narrativeTextArea.getText() == null || narrativeTextArea.getText().length() == 0) {
            errorMessage += "Not a valid narrative!\n"; 
        }
        
        if (!(confModerateField.getText().equals("x") || confModerateField.getText().equals("X")) && confModerateField.getText().isEmpty() == false) {
            errorMessage += "Not a valid confidentiality moderate mark!\n"; 
        }
        
        if (!(confLow1Field.getText().equals("x") || confLow1Field.getText().equals("X")) && confLow1Field.getText().isEmpty() == false) {
            errorMessage += "Not a valid confidentiality low1 mark!\n"; 
        }
        
        if (!(confLow2Field.getText().equals("x") || confLow2Field.getText().equals("X")) && confLow2Field.getText().isEmpty() == false) {
            errorMessage += "Not a valid confidentiality low2 mark!\n"; 
        }
        
        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }
}