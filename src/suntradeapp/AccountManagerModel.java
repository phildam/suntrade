/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package suntradeapp;

import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

/**
 *
 * @author g33k5qu4d
 */
public class AccountManagerModel {
    
    private String imageUrl;
    private TextField address;
    private DatePicker birthday;
    private ChoiceBox<String> country;
    private TextField email;
    private VBox formVBox;
    private TextField othernames;
    private Button saveInfo;
    private TextField phonenumber;
    private TextField surname;
    private TextField occupation;
    private ChoiceBox<String> groupname;
    private ChoiceBox<String> gender;
    private ChoiceBox<String> maritalStatus;
    private TextField openingBalance;
    private DatePicker membershipDate;
   // private DatePicker birthday;
    private TextField groupNumber;
    private TextField state;
    private TextField lga;
   // private TextField customerSVN;
    private TextField accountNumber;
    private TextField issuingname;
    private DatePicker issuingdate;

    private static AccountManagerModel acc = new AccountManagerModel();

    public static void setFlm(AccountManagerModel acct) {
        acc = acct;
    }
    
    private AccountManagerModel() {
        
    }

    public static AccountManagerModel getInstance() {
        return AccountManagerModel.acc;
    }
    
    

    public TextField getAddress() {
        return address;
    }

    public DatePicker getBirthday() {
        return birthday;
    }

    public ChoiceBox<String> getCountry() {
        return country;
    }

    public TextField getEmail() {
        return email;
    }

    public VBox getFormVBox() {
        return formVBox;
    }

    public TextField getOthernames() {
        return othernames;
    }

    public TextField getPhonenumber() {
        return phonenumber;
    }

    public Button getSaveInfo() {
        return saveInfo;
    }

    public TextField getSurname() {
        return surname;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(TextField address) {
        this.address = address;
    }

    public void setBirthday(DatePicker birthday) {
        this.birthday = birthday;
    }

    public void setCountry(ChoiceBox<String> country) {
        this.country = country;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(TextField email) {
        this.email = email;
    }

    /**
     * @param formVBox the formVBox to set
     */
    public void setFormVBox(VBox formVBox) {
        this.formVBox = formVBox;
    }

    /**
     * @param othernames the othernames to set
     */
    public void setOthernames(TextField othernames) {
        this.othernames = othernames;
    }

    /**
     * @param saveInfo the saveInfo to set
     */
    public void setSaveInfo(Button saveInfo) {
        this.saveInfo = saveInfo;
    }

    /**
     * @param phonenumber the phonenumber to set
     */
    public void setPhonenumber(TextField phonenumber) {
        this.phonenumber = phonenumber;
    }

    /**
     * @param surname the surname to set
     */
    public void setSurname(TextField surname) {
        this.surname = surname;
    }

    public ChoiceBox<String> getGroupName() {
        return groupname;
    }

    public void setGroupName(ChoiceBox<String> groupname) {
        this.groupname = groupname;
    }

    public ChoiceBox<String> getGender() {
        return gender;
    }

    public void setGender(ChoiceBox<String> gender) {
        this.gender = gender;
    }

    public ChoiceBox<String> getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(ChoiceBox<String> maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public TextField getOccupation() {
        return occupation;
    }

    public void setOccupation(TextField occupation) {
        this.occupation = occupation;
    }

    public TextField getGroupNumber() {
        return groupNumber;
    }

    public void setGroupNumber(TextField groupNumber) {
        this.groupNumber = groupNumber;
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

//    public TextField getCustomerSVN() {
     //   return customerSVN;
 //   }

 //   public void setCustomerSVN(TextField customerSVN) {
   //     this.customerSVN = customerSVN;
  //  }

    public TextField getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(TextField accountNumber) {
        this.accountNumber = accountNumber;
    }

    public TextField getIssuingname() {
        return issuingname;
    }

    public void setIssuingname(TextField issuingname) {
        this.issuingname = issuingname;
    }

    public DatePicker getIssuingdate() {
        return issuingdate;
    }

    public void setIssuingdate(DatePicker issuingdate) {
        this.issuingdate = issuingdate;
    }

    public DatePicker setDateOfBirth() {
        return membershipDate;
    }

    public void setDateOfBirth(DatePicker membershipDate) {
        this.membershipDate = membershipDate;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public TextField getOpeningBalance() {
        return openingBalance;
    }

    public void setOpeningBalance(TextField openingBalance) {
        this.openingBalance = openingBalance;
    }
}

