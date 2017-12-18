package entities;
// Generated Feb 12, 2016 2:24:18 PM by Hibernate Tools 4.3.1


import java.util.Date;

/**
 * Account generated by hbm2java
 */
public class Account  implements java.io.Serializable {


     private String accountNumber;
     private Double balance;
     private Date dateModified;
     private Boolean active;
     
    public Account() {
    }

	
    public Account(String accountNumber) {
        this.accountNumber = accountNumber;
    }
    public Account(String accountNumber, Double balance, Date dateModified, Boolean active) {
       this.accountNumber = accountNumber;
       this.balance = balance;
       this.dateModified = dateModified;
       this.active=active;
    }
   
    public String getAccountNumber() {
        return this.accountNumber;
    }
    
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
    public Double getBalance() {
        return this.balance;
    }
    
    public void setBalance(Double balance) {
        this.balance = balance;
    }
    public Date getDateModified() {
        return this.dateModified;
    }
    
    public void setDateModified(Date dateModified) {
        this.dateModified = dateModified;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }




}


