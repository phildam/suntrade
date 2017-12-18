/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package suntradeapp;

import entities.Setting;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Interpolator;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author g33k5qu4d
 */
public class LoginController implements Initializable {

    @FXML
    private PasswordField password;
    @FXML
    private TextField username;
    @FXML
    private Button login;
    @FXML
    private Label checkInLabel;
    @FXML
    private Color x41;
    @FXML
    private Font x31;
    @FXML
    private AnchorPane ParentAnchorPane;
    @FXML
    private ImageView tribcImage;
    private TextFlow textflow;
    @FXML
    private BorderPane borderpane;
    @FXML
    private AnchorPane acpane;
    private String pWord;
    private String userID;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        login.requestFocus();
        setAccountDetails();
        // TODO
        //  Image bgim = new Image(ClassLoader.getSystemResourceAsStream("images/suntradelogo.png"));
        //  BackgroundImage bgm = new BackgroundImage(bgim, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, new BackgroundSize(Double.MAX_VALUE, Double.MAX_VALUE, true, true, true, true));
        //  ParentAnchorPane.setBackground(new Background(bgm));
        // tribcImage.setImage(im);
        //readTxtAndAnim(txt());
        acpane.setStyle("-fx-background-color:rgba(0,0,0,0.3);\n"
                + "    -fx-background-image:url(icons/whiteBg.png);\n"
                + "    -fx-background-repeat:no-repeat;\n"
                + "    -fx-background-size:contain;\n"
                + "    -fx-background-radius:50;\n"
                + "    -fx-border-radius:50;");

        username.setStyle("-fx-background-radius:50;\n"
                + "    -fx-border-radius:50;\n"
                + "    -fx-border-color:teal;");

        password.setStyle("-fx-background-radius:50;\n"
                + "    -fx-border-radius:50;\n"
                + "    -fx-border-color:teal;");

    }

    public String txt() {
        return "Text textRef = new Text(s);\n"
                + "            textRef.setLayoutY(Origin(VPos.TOP);\\n\" +\n"
                + "\"            textRef.setText100);\n"
                + "            textRef.setTextAlignment(TextAlignment.JUSTIFY);\n"
                + "            textRef.setWrappingWidth(400);\n"
                + "            textRef.setFill(Color.rgb(187, 195, 107));\n"
                + "            textRef.setFont(Font.font(\"SansSerif\", FontWeight.BOLD, 24));\n"
                + "// Provides the animated scrolling behavior for the text\n"
                + "            TranslateTransition transTransition = new TranslateTransition(new Duration(75000), textRef);\n"
                + "            transTransition.setToY(-820);\n"
                + "            transTransition.setInterpolator(Interpolator.LINEAR);\n"
                + "            transTransition.setCycleCount(Timeline.INDEFINITE);\n"
                + "\n"
                + "            typeWriter.getChildren().add(textRef);\n"
                + "    }\n"
                + "\n"
                + "    public void swapMainAnchor(Node node) {\n"
                + "        ParentAnchorPane.getChildren().setAll(node);\n"
                + "    }\n"
                + "\n"
                + "    @FXML\n"
                + "    private void checkValidity(ActionEvent event) {\n"
                + "        boolean userEmpty = username.getText().isEmpty();\n"
                + "        boolean passwordEmpty = password.getText().isEmpty();\n"
                + "        if (userEmpty && passwordEmpty) {\n"
                + "            try {\n"
                + "                swapMainAnchor((Node) FXMLLoader.load(getClass().getResource(SwapperClass.vBoxContentLoader)));\n"
                + "            } catch (IOException ex) {\n"
                + "                checkInLabel.autosize();\n"
                + "                checkInLabel.setText(ex.getMessage());\n"
                + "                ex.printStackTrace();\n"
                + "            } catch (Exception e) {\n"
                + "                e.getCause(); ";
    }

    public void readTxtAndAnim(String s) {

        Text textRef = new Text(s);
        textRef.setLayoutY(100);
        textRef.setTextOrigin(VPos.TOP);
        textRef.setTextAlignment(TextAlignment.JUSTIFY);
        textRef.setWrappingWidth(400);
        textRef.setFill(Color.CORNFLOWERBLUE);

        textRef.setFont(Font.font("SansSerif", FontWeight.BOLD, 8));
// Provides the animated scrolling behavior for the text
        TranslateTransition transTransition = new TranslateTransition(new Duration(75000), textRef);
        transTransition.setToY(-820);
        transTransition.setInterpolator(Interpolator.LINEAR);
        transTransition.setCycleCount(Timeline.INDEFINITE);
        transTransition.setRate(10.0);
        textflow.getChildren().add(textRef);

        transTransition.play();

    }

    public void swapMainAnchor(Node node) {
        ParentAnchorPane.getChildren().setAll(node);
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
    private void checkValidity(ActionEvent event) {
        boolean userEmpty = username.getText().isEmpty();
        boolean passwordEmpty = password.getText().isEmpty();

        if ((username.getText().equals(getUSerID())) && (password.getText().equals(getPWord()))) {
            try {
                swapMainAnchor(loadFxml("AppMain.fxml"));
            } catch (Exception e) {
                e.getCause();
                //System.out.println("Exception error in loginController class");
            }

        } else {
            checkInLabel.setTextFill(Color.RED);
            checkInLabel.setText("Password or username invalid, try again");
            Image im = new Image(getClass().getResourceAsStream("/images/error.png"));
            ImageView iv = new ImageView(im);
            iv.setFitHeight(32);
            iv.setFitWidth(32);
            checkInLabel.setGraphic(iv);
            checkInLabel.setGraphicTextGap(5.0);
        }
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
    private void welcomeTo(MouseEvent event) {
    }

}
