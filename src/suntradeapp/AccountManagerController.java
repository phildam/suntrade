/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package suntradeapp;

import app.ImageOps;
import app.ModelListener;
import app.ObservableXMLLoader;
import app.Rocket;
import entities.Setting;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
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
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author g33k5qu4d
 */
public class AccountManagerController implements Initializable {

    @FXML
    private Button saveInfo;
    @FXML
    private Label errorLabel;
    @FXML
    private VBox formVBox1;
    @FXML
    private TextField sSurname;
    @FXML
    private TextField sOthername;
    @FXML
    private TextField sEmailAddr;
    @FXML
    private TextField sMobileNumber;
    @FXML
    private TextField sOccupation;
    @FXML
    private ChoiceBox<String> sGender;
    @FXML
    private ChoiceBox<String> sMaritalStat;
    @FXML
    private TextField sAddress;
    @FXML
    private ChoiceBox<String> sGroupName;
    @FXML
    private TextField sGroupNumber;
    @FXML
    private ChoiceBox<String> sCountry;
    @FXML
    private TextField sState;
    @FXML
    private TextField sLGA;

    @FXML
    private TextField sPassbookNumber;
    @FXML
    private TextField sIssuingName;
    @FXML
    private DatePicker sIssuingDate;
    @FXML
    private Button uploadButton;
    @FXML
    private ImageView accountPassport;
    @FXML
    private TextField openingBalance;
    @FXML
    private DatePicker sDateOFBirth;
    @FXML
    private BorderPane loaderCore;
    @FXML
    private ListView<String> formreport;

    GroupListModel glm = new GroupListModel();
    private final Rocket rock = new Rocket();
    File imageFile = null;
    private File selectedFile;
    static String dir = System.getProperty("user.home");
    static Stage stage = new Stage();
    private boolean check;
    Rocket roc = new Rocket();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadInstances();
        Common.setButtonGraphics(uploadButton, new ImageView(), Common.imageStreamer("images/Camera.png"), "Upload Passport here");
        loadSomeInfo();
        Validators(new ModelListener());
        clearLeftDatas();
    }

    public void loadSomeInfo() {

        sIssuingDate.setValue(LocalDate.now());
        sDateOFBirth.setValue(LocalDate.now());
        ObservableXMLLoader xmlLoader = new ObservableXMLLoader();
        AccountManagerModel.getInstance().getCountry().getItems().addAll(AppMainModel.getCountries());
        ObservableList<String> gender = FXCollections.observableArrayList("Male", "Female");
        ObservableList<String> maritals = FXCollections.observableArrayList("Married", "Single", "Divorced");
        ObservableList<String> group = FXCollections.observableArrayList(GroupListModel.getGroupNames());
        sGroupName.setItems(group);
        sGender.setItems(gender);
        sMaritalStat.setItems(maritals);

    }

    public void Validators(ModelListener model) {
        model.addressListener(sAddress, errorLabel);
        model.surnameListener(sSurname, errorLabel);
        model.otherNamesListener(sOthername, errorLabel);
        model.ImageListener(accountPassport, errorLabel);
        model.emailListener(sEmailAddr, errorLabel);
        model.openingBalanceValidator(openingBalance, errorLabel);
        model.phoneNoListener(sMobileNumber, errorLabel);
        model.occupationListener(sOccupation, errorLabel);
        model.countryListener(sCountry, errorLabel);
        model.stateListener(sState, errorLabel);
        model.lGAListener(sLGA, errorLabel);
        model.genderListener(sGender, errorLabel);
        model.maritalStatListener(sMaritalStat, errorLabel);
        model.groupNameListener(sGroupName, errorLabel);
        model.groupNumberListener(sGroupNumber, errorLabel);
        model.dateOFBirthListener(sDateOFBirth, errorLabel);
        // model.customerSVN(sCustomerId, errorLabel);
        model.accountNumberListener(sPassbookNumber, errorLabel);
        model.issuingNameValidator(sIssuingName, errorLabel);
        model.issuingDateListener(sIssuingDate, errorLabel);

        model.focusListener(sAddress, errorLabel);
        model.focusListener(sSurname, errorLabel);
        model.focusListener(sOthername, errorLabel);
        model.focusListener(openingBalance, errorLabel);
        model.focusListener(sEmailAddr, errorLabel);
        model.focusListener(sMobileNumber, errorLabel);
        model.focusListener(sOccupation, errorLabel);
        model.focusListener(sGender, errorLabel);
        model.focusListener(sMaritalStat, errorLabel);
        model.focusListener(sCountry, errorLabel);
        model.focusListener(sLGA, errorLabel);
        model.focusListener(sState, errorLabel);
        model.focusListener(sGroupName, errorLabel);
        model.focusListener(sLGA, errorLabel);
        model.focusListener(sGroupNumber, errorLabel);
        model.focusListener(sDateOFBirth, errorLabel);
        //    model.focusListener(sCustomerId, errorLabel);
        model.focusListener(sPassbookNumber, errorLabel);
        model.focusListener(sIssuingName, errorLabel);
        model.focusListener(sIssuingDate, errorLabel);

    }

    public void loadInstances() {

        AccountManagerModel.getInstance().setCountry(sCountry);
        AccountManagerModel.getInstance().setAddress(sAddress);
        AccountManagerModel.getInstance().setOpeningBalance(openingBalance);
        AccountManagerModel.getInstance().setEmail(sEmailAddr);
        AccountManagerModel.getInstance().setOthernames(sOthername);
        AccountManagerModel.getInstance().setPhonenumber(sMobileNumber);
        AccountManagerModel.getInstance().setSurname(sSurname);
        AccountManagerModel.getInstance().setGroupName(sGroupName);
        AccountManagerModel.getInstance().setOccupation(sOccupation);
        AccountManagerModel.getInstance().setMaritalStatus(sMaritalStat);
        AccountManagerModel.getInstance().setGender(sGender);
        AccountManagerModel.getInstance().setGroupNumber(sGroupNumber);
        AccountManagerModel.getInstance().setBirthday(sDateOFBirth);
        AccountManagerModel.getInstance().setState(sState);
        AccountManagerModel.getInstance().setLga(sLGA);
        //  AccountManagerModel.getInstance().setCustomerSVN(sCustomerId);
        AccountManagerModel.getInstance().setAccountNumber(sPassbookNumber);
        AccountManagerModel.getInstance().setIssuingdate(sIssuingDate);
        AccountManagerModel.getInstance().setIssuingname(sIssuingName);

    }

    public void commitToDB(String accountNumber, String uSurname, String othernames, String uEmail, String phonenumber,
            String uAddress, String uCountry, String groupname, String groupNumber, String issuingName, String state,
            String occupation, String lga, String gender, String maritalStat, Date dob, String pictureurl, Date issuingDate, Double openingBalance,
            Date modifiedDate, Label errorLabel)
            throws SQLException {
        formreport.getItems().clear();
        if (accountNumber.length() > 3) {
            //   errorLabel.setText("Ready to dump in base");
            if (Rocket.isValidatedAccountNumber()) {
                try {

                    UtilityExtractor util = new UtilityExtractor();
                    util.createAccount(uSurname, othernames, gender, maritalStat, uEmail, occupation, groupname, phonenumber,
                            uAddress, uCountry, state, lga, dob, issuingDate, pictureurl, issuingName, accountNumber, modifiedDate, openingBalance, true);
                    formreport.getItems().add("Surname: " + uSurname);
                    formreport.getItems().add("OtherNames: " + othernames);
                    formreport.getItems().add("Group Name: " + groupname);
                    formreport.getItems().add("Pass-Book Number: " + accountNumber);
                    formreport.getItems().add("Opening balance: NGN" + Common.formatDouble(openingBalance) + "K");
                    formreport.getItems().add("Your Data has been sucessfully Uploaded");

                    errorLabel.setTextFill(Color.CORNFLOWERBLUE);
                    errorLabel.setText(" Data sucessfully uploaded to database");

                    Rocket.setSuccessfulCommit(true);
                } catch (Exception e) {

                    errorLabel.setText("Some errors occurred ");
                    System.out.println(e.getMessage());

                    e.printStackTrace();
                }
            } else {
                errorLabel.setText("Account Number in use");
            }
        } else {

            errorLabel.setTextFill(Color.RED);
            errorLabel.setText("Account Number minimum-length: 4");
        }
    }

    public void setChecked(boolean check) {
        this.check = check;
    }

    public boolean isChecked() {
        return check;
    }

    public void checked() {
        UtilityExtractor util = new UtilityExtractor();
        List li = util.queryTable("FROM Setting");
        boolean checked = false;
        if (!li.isEmpty()) {
            for (Object o : li) {
                Setting setting = (Setting) o;
                checked = setting.getSkipformcheck();

            }
        }
        setChecked(checked);
        System.out.println(isChecked());
    }

    @FXML
    private void savetoDatabase(ActionEvent event) throws SQLException {
        //Rocket roc = new Rocket();
        checked();
        if (roc.memberDataIsReady() || isChecked()) {
            String surname = AccountManagerModel.getInstance().getSurname().getText();
            String othernames = AccountManagerModel.getInstance().getOthernames().getText();
            String email = AccountManagerModel.getInstance().getEmail().getText();

            String address = AccountManagerModel.getInstance().getAddress().getText();
            String phonenumber = AccountManagerModel.getInstance().getPhonenumber().getText();
            String country = AccountManagerModel.getInstance().getCountry().getValue();
            String gender = AccountManagerModel.getInstance().getGender().getValue();
            String maritalstat = AccountManagerModel.getInstance().getMaritalStatus().getValue();
            String groupName = AccountManagerModel.getInstance().getGroupName().getValue();
            String occupation = AccountManagerModel.getInstance().getOccupation().getText();
            String groupNumber = AccountManagerModel.getInstance().getGroupNumber().getText();

            LocalDate dateOfBirth = AccountManagerModel.getInstance().getBirthday().getValue() == null ? LocalDate.now() : sDateOFBirth.getValue();
            Date bDay = Date.valueOf(dateOfBirth);

            String state = AccountManagerModel.getInstance().getState().getText();
            String lga = AccountManagerModel.getInstance().getLga().getText();

            String balance = AccountManagerModel.getInstance().getOpeningBalance().getText();
            Double openingBalance;
            if (balance.isEmpty()) {
                openingBalance = 0.0;
            } else {
                openingBalance = Double.parseDouble(balance);
            }

            String accountNumber = AccountManagerModel.getInstance().getAccountNumber().getText();
            //String openingBalance=AccountManagerModel.getInstance().get
            String issuingname = AccountManagerModel.getInstance().getIssuingname().getText();
            LocalDate issuingDate = AccountManagerModel.getInstance().getIssuingdate().getValue() == null ? LocalDate.now() : sDateOFBirth.getValue();
            Date idate = Date.valueOf(issuingDate);

            String filename = null;
            if (getSelectedFile() != null && !accountNumber.isEmpty()) {
                try {
                    filename = saveFile(getSelectedFile(), accountNumber);
                } catch (IOException ex) {
                    Logger.getLogger(AccountManagerController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            //commit here
            commitToDB(accountNumber, surname, othernames, email, phonenumber,
                    address, country, groupName, groupNumber, issuingname, state,
                    occupation, lga, gender, maritalstat, bDay, filename, idate, openingBalance,
                    Date.valueOf(LocalDate.now()), errorLabel);
            event.consume();
        } else {
            errorLabel.setStyle("-fx-text-fill:red;");
            errorLabel.setText("Fill in neccessary Fields");
        }
    }

    private synchronized void clearLeftDatas() {
        AccountManagerModel.getInstance().getSurname().clear();
        AccountManagerModel.getInstance().getOthernames().clear();
        AccountManagerModel.getInstance().getEmail().clear();
        AccountManagerModel.getInstance().getAddress().clear();
        AccountManagerModel.getInstance().getAccountNumber().clear();
        AccountManagerModel.getInstance().getBirthday().setValue(LocalDate.now());
        //AccountManagerModel.getInstance().getCountry().setValue("Nigeria");
        AccountManagerModel.getInstance().getGender().getSelectionModel().selectLast(); //clearSelection();
        AccountManagerModel.getInstance().getOccupation().clear();
        AccountManagerModel.getInstance().getGroupName().getSelectionModel().selectLast();
        AccountManagerModel.getInstance().getGroupNumber().clear();
        AccountManagerModel.getInstance().getOpeningBalance().clear();
//        AccountManagerModel.getInstance().getCustomerSVN().clear();
        AccountManagerModel.getInstance().getMaritalStatus().getSelectionModel().selectLast();
        AccountManagerModel.getInstance().getState().clear();
        AccountManagerModel.getInstance().getLga().clear();
        AccountManagerModel.getInstance().getIssuingdate().setValue(LocalDate.now());
        AccountManagerModel.getInstance().getIssuingname().clear();
        AccountManagerModel.getInstance().getPhonenumber().clear();
        AccountManagerModel.getInstance().getCountry().setValue("NIGERIA");
        //  AccountManagerModel.getInstance().setImageUrl(null);
        accountPassport.setImage(null);

        rock.setDataIsReady(false);
        Rocket.setSuccessfulCommit(false);
        errorLabel.setTextFill(Color.BLACK);
        errorLabel.setGraphic(null);
        errorLabel.setText(null);
        //Members.setUpdate(false);

    }

    @FXML
    private void clear(MouseEvent event) {
        // 
        if (Rocket.isSuccessfulCommit()) {
            clearLeftDatas();
        }
        event.consume();
    }

    private File getSelectedFile() {
        return selectedFile;
    }

    private void setSelectedFile(File file) {
        this.selectedFile = file;
    }

    @FXML
    private void uploadImage(ActionEvent event) {
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

            accountPassport.setImage(new Image(new FileInputStream(selectedFile)));

        } catch (IOException ex) {
            Logger.getLogger(AccountManagerController.class.getName()).log(Level.SEVERE, null, ex);
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

}
