/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

/**
 *
 * @author g33k5qu4d
 */
public class Rocket {

    private static boolean validatedloanId;
    private static boolean validatedLoanAmount;
    private static boolean validatedLoanServiceCharge;
    private static boolean validatedLoanPlusServiceCharge;
    private static boolean validatedLoanType;
    private static boolean validatedLoanInstallmentTotal;
    private static boolean validatedLoanSchemeName;
    private static boolean validatedLoanAmountperWeeklyIstall;
    private static boolean validatedLoanMandatorySavingWeek;
    private static boolean validatedLoanIssuer;
    private static boolean validatedLoanDisbursementDate;
    private static boolean validatedLoanIssuingDate;
    
    
    public static boolean validatedSurname;
    public static boolean validatedEmail;
    public static boolean validatedDob;
    public static boolean validatedPhone;
    public static boolean validatedAddress;
    public static boolean validatedOtherName;
    private static boolean validatedCountry;
    private static boolean validatedGender;
    private static boolean validatedMaritalstatus;
    private static boolean occupation;
     
    private static boolean validatedGroupName;
    private static boolean validatedGroupNo;
    private static boolean validatedMembershipDate;
    private static boolean validatedState;
    private static boolean validatedLGA;
   // private static boolean validatedCustomerSVN;
    private static boolean validatedAccountNumber;
    private static boolean validatedIssuingName;
    private static boolean validatedIssuingDate;
    private static boolean validatedOpeningBalance;
    
    private static boolean validatedAccountPassport;
    
    
    public static boolean ValidatedSuccessfulCommit;

    public static boolean isValidatedOpeningBalance() {
        return validatedOpeningBalance;
    }

    public static void setValidatedOpeningBalance(boolean aValidatedOpeningBalance) {
        validatedOpeningBalance = aValidatedOpeningBalance;
    }
    private boolean dataReady;
   

    public Rocket() {
        
    }

    public static void setSuccessfulCommit(boolean value) {
        ValidatedSuccessfulCommit = value;
    }

    public static boolean isSuccessfulCommit() {
        return ValidatedSuccessfulCommit;
    }

    public static void setValidSurname(boolean value) {
        validatedSurname = value;
    }

    public boolean isValidatedSurname() {
        return validatedSurname;
    }

    public static void setValidOccupation(boolean value) {
        occupation = value;
    }

    public boolean isValidatedOccupation() {
        return occupation;
    }

    public static void setValidOtherName(boolean value) {
        validatedOtherName = value;
    }

    public boolean isValidatedOtherName() {
        return validatedOtherName;
    }

    public static void setValidEmail(boolean value) {
        validatedEmail = value;
    }

    public boolean isValidatedEmail() {
        return validatedEmail;
    }

    public static void setValidDOB(boolean value) {
        validatedDob = value;
    }

    public boolean isValidatedDOB() {
        return validatedDob;
    }

    public static void setValidPhone(boolean value) {
        validatedPhone = value;
    }

    public boolean isValidatedAddress() {
        return validatedAddress;
    }

    public static void setValidAddress(boolean value) {
        validatedAddress = value;
    }

    public boolean isValidatedPhone() {
        return validatedPhone;
    }

    public static void setValid(boolean value) {
        validatedPhone = value;
    }

    public static boolean isValidatedCountry() {
        return validatedCountry;
    }

    public static void setValidatedCountry(boolean aValidatedCountry) {
        validatedCountry = aValidatedCountry;
    }

    public static boolean isValidatedGender() {
        return validatedGender;
    }

    public static void setValidatedGender(boolean aValidatedGender) {
        validatedGender = aValidatedGender;
    }

    public static boolean isValidatedMaritalstatus() {
        return validatedMaritalstatus;
    }

    public static void setValidatedMaritalstatus(boolean aValidatedMaritalstatus) {
        validatedMaritalstatus = aValidatedMaritalstatus;
    }

    
    public void setDataIsReady(boolean flag) {
        dataReady = flag;
    }

    public  boolean memberDataIsReady() {
        dataReady = isValidatedAddress() && isValidatedSurname() && isValidatedDOB()
                && isValidatedEmail() && isValidatedPhone() && isValidatedCountry()
                && isValidatedGender() && isValidatedMaritalstatus() && isValidatedAccountNumber() && isValidatedOpeningBalance()
               // && isValidatedCustomerSVN()
                && isValidatedGroupName() && isValidatedGroupNo() && isValidatedIssuingDate()
                ;
        return dataReady;

    }
    
    public boolean  firstTimerDataIsReady(){
        dataReady=isValidatedSurname() && isValidatedOtherName() &&
                isValidatedGender() && isValidatedMaritalstatus()
                && isValidatedEmail() && isValidatedPhone() && isValidatedMembershipDate()
                && isValidatedAddress() //&& isValidatedCustomerSVN()
                && isValidatedGroupName() && isValidatedGroupNo()
                && isValidatedIssuingDate() && isValidatedIssuingName()
                && isValidatedLGA() && isValidatedState();
        return true;
    }

    public static boolean isValidatedGroupName() {
        return validatedGroupName;
    }

    public static void setValidatedGroupName(boolean aValidatedGroupName) {
        validatedGroupName = aValidatedGroupName;
    }

    public static boolean isValidatedGroupNo() {
        return validatedGroupNo;
    }

    public static void setValidatedGroupNo(boolean aValidatedGroupNo) {
        validatedGroupNo = aValidatedGroupNo;
    }

    public static boolean isValidatedMembershipDate() {
        return validatedMembershipDate;
    }

    public static void setValidatedMembershipDate(boolean aValidatedMembershipDate) {
        validatedMembershipDate = aValidatedMembershipDate;
    }

    public static boolean isValidatedState() {
        return validatedState;
    }

    public static void setValidatedState(boolean aValidatedState) {
        validatedState = aValidatedState;
    }

    public static boolean isValidatedLGA() {
        return validatedLGA;
    }

    public static void setValidatedLGA(boolean aValidatedLGA) {
        validatedLGA = aValidatedLGA;
    }

  //  public static boolean isValidatedCustomerSVN() {
      //  return validatedCustomerSVN;
   // }

  //  public static void setValidatedCustomerSVN(boolean aValidatedCustomerSVN) {
   //     validatedCustomerSVN = aValidatedCustomerSVN;
  //  }

    public static boolean isValidatedAccountNumber() {
        return validatedAccountNumber;
    }

    public static void setValidatedAccountNumber(boolean aValidatedAccountNumber) {
        validatedAccountNumber = aValidatedAccountNumber;
    }

    public static boolean isValidatedIssuingName() {
        return validatedIssuingName;
    }

    public static void setValidatedIssuingName(boolean aValidatedIssuingName) {
        validatedIssuingName = aValidatedIssuingName;
    }

    public static boolean isValidatedIssuingDate() {
        return validatedIssuingDate;
    }

    public static void setValidatedIssuingDate(boolean aValidatedIssuingDate) {
        validatedIssuingDate = aValidatedIssuingDate;
    }

    
    
    public static boolean isValidatedloanId() {
        return validatedloanId;
    }

    public static void setValidatedloanId(boolean aValidatedloanId) {
        validatedloanId = aValidatedloanId;
    }

    public static boolean isValidatedLoanAmount() {
        return validatedLoanAmount;
    }

    public static void setValidatedLoanAmount(boolean aValidatedLoanAmount) {
        validatedLoanAmount = aValidatedLoanAmount;
    }

    public static boolean isValidatedLoanServiceCharge() {
        return validatedLoanServiceCharge;
    }

    public static void setValidatedLoanServiceCharge(boolean aValidatedLoanServiceCharge) {
        validatedLoanServiceCharge = aValidatedLoanServiceCharge;
    }

    public static boolean isValidatedLoanPlusServiceCharge() {
        return validatedLoanPlusServiceCharge;
    }

    public static void setValidatedLoanPlusServiceCharge(boolean aValidatedLoanPlusServiceCharge) {
        validatedLoanPlusServiceCharge = aValidatedLoanPlusServiceCharge;
    }

    public static boolean isValidatedLoanType() {
        return validatedLoanType;
    }

    public static void setValidatedLoanType(boolean aValidatedLoanType) {
        validatedLoanType = aValidatedLoanType;
    }

    public static boolean isValidatedLoanInstallmentTotal() {
        return validatedLoanInstallmentTotal;
    }

    public static void setValidatedLoanInstallmentTotal(boolean aValidatedLoanInstallmentTotal) {
        validatedLoanInstallmentTotal = aValidatedLoanInstallmentTotal;
    }

    public static boolean isValidatedLoanSchemeName() {
        return validatedLoanSchemeName;
    }

    public static void setValidatedLoanSchemeName(boolean aValidatedLoanSchemeName) {
        validatedLoanSchemeName = aValidatedLoanSchemeName;
    }

    public static boolean isValidatedLoanAmountperWeeklyIstall() {
        return validatedLoanAmountperWeeklyIstall;
    }

    public static void setValidatedLoanAmountperWeeklyIstall(boolean aValidatedLoanAmountperWeeklyIstall) {
        validatedLoanAmountperWeeklyIstall = aValidatedLoanAmountperWeeklyIstall;
    }

    public static boolean isValidatedLoanMandatorySavingWeek() {
        return validatedLoanMandatorySavingWeek;
    }

    public static void setValidatedLoanMandatorySavingWeek(boolean aValidatedLoanMandatorySavingWeek) {
        validatedLoanMandatorySavingWeek = aValidatedLoanMandatorySavingWeek;
    }

    public static boolean isValidatedLoanIssuer() {
        return validatedLoanIssuer;
    }

    public static void setValidatedLoanIssuer(boolean aValidatedLoanIssuer) {
        validatedLoanIssuer = aValidatedLoanIssuer;
    }

    public static boolean isValidatedLoanDisbursementDate() {
        return validatedLoanDisbursementDate;
    }

    public static void setValidatedLoanDisbursementDate(boolean aValidatedLoanDisbursementDate) {
        validatedLoanDisbursementDate = aValidatedLoanDisbursementDate;
    }

    public static boolean isValidatedLoanIssuingDate() {
        return validatedLoanIssuingDate;
    }

    public static void setValidatedLoanIssuingDate(boolean aValidatedLoanIssuingDate) {
        validatedLoanIssuingDate = aValidatedLoanIssuingDate;
    }

    public static boolean isValidatedAccountPassport() {
        return validatedAccountPassport;
    }

    public static void setValidatedAccountPassport(boolean aValidatedAccountPassport) {
        validatedAccountPassport = aValidatedAccountPassport;
    }

    
    
}
