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
public class AccountTransaction {
    
    private final SimpleStringProperty transactionAmount;
    private final SimpleStringProperty transactionDate;
    private final SimpleStringProperty previousBalance;
    private final SimpleStringProperty  accountNumber; 
    private final SimpleStringProperty transactionType;
    
    public AccountTransaction(String accountNumber,String transactionAmount,String transactionType, String transactionDate, String previousBalance){
        this.previousBalance=new SimpleStringProperty(previousBalance);
        this.transactionAmount=new SimpleStringProperty(transactionAmount);
        this.transactionDate=new SimpleStringProperty(transactionDate);
        this.transactionType=new SimpleStringProperty(transactionType);
        this.accountNumber=new SimpleStringProperty(accountNumber);
    }

    public String getTransactionAmount() {
        return transactionAmount.get();
    }

    public void setTransactionAmount(String depo) {
        transactionAmount.set(depo);
    }

    public String getTransactionDate() {
        return transactionDate.get();
    }

    public void setTransactionDate(String day) {
        transactionDate.set(day);
    }

    public String getPreviousBalance() {
        return previousBalance.get();
    }

    public void setPreviousBalance(String bal) {
        previousBalance.set(bal);
    }

    public String getTransactionType() {
        return transactionType.get();
    }

    public void setTransactionType(String ty) {
        transactionType.set(ty);
    }
    
    public String getAccountNumber(){
        return accountNumber.get();
    }
    public void setAccountBalance(String accnumber){
        accountNumber.set(accnumber);
    }

    
}
