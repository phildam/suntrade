/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package suntradeapp;

import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author g33k5qu4d
 */
public class MainLoader {
    private static Stage stage;
    private static Stage confirmDialogStage;
    private static Scene scene;
    private static Stage fullClose;
    
    public static void setStage(Stage sta){
        stage=sta;
    }
    
    public static Stage getStage(){
        return stage;
    }
    
    public static void setScene(Scene scen){
        scene=scen;
    }
    public static Scene getScene(){
        return scene;
    }
    
    public static void setConfirmDialogStage(Stage sta){
        confirmDialogStage=sta;
    }
    
    public static Stage getConfirmDialogStage(){
        return confirmDialogStage;
    }

    public static Stage getFullClose() {
        return fullClose;
    }

    public static void setFullClose(Stage aFullClose) {
        fullClose = aFullClose;
    }
    
}
