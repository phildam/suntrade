package entities;
// Generated Feb 12, 2016 2:24:18 PM by Hibernate Tools 4.3.1


import java.util.Date;

/**
 * Transact generated by hbm2java
 */
public class Transact  implements java.io.Serializable {


     private int id;
     private String accountNumber;
     private Date transactionDate;
     private String transactionType;
     private Double transactionAmount;
     private Double previousBalance;

    public Transact() {
    }

    public Transact(String accountNumber, Date transactionDate, String transactionType, Double transactionAmount, Double previousBalance) {
       this.accountNumber = accountNumber;
       this.transactionDate = transactionDate;
       this.transactionType = transactionType;
       this.transactionAmount = transactionAmount;
       this.previousBalance = previousBalance;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public String getAccountNumber() {
        return this.accountNumber;
    }
    
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
    public Date getTransactionDate() {
        return this.transactionDate;
    }
    
    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }
    public String getTransactionType() {
        return this.transactionType;
    }
    
    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }
    public Double getTransactionAmount() {
        return this.transactionAmount;
    }
    
    public void setTransactionAmount(Double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }
    public Double getPreviousBalance() {
        return this.previousBalance;
    }
    
    public void setPreviousBalance(Double previousBalance) {
        this.previousBalance = previousBalance;
    }




}

