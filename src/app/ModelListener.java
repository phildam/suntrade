/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import java.net.URISyntaxException;
import javafx.scene.control.ChoiceBox;
import java.time.LocalDate;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author g33k5qu4d
 */
public class ModelListener {

    
    ObservableXMLLoader xmlLoader = new ObservableXMLLoader();
    Validatron fv = new Validatron();
    
    private String selectedItem;

   // public void loadChoiceBox(ChoiceBox<String> choice) throws URISyntaxException {
    //    choice.getItems().addAll(xmlLoader.addCountries());
   // }

    
    public void focusListener(Node node, Label label) {
        node.focusedProperty().addListener(new ChangeListener<Boolean>() {
            
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                label.setText(null);
                label.setGraphic(null);
                
            }
        });
    }
    
    public void ImageListener(ImageView imageView, Label errorLabel){
        imageView.imageProperty().addListener(new ChangeListener<Image>() {

            @Override
            public void changed(ObservableValue<? extends Image> observable, Image oldValue, Image newValue) {
                Rocket.setValidatedAccountPassport(fv.imageValidator(newValue, errorLabel));
            }
        });
    }

    public void emailListener(TextField email, Label errorLabel) {
        email.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                fv.emailValidator(newValue, errorLabel);
                
            }
        });
    }

    public void otherNamesListener(TextField node, Label errorLabel) {

        node.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                Rocket.setValidOtherName(fv.textFieldValidator(newValue, errorLabel));
                }
        });
    }

    public void surnameListener(TextField node, Label errorLabel) {

        node.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                
                 Rocket.setValidSurname(fv.textFieldValidator(newValue, errorLabel));
                  }
        });
    }
    
    public void occupationListener(TextField node, Label errorLabel) {

        node.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                
                 Rocket.setValidOccupation(fv.textFieldValidator(newValue, errorLabel));
                 }
        });
    }

    public void addressListener(TextField address, Label errorLabel) {
        address.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                fv.addressvalidator(newValue, errorLabel);             
            }
        });
    }

    public void dobListener(DatePicker birthday, Label errorLabel) {
        birthday.valueProperty().addListener(new ChangeListener<LocalDate>() {

            public void changed(ObservableValue<? extends LocalDate> observable, LocalDate oldValue, LocalDate newValue) {
                boolean v;
                System.out.println("Date: "+newValue);
                if(newValue == null){
                    v= fv.dateValidator(oldValue, errorLabel);
                }else{
                    v= fv.dateValidator(newValue, errorLabel);
                }
                Rocket.setValidDOB(v);
            }
        });
        
    }
    
    public void phoneNoListener(TextField phoneNo, Label errorLabel) {
        phoneNo.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
           
            fv.phoneNumber(newValue, errorLabel);
        });
    }

    public void countryListener(ChoiceBox choiceBox ,Label errorLabel) {

        SingleSelectionModel smodel = choiceBox.getSelectionModel();
        smodel.selectedIndexProperty().addListener((Observable observable) -> {
            int selectedIndex = smodel.selectedIndexProperty().getValue();
            String item = (String) choiceBox.getItems().get(selectedIndex);
            Rocket.setValidatedCountry(fv.choiceBoxValidator(item, errorLabel));
            
        });

    }
    
    public void stateListener(TextField node ,Label errorLabel) {

        node.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                 Rocket.setValidatedState(fv.textFieldValidator(newValue, errorLabel)); 
                 
                  }
        });
    }
    
    public void lGAListener(TextField node ,Label errorLabel) {

        node.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                 Rocket.setValidatedLGA(fv.textFieldValidator(newValue, errorLabel)); 
                 
                  }
        });
    }
    
    public void genderListener(ChoiceBox choiceBox ,Label errorLabel) {

        SingleSelectionModel smodel = choiceBox.getSelectionModel();
        smodel.selectedIndexProperty().addListener((Observable observable) -> {
            int selectedIndex = smodel.selectedIndexProperty().getValue();
            String item = (String) choiceBox.getItems().get(selectedIndex);
            Rocket.setValidatedGender(fv.choiceBoxValidator(item, errorLabel)); 
        });
    }
    
    public void maritalStatListener(ChoiceBox choiceBox ,Label errorLabel) {

        SingleSelectionModel smodel = choiceBox.getSelectionModel();
        smodel.selectedIndexProperty().addListener((Observable observable) -> {
            int selectedIndex = smodel.selectedIndexProperty().getValue();
            String item = (String) choiceBox.getItems().get(selectedIndex);
            Rocket.setValidatedMaritalstatus(fv.choiceBoxValidator(item, errorLabel)); 
        });
    }
    
    public void groupNameListener(ChoiceBox choiceBox ,Label errorLabel) {

        SingleSelectionModel smodel = choiceBox.getSelectionModel();
        smodel.selectedIndexProperty().addListener((Observable observable) -> {
            int selectedIndex = smodel.selectedIndexProperty().getValue();
            String item = (String) choiceBox.getItems().get(selectedIndex);
            Rocket.setValidatedGroupName(fv.choiceBoxValidator(item, errorLabel)); 
        });
    }
    
    public void groupNumberListener(TextField node, Label errorLabel) {

        node.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                
                 Rocket.setValidatedGroupNo(fv.integerFieldValidator(newValue, errorLabel));
                  }
        });
    }
    
    public void dateOFBirthListener(DatePicker day, Label errorLabel) {
        day.valueProperty().addListener((ObservableValue<? extends LocalDate> observable, LocalDate oldValue, LocalDate newValue) -> {
            boolean v=false;
            if(newValue == null){
               v= fv.dateValidator(oldValue, errorLabel);
            }else{
               v= fv.dateValidator(newValue, errorLabel);
            }
            Rocket.setValidatedMembershipDate(v);
        });
        
    }
    
    public void issuingDateListener(DatePicker moment, Label errorLabel) {
        moment.valueProperty().addListener((ObservableValue<? extends LocalDate> observable, LocalDate oldValue, LocalDate newValue) -> {
            boolean v=false;
            if(newValue == null){
               v= fv.dateValidator(oldValue, errorLabel);
            }else{
               v= fv.dateValidator(newValue, errorLabel);
            }
            Rocket.setValidatedIssuingDate(v);
        });
        
    }
    
    public void issuingNameValidator(TextField node, Label errorLabel) {

        node.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                
                 Rocket.setValidatedIssuingName(fv.textFieldValidator(newValue, errorLabel));
                  }
        });
    }
    
    public void accountNumberListener(TextField node, Label errorLabel) {
        
        node.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
               
                 Rocket.setValidatedAccountNumber(fv.accountnumberValidator(newValue, errorLabel));
                 
                  }
        });
    }
    
   // public void customerSVN(TextField node, Label errorLabel) {

      //  node.textProperty().addListener(new ChangeListener<String>() {

       //     @Override
       //     public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                
             //    Rocket.setValidatedCustomerSVN(fv.integerFieldValidator(newValue, errorLabel));
            //      }
      //  });
  //  }
    
    
    
    public String getSelectedString() {
        return selectedItem;
    }
    
    
    //credit fields
    
    public void creditDateListener(DatePicker date , String dateFieldName, Label label){
       date.valueProperty().addListener((ObservableValue<? extends LocalDate> observable, LocalDate oldValue, LocalDate newValue) -> {
            boolean v=false;
            if(newValue == null){
               v= fv.dateValidator(oldValue, label);
            }else{
               v= fv.dateValidator(newValue, label);
            }
            switch(dateFieldName){
                case "disbursementDate":
                    Rocket.setValidatedLoanDisbursementDate(v);
                    break;
                case "issuingDate":
                    Rocket.setValidatedLoanIssuingDate(v);
            }
            
        });
        
    }
    
    public void openingBalanceValidator(TextField node, Label errorLabel){
        node.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                Rocket.setValidatedOpeningBalance(fv.integerFieldValidator(newValue, errorLabel));
            }
        });
    }
    
    
    public void creditFieldListener(TextField node,String fieldName, Label errorLabel) {

        node.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                
                switch(fieldName){
                    case "loanAmount":
                        Rocket.setValidatedLoanAmount(fv.integerFieldValidator(newValue, errorLabel));
                        break;
                    case "serviceCharge":
                        Rocket.setValidatedLoanServiceCharge(fv.integerFieldValidator(newValue, errorLabel));
                        break;
                    case "loanWithServiceCharge":
                        Rocket.setValidatedLoanPlusServiceCharge(fv.integerFieldValidator(newValue, errorLabel));
                        break;
                    case "loanId":
                         Rocket.setValidatedloanId(fv.loanIdValidator(newValue, errorLabel));
                        break;
                    case "typesOfLoan":
                        Rocket.setValidatedLoanType(fv.alphabetValidator(newValue, errorLabel));
                        break;
                    case "totalNumberOfInstallment":
                        Rocket.setValidatedLoanInstallmentTotal(fv.alphabetValidator(newValue, errorLabel));
                        break;
                    case "schemeName":
                        Rocket.setValidatedLoanSchemeName(fv.alphabetValidator(newValue, errorLabel));
                        break;
                    case "amountWeeklyInstallment":
                        Rocket.setValidatedLoanAmountperWeeklyIstall(fv.integerFieldValidator(newValue, errorLabel));
                        break;
                    case "mandatorySavingsWeek" :
                        Rocket.setValidatedLoanMandatorySavingWeek(fv.integerFieldValidator(newValue, errorLabel));
                        break;
                    case"issuingName":
                        Rocket.setValidatedLoanIssuer(fv.alphabetValidator(newValue, errorLabel));
                        break;
                    }
                
             //    Rocket.setValidOccupation(fv.textFieldValidator(newValue, errorLabel));
                 }
        });
    }
    
}
