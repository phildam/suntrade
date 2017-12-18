/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package suntradeapp;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author g33k5qu4d
 */
public class AboutMeController implements Initializable {
    @FXML
    private AnchorPane g33k;
    @FXML
    private Button closeButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        g33k.setStyle("-fx-background-image: url(/images/PHILCORP.PNG);");
        // TODO
    }    

    @FXML
    private void closeStage(ActionEvent event) {
       MainLoader.getConfirmDialogStage().close();
    }
    
}
