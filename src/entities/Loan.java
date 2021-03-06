package entities;
// Generated Feb 12, 2016 2:24:18 PM by Hibernate Tools 4.3.1


import java.util.Date;

/**
 * Loan generated by hbm2java
 */
public class Loan  implements java.io.Serializable {


     private String customerLoanNumber;
     private String accountNumber;
     private String totalistallmentdays;
     private Double loanAmount;
     private Double serviceCharge;
     private Double loantotal;
     private Double currentLoanBalance;
     private String loantype;
     private Date dateofdisbursement;
     private Double weeklyInstallment;
     private String loanCompleteStatus;

    public Loan() {
    }

	
    public Loan(String customerLoanNumber) {
        this.customerLoanNumber = customerLoanNumber;
    }
    public Loan(String customerLoanNumber, String accountNumber, String totalistallmentdays, Double loanAmount, Double serviceCharge, Double loantotal, Double currentLoanBalance, String loantype, Date dateofdisbursement, Double weeklyInstallment, String loanCompleteStatus) {
       this.customerLoanNumber = customerLoanNumber;
       this.accountNumber = accountNumber;
       this.totalistallmentdays = totalistallmentdays;
       this.loanAmount = loanAmount;
       this.serviceCharge = serviceCharge;
       this.loantotal = loantotal;
       this.currentLoanBalance = currentLoanBalance;
       this.loantype = loantype;
       this.dateofdisbursement = dateofdisbursement;
       this.weeklyInstallment = weeklyInstallment;
       this.loanCompleteStatus = loanCompleteStatus;
    }
   
    public String getCustomerLoanNumber() {
        return this.customerLoanNumber;
    }
    
    public void setCustomerLoanNumber(String customerLoanNumber) {
        this.customerLoanNumber = customerLoanNumber;
    }
    public String getAccountNumber() {
        return this.accountNumber;
    }
    
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
    public String getTotalistallmentdays() {
        return this.totalistallmentdays;
    }
    
    public void setTotalistallmentdays(String totalistallmentdays) {
        this.totalistallmentdays = totalistallmentdays;
    }
    public Double getLoanAmount() {
        return this.loanAmount;
    }
    
    public void setLoanAmount(Double loanAmount) {
        this.loanAmount = loanAmount;
    }
    public Double getServiceCharge() {
        return this.serviceCharge;
    }
    
    public void setServiceCharge(Double serviceCharge) {
        this.serviceCharge = serviceCharge;
    }
    public Double getLoantotal() {
        return this.loantotal;
    }
    
    public void setLoantotal(Double loantotal) {
        this.loantotal = loantotal;
    }
    public Double getCurrentLoanBalance() {
        return this.currentLoanBalance;
    }
    
    public void setCurrentLoanBalance(Double currentLoanBalance) {
        this.currentLoanBalance = currentLoanBalance;
    }
    public String getLoantype() {
        return this.loantype;
    }
    
    public void setLoantype(String loantype) {
        this.loantype = loantype;
    }
    public Date getDateofdisbursement() {
        return this.dateofdisbursement;
    }
    
    public void setDateofdisbursement(Date dateofdisbursement) {
        this.dateofdisbursement = dateofdisbursement;
    }
    public Double getWeeklyInstallment() {
        return this.weeklyInstallment;
    }
    
    public void setWeeklyInstallment(Double weeklyInstallment) {
        this.weeklyInstallment = weeklyInstallment;
    }
    public String getLoanCompleteStatus() {
        return this.loanCompleteStatus;
    }
    
    public void setLoanCompleteStatus(String loanCompleteStatus) {
        this.loanCompleteStatus = loanCompleteStatus;
    }




}


