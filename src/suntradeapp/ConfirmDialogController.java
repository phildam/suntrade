/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package suntradeapp;

import dbPack.JDBCUtil;
import entities.Account;
import entities.Setting;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import static suntradeapp.AccouintEditorController.dir;

/**
 * FXML Controller class
 *
 * @author g33k5qu4d
 */
public class ConfirmDialogController implements Initializable {

    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private BorderPane borderPane;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Button confirmAction;
    @FXML
    private Button closeButton;
    @FXML
    private Label infoLabel;

    private String userID;
    private String pWord;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ConfirmDialogModel.getInstance().setCloseButton(closeButton);
        ConfirmDialogModel.getInstance().setInfoLabel(infoLabel);
        ConfirmDialogModel.getInstance().setPassword(password);
        ConfirmDialogModel.getInstance().setUsername(username);

        borderPane.setStyle("-fx-background-color:transparent;");
        anchorPane.setStyle("-fx-background-color:rgba(0,0,0,0.3);\n"
                + "    -fx-background-image:url(icons/whiteBg.png);\n"
                + "    -fx-background-repeat:no-repeat;\n"
                + "    -fx-background-size:contain;\n"
                + "    -fx-background-radius:50;\n"
                + "    -fx-border-radius:50;");

        username.setStyle("-fx-background-radius:10;\n"
                + "    -fx-border-radius:10;\n"
                + "    -fx-border-color:teal;");

        password.setStyle("-fx-background-radius:10;\n"
                + "    -fx-border-radius:10;\n"
                + "    -fx-border-color:teal;");

        confirmAction.setStyle("-fx-background-radius:50;\n"
                + "    -fx-border-radius:50;\n"
                + "    -fx-border-color:teal;");
        setAccountDetails();
    }

    public void setPWord(String pass) {
        this.pWord = pass;
    }

    public String getPWord() {
        return this.pWord;
    }

    public void SetUserID(String UID) {
        this.userID = UID;
    }

    public String getUSerID() {
        return this.userID;
    }

    @FXML
    private void confirmAction(ActionEvent event) throws SQLException {
        // SettingBean bean=new SettingBean();
        boolean userEmpty = username.getText().isEmpty();
        boolean passwordEmpty = password.getText().isEmpty();
        if (!userEmpty && !passwordEmpty) {

            if ((username.getText().equals(getUSerID())) && (password.getText().equals(getPWord()))) {
                if (Common.getAction().equalsIgnoreCase("restoreDb")) {
                    Stage stage = new Stage();
                    DirectoryChooser directory = new DirectoryChooser();
                    directory.setTitle("Choose saved File Dir");
                    directory.setInitialDirectory(new File(dir));

                    File chosenFile = directory.showDialog(stage);
                    if (chosenFile == null) {

                        MainLoader.getStage().close();
                        return;
                    }

                    String dbURL = JDBCUtil.getUrl()+";restoreFrom=" + chosenFile;
                    DriverManager.getConnection(JDBCUtil.getUrl()+";shutdown=true", "suntrade", "suntrade");
                   // System.out.println(dbURL);
                    
                        try {
                            Connection conn = DriverManager.getConnection(dbURL, "suntrade", "suntrade");
                            //conn.createStatement();
                            if (conn != null) {
                              
                                
                                infoLabel.setText("Database Backup restore initiated");
                                infoLabel.setTextFill(Color.GREEN);
                                confirmAction.setDisable(true);
                            }else{
                                infoLabel.setText("initializtion failed");
                                infoLabel.setTextFill(Color.RED);
                                confirmAction.setDisable(false);
                            }
                            
                        } catch (SQLException ex) {
                            Logger.getLogger(ConfirmDialogController.class.getName()).log(Level.SEVERE, null, ex);
                             MainLoader.getStage().close();
                            MainLoader.getConfirmDialogStage().close();
                        MainLoader.getFullClose().close();
                        }
                        
                       
                    
                }
                if (AccouintEditorModel.getInstance().isFoundit()) {
                    String action = Common.getAction();
                    String accNo = AccouintEditorModel.getInstance().getAccountNumberSearch().getText();
                    UtilityExtractor util = new UtilityExtractor();
                    switch (action) {

                        case "delete":
                            if (accNo != null) {
                                System.out.println(accNo); //.getAccountNumber());
                                loadem(accNo);
                            }
                            break;
                        case "resetAccount":

                            if (Common.activeAccount(accNo, util)) {
                                util.updateAccountActiveStatus(accNo, false);

                                labelInfo("Account has been Deactivated", "/images/passed.png");
                                infoLabel.setTextFill(Color.RED);
                                confirmAction.setDisable(true);
                            } else {
                                util.updateAccountActiveStatus(accNo, true);
                                labelInfo("Account has been Reactivated", "/images/passed.png");
                                infoLabel.setTextFill(Color.GREEN);

                                confirmAction.setDisable(true);
                            }
                            break;

                    }

                }

            } else {
                labelInfo("Password or username invalid, try again", "/images/error.png");

            }
        } else if (userEmpty || passwordEmpty) {
            labelInfo("All fields must be filled", "/images/error.png");

        }
    }

    public void loadem(String accNo) {

        SessionFactory sessionfactory = HibernateUtil.getSessionFactory();
        Session session = sessionfactory.openSession();
        session.beginTransaction();
        try {

            Account account = (Account) session.get(Account.class, accNo);
            session.delete(account);
            session.getTransaction().commit();
            Label label = AccouintEditorModel.getInstance().getInfoLabel();
            label.setStyle("-fx-text-fill:red; -fx-font: italic bold 16 sans-serif;");
            infoLabel.setText("Account has been Deleted");
            label.setText("Account has been deleted");
            confirmAction.setDisable(true);
        } catch (HibernateException he) {
            he.printStackTrace();
        }
//           AccouintEditorModel.getInstance().getConfirmStage().close();
    }

    public void resetForms() {

        for (Node node : AccouintEditorModel.getInstance().getDetailsPane().getChildren()) {
            if (node.getClass() == TextField.class) {
                TextField tf = (TextField) node;
                tf.clear();
            } else if (node.getClass() == ChoiceBox.class) {
                ChoiceBox cb = (ChoiceBox) node;
                cb.getSelectionModel().select(null);
            } else if (node.getClass() == ImageView.class) {
                ImageView iv = (ImageView) node;
                iv.setImage(new Image(getClass().getResourceAsStream("/icons/noIm.jpg")));
            } else if (node.getClass() == Label.class) {
                Label label = (Label) node;
                label.setText("");
            }
        }
        AccouintEditorModel.getInstance().getPicture().setImage(new Image(getClass().getResourceAsStream("/icons/noIm.jpg")));
        AccouintEditorModel.getInstance().getListDetails().getItems().clear();
        AccouintEditorModel.getInstance().getAccountNumberSearch().clear();

    }

    public void labelInfo(String txt, String url) {
        infoLabel.setTextFill(Color.RED);
        infoLabel.setText(txt);
        Image im = new Image(getClass().getResourceAsStream(url));
        ImageView iv = new ImageView(im);
        iv.setFitHeight(32);
        iv.setFitWidth(32);
        infoLabel.setGraphic(iv);
        infoLabel.setGraphicTextGap(5.0);
    }

    public void setAccountDetails() {
        UtilityExtractor util = new UtilityExtractor();
        List li = util.queryTable("FROM Setting");
        if (!li.isEmpty()) {
            for (Object o : li) {
                Setting settings = (Setting) o;
                SetUserID(settings.getUsername());
                setPWord(settings.getPassword());
            }
        }
    }

    @FXML
    private void closeWindow(ActionEvent event) throws IOException {
        // Common.getStage().close();
        //  String accNo = AccouintEditorModel.getInstance().getAccountNumberSearch().getText();

        if ("resetAccount".equals(Common.getAction())) {
            resetForms();
            AccouintEditorModel.getInstance().getVloader().getChildren().clear();
            AccouintEditorModel.getInstance().getVloader().getChildren().add(AccouintEditorModel.getInstance().getLoadPane());
        }

        if (Common.getAction().equals("restoreDb")) {
            MainLoader.getStage().close();
        } else {
            MainLoader.getConfirmDialogStage().close();
        }
        //AccouintEditorModel.getInstance().getAccountNumberSearch().setText(accNo);
    }

    @FXML
    private void pass(ActionEvent event) {
    }

}
