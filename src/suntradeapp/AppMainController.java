/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package suntradeapp;

import ComponentUpdaterClass.UnitComponentHandler;
import entities.Setting;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.Border;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * FXML Controller class
 *
 * @author g33k5qu4d
 */
public class AppMainController implements Initializable {

    @FXML
    private VBox mainVBox;
    @FXML
    private MenuBar menuBar;
    @FXML
    private Menu fileMenuItem;
    @FXML
    private Menu helpMenuItem;

    @FXML
    private AnchorPane AppLoader;
    @FXML
    private Color x41;
    @FXML
    private Font x31;
    @FXML
    private Label rigthstatusLabel;
    private final UnitComponentHandler loader = new UnitComponentHandler();
    @FXML
    private Button accountOpener;
    @FXML
    private Button loanApplicator;
    private Button generalAccViewer;
    @FXML
    private Button accountEditor;
    @FXML
    private Button GroupCreator;
    @FXML
    private Button transactionViwer;
    @FXML
    private Button openAccount;
    @FXML
    private Button applyForLoan;
    @FXML
    private Button makeTransactions;
    @FXML
    private Button accountEditor1;
    @FXML
    private Button groupView;
    @FXML
    private MenuItem print;
    @FXML
    private MenuItem preference;
    @FXML
    private MenuItem quit;
    @FXML
    private CheckMenuItem defaultSkin;
    @FXML
    private CheckMenuItem skinBlue;
    @FXML
    private CheckMenuItem skinRed;
    @FXML
    private MenuItem regMode;
    @FXML
    private MenuItem mode5;
    @FXML
    private MenuItem traditor;
    @FXML
    private MenuItem aboutSteec;
    @FXML
    private CheckMenuItem skinTeal;
    @FXML
    private CheckMenuItem skinWhite;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        rigthstatusLabel.setText(null);
        AppMainModel.getInstance().setLeftStatusBind(rigthstatusLabel);
        loader.loadToUnit();
        // fxmlLoader();
        
        loadAllButtonsGraphics();
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
            Logger.getLogger(AppMainController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return nod;
    }

    @FXML
    private void AccountEditor(ActionEvent event) {
        Node grpm = loadFxml("AccouintEditor.fxml");
        AppLoader.getChildren().setAll(grpm);
    }

    @FXML
    private void createGroup(ActionEvent event) {
        Node grpm = loadFxml("GroupList.fxml");
        AppLoader.getChildren().setAll(grpm);
    }

    @FXML
    private void loadTransaction(ActionEvent event) {
        Node grpm = loadFxml("AccountTransaction.fxml");
        AppLoader.getChildren().setAll(grpm);
    }

    @FXML
    private void OpenNewAccount(ActionEvent event) {
        Node acc = loadFxml("AccountManager.fxml");
        AppLoader.getChildren().setAll(acc);

    }

    @FXML
    private void ApplyForLoan(ActionEvent event) {
        Node grpm = loadFxml("Credit.fxml");
        AppLoader.getChildren().setAll(grpm);
    }

    @FXML
    private void openAccount(ActionEvent event) {
        Node acc = loadFxml("AccountManager.fxml");
        AppLoader.getChildren().setAll(acc);
    }

    @FXML
    private void applyForLoan(ActionEvent event) {
        Node grpm = loadFxml("Credit.fxml");
        AppLoader.getChildren().setAll(grpm);
    }

    @FXML
    private void makeTransactions(ActionEvent event) {
        Node grpm = loadFxml("AccountTransaction.fxml");
        AppLoader.getChildren().setAll(grpm);
    }

    @FXML
    private void accountEditor(ActionEvent event) {
        Node grpm = loadFxml("AccouintEditor.fxml");
        AppLoader.getChildren().setAll(grpm);
    }

    @FXML
    private void groupView(ActionEvent event) {
        Node grpm = loadFxml("GroupList.fxml");
        AppLoader.getChildren().setAll(grpm);

    }

    public void setButtonGraphics(Button but, ImageView iv, InputStream url, String toolTextTip) {
        iv.setImage(new Image(url));
        Tooltip tp = new Tooltip(toolTextTip);

        iv.setFitHeight(28.0);
        iv.setFitWidth(28.0);
        iv.setPreserveRatio(true);

        but.setTooltip(tp);
        but.setBackground(Background.EMPTY);
        // but.setOpaqueInsets(Insets.EMPTY);
        // but.setPadding(Insets.EMPTY);
        but.setBorder(Border.EMPTY);
        // but.setBackground(Background.EMPTY);
        but.setText(null);
        but.autosize();
        but.setGraphic(iv);

    }

    public ImageView getIm() {
        return new ImageView();
    }

    public void loadAllButtonsGraphics() {
        setButtonGraphics(accountOpener, getIm(), imageStreamer("icons/openaccount.png"), "Open new account");
        setButtonGraphics(loanApplicator, getIm(), imageStreamer("icons/loan.png"), "loan application");
//        setButtonGraphics(generalAccViewer, getIm(), imageStreamer("images/viewAllAccount.png"), "View all Account");
        setButtonGraphics(GroupCreator, getIm(), imageStreamer("icons/group.png"), "Group View");
        setButtonGraphics(transactionViwer, getIm(), imageStreamer("icons/transaction.png"), "Perform all Transactions here");
        setButtonGraphics(accountEditor, getIm(), imageStreamer("icons/editAccount.png"), "Edit accounts Details");
    }

    public InputStream imageStreamer(String path) {
        return ClassLoader.getSystemResourceAsStream(path);
    }

    public void updateUserSetings(String name, Object value) {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            Setting settings = (Setting) session.get(Setting.class, 1);
            switch (name) {
                case "password":
                    settings.setPassword(value.toString());
                    session.update(settings);
                    break;
                case "username":
                    settings.setUsername(value.toString());
                    session.update(settings);
                    break;
                case "pictureurl":
                    settings.setPictureurl(value.toString());
                    session.update(settings);
                    break;
                case "screenshot":
                    settings.setScreenshot((Boolean) value);
                    session.update(settings);
                    break;
                case "skipformcheck":
                    settings.setSkipformcheck((Boolean) value);
                    session.update(settings);
                    break;
                case "snapshot":
                    settings.setSnapshot((Boolean)value);
                    session.update(settings);
                    break;
                case "stylesheet":
                    settings.setStylesheet((String)value);
                    session.update(settings);
                    break;
            }

            session.getTransaction().commit();
        } catch (HibernateException he) {
            he.printStackTrace();
        }
    }

    @FXML
    private void getPrinterJob(ActionEvent event) {
        PrinterJob job = PrinterJob.createPrinterJob();
        if (job != null) {
            boolean success = job.printPage(AppLoader);
            if (success) {
                job.endJob();
                rigthstatusLabel.setText("Page Printing Successful");
            }
        }
    }

    @FXML
    private void setPreference(ActionEvent event) throws IOException {

        Stage stage = getStage("setting.fxml");
       
        MainLoader.setConfirmDialogStage(stage);
        stage.show();

    }

    public Stage getStage(String filename) throws IOException {

        FXMLLoader loaded = new FXMLLoader();
        Parent root = loaded.load(getClass().getResourceAsStream(filename));

        Scene scene = new Scene(root, 1366, 768);
        scene.setFill(Color.web("black", 0.3));
       
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.setIconified(false);
        
        stage.setScene(scene);
         stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.TRANSPARENT);
        return stage;

    }

    @FXML
    private void quitApp(ActionEvent event) {
        //MainLoader.getStage().close();
      //  com.sun.javafx.application.PlatformImpl.tkExit();
        Platform.exit();
        System.exit(0);
    }

    @FXML
    private void loanDefaultSkin(ActionEvent event) {
        MainLoader.getScene().getStylesheets().clear();
        MainLoader.getScene().getStylesheets().add("/suntradeapp/skinDefault.css");
        updateUserSetings("stylesheet", "/suntradeapp/skinDefault.css");
    }

    @FXML
    private void loadSkinBlue(ActionEvent event) {
        MainLoader.getScene().getStylesheets().clear();
        MainLoader.getScene().getStylesheets().add("/suntradeapp/skinblue.css");
        updateUserSetings("stylesheet", "/suntradeapp/skinblue.css");
    }

    @FXML
    private void loadSkinTeal(ActionEvent event) {
        MainLoader.getScene().getStylesheets().clear();
        MainLoader.getScene().getStylesheets().add("/suntradeapp/skinTeal.css");
        updateUserSetings("stylesheet", "/suntradeapp/skinTeal.css");
    }

    @FXML
    private void loadSkinRed(ActionEvent event) {
        MainLoader.getScene().getStylesheets().clear();
        MainLoader.getScene().getStylesheets().add("/suntradeapp/skinRed.css");
        updateUserSetings("stylesheet", "/suntradeapp/skinRed.css");
    }

    @FXML
    private void loadRegMode(ActionEvent event) {
        Node grpm = loadFxml("RegMode.fxml");
        AppLoader.getChildren().setAll(grpm);
    }

    @FXML
    private void loadMode5(ActionEvent event) {
        Node grpm = loadFxml("Mode5.fxml");
        AppLoader.getChildren().setAll(grpm);
       
    }

    @FXML
    private void loadTraditor(ActionEvent event) {

         Node grpm = loadFxml("RegMode.fxml");
        AppLoader.getChildren().setAll(grpm);

    }


    @FXML
    private void loadAboutSteec(ActionEvent event) throws IOException {
        Stage stage = getStage("AboutMe.fxml");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.TRANSPARENT);
        MainLoader.setConfirmDialogStage(stage);
        stage.show();
    }


    @FXML
    private void loadSkinWhite(ActionEvent event) {
         MainLoader.getScene().getStylesheets().clear();
        MainLoader.getScene().getStylesheets().add("/suntradeapp/skinWhite.css");
        updateUserSetings("stylesheet", "/suntradeapp/skinWhite.css");
    }

}
