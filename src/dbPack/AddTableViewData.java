/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbPack;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author g33k5qu4d
 */
public class AddTableViewData {
    
    private final SimpleStringProperty lastname;
    private final SimpleStringProperty surname;
    private final SimpleStringProperty email;
    private final SimpleStringProperty address;
    
    public AddTableViewData(String surname, String lastname, String email,String addr){
        this.lastname=new SimpleStringProperty(lastname);
        this.surname=new SimpleStringProperty(surname);
        this.address=new SimpleStringProperty(addr);
        this.email=new SimpleStringProperty(email);
    }
    
    public String getSurname(){
        return surname.get();
    }
    public String getLastname(){
        return lastname.get();
    }
    public String getMail(){
        return email.get();
    }
    public String getAddress(){
        return address.get();
    }
}
