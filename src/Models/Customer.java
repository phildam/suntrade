/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author g33k5qu4d
 */
public class Customer {
    
    private SimpleStringProperty surname;
    private SimpleStringProperty othernames;
    private SimpleStringProperty email;
    private SimpleStringProperty contact;
    private SimpleStringProperty birthday;
    private SimpleStringProperty occupation;
    private SimpleStringProperty gender;
    private SimpleStringProperty maritalstatus;
    private SimpleStringProperty Address;
    private SimpleStringProperty groupname;
    private SimpleStringProperty groupnumber;
    private SimpleStringProperty membershipdate;
    private SimpleStringProperty state;
    private SimpleStringProperty lga;
    private SimpleStringProperty accountnumber;
    private SimpleStringProperty issuingname;
    private SimpleStringProperty issuingdate;
    
    public Customer(){
        
    }
    
    public SimpleStringProperty getSurname() {
        return surname;
    }

    public void setSurname(SimpleStringProperty surname) {
        this.surname = surname;
    }

    public SimpleStringProperty getOthernames() {
        return othernames;
    }

    public void setOthernames(SimpleStringProperty othernames) {
        this.othernames = othernames;
    }

    public SimpleStringProperty getEmail() {
        return email;
    }

    public void setEmail(SimpleStringProperty email) {
        this.email = email;
    }

    public SimpleStringProperty getContact() {
        return contact;
    }

    public void setContact(SimpleStringProperty contact) {
        this.contact = contact;
    }

    public SimpleStringProperty getBirthday() {
        return birthday;
    }

    public void setBirthday(SimpleStringProperty birthday) {
        this.birthday = birthday;
    }

    public SimpleStringProperty getOccupation() {
        return occupation;
    }

    public void setOccupation(SimpleStringProperty occupation) {
        this.occupation = occupation;
    }

    public SimpleStringProperty getGender() {
        return gender;
    }

    public void setGender(SimpleStringProperty gender) {
        this.gender = gender;
    }

    public SimpleStringProperty getMaritalstatus() {
        return maritalstatus;
    }

    public void setMaritalstatus(SimpleStringProperty maritalstatus) {
        this.maritalstatus = maritalstatus;
    }

    public SimpleStringProperty getAddress() {
        return Address;
    }

    public void setAddress(SimpleStringProperty Address) {
        this.Address = Address;
    }

    public SimpleStringProperty getGroupname() {
        return groupname;
    }

    public void setGroupname(SimpleStringProperty groupname) {
        this.groupname = groupname;
    }

    public SimpleStringProperty getGroupnumber() {
        return groupnumber;
    }

    public void setGroupnumber(SimpleStringProperty groupnumber) {
        this.groupnumber = groupnumber;
    }

    public SimpleStringProperty getMembershipdate() {
        return membershipdate;
    }

    public void setMembershipdate(SimpleStringProperty membershipdate) {
        this.membershipdate = membershipdate;
    }

    public SimpleStringProperty getState() {
        return state;
    }

    public void setState(SimpleStringProperty state) {
        this.state = state;
    }

    public SimpleStringProperty getLga() {
        return lga;
    }

    public void setLga(SimpleStringProperty lga) {
        this.lga = lga;
    }

    public SimpleStringProperty getAccountnumber() {
        return accountnumber;
    }

    public void setAccountnumber(SimpleStringProperty accountnumber) {
        this.accountnumber = accountnumber;
    }

    public SimpleStringProperty getIssuingname() {
        return issuingname;
    }

    public void setIssuingname(SimpleStringProperty issuingname) {
        this.issuingname = issuingname;
    }

    public SimpleStringProperty getIssuingdate() {
        return issuingdate;
    }

    public void setIssuingdate(SimpleStringProperty issuingdate) {
        this.issuingdate = issuingdate;
    }
}
