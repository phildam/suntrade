/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package suntradeapp;

import ComponentUpdaterClass.TableGenerator;
import ComponentUpdaterClass.UnitComponentHandler;
import Transaction.Queries;
import entities.Account;
import entities.Customer;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author g33k5qu4d
 *
 */
public class GroupListController implements Initializable {

    ToggleGroup group = new ToggleGroup();

    boolean isSet = false;
    ObservableList<String> unitList = FXCollections.observableArrayList();
    static ObservableList<String> userData = FXCollections.observableArrayList();
    ObservableList<String> searchList = FXCollections.observableArrayList();
    static ObservableList<Customer> tableItem = FXCollections.observableArrayList();

    private final UnitComponentHandler loader = new UnitComponentHandler();
    @FXML
    private ScrollPane unitUpdateScrollPane;
    @FXML
    private VBox updateUnitBox;
    GroupListModel glm = GroupListModel.getInstance();
    @FXML
    private TableView<?> GroupMembers;
    @FXML
    private Label groupReporter;
    @FXML
    private TextField searchGroup;
    @FXML
    private ChoiceBox<String> searchBy;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {

            GroupListModel.getInstance().setGroup(group);
            searchBy.setItems(FXCollections.observableArrayList("IssuingName", "Account Number", "Surname", "Email",
                    "Othernames", "phoneNumber", "Address", "Group Name"));
            searchBy.getSelectionModel().select("Account Number");

            setToggleButton();
            ///loadListData();
            selectedToggle();
            loadTableColumns();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(GroupListController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void loadTableColumns() {
        ObservableList<String> customerList = FXCollections.observableArrayList("surname", "otherNames", "gender", "maritalStatus", "email",
                "occupation", "phoneNumber", "groupName", "address", "country", "state", "lga", "dob", "isuingDate", "issuingName", "passBookNumber");
        TableGenerator loanTab = new TableGenerator(customerList);
        loanTab.generatorCol(GroupMembers);
    }

    public RadioButton updateRadioButton(String buttonName) {

        RadioButton but = new RadioButton(buttonName);
        but.setUserData(buttonName);
        but.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                GroupMembers.getItems().clear();
                tableItem.clear();
                String s = (String) but.getUserData();

                System.out.println(s);
                UtilityExtractor util = new UtilityExtractor();
                String query = "FROM Customer c where c.groupName ='";
                List li = util.queryTable(query + s + "'");
                for (Object o : li) {
                    Customer c = (Customer) o;
                    Account a=c.getAccountNumber();
                    c.setPassBookNumber(a.getAccountNumber());
                    tableItem.add(c);

                }
                TableGenerator.setTabGenColItem(tableItem);
            }
        });
        but.setToggleGroup(group);
        group.selectToggle(but);
        return but;
    }

    public void UpdateUnitBox() {

        // ObservableList<String> ol=loader.getUnitList();
        ObservableList<String> items = FXCollections.observableArrayList();

        items.addAll(GroupListModel.getGroupNames());

        items.stream().forEach((s) -> {

            updateUnitBox.getChildren().add(updateRadioButton(s));
            // System.out.print(s+ " ");
            //   if(s.equalsIgnoreCase("Golden Mercury")){
            //      group.selectToggle(updateRadioButton(s));
            //}
        });
    }

    private void setToggleButton() {
        UpdateUnitBox();
    }

    public void loadListData() {
        ObservableList<Toggle> togs = group.getToggles();

        for (int i = 0; i < togs.size(); i++) {
            String add = (String) togs.get(i).getUserData();
            userData.add(add);
            System.out.println(add);
        }
        togs.size();
    }

    public ObservableList<String> getUserDataUnitList() {
        return userData;
    }

    public ObservableList<String> getUnitlist() {
        return unitList;
    }

    private void selectedToggle() throws FileNotFoundException {

        group.selectedToggleProperty().addListener((ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) -> {

            System.out.println("Am clicked " + newValue.getUserData());

        });
    }

    public String executeStatement(String search) {
        //  UtilityExtractor util = new UtilityExtractor();
        String searchby = null;
        switch (search) {
            case "IssuingName":
                searchby = Queries.cIssuingName;
                break;
            case "Surname":
                searchby = Queries.cSurnameQuery;
                break;
            case "Email":
                searchby = Queries.cEmailQuery;
                break;
            case "Group Name":
                searchby = Queries.gGroupNameQuery;
                break;
            case "Othernames":
                searchby = Queries.cOthernamesQuery;
                break;
            case "phoneNumber":
                searchby = Queries.cPhoneNumberQuery;
                break;
            case "Address":
                searchby = Queries.cLGA;
                break;
            case "Account Number":
                searchby = Queries.cCustomerAccounNumber;
                break;
        }
        return searchby;
    }

    @FXML
    private void searchResultOnEnter(KeyEvent event) {
        //GroupListView.getItems().clear();

    }

    @FXML
    private void searchResult(ActionEvent event) {
        GroupMembers.getItems().clear();
        String srcby = searchBy.getSelectionModel().getSelectedItem();
        UtilityExtractor utilExtract = new UtilityExtractor();
        if (!searchGroup.getText().trim().equals("")) {
            System.err.println("Selected choice:" + srcby);
            System.err.println(executeStatement(srcby));
            String get = executeStatement(srcby) + searchGroup.getText() + "%' and c.groupName='" + group.getSelectedToggle().getUserData() + "'";
            List list = utilExtract.queryTable(get);
            if (!list.isEmpty()) {
                for (Object s : list) {
                    Customer c = (Customer) s;
                    Account a=c.getAccountNumber();
                    c.setPassBookNumber(a.getAccountNumber());
                    tableItem.add(c);
                }
                TableGenerator.setTabGenColItem(tableItem);
            }
        }
    }

}
