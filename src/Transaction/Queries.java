/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Transaction;

/**
 *
 * @author g33k5qu4d
 */
public class Queries {
    
    public static final String cSurnameQuery = "from Customer c where c.surname like '";
    public static final String cOthernamesQuery = "from Customer c where c.otherNames like '";
    public static final String cPhoneNumberQuery = "from Customer c where c.phoneNumber like '";
    public static final String cMaritalStatusQuery = "from Customer c where c.maritalStatus like '";
    public static final String cCustomerAccounNumber = "from Customer c where c.accountNumber like '";
    public static final String cEmailQuery = "from Customer c where c.email like '";
    public static final String cAddressQuery = "from Customer c where c.address like '";
    public static final String cOccupationQuery = "from Customer c where c.occupation like '";
    public static final String cCountryQuery = "from Customer c where c.country like '";
    public static final String cGenderQuery = "from Customer c where c.gender like '";
     public static final String cGroupNumberQuery = "from Customer c where c.groupNumber like '";
    public static final String cLGA = "from Customer c where c.lga like '";
    public static final String cState = "from Customer c where c.state like '";
    public static final String cDateOfBirth = "from Customer c where c.dob like '";
    public static final String cIssuingDate = "from Customer c where c.issuinDate like '";
    public static final String cIssuingName = "from Customer c where c.issuingName like '";

    //Account queries
    public static final String aAccountNumber = "from Account a where a.accountnumber like '";
    public static final String aBalance = "from Account a where a.balance like '";
    public static final String aDateModified = "from Account a where a.datemodified like '";
    
    public static final String gGroupNameQuery = "from Groups c where c.groupName like '";
   

    //AccountHolder
    public static final String ahAccountNumber = "from Customer c where c.othername like '";
    public static final String ahCustomerID = "from Customer c where c.othername like '";

    //Transaction
    public static final String transactionID = "from Customer c where c.othername like '";
    public static final String transactionType = "from Customer c where c.othername like '";
    public static final String transactionDate = "from Customer c where c.othername like '";
    public static final String transactionPreviousBalance = "from Customer c where c.othername like '";
    public static final String transactionAccountNumber = "from Customer c where c.othername like '";
    public static final String transactionAmount = "from Customer c where c.othername like '";

}
