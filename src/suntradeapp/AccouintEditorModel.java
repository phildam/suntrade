/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package suntradeapp;

import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author g33k5qu4d
 */
public class AccouintEditorModel {
    
    private Stage confirmStage;
    
    private TextField accountNumberSearch;
   
    private TextField surname;
   
    private TextField othernames;
   
    private ChoiceBox<String> gender;
   
    private TextField email;
   
    private ChoiceBox<String> maritalstatus;
   
    private TextField occupation;
   
    private TextField phoneNumber;
   
    private ChoiceBox<String> groupName;
   
    private TextField address;
    
    private TextField country;
   
    private DatePicker dob;
   
    private TextField state;
   
    private TextField lga;
   
    private DatePicker issuingDate;
   
    private TextField issuingName;
   
    private ImageView picture;
   
    private Button updateAccount;
   
    private Label infoLabel;
   
    private ListView<String> listDetails;
    
    private boolean foundit;
    
    private int custId;
   
    private Button deleteAccount;
   
    private Button accStatement;
   
    private Button deactivateAccount;
   
    private ChoiceBox<String> groupList;
   
    private TextField newGroupName;
   
    private Button updateGroup;
   
    private TextField createNewGroups;
   
    private Button createGroup;
   
    private ChoiceBox<String> groupListdel;
   
    private Button deleteGroup;
   
    private Label editExistingGroupLabel;
   
    private Label createNewLabel;
   
    private Label delgrouplabel;
   
    private Pane loadPane;

    private VBox vloader;
    
    private VBox detailsPane;
    
    static AccouintEditorModel am=new AccouintEditorModel();
    
    private AccouintEditorModel(){};
    
    public static AccouintEditorModel getInstance(){
        return am;
    }

    public TextField getAccountNumberSearch() {
        return accountNumberSearch;
    }

    public void setAccountNumberSearch(TextField accountNumberSearch) {
        this.accountNumberSearch = accountNumberSearch;
    }

    public TextField getSurname() {
        return surname;
    }

    public void setSurname(TextField surname) {
        this.surname = surname;
    }

    public TextField getOthernames() {
        return othernames;
    }

    public void setOthernames(TextField othernames) {
        this.othernames = othernames;
    }

    public ChoiceBox<String> getGender() {
        return gender;
    }

    public void setGender(ChoiceBox<String> gender) {
        this.gender = gender;
    }

    public TextField getEmail() {
        return email;
    }

    public void setEmail(TextField email) {
        this.email = email;
    }

    public ChoiceBox<String> getMaritalstatus() {
        return maritalstatus;
    }

    public void setMaritalstatus(ChoiceBox<String> maritalstatus) {
        this.maritalstatus = maritalstatus;
    }

    public TextField getOccupation() {
        return occupation;
    }

    public void setOccupation(TextField occupation) {
        this.occupation = occupation;
    }

    public TextField getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(TextField phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public ChoiceBox<String> getGroupName() {
        return groupName;
    }

    public void setGroupName(ChoiceBox<String> groupName) {
        this.groupName = groupName;
    }

    public TextField getAddress() {
        return address;
    }

    public void setAddress(TextField address) {
        this.address = address;
    }

    public TextField getCountry() {
        return country;
    }

    public void setCountry(TextField country) {
        this.country = country;
    }

    public DatePicker getDob() {
        return dob;
    }

    public void setDob(DatePicker dob) {
        this.dob = dob;
    }

    public TextField getState() {
        return state;
    }

    public void setState(TextField state) {
        this.state = state;
    }

    public TextField getLga() {
        return lga;
    }

    public void setLga(TextField lga) {
        this.lga = lga;
    }

    public DatePicker getIssuingDate() {
        return issuingDate;
    }

    public void setIssuingDate(DatePicker issuingDate) {
        this.issuingDate = issuingDate;
    }

    public TextField getIssuingName() {
        return issuingName;
    }

    public void setIssuingName(TextField issuingName) {
        this.issuingName = issuingName;
    }

    public ImageView getPicture() {
        return picture;
    }

    public void setPicture(ImageView picture) {
        this.picture = picture;
    }

    public Button getUpdateAccount() {
        return updateAccount;
    }

    public void setUpdateAccount(Button updateAccount) {
        this.updateAccount = updateAccount;
    }

    public Label getInfoLabel() {
        return infoLabel;
    }

    public void setInfoLabel(Label infoLabel) {
        this.infoLabel = infoLabel;
    }

    public ListView<String> getListDetails() {
        return listDetails;
    }

    public void setListDetails(ListView<String> listDetails) {
        this.listDetails = listDetails;
    }

    public boolean isFoundit() {
        return foundit;
    }

    public void setFoundit(boolean foundit) {
        this.foundit = foundit;
    }

    public int getCustId() {
        return custId;
    }

    public void setCustId(int custId) {
        this.custId = custId;
    }

    public Button getDeleteAccount() {
        return deleteAccount;
    }

    public void setDeleteAccount(Button deleteAccount) {
        this.deleteAccount = deleteAccount;
    }

    public Button getAccStatement() {
        return accStatement;
    }

    public void setAccStatement(Button accStatement) {
        this.accStatement = accStatement;
    }

    public Button getDeactivateAccount() {
        return deactivateAccount;
    }

    public void setDeactivateAccount(Button deactivateAccount) {
        this.deactivateAccount = deactivateAccount;
    }

    public ChoiceBox<String> getGroupList() {
        return groupList;
    }

    public void setGroupList(ChoiceBox<String> groupList) {
        this.groupList = groupList;
    }

    public TextField getNewGroupName() {
        return newGroupName;
    }

    public void setNewGroupName(TextField newGroupName) {
        this.newGroupName = newGroupName;
    }

    public Button getUpdateGroup() {
        return updateGroup;
    }

    public void setUpdateGroup(Button updateGroup) {
        this.updateGroup = updateGroup;
    }

    public TextField getCreateNewGroups() {
        return createNewGroups;
    }

    public void setCreateNewGroups(TextField createNewGroups) {
        this.createNewGroups = createNewGroups;
    }

    public Button getCreateGroup() {
        return createGroup;
    }

    public void setCreateGroup(Button createGroup) {
        this.createGroup = createGroup;
    }

    public ChoiceBox<String> getGroupListdel() {
        return groupListdel;
    }

    public void setGroupListdel(ChoiceBox<String> groupListdel) {
        this.groupListdel = groupListdel;
    }

    public Button getDeleteGroup() {
        return deleteGroup;
    }

    public void setDeleteGroup(Button deleteGroup) {
        this.deleteGroup = deleteGroup;
    }

    public Label getEditExistingGroupLabel() {
        return editExistingGroupLabel;
    }

    public void setEditExistingGroupLabel(Label editExistingGroupLabel) {
        this.editExistingGroupLabel = editExistingGroupLabel;
    }

    public Label getCreateNewLabel() {
        return createNewLabel;
    }

    public void setCreateNewLabel(Label createNewLabel) {
        this.createNewLabel = createNewLabel;
    }

    public Label getDelgrouplabel() {
        return delgrouplabel;
    }

    public void setDelgrouplabel(Label delgrouplabel) {
        this.delgrouplabel = delgrouplabel;
    }

    public Pane getLoadPane() {
        return loadPane;
    }

    public void setLoadPane(Pane loadPane) {
        this.loadPane = loadPane;
    }

    public VBox getVloader() {
        return vloader;
    }

    public void setVloader(VBox vloader) {
        this.vloader = vloader;
    }

    public VBox getDetailsPane() {
        return detailsPane;
    }

    public void setDetailsPane(VBox detailsPane) {
        this.detailsPane = detailsPane;
    }

    public Stage getConfirmStage() {
        return confirmStage;
    }

    public void setConfirmStage(Stage confirmStage) {
        this.confirmStage = confirmStage;
    }
}
