/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package suntradeapp;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author g33k5qu4d
 */
public class TransactionViewController implements Initializable {
    @FXML
    private AnchorPane transact;
    @FXML
    private AnchorPane editor;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        Node node1=loadFxml("SunTradeTransaction.fxml");
        transact.getChildren().setAll(node1);
   
        Node node2=loadFxml("AccouintEditor.fxml");
        editor.getChildren().setAll(node2);  
       
    }    
    
     public Node loadFxml(String file){
        Node nod =null;
        try {
            nod= FXMLLoader.load(getClass().getResource(file));
            
            AnchorPane.setLeftAnchor(nod, 0.0);
            AnchorPane.setRightAnchor(nod, 0.0);
            AnchorPane.setBottomAnchor(nod, 0.0);
            AnchorPane.setTopAnchor(nod, 0.0);
            
        } catch (IOException ex) {
            Logger.getLogger(AppMainController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return nod;
    }


}
