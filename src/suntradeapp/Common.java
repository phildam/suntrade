/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package suntradeapp;

import app.FileDater;
import entities.Account;
import entities.Groups;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.time.Month;
import java.time.Year;
import java.time.format.TextStyle;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.Border;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author g33k5qu4d
 */
public class Common {
    private static String actio;

    public static String stringedDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        Month month = FileDater.getMonth(cal);
        int day = FileDater.getDayInMOnth(cal);
        Year year = FileDater.getyear(cal);
        String dateString = month.getDisplayName(TextStyle.FULL, Locale.ENGLISH) + " " + day + ", " + year.getValue();
        return dateString;

    }

    public static String formatDouble(double d) {
        return String.format("%,.2f", d);
    }

    public static Stage getStage(Parent root) throws IOException {

        Scene scene = new Scene(root, 1366, 768);
        scene.setFill(Color.web("black", 0.3));
        Stage stage = new Stage();

        stage.setResizable(false);
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.TRANSPARENT);
        return stage;

    }
    public static void setButtonGraphics(Button but, ImageView iv, InputStream url, String toolTextTip) {
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

    public static InputStream imageStreamer(String path) {
        return ClassLoader.getSystemResourceAsStream(path);
    }

    public ObservableList loadGroup() {
        UtilityExtractor util = new UtilityExtractor();
        ObservableList ol = FXCollections.observableArrayList();
        String query = "FROM Groups";
        List resultList = util.queryTable(query);
        if (!resultList.isEmpty()) {
            for (Object s : resultList) {
                Groups group = (Groups) s;
                ol.add(group.getGroupName());
            }
        }

        return ol;
    }
    
    public static boolean activeAccount(String accountNumber, UtilityExtractor util) {
        boolean active = true;
        List li = util.queryTable("FROM Account a where a.accountNumber = '" +accountNumber + "'");
        if (!li.isEmpty()) {
            for (Object o : li) {
                Account acc = (Account) o;
                active = acc.getActive();
            }
        }
        return active;
    }
    
    public static void setAction(String action){
       actio=action;
    }
    
    public static String getAction(){
        return actio;
    }

}
