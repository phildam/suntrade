/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package suntradeapp;


import app.ObservableXMLLoader;
import dbPack.CreateDb;
import entities.Databaseset;
import entities.Groups;
import entities.Setting;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author g33k5qu4d
 */
public class SunMain extends Application {

    GroupListModel glm = new GroupListModel();

    @Override
    public void start(Stage primaryStage) throws IOException  {

        initialPreLoads();
        ObservableXMLLoader xmlLoader = new ObservableXMLLoader();
        AppMainModel.setCountries(xmlLoader.addCountries());
       
        FXMLLoader loader = new FXMLLoader();
        Parent root = loader.load(getClass().getResourceAsStream("LoginMain.fxml"));

        Scene scene = new Scene(root);

        primaryStage.setTitle("SunTrade Economic Empowerment Center");
        primaryStage.setScene(scene);
        scene.getStylesheets().add(StyleSheet());
        MainLoader.setScene(scene);
        MainLoader.setStage(primaryStage);
        MainLoader.setFullClose(primaryStage);
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/images/suntrade.ico")));
       // primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/images/suntradelogo.png")));
        primaryStage.show();
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {

            @Override
            public void handle(WindowEvent event) {
              //  com.sun.javafx.application.PlatformImpl.tkExit();
                Platform.exit();
                System.exit(0);
            }
        });
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    public String StyleSheet() {
        UtilityExtractor util = new UtilityExtractor();
        List li = util.queryTable("FROM Setting");
      //   initSettings();
        String styleSheet = STYLESHEET_MODENA;
        if (!li.isEmpty()) {
            for (Object o : li) {
                Setting set = (Setting) o;
                styleSheet = set.getStylesheet();
            }
        }
        return styleSheet;
    }
    
    public void initSettings(){
        UtilityExtractor util = new UtilityExtractor();
        List list = util.queryTable("FROM Setting");
            
            if (list.isEmpty()) {
                SessionFactory factory = HibernateUtil.getSessionFactory();
                Session session = factory.openSession();
                Transaction tx = null;
                try {
                    tx = session.beginTransaction();
                    Setting setting = new Setting("default", "default", null, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, "/suntradeapp/skinDefault.css");
                    session.save(setting);
                    tx.commit();
                } catch (HibernateException e) {
                    if (tx != null) {
                        tx.rollback();
                    }
                } finally {
                    session.close();
                }
            }
    }
    
    public void initDbSettings(){
        UtilityExtractor util = new UtilityExtractor();
        List list = util.queryTable("FROM Databaseset");
            
            if (list.isEmpty()) {
                SessionFactory factory = HibernateUtil.getSessionFactory();
                Session session = factory.openSession();
                Transaction tx = null;
                try {
                    tx = session.beginTransaction();
                    Databaseset setting = new Databaseset(Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE);
                    session.save(setting);
                    tx.commit();
                } catch (HibernateException e) {
                    if (tx != null) {
                        tx.rollback();
                    }
                } finally {
                    session.close();
                }
            }
    }

    public void initialPreLoads() {
        try {
            CreateDb db = new CreateDb();
            CreateDb.createDb("DATABASESET", db.dataBaseSet());
            initDbSettings();
            UtilityExtractor util = new UtilityExtractor();
            List list = util.queryTable("FROM Databaseset");
                
             
           //
              
              

            for (Object o : list) {
                Databaseset dbset = (Databaseset) o;
                if (dbset.getAccount() == false || dbset.getAccount() == null) {
                    CreateDb.createDb("ACCOUNT", db.account());
                }
                 if (dbset.getGroups() == false || dbset.getGroups() == null) {
                    CreateDb.createDb("GROUPS", db.Group());
                }
                if (dbset.getCustomer() == false || dbset.getCustomer() == null) {
                    CreateDb.createDb("CUSTOMER", db.customer());
                }
               
                if (dbset.getLoan() == false || dbset.getLoan() == null) {
                    CreateDb.createDb("LOAN", db.loanAccount());
                }
                if (dbset.getLoanrepayment() == false || dbset.getLoanrepayment() == null) {
                    CreateDb.createDb("LOANREPAYMENT", db.Repayment());
                }
                if (dbset.getTransact() == false || dbset.getTransact() == null) {
                    CreateDb.createDb("TRANSACT", db.Transaction());
                }
                if (dbset.getSettings() == false || dbset.getSettings() == null) {
                    CreateDb.createDb("SETTING", db.setting());
                    initSettings();
                }
                
               
            }

            //CreateDb.createDb("loan", db.loanAccount());
           loadGroup(util);
        } catch (SQLException ex) {
            Logger.getLogger(SunMain.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SunMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void loadGroup(UtilityExtractor util){
        if(GroupListModel.getGroupNames() != null){
        GroupListModel.getGroupNames().clear();
        }
        ObservableList ol = FXCollections.observableArrayList();
            String query = "FROM Groups";
            List<Groups> resultList = util.queryTable(query);
            if(!resultList.isEmpty()){
            for (Object s : resultList) {
                Groups group = (Groups) s;
                ol.add(group.getGroupName());
            }
            }
            
            GroupListModel.SetGroupNames(ol);
    }
}
