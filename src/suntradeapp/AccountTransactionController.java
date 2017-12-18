/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package suntradeapp;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author g33k5qu4d
 */
public class AccountTransactionController implements Initializable {

    @FXML
    private AnchorPane loadSavingsInformation;
    @FXML
    private Tab accountRecord;
    @FXML
    private AnchorPane accountRecordPane;
   
    private AnchorPane groupinfomatics;
    @FXML
    private ImageView customerImage;
    @FXML
    private TextField loanId;
    @FXML
    private TextField surname;
    @FXML
    private TextField othername;
    @FXML
    private TextField groupname;
    @FXML
    private TextField accountbalance;
    @FXML
    private TextField loanBalance;
    @FXML
    private TextField entityAccountNumber;
    @FXML
    private Button get;
    @FXML
    private Label getInformation;
    @FXML
    private ProgressIndicator progress;
    @FXML
    private DatePicker paymentDate;
    @FXML
    private TextField installmentNumber;
    @FXML
    private TextField repaymentAmount;
    @FXML
    private Button payLoanButton;
    @FXML
    private ListView<?> loanList;
    @FXML
    private PieChart chart;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        accountRecordPane.getChildren().add(loadFxml("SunTradeTransaction.fxml"));
        
      
    }
    public Node loadFxml(String file) {
        Node nod = null;
        try {
            nod = FXMLLoader.load(getClass().getResource(file));

            AnchorPane.setLeftAnchor(nod, 0.0);
            AnchorPane.setRightAnchor(nod, 0.0);
            AnchorPane.setBottomAnchor(nod, 0.0);
            AnchorPane.setTopAnchor(nod, 0.0);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return nod;
    }
    @FXML
    private void checkAccountRecord(Event event) {

    }

    @FXML
    private void checkDigit(KeyEvent event) {
    }

    @FXML
    private void getAccountDetails(ActionEvent event) {
    }

}
