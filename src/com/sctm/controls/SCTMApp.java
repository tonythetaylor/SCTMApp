/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sctm.controls;

import java.io.IOException;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import com.sctm.controls.model.Control;
import com.sctm.controls.model.ControlListWrapper;
import com.sctm.controls.view.ControlEditDialogController;
import com.sctm.controls.view.RootLayoutController;
import com.sctm.controls.view.SCTMOverviewController;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.prefs.Preferences;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Modality;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class SCTMApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    
    /**
     * The data as an observable list of Controls.
     */
    private final ObservableList<Control> controlData = FXCollections.observableArrayList();

    /**
     * Constructor
     */
    public SCTMApp() {
        // Add some sample data
        /*controlData.add(new Control(1, "Muster"));
        controlData.add(new Control(2, "Mueller"));
        controlData.add(new Control(3, "Kurz"));
        controlData.add(new Control(4, "Meier"));
        controlData.add(new Control(5, "Meyer"));
        controlData.add(new Control(6, "Kunz"));
        controlData.add(new Control(7, "Best"));
        controlData.add(new Control(8, "Meier"));
        controlData.add(new Control(9, "Mueller"));*/
        
        
        /*String filePath = "sctm.xml";
        File xmlFile = new File(filePath);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;
        try {
            dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();
            //System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
            NodeList nodeList = doc.getElementsByTagName("controls");
            //now XML is loaded as Document in memory, lets convert it to Object List
            List<ctrlData> empList = new ArrayList<ctrlData>();
            for (int i = 0; i < nodeList.getLength(); i++) {
                empList.add(getctrlData(nodeList.item(i)));
                
            }
            //lets print ctrlData list information
            for (ctrlData emp : empList) {
                controlData.add(new Control(emp.getControlNum(), emp.getControlId(), emp.getControlTitle(), emp.getControlText()));
                //System.out.println(emp.toString());
            }
        } catch (SAXException | ParserConfigurationException | IOException | NullPointerException e1) {
        	
        	//System.out.println("darn!");
            e1.printStackTrace();
        }*/
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
    /**
     * Returns the data as an observable list of Controls. 
     * @return
     */
    public ObservableList<Control> getControlData() {
        return controlData;
    }
    
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("SCTM App");

        initRootLayout();
        showSCTMOverview();
   
    }

    /**
 * Initializes the root layout and tries to load the last opened
 * control file.
 */
public void initRootLayout() {
    try {
        // Load root layout from fxml file.
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(SCTMApp.class
                .getResource("view/RootLayout.fxml"));
        rootLayout = (BorderPane) loader.load();

        // Show the scene containing the root layout.
        Scene scene = new Scene(rootLayout);
        primaryStage.setScene(scene);

        // Give the controller access to the main(sctm) app.
        RootLayoutController controller = loader.getController();
        controller.setSCTMApp(this);

        primaryStage.show();
    } catch (IOException e) {
        e.printStackTrace();
    }

    // Try to load last opened control file.
    File file = getControlFilePath();
    if (file != null) {
        loadControlDataFromFile(file);
    }
}
       
    /**
 * Returns the control file preference, i.e. the file that was last opened.
 * The preference is read from the OS specific registry. If no such
 * preference can be found, null is returned.
 * 
 * @return
 */
public File getControlFilePath() {
    Preferences prefs = Preferences.userNodeForPackage(SCTMApp.class);
    String filePath = prefs.get("filePath", null);
    if (filePath != null) {
        return new File(filePath);
    } else {
        return null;
    }
}

/**
 * Sets the file path of the currently loaded file. The path is persisted in
 * the OS specific registry.
 * 
 * @param file the file or null to remove the path
 */
public void setControlFilePath(File file) {
    Preferences prefs = Preferences.userNodeForPackage(SCTMApp.class);
    if (file != null) {
        prefs.put("filePath", file.getPath());

        // Update the stage title.
        primaryStage.setTitle("SCTMApp - " + file.getName());
    } else {
        prefs.remove("filePath");

        // Update the stage title.
        primaryStage.setTitle("SCTM App");
    }
}
    /**
 * Shows the control overview inside the root layout.
 */
public void showSCTMOverview() {
    try {
        // Load control overview.
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(SCTMApp.class.getResource("view/SCTMOverview.fxml"));
        AnchorPane controlOverview = (AnchorPane) loader.load();

        // Set control overview into the center of root layout.
        rootLayout.setCenter(controlOverview);

        // Give the controller access to the main app.
        SCTMOverviewController controller = loader.getController();
        controller.setSCTMApp(this);

    } catch (IOException e) {
        e.printStackTrace();
    }
}

/**
 * Opens a dialog to edit details for the specified control. If the user
 * clicks OK, the changes are saved into the provided control object and true
 * is returned.
 * 
 * @param control the control object to be edited
 * @return true if the user clicked OK, false otherwise.
 */
public boolean showControlEditDialog(Control control) {
    try {
        // Load the fxml file and create a new stage for the popup dialog.
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(SCTMApp.class.getResource("view/ControlEditDialog.fxml"));
        AnchorPane page = (AnchorPane) loader.load();

        // Create the dialog Stage.
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Edit Control");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(primaryStage);
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        // Set the control into the controller.
        ControlEditDialogController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setControl(control);

        // Show the dialog and wait until the user closes it
        dialogStage.showAndWait();

        return controller.isOkClicked();
    } catch (IOException e) {
        e.printStackTrace();
        return false;
    }
}

/**
 * Loads control data from the specified file. The current control data will
 * be replaced.
 * 
 * @param file
 */
public void loadControlDataFromFile(File file) {
    
//public void loadControlDataFromFile(File file) {
    try {
        JAXBContext context = JAXBContext.newInstance(ControlListWrapper.class);
        Unmarshaller um = context.createUnmarshaller();
        //System.out.println(um);
        // Reading XML from the file and unmarshalling.
        ControlListWrapper wrapper = (ControlListWrapper) um.unmarshal(file);
        System.out.println();
        controlData.clear();
        controlData.addAll(wrapper.getControls());

        // Save the file path to the registry.
        setControlFilePath(file);

    } catch (Exception e) { // catches ANY exception
        Alert alert = new Alert(AlertType.ERROR);
        System.out.println(e);
        alert.setTitle("Error");
        alert.setHeaderText("Could not load data");
        alert.setContentText("Could not load data from file:\n" + file.getPath());

        alert.showAndWait();
    }
}

/**
 * Saves the current control data to the specified file.
 * 
 * @param file
 */
public void saveControlDataToFile(File file) {
    try {
        JAXBContext context = JAXBContext.newInstance(ControlListWrapper.class); //changed from Control.class to ctrlData.class
        Marshaller m = context.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        // Wrapping our control data.
        ControlListWrapper wrapper = new ControlListWrapper();  //changed from controlListWrapper.java tp ctrlData.java and added appropriate code for wrapping
        wrapper.setControls(controlData);

        // Marshalling and saving XML to the file.
        m.marshal(wrapper, file);
        System.out.println(wrapper);

        // Save the file path to the registry.
        setControlFilePath(file);
    } catch (JAXBException e) { // catches ANY exception
        System.out.print(e);
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Could not save data");
        alert.setContentText("Could not save data to file:\n" + file.getPath());

        alert.showAndWait();
    }
}

    /**
     * Returns the main stage.
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }

}