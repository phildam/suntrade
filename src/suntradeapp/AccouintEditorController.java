/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package suntradeapp;

import app.ImageOps;
import entities.Account;
import entities.Customer;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
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
public class AccouintEditorController implements Initializable {

    @FXML
    private TextField accountNumberSearch;
    @FXML
    private TextField surname;
    @FXML
    private TextField othernames;
    @FXML
    private ChoiceBox<String> gender;
    @FXML
    private TextField email;
    @FXML
    private ChoiceBox<String> maritalstatus;
    @FXML
    private TextField occupation;
    @FXML
    private TextField phoneNumber;
    @FXML
    private ChoiceBox<String> groupName;
    @FXML
    private TextField address;
    private TextField country;
    @FXML
    private DatePicker dob;
    @FXML
    private TextField state;
    @FXML
    private TextField lga;
    @FXML
    private DatePicker issuingDate;
    @FXML
    private TextField issuingName;
    @FXML
    private ImageView picture;
    @FXML
    private Button updateAccount;
    @FXML
    private Label infoLabel;
    @FXML
    private ListView<String> listDetails;
    private boolean foundit;
    private int custId;
    @FXML
    private Button deleteAccount;
    @FXML
    private Button accStatement;
    @FXML
    private Button deactivateAccount;
    @FXML
    private Button changepic;
    private File selectedFile;
    static String dir = System.getProperty("user.home");
    static Stage stage = new Stage();
    @FXML
    private ChoiceBox<String> groupList;
    @FXML
    private TextField newGroupName;
    @FXML
    private Button updateGroup;
    @FXML
    private TextField createNewGroups;
    @FXML
    private Button createGroup;
    @FXML
    private ChoiceBox<String> groupListdel;
    @FXML
    private Button deleteGroup;
    @FXML
    private Label editExistingGroupLabel;
    @FXML
    private Label createNewLabel;
    @FXML
    private Label delgrouplabel;
    @FXML
    private Pane loadPane;

    ObservableList list = null;
    @FXML
    private VBox vloader;
    @FXML
    private VBox detailsPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        deactivateAccount.setText("Set Status");
        loadSomeInfo();
        // picture.setImage(new Image(getClass().getResourceAsStream("/icons/business_user_edt.png")));
        Common common = new Common();

        list = common.loadGroup();

        groupList.getItems().addAll(list);
        groupList.getSelectionModel().clearSelection();
        groupListdel.getItems().addAll(list);
        groupListdel.getSelectionModel().clearSelection();
    }

    public void loadSomeInfo() {

        AccouintEditorModel.getInstance().setVloader(vloader);
        AccouintEditorModel.getInstance().setLoadPane(loadPane);
        AccouintEditorModel.getInstance().setDetailsPane(detailsPane);
        AccouintEditorModel.getInstance().setAccStatement(accStatement);
        AccouintEditorModel.getInstance().setAccountNumberSearch(accountNumberSearch);
        AccouintEditorModel.getInstance().setAddress(address);
        AccouintEditorModel.getInstance().setCreateGroup(createGroup);
        AccouintEditorModel.getInstance().setCreateNewGroups(createNewGroups);
        AccouintEditorModel.getInstance().setDeactivateAccount(deactivateAccount);
        AccouintEditorModel.getInstance().setDeleteAccount(deleteAccount);
        AccouintEditorModel.getInstance().setInfoLabel(infoLabel);
        AccouintEditorModel.getInstance().setListDetails(listDetails);
        AccouintEditorModel.getInstance().setPicture(picture);
        AccouintEditorModel.getInstance().setDob(dob);
        AccouintEditorModel.getInstance().setSurname(surname);
        AccouintEditorModel.getInstance().setPicture(picture);
        AccouintEditorModel.getInstance().setFoundit(foundit);
        //   sIssuingDate.setValue(LocalDate.now());
        //  sDateOFBirth.setValue(LocalDate.now());
        ObservableList<String> gende = FXCollections.observableArrayList("Male", "Female");
        ObservableList<String> maritals = FXCollections.observableArrayList("Married", "Single", "Divorced");
        ObservableList<String> group = FXCollections.observableArrayList(GroupListModel.getGroupNames());
        gender.getItems().setAll(gende);
        maritalstatus.getItems().setAll(maritals);
        groupName.getItems().setAll(group);

    }

    public boolean isFound() {
        return foundit;
    }

    public void setFound(boolean foundEm) {
        this.foundit = foundEm;
    }

    public int getCustomerId() {
        return custId;
    }

    public void setCustomerId(int d) {
        this.custId = d;
    }

    public void resetForms() {

        for (Node node : detailsPane.getChildren()) {
            if (node.getClass() == TextField.class) {
                TextField tf = (TextField) node;
                tf.clear();
            } else if (node.getClass() == ChoiceBox.class) {
                ChoiceBox cb = (ChoiceBox) node;
                cb.getSelectionModel().select(null);
            } else if (node.getClass() == ImageView.class) {
                ImageView iv = (ImageView) node;
                iv.setImage(new Image(getClass().getResourceAsStream("/icons/noIm.jpg")));
            }
        }
        listDetails.getItems().clear();

    }

    public boolean activeAccount(String accountNumber, UtilityExtractor util) {
        boolean active = true;
        List li = util.queryTable("FROM Account a where a.accountNumber = '" + accountNumber + "'");
        if (!li.isEmpty()) {
            for (Object o : li) {
                Account acc = (Account) o;
                active = acc.getActive();
            }
        }
        return active;
    }
    
    public void refreshList(){
        listDetails.getItems().clear();
        UtilityExtractor util = new UtilityExtractor();
        List li = util.queryTable("FROM Customer c where c.accountNumber = '" + accountNumberSearch.getText() + "'");
        if (!li.isEmpty()) {
            
                for (Object o : li) {
                    Customer c = (Customer) o;
                   
                    listDetails.getItems().add("Address: " + c.getAddress());
                    listDetails.getItems().add("Email: " + c.getEmail());
                    listDetails.getItems().add("Gender: " + c.getGender());
                    listDetails.getItems().add("Group Name: " + c.getGroupName());
                    listDetails.getItems().add("Issuing Name: " + c.getIssuingName());
                    listDetails.getItems().add("L.G.A. : " + c.getLga());
                    listDetails.getItems().add("Marital Status : " + c.getMaritalStatus());
                    listDetails.getItems().add("Occupation : " + c.getOccupation());
                     listDetails.getItems().add("Phone Number : " + c.getPhoneNumber());
                    listDetails.getItems().add("Othernames : " + c.getOtherNames());
                    listDetails.getItems().add("State : " + c.getState());
                    listDetails.getItems().add("Surname : " + c.getSurname());
                    
                }
            }
        }

    @FXML
    private void getaccountDetails(KeyEvent event) {
        setFound(false);
        resetForms();
        picture.setImage(new Image(getClass().getResourceAsStream("/icons/noIm.jpg")));
        UtilityExtractor util = new UtilityExtractor();
        List li = util.queryTable("FROM Customer c where c.accountNumber = '" + accountNumberSearch.getText() + "'");
        if (!li.isEmpty()) {
            setFound(true);
            AccouintEditorModel.getInstance().setFoundit(true);
            if (activeAccount(accountNumberSearch.getText(), util)) {

                AccouintEditorModel.getInstance().setFoundit(true);
                for (Object o : li) {
                    Customer c = (Customer) o;
                    address.setText(c.getAddress());
                    listDetails.getItems().add("Address: " + c.getAddress());
                    //country.setText(c.getCountry());
                    email.setText(c.getEmail());
                    listDetails.getItems().add("Email: " + c.getEmail());
                    gender.getSelectionModel().select(c.getGender());
                    listDetails.getItems().add("Gender: " + c.getGender());
                    groupName.getSelectionModel().select(c.getGroupName());
                    listDetails.getItems().add("Group Name: " + c.getGroupName());
                    issuingName.setText(c.getIssuingName());
                    listDetails.getItems().add("Issuing Name: " + c.getIssuingName());
                    setCustomerId(c.getId());
                    //    issuingDate.setValue(dtoLD(c.getIsuingDate()));
                    lga.setText(c.getLga());
                    listDetails.getItems().add("L.G.A. : " + c.getLga());
                    maritalstatus.getSelectionModel().select(c.getMaritalStatus());
                    listDetails.getItems().add("Marital Status : " + c.getMaritalStatus());
                    occupation.setText(c.getOccupation());
                    listDetails.getItems().add("Occupation : " + c.getOccupation());
                    phoneNumber.setText(c.getPhoneNumber());
                    listDetails.getItems().add("Phone Number : " + c.getPhoneNumber());
                    othernames.setText(c.getOtherNames());
                    listDetails.getItems().add("Othernames : " + c.getOtherNames());
                    String pictureurl = c.getPictureurl();
                    if (pictureurl != null) {
                        Image im = null;
                        InputStream fis = null;
                        try {

                            if (new File(pictureurl).exists()) {
                                fis = new FileInputStream(new File(pictureurl));
                            } else {
                                fis = getClass().getResourceAsStream("/icons/noIm.jpg");
                            }
                            im = new Image(fis);
                            picture.setImage(im);
                        } catch (FileNotFoundException ex) {
                            Logger.getLogger(AccouintEditorController.class.getName()).log(Level.SEVERE, null, ex);
                        } finally {
                            if (fis != null) {
                                try {
                                    fis.close();
                                } catch (IOException ex) {
                                    Logger.getLogger(AccouintEditorController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                        }
                    }
                    state.setText(c.getState());
                    listDetails.getItems().add("State : " + c.getState());
                    surname.setText(c.getSurname());
                    //dob.setValue(LocalDate.parse(dir));
                   
                    listDetails.getItems().add("Surname : " + c.getSurname());
                    deactivateAccount.setText("De-activate");
                    infoLabel.setText("");
                }
            } else {
                deactivateAccount.setText("Re-activate");
                infoLabel.setStyle("-fx-text-fill:red; -fx-font: italic bold 16 sans-serif;");
                infoLabel.setText("Deactivated Account");
            }
        } else {
            resetForms();
            infoLabel.setText("");
        }
    }

    public LocalDate dtoLD(Date date) {
        Instant instant = date.toInstant();
        ZonedDateTime zdt = instant.atZone(ZoneId.systemDefault());
        LocalDate ld = zdt.toLocalDate();
        return ld;
    }

    @FXML
    private void doAccountUpdate(ActionEvent event) throws IOException {

        if (isFound()) {
            SessionFactory sessionfactory = HibernateUtil.getSessionFactory();
            Session session = sessionfactory.openSession();

            try {
                session.beginTransaction();
                Customer customer = (Customer) session.get(Customer.class, getCustomerId());
                customer.setAddress(address.getText());
//                customer.setCountry(country.getText());
                customer.setEmail(email.getText());
                customer.setGender(gender.getValue());
                customer.setGroupName(groupName.getValue());
                customer.setLga(lga.getText());
                customer.setMaritalStatus(maritalstatus.getValue());
                customer.setOccupation(occupation.getText());
                customer.setOtherNames(othernames.getText());
                customer.setPhoneNumber(phoneNumber.getText());
                customer.setState(state.getText());
                customer.setSurname(surname.getText());

                if(dob.getValue() != null){
                java.sql.Date birthday = java.sql.Date.valueOf(dob.getValue());
                customer.setDob(birthday);
                }
                if(issuingDate.getValue() != null){
                java.sql.Date issuingDay = java.sql.Date.valueOf(issuingDate.getValue());
                customer.setIsuingDate(issuingDay);
                }
                //set dates also
                session.update(customer);
                session.getTransaction().commit();
                 refreshList();
                 infoLabel.setTextFill(Color.RED);
                infoLabel.setText("Update Successfull");
                //System.out.println("Update complete");
            } catch (HibernateException he) {
                he.printStackTrace();
            }

        }
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

    @FXML
    private void deleteAccount(ActionEvent event) throws IOException {
        if (isFound()) {

            FXMLLoader loader = new FXMLLoader();
            Parent root = loader.load(getClass().getResourceAsStream("confirmDialog.fxml"));

            Stage st = getStage(root);
            MainLoader.setConfirmDialogStage(st);
            st.show();
            Common.setAction("delete");
            resetForms();
        } else {
            infoLabel.setStyle("-fx-text-fill:red; -fx-font: italic bold 16 sans-serif;");
            infoLabel.setText("Invalid Values passed to app");
        }
    }

    @FXML
    private void getAccountStatement(ActionEvent event) {

    }

    @FXML
    private void AccountDeactivator(ActionEvent event) throws IOException {
        if (isFound()) {

            FXMLLoader loader = new FXMLLoader();
            Parent root = loader.load(getClass().getResourceAsStream("confirmDialog.fxml"));
            Common.setAction("resetAccount");
            Stage st = getStage(root);
            MainLoader.setConfirmDialogStage(st);

            st.show();

        } else {
            infoLabel.setStyle("-fx-text-fill:red; -fx-font: italic bold 16 sans-serif;");
            infoLabel.setText("Invalid Values passed to app");
        }
    }

    @FXML
    private void changePicture(ActionEvent event) throws IOException {
        if (isFound()) {
            try {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Choose Image");
                fileChooser.setInitialDirectory(new File(dir));
                fileChooser.getExtensionFilters().addAll(
                        new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));

                File chosenFile = fileChooser.showOpenDialog(stage);
                if (chosenFile == null) {
                    return;
                }
                this.setSelectedFile(chosenFile);

                picture.setImage(new Image(new FileInputStream(selectedFile)));

            } catch (IOException ex) {
                Logger.getLogger(AccountManagerController.class.getName()).log(Level.SEVERE, null, ex);
            }
            String filename = saveFile(getSelectedFile(), accountNumberSearch.getText());

            SessionFactory sessionfactory = HibernateUtil.getSessionFactory();
            Session session = sessionfactory.openSession();

            try {
                if (filename != null) {
                    session.beginTransaction();
                    Customer customer = (Customer) session.get(Customer.class, getCustomerId());
                    customer.setPictureurl(filename);
                    session.update(customer);
                    session.getTransaction().commit();
                }
            } catch (HibernateException he) {
                he.printStackTrace();
            }

        }
    }

    public String saveFile(File fileName, String saveAs) throws IOException {
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        FileInputStream fis = null;
        FileOutputStream fos = null;
        String fileSaveLocation = null;
        try {

            fis = new FileInputStream(fileName);
            bis = new BufferedInputStream(fis);
            String ext = null;
            File saveLoc = null;
            File saveFile = null;
            if (getSelectedFile() != null) {
                int pointLoc = getSelectedFile().getAbsolutePath().lastIndexOf(".");
                ext = getSelectedFile().getAbsolutePath().substring(pointLoc);
                saveLoc = new File(dir + File.separator + ".res appfolder" + File.separator);
                saveFile = new File(saveLoc + File.separator + saveAs + ext);
                fileSaveLocation = saveLoc + File.separator + saveAs + ext;
                if (!saveLoc.exists()) {
                    saveLoc.mkdirs();
                }
                if (!saveFile.exists()) {
                    saveFile.createNewFile();
                }
            }
            fos = new FileOutputStream(saveFile);
            bos = new BufferedOutputStream(fos);
            int i = 0;
            int c;
            while ((c = bis.read()) != -1) {

                bos.write(c);
                // System.out.print(c);
                i++;
            }

            // System.out.println("byteLength: "+i);
            bos.flush();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(ImageOps.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (fis != null) {
                fis.close();
            }
            if (bis != null) {
                bis.close();
            }
            if (bos != null) {
                bos.close();
            }
            if (fos != null) {
                fos.close();
            }

        }
        return fileSaveLocation;
    }

    private File getSelectedFile() {
        return selectedFile;
    }

    private void setSelectedFile(File file) {
        this.selectedFile = file;
    }

    @FXML
    private void editExistingGroup(ActionEvent event) {

        String selectedItem = groupList.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            try {
                UtilityExtractor util = new UtilityExtractor();
                util.updateGroup(selectedItem, newGroupName.getText());
                editExistingGroupLabel.setText(selectedItem + " group updated to " + newGroupName.getText());
            } catch (SQLException ex) {
                Logger.getLogger(AccouintEditorController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(AccouintEditorController.class.getName()).log(Level.SEVERE, null, ex);
            }
            Common cmon = new Common();
            groupList.getItems().clear();
            groupList.getItems().addAll(cmon.loadGroup());

            groupListdel.getItems().clear();
            groupListdel.getItems().addAll(cmon.loadGroup());
        }
        reload();
    }

    @FXML
    private void createNewGroup(ActionEvent event) {

        //Long time=System.currentTimeMillis();
        String text = createNewGroups.getText();
        if (text != null) {
            UtilityExtractor util = new UtilityExtractor();
            util.createGroup(text);
            createNewLabel.setText(text + " group Created");

            Common cmon = new Common();
            groupList.getItems().clear();
            groupList.getItems().addAll(cmon.loadGroup());

            groupListdel.getItems().clear();
            groupListdel.getItems().addAll(cmon.loadGroup());

        }
        reload();
    }

    @FXML
    private void deleteGroup(ActionEvent event) {

        SelectionModel<String> newValue = groupListdel.getSelectionModel();
        if (newValue.getSelectedItem() != null) {
            String value = newValue.getSelectedItem();
            UtilityExtractor util = new UtilityExtractor();
            util.deleteGroup(value);
            delgrouplabel.setText(newValue.getSelectedItem() + " group deleted");

            Common cmon = new Common();
            groupList.getItems().clear();
            groupList.getItems().addAll(cmon.loadGroup());

            groupListdel.getItems().clear();
            groupListdel.getItems().addAll(cmon.loadGroup());
        }
        reload();
    }

    public void reload() {

        Common cmon = new Common();
        GroupListModel.getGroupNames().clear();
        GroupListModel.SetGroupNames(cmon.loadGroup());
        ObservableList list = GroupListModel.getGroupNames();
        //  groupList.getItems().addAll(list);
        groupList.getSelectionModel().clearSelection();
        // groupListdel.getItems().addAll(list);
        groupListdel.getSelectionModel().clearSelection();
        ObservableList children = loadPane.getChildren();
        for (Object o : children) {
            if (o.getClass() == TextField.class) {
                TextField tf = (TextField) o;
                tf.setText(null);
                //  tf.clear();
            } else if (o.getClass() == Label.class) {
                Label l = (Label) o;
                // l.setText(null);

            }

        }
        vloader.getChildren().clear();
        vloader.getChildren().add(loadPane);
    }

}
