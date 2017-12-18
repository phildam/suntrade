/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ComponentUpdaterClass;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.CheckBox;

/**
 *
 * @author g33k5qu4d
 */
public class Customer {
    
    private final SimpleStringProperty surname;
    private final SimpleStringProperty firstname;
    private final SimpleStringProperty email;
    private final SimpleStringProperty contact;
    private final SimpleStringProperty address;
    private final SimpleStringProperty state;
    private final SimpleStringProperty birthday;
    
    
    private final static SimpleBooleanProperty updated=new SimpleBooleanProperty(false);
    private static CheckBox cb=new CheckBox();
    
    public Customer(String surname, String first, String mail,
            String phone, String addr, String state, String dob){
        this.surname=new SimpleStringProperty(surname);
        this.firstname=new SimpleStringProperty(first);
        this.address=new SimpleStringProperty(addr);
        this.contact=new SimpleStringProperty(phone);
        this.email=new SimpleStringProperty(mail);
        this.birthday=new SimpleStringProperty(dob);
        this.state=new SimpleStringProperty(state);
    }
    
    public static boolean isUpdated(){
        return updated.get();
    }
    public static void setCheckBox(CheckBox checkBox){
        cb=checkBox;
    }
    public static CheckBox getcheckBox(){
        return cb;
    }
    public static void setUpdate(boolean value){
        updated.set(value);
    }
    public static SimpleBooleanProperty updateAction(){
        return updated;
    }
    public String getFirstname() {
        return firstname.get();
    }

    public String getSurname() {
        return surname.get();
    }

    public String getEmail() {
        return email.get();
    }

    public String getContact() {
        return contact.get();
    }

    
    public String getAddress() {
        return address.get();
    }

    
    public String getCountry() {
        return state.get();
    }

    
    public String getBirthday() {
        return birthday.get();
    }

   
    public void setFirstname(String fname) {
        firstname.set(fname);
    }

    public void setSurname(String lname) {
        surname.set(lname);
    }

    
    public void setEmail(String mail) {
        email.set(mail);
    }

   
    public void setContact(String phone) {
        contact.set(phone);
    }

    
    public void setAddress(String addr) {
        address.set(addr);
    }

    
    public void setCountry(String co) {
        state.set(co);
    }

    
    public void setBirthday(String birthDay) {
        birthday.set(birthDay);
    }
    
}
