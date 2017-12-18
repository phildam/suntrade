/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package suntradeapp;

import dbPack.JDBCUtil;
import entities.Setting;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
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
public class SettingController implements Initializable {

    @FXML
    private TextField oldusername;
    @FXML
    private TextField newUsername;
    @FXML
    private Button changeUsername;
    @FXML
    private PasswordField oldPassword;
    @FXML
    private PasswordField newPassword;
    @FXML
    private Button changePassword;
    @FXML
    private CheckBox skipFormCheck;
    @FXML
    private CheckBox activateSnapShot;
    @FXML
    private CheckBox activateScreenCapture;
    @FXML
    private Label infoLabel;
    @FXML
    private BorderPane borderpane;

    /**
     * Initializes the controller class.
     */
    SettingModel sm = new SettingModel();

    @FXML
    private Button defaultFolder;
    @FXML
    private PasswordField reEnterPass;
    @FXML
    private TextField folderLoc;
    @FXML
    private Tooltip folderTooltip;
    @FXML
    private Button backupDb;
    @FXML
    private Button restore;
    @FXML
    private Button closeSettings;
    @FXML
    private ProgressBar progress;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        progress.setVisible(false);
        borderpane.getStylesheets().add("/suntradeapp/confirmdialog.css");
        borderpane.setStyle("-fx-background-color:rgba(0,0,0,0.3);\n"
                + "    -fx-background-image:url(icons/whiteBg.png);\n"
                + "    -fx-background-repeat:no-repeat;\n"
                + "    -fx-background-size:contain;\n"
                + "    -fx-background-radius:50;\n"
                + "    -fx-border-radius:50;");
        preloaders();
        
        folderLoc.textProperty().bind(new SimpleStringProperty(sm.getPictureurl()));
        folderTooltip.textProperty().bind(new SimpleStringProperty(sm.getPictureurl()));
        SimpleBooleanProperty skippy=new SimpleBooleanProperty(sm.getSkipformcheck());
        skipFormCheck.selectedProperty().bindBidirectional(skippy);
    }

    public void preloaders() {
        UtilityExtractor util = new UtilityExtractor();

        List list = util.queryTable("FROM Setting");
        if (!list.isEmpty()) {
            for (Object o : list) {
                Setting set = (Setting) o;
                sm.setUsername(set.getUsername());
                sm.setPasssword(set.getPassword());
                sm.setPictureurl(set.getPictureurl());
                sm.setScreenshot(set.getScreenshot());
                sm.setSkipformcheck(set.getSkipformcheck());
                sm.setSnapShot(set.getSnapshot());
                sm.setStylesheet(set.getStylesheet());
            }

        }

    }

    

    @FXML
    private void changeUsernameDetails(ActionEvent event) {
        
        String usernameOld = sm.getUsername();
        String oldUsername = oldusername.getText();

        if (usernameOld.equals(oldUsername)) {
            if (newUsername.getText() != null) {
                SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
                Session session = sessionFactory.openSession();

                try {
                    session.beginTransaction();
                    Setting setting = (Setting) session.get(Setting.class, 1);
                    setting.setUsername(newUsername.getText());
                    session.update(setting);
                    session.getTransaction().commit();
                } catch (HibernateException he) {
                    he.printStackTrace();
                }
            }
        }
        preloaders();
    }

    @FXML
    private void changePassword(ActionEvent event) {
        
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        String passwordOld = sm.getPasssword();
        String olDpassword = oldPassword.getText();
        //String renenterNew=reEnterPass.getText();
        if (passwordOld.equals(olDpassword)) {
            if ((newPassword.getText().equals(reEnterPass.getText()))) {

                try {
                    session.beginTransaction();
                    Setting setting = (Setting) session.get(Setting.class, 1);
                    setting.setPassword(newPassword.getText());
                    session.update(setting);
                    session.getTransaction().commit();
                    infoLabel.setText("Password Changed");
                } catch (HibernateException he) {
                    he.printStackTrace();
                }
            }
        }
        preloaders();
    }

    public void boolly(String name, Object val) {
        preloaders();
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            Setting setting = (Setting) session.get(Setting.class, 1);
            switch (name) {
                case "formcheck":
                    setting.setSkipformcheck((Boolean) val);
                    break;
                case "snapshot":
                    setting.setSnapshot((Boolean) val);
                    break;
                case "screenshot":
                    setting.setScreenshot((Boolean) val);
                    break;
                case "pictureurl":
                    setting.setPictureurl((String) val);
                    break;

            }
            session.update(setting);
            session.getTransaction().commit();
        } catch (HibernateException he) {
            he.printStackTrace();
        }
        preloaders();
    }

    @FXML
    private void skipFormCheck(ActionEvent event) {
        boolly("formcheck", skipFormCheck.isSelected());
        //System.out.println(skipFormCheck.isSelected());
        preloaders();
    }

    @FXML
    private void activateSnapShot(ActionEvent event) {
        boolly("snapshot", activateSnapShot.isSelected());
    }

    @FXML
    private void activateScreenCapture(ActionEvent event) {
        boolly("screenshot", activateScreenCapture.isSelected());
    }

    @FXML
    private void setdeffolder(ActionEvent event) {
        Stage stage = new Stage();
        DirectoryChooser directory = new DirectoryChooser();
        directory.setTitle("Choose saved File Dir");
        directory.setInitialDirectory(new File(dir));

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        File chosenFile = directory.showDialog(stage);
        if (chosenFile == null) {
            return;
        }

        try {
            session.beginTransaction();
            Setting setting = (Setting) session.get(Setting.class, 1);
            setting.setPictureurl(chosenFile.getAbsolutePath());
            session.update(setting);
            session.getTransaction().commit();
        } catch (HibernateException he) {
            he.printStackTrace();
        }
        preloaders();
        // this.setSelectedFile(chosenFile);
    }

    @FXML
    private void backupDb(ActionEvent event) {

        try {
            progress.setVisible(true);
            Connection conn = JDBCUtil.getConnection();
            String sqlstmt = "CALL SYSCS_UTIL.SYSCS_BACKUP_DATABASE(?)";
            try (CallableStatement cs = conn.prepareCall(sqlstmt)) {
                cs.setString(1, sm.getPictureurl());
                cs.execute();
                progress.setVisible(false);
                infoLabel.setTextFill(Color.RED);
                infoLabel.setText("BackUp Operation Complete");
            }
//                        AppMainModel.getInstance().getRightStatusBind().setText("BackUp Complete");

        } catch (SQLException | IOException ex) {
            Logger.getLogger(SettingController.class.getName()).log(Level.SEVERE, null, ex);
        }
        preloaders();

    }

    @FXML
    private void restoreDb(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        Parent root = loader.load(getClass().getResourceAsStream("confirmDialog.fxml"));
        Common.setAction("restoreDb");
        Stage st = Common.getStage(root);

        MainLoader.setStage(st);
       
        st.show();
         preloaders();
    }

    @FXML
    private void closeSettings(ActionEvent event) {
        MainLoader.getConfirmDialogStage().close();
    }

}
