/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ComponentUpdaterClass;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author g33k5qu4d
 */
public class LoanRepayment {
     private final SimpleStringProperty customerLoanNumber;
    private final SimpleStringProperty paydate;
    private final SimpleStringProperty amount;
    
    public LoanRepayment(String customerLoanNumber,String payDate, String amount){
        this.customerLoanNumber=new SimpleStringProperty(customerLoanNumber);
        this.paydate=new SimpleStringProperty(payDate);
        this.amount=new SimpleStringProperty(amount);
    }
    
     public String getCustomerLoanNumber() {
        return customerLoanNumber.get();
    }

    public void setCustomerLoanNumber(String cln) {
        customerLoanNumber.set(cln);
    }
    
    public String getPaydate() {
        return paydate.get();
    }

    public void setPaydate(String date) {
        paydate.set(date);
    }
    public String getAmount(){
        return amount.get();
    }
    
    public void setAmount(String amo){
        amount.set(amo);
    }
    
}
