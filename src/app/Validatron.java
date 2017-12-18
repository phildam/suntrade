/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import entities.Account;
import entities.Loan;
import java.time.LocalDate;
import java.util.List;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.paint.Color;
import suntradeapp.UtilityExtractor;

/**
 *
 * @author g33k5qu4d
 */
public class Validatron {

    Rocket rock = new Rocket();

    public boolean intValidator(String newValue, Label label) {
        boolean flag;
        String txt = null;
        if (newValue.matches("\\d+")) {
            flag = true;
            txt = " ";
            setLabelImageAndTxt(flag, label, txt);
        } else {
            flag = false;
            txt = "Invalid character in field";
            setLabelImageAndTxt(flag, label, txt);
        }
        return flag;
    }

    public boolean alphabetValidator(String newValue, Label label) {
        boolean flag;
        String txt = null;
        if (newValue.matches("\\w.+")) {
            flag = true;

            txt = "";
            setLabelImageAndTxt(flag, label, txt);
        } else {
            flag = false;
            txt = "Invalid character in field";
            setLabelImageAndTxt(flag, label, txt);
        }
        return flag;
    }

    public boolean textFieldValidator(String newValue, Label label) {
        label.autosize();
        boolean flag;
        if (newValue.matches(".*?\\d+?.*")) {
            flag = false;
            if (newValue.length() > 0) {
                String txt = "Digits disallowed";
                setLabelImageAndTxt(flag, label, txt);
            }

        } else if (newValue.isEmpty()) {
            flag = false;
            String txt = "Field cannot be empty";
            setLabelImageAndTxt(flag, label, txt);

        } else if (newValue.length() > 50) {
            flag = false;
            String txt = "Field too long";
            setLabelImageAndTxt(flag, label, txt);
        } else if (newValue.matches(".*?\\d+.*") && newValue.length() > 3) {
            String txt = "Digit not allowed in field";
            flag = false;
            setLabelImageAndTxt(flag, label, txt);
        } else if (newValue.matches("^\\w{2,50}\\s*?.*?")) {
            String txt = "Valid";
            flag = true;
            setLabelImageAndTxt(flag, label, txt);
        } else {
            flag = false;
            String txt = "Field value invalid";
            setLabelImageAndTxt(flag, label, txt);
        }
        return flag;
    }

    public boolean accountnumberValidator(String newValue, Label label) {
        label.autosize();
        boolean flag = false;
        String txt;
        if (newValue != null || !newValue.isEmpty()) {
            UtilityExtractor util = new UtilityExtractor();

            List list = util.queryTable("FROM Account a where a.accountNumber= '" + newValue + "'");
            if (list.isEmpty()) {
                txt = "Passbook Number is available";
                flag = true;
                setLabelImageAndTxt(flag, label, txt);
            } else {
                for (Object s : list) {
                    Account obj = (Account) s;
                    if (obj.getAccountNumber() == null ? newValue == null : obj.getAccountNumber().equals(newValue)) {
                        txt = "Passbook Number already exist,input a non-existing number ";
                        flag = false;
                        setLabelImageAndTxt(flag, label, txt);
                    }
                }

            }
        }
        return flag;
    }

    public Boolean loanIdValidator(String newvalue, Label label) {
        String txt = " ";
        boolean flag = false;
        if (newvalue != null || !newvalue.isEmpty()) {
            UtilityExtractor util = new UtilityExtractor();

            List list = util.queryTable("FROM Loan l where l.customerLoanNumber= '" + newvalue + "'");
            if (list.isEmpty()) {
                txt = "Loan Id available";
                flag = true;
                setLabelImageAndTxt(flag, label, txt);
            } else {
                for (Object s : list) {
                    Loan obj = (Loan) s;
                    if (obj.getCustomerLoanNumber() == null ? newvalue == null : obj.getCustomerLoanNumber().equals(newvalue)) {
                        txt = "Loan Id already exist,input a unique Id value";
                        flag = false;
                        setLabelImageAndTxt(flag, label, txt);
                    }
                }

            }
        }

        return flag;
    }

    public Boolean integerFieldValidator(String newValue, Label label) {
        label.autosize();
        String txt = " ";
        boolean flag;
        if (newValue.matches("\\d+?")) {
            txt = "Integer only Field";
            flag = true;
            setLabelImageAndTxt(flag, label, txt);
        } else {
            txt = "Non-integer and whitespace dissallowed in Field";
            flag = false;
            setLabelImageAndTxt(flag, label, txt);
        }

        return flag;
    }

    public Boolean dateValidator(LocalDate newValue, Label label) {
        boolean flag;
        String txt = null;

        if (newValue != null) {
            txt = "Date field Validated";
            flag = true;
            setLabelImageAndTxt(flag, label, txt);
        } else {
            txt = "Value not Valid";
            flag = false;
            setLabelImageAndTxt(flag, label, txt);
        }

        return flag;
    }

    public boolean imageValidator(Image im, Label label) {
        boolean flag = false;
        String txt = "";
        if (im != null) {
            if (im.isBackgroundLoading()) {
                txt = "Image loading";
                flag = false;

            } else if (im.isError()) {
                txt = "Error in loading Image";
                flag = false;
            }else {
                PixelReader px=im.getPixelReader();
                System.out.println(im.getProgress());
                txt="Image Loaded";
                flag=true;
            }
        }
        setLabelImageAndTxt(flag, label, txt);
        return flag;
    }

    public void emailValidator(String newValue, Label label) {
        boolean flag;
        if (!newValue.matches("\\w{1,100}@.+\\.com$")) {
            String txt = "Invalid Email Address";
            flag = false;
            setLabelImageAndTxt(flag, label, txt);

        } else if (newValue.isEmpty()) {
            String txt = "Empty email address";
            flag = false;
            setLabelImageAndTxt(flag, label, txt);
        } else if (newValue.matches("^\\S+@\\w+\\.com|COM$")) {
            flag = true;
            String txt = "Email validated";
            setLabelImageAndTxt(flag, label, txt);
        } else {
            flag = false;
            String txt = "Invalid Input";
            setLabelImageAndTxt(flag, label, txt);
        }

        Rocket.setValidEmail(flag);
    }

    public void addressvalidator(String newValue, Label label) {
        boolean flag;
        if (newValue.matches(".*{5,250}.*?")) {
            flag = true;
            String txt = "Address validated";
            setLabelImageAndTxt(flag, label, txt);
        } else {
            flag = false;
            String txt = "Invalid Address";
            setLabelImageAndTxt(flag, label, txt);
        }

        Rocket.setValidAddress(flag);
    }

    public void phoneNumber(String newValue, Label label) {
        boolean flag;
        if (newValue.matches("0[7-9][0-2]\\d{8}")) {
            flag = true;
            String txt = "Phone Number valid";
            setLabelImageAndTxt(flag, label, txt);
        } else {
            flag = false;
            String txt = "Invalid phone Number";
            setLabelImageAndTxt(flag, label, txt);
        }

        Rocket.setValidPhone(flag);
    }

    public boolean choiceBoxValidator(String newValue, Label label) {
        boolean flag = false;
        if (!newValue.isEmpty()) {
            flag = true;
            String txt = newValue + " selected";
            setLabelImageAndTxt(flag, label, newValue);
        }
        return flag;
    }

    public void setLabelImageAndTxt(boolean flag, Label label, String txt) {
        Image im = null;
        label.setStyle("-fx-font: italic bold 16 san-serif; ");
        if (flag) {
            im = new Image(getClass().getResourceAsStream("/images/passed.png"));
            label.setTextFill(Color.GREEN);

        } else if (!flag) {
            im = new Image(getClass().getResourceAsStream("/images/stat_notify_error.png"));
            label.setTextFill(Color.RED);
        }
        label.setText(txt);
        label.setGraphic(new ImageView(im));
        label.setGraphicTextGap(5.0);
    }

}
