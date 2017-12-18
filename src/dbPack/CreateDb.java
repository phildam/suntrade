/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbPack;

import entities.Databaseset;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import suntradeapp.HibernateUtil;

/**
 *
 * @author g33k5qu4d
 */
public class CreateDb {

    public String Group() {
        return "create table groups("
                + "group_name varchar(250),"
                + "group_no varchar(250),"
                + "Primary Key(group_name)"
                + ")";
    }

    //customer id =accountnumber

    public String account() {
        return "create table account("
                + "account_number varchar(250),"
                + "balance double,"
                + "active boolean,"
                + "date_modified date,"
                + "Primary Key(account_number)"
                + ") ";
    }

    public String customer() {
        return "create table customer("
                + "id INTEGER "
                + "PRIMARY KEY GENERATED ALWAYS AS IDENTITY"
                + "(START WITH 1,INCREMENT BY 1),"
                + "surname varchar(100),"
                + "other_names varchar(100),"
                + "gender varchar(100),"
                + "marital_status varchar(100),"
                + "email varchar(100),"
                + "occupation varchar(100),"
                + "group_name varchar(250),"
                + "phone_number varchar(100),"
                + "address varchar(250),"
                + "country varchar(100),"
                + "state varchar(100),"
                + "lga varchar(100),"
                + "dob date,"
                + "isuing_date date,"
                + "pictureurl varchar(250),"
                + "account_number varchar(250),"
                + "issuing_name varchar(100),"
                + "FOREIGN KEY (Group_name)"
                + "REFERENCES groups (Group_name)"
                + "ON DELETE SET NULL ON UPDATE RESTRICT, "
                + "FOREIGN KEY (account_number)"
                + "REFERENCES account (account_number)"
                + "ON DELETE CASCADE ON UPDATE RESTRICT"
                + ") ";
    }

    public String Transaction() {
        return "create table transact("
                + "id INTEGER "
                + "PRIMARY KEY GENERATED ALWAYS AS IDENTITY"
                + "(START WITH 1,INCREMENT BY 1),"
                + "account_number varchar(250),"
                + "transaction_date date,"
                + "transaction_type varchar(20),"
                + "transaction_amount double,"
                + "previous_balance double,"
                + "FOREIGN KEY (account_number)"
                + "REFERENCES account (account_number)"
                + "ON DELETE CASCADE ON UPDATE RESTRICT"
                + ") ";
    }

  
    public String dataBaseSet() {
        return "create table databaseSet("
                + "Id INTEGER "
                + "PRIMARY KEY GENERATED ALWAYS AS IDENTITY"
                + "(START WITH 1,INCREMENT BY 1),"
                + "account boolean,"
                + "groups boolean,"
                + "customer boolean,"
                + "transact boolean,"
                + "settings boolean,"
                + "loanRepayment boolean,"
                + "loan boolean"
                + ")";
    }

    public String setting() {
        return "create table setting("
                + "id INTEGER "
                + "PRIMARY KEY GENERATED ALWAYS AS IDENTITY"
                + "(START WITH 1,INCREMENT BY 1),"
                + "username varchar(250),"
                + "password varchar(250),"
                + "pictureurl varchar(250),"
                + "skipformcheck boolean,"
                + "snapshot boolean,"
                + "screenshot boolean,"
                + "stylesheet varchar(250)"
                + ")";
    }

    public String Repayment() {
        return "create table loanRepayment("
                + "loan_id INTEGER "
                + "PRIMARY KEY GENERATED ALWAYS AS IDENTITY"
                + "(START WITH 1,INCREMENT BY 1),"
                + "customer_loan_number varchar(250),"
                + "payDate date,"
                + "Amount double,"
                + "FOREIGN KEY (customer_loan_number)"
                + "REFERENCES loan (customer_loan_number)"
                + "ON DELETE CASCADE "
                + ")";
    }

    public String loanAccount() {
        return "create table loan ("
                + "customer_loan_number varchar(250),"
                + "account_number varchar(250),"
                + "totalIstallmentDays varchar(250),"
                + "loan_amount double,"
                + "service_charge double,"
                + "loanTotal double,"
                + "current_loan_balance double,"
                + "loantype varchar(250),"
                + "dateOfDisbursement date,"
                + "weekly_installment double,"
                + "loan_complete_status varchar(250),"
                + "Primary Key(customer_loan_number),"
                + "FOREIGN KEY (account_number)"
                + "REFERENCES account (account_number)"
                + "ON DELETE CASCADE "
                + ")";
    }

    private static boolean tableExist(String table) throws SQLException, IOException {
        boolean exist = false;
        DatabaseMetaData metadata = JDBCUtil.getConnection().getMetaData();
        ResultSet rs = metadata.getTables(null, null, table.toUpperCase(), null);
        exist = rs.next();
        System.out.println("Result set next: " + rs.next());
        return exist;
    }

    private static void createTables(Connection conn, String s) throws SQLException {
        Statement statement1 = null;
        try {
            statement1 = conn.createStatement();
            statement1.executeUpdate(s);

            JDBCUtil.commit(conn);
        } finally {
            if (conn != null) {
                JDBCUtil.closeStatement(statement1);
            }
        }
    }

    public static void updateSettings(String tableName, Boolean value) {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            Databaseset dbset = (Databaseset) session.get(Databaseset.class, 1);
            switch (tableName) {
                case "GROUPS":
                    dbset.setGroups(value);
                    session.update(dbset);
                    break;
                case "LOAN":
                    dbset.setLoan(value);
                    session.update(dbset);
                    break;
                case "ACCOUNT":
                    dbset.setAccount(value);
                    session.update(dbset);
                    break;
                case "CUSTOMER":
                    dbset.setCustomer(value);
                    session.update(dbset);
                    break;
                case "LOANREPAYMENT":
                    dbset.setLoanrepayment(value);
                    session.update(dbset);
                    break;
                case "TRANSACT":
                    dbset.setTransact(value);
                    session.update(dbset);
                    break;
                case "SETTING":
                    dbset.setSettings(value);
                    session.update(dbset);
                    break;

            }

            // session.update(dbset);
            session.getTransaction().commit();
        } catch (HibernateException he) {
            he.printStackTrace();
        }
    }

    public static void createDb(String tableName, String dbString) throws SQLException, IOException {

        if (tableExist(tableName) == false) {
            createTables(JDBCUtil.getConnection(), dbString);
            updateSettings(tableName, Boolean.FALSE);
            System.out.println(tableName + "isExist " + tableExist(tableName) + "****$##@@#$$%%^&&&");

        } else if (tableExist(tableName) == true) {
            updateSettings(tableName, Boolean.TRUE);
            System.out.println(tableName + " Table already exist ");
        }

    }
}
