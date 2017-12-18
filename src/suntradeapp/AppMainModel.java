/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package suntradeapp;

import javafx.collections.ObservableList;
import javafx.scene.control.Label;

/**
 *
 * @author g33k5qu4d
 */
public class AppMainModel {
    private static final AppMainModel vcm=new AppMainModel();
    
    private static ObservableList countries;

    public static ObservableList getCountries() {
        return countries;
    }

    public static void setCountries(ObservableList aCountries) {
        countries = aCountries;
    }
    
    private boolean flag;
    
    private Label rightStatusBind;
    private Label leftStatusBind;
    
    private Label surnameBind;
    private Label otherNameBind;
    private Label emailBind;
    private Label addressBind;
    private Label genderBind;
    private Label maritalStatusBind;
    private Label occupationBind;
    private Label statusBind;
    
    private AppMainModel(){
        
    }
    public static AppMainModel getInstance(){
        return vcm;
    }
   
    public boolean isFlag(){
        return flag;
    }

    public Label getSurnameBind() {
        return surnameBind;
    }

    public Label getOtherNameBind() {
        return otherNameBind;
    }

    public Label getEmailBind() {
        return emailBind;
    }

    public Label getAddressBind() {
        return addressBind;
    }

    public Label getGenderBind() {
        return genderBind;
    }

    public Label getMaritalStatusBind() {
        return maritalStatusBind;
    }

    public Label getOccupationBind() {
        return occupationBind;
    }

    public Label getStatusBind() {
        return statusBind;
    }

    public void setSurnameBind(Label surnameBind) {
        this.surnameBind = surnameBind;
    }

    public void setOtherNameBind(Label otherNameBind) {
        this.otherNameBind = otherNameBind;
    }

    public void setEmailBind(Label emailBind) {
        this.emailBind = emailBind;
    }

    public void setAddressBind(Label addressBind) {
        this.addressBind = addressBind;
    }

    public void setGenderBind(Label genderBind) {
        this.genderBind = genderBind;
    }

    public void setMaritalStatusBind(Label maritalStatusBind) {
        this.maritalStatusBind = maritalStatusBind;
    }

    public void setOccupationBind(Label occupationBind) {
        this.occupationBind = occupationBind;
    }

    public void setStatusBind(Label statusBind) {
        this.statusBind = statusBind;
    }

    public Label getRightStatusBind() {
        return rightStatusBind;
    }

    public void setRightStatusBind(Label rightStatusBind) {
        this.rightStatusBind = rightStatusBind;
    }

    public Label getLeftStatusBind() {
        return leftStatusBind;
    }

    public void setLeftStatusBind(Label leftStatusBind) {
        this.leftStatusBind = leftStatusBind;
    }
    
    
}
