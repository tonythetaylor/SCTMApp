/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sctm.controls.view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import com.sctm.controls.model.Control;
import com.sctm.controls.ctrlData;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextArea;
import com.sctm.controls.SCTMApp;
/**
 *
 * @author TonyTheTaylor
 */

public class SCTMOverviewController { 

    @FXML
    private TableView<Control> controlTable;
    @FXML
    private TableColumn<Control, Integer> controlNumColumn;
    @FXML
    private TableColumn<Control, String> controlIDColumn;
    @FXML
    private Label controlIDLabel;
    @FXML
    private Label controlTitleLabel;
    @FXML
    private TextArea controlTextArea;
    // Reference to the main application.
    private SCTMApp sctmApp;

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public SCTMOverviewController() {
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        // Initialize the control table with the two columns.
        controlNumColumn.setCellValueFactory(cellData -> cellData.getValue().controlNumProperty().asObject()); //added .asObject() to accept integer.
        controlIDColumn.setCellValueFactory(cellData -> cellData.getValue().controlIDProperty());
        
        // Clear control details.
    showControlDetails(null);

    // Listen for selection changes and show the control details when changed.
    controlTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showControlDetails(newValue));
    }

    
 /**
 * Fills all text fields to show details about the control.
 * If the specified control is null, all text fields are cleared.
 * 
 * @param control the control or null
 */
private void showControlDetails(Control control) {
    if (control != null) {
        // Fill the labels with info from the control object.
        controlIDLabel.setText(control.getControlID());
        controlTitleLabel.setText(control.getControlTitle());
        controlTextArea.setText(control.getControlText());

        // TODO: We need a way to convert control numbers into a String! 
        // controlRatingLabel.setText(...);
    } else {
        // Control is null, remove all the text.
        controlIDLabel.setText("");
        controlTitleLabel.setText("");
        controlTextArea.setText("");
    }
}
    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param sctmApp
     */
    public void setSCTMApp(SCTMApp sctmApp) {
        this.sctmApp = sctmApp;

        // Add observable list data to the table
        controlTable.setItems(sctmApp.getControlData());
    }
    
    /**
 * Called when the user clicks on the delete button.
 */
@FXML
private void handleDeleteControl() {
    int selectedIndex = controlTable.getSelectionModel().getSelectedIndex();
    if (selectedIndex >= 0) {
        controlTable.getItems().remove(selectedIndex);
    } else {
        // Nothing selected.
        Alert alert = new Alert(AlertType.WARNING);
        alert.initOwner(sctmApp.getPrimaryStage());
        alert.setTitle("No Selection");
        alert.setHeaderText("No Control Selected");
        alert.setContentText("Please select a control in the table.");

        alert.showAndWait();
    }
}

/**
 * Called when the user clicks the new button. Opens a dialog to edit
 * details for a new control.
 */
@FXML
private void handleNewControl() {
    Control tempControl = new Control();
    boolean okClicked = sctmApp.showControlEditDialog(tempControl);
    if (okClicked) {
        sctmApp.getControlData().add(tempControl);
    }
}

/**
 * Called when the user clicks the edit button. Opens a dialog to edit
 * details for the selected control.
 */
@FXML
private void handleEditControl() {
    Control selectedControl = controlTable.getSelectionModel().getSelectedItem();
    if (selectedControl != null) {
        boolean okClicked = sctmApp.showControlEditDialog(selectedControl);
        if (okClicked) {
            showControlDetails(selectedControl);
        }

    } else {
        // Nothing selected.
        Alert alert = new Alert(AlertType.WARNING);
        alert.initOwner(sctmApp.getPrimaryStage());
        alert.setTitle("No Selection");
        alert.setHeaderText("No Control Selected");
        alert.setContentText("Please select a control in the table.");

        alert.showAndWait();
    }
}
}