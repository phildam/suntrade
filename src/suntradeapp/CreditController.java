/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package suntradeapp;

import app.ModelListener;
import app.Rocket;
import entities.Customer;
import entities.Loan;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 * FXML Controller class
 *
 * @author g33k5qu4d
 */
public class CreditController implements Initializable {

    @FXML
    private VBox formVBox;
    @FXML
    private TextField loanerSurname;
    @FXML
    private TextField loanerothernames;
    @FXML
    private TextField loanerphonenumber;
    @FXML
    private TextField loanerAddress;
    @FXML
    private ImageView lpassport;
    @FXML
    private Label elligibilitychecklabel;
    @FXML
    private TextField loanAmount;
    @FXML
    private TextField mandatorySavings;
    @FXML
    private TextField serviceCharge;
    @FXML
    private DatePicker dateOfDisbursement;
    @FXML
    private TextField schemeName;
    @FXML
    private TextField loanWithservicecharge;
    @FXML
    private TextField installmentTotal;
    @FXML
    private TextField loanType;
    @FXML
    private TextField issuingName;
    @FXML
    private DatePicker LoanissuingDate;
    @FXML
    private Button approveLoan;
    @FXML
    private AnchorPane creditBackgroud;
    @FXML
    private BorderPane loaderCore;
    @FXML
    private TextField loanerAccountNumber;
    @FXML
    private TextField loanergroupname;
    @FXML
    private TextField loanID;
    @FXML
    private TextField weeklyInstallment;
    @FXML
    private ProgressIndicator progressIndi;
    @FXML
    private Label infoLabel;

    ModelListener model = new ModelListener();

    @FXML
    private Button checkEligible;
    @FXML
    private ListView<String> reportListview;

    private boolean activeLoan;

    @FXML
    private VBox contentVBox;
    @FXML
    private VBox detailsBlock3;
    @FXML
    private VBox detailsBlock1;
    @FXML
    private VBox detailsBlock2;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        reportListview.getItems().clear();
        //approveLoan.setDisable(activeLoan.get());
        checkEligible.setDisable(true);
        approveLoan.setDisable(true);
        progressIndi.setVisible(false);

        validator();
        CheckInts();
        getAccountDetails();

    }

    public void validator() {
        // model.
        model.creditFieldListener(loanID, "loanId", infoLabel);
        model.creditFieldListener(loanAmount, "loanAmount", infoLabel);
        model.creditFieldListener(serviceCharge, "serviceCharge", infoLabel);
        model.creditFieldListener(loanWithservicecharge, "loanWithServiceCharge", infoLabel);
        model.creditFieldListener(loanType, "typesOfLoan", infoLabel);
        model.creditFieldListener(installmentTotal, "totalNumberOfInstallment", infoLabel);
        model.creditFieldListener(issuingName, "issuingName", infoLabel);
        model.creditDateListener(LoanissuingDate, "issuingDate", infoLabel);
        model.creditDateListener(dateOfDisbursement, "disbursementDate", infoLabel);
        model.creditFieldListener(schemeName, "schemeName", infoLabel);
        model.creditFieldListener(weeklyInstallment, "amountWeeklyInstallment", infoLabel);
        model.creditFieldListener(mandatorySavings, "mandatorySavingsWeek", infoLabel);
    }

    public void TIListener(TextField text, Label infoLabel) {

        text.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent event) {
                progressIndi.setVisible(true);
            }
        });

        text.addEventFilter(KeyEvent.KEY_RELEASED, new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent event) {
                progressIndi.setVisible(false);

            }
        });

    }

    public void CheckInts() {
        TIListener(loanAmount, infoLabel);
        TIListener(loanWithservicecharge, infoLabel);
        TIListener(serviceCharge, infoLabel);
        TIListener(loanID, infoLabel);
        TIListener(issuingName, infoLabel);
        TIListener(loanType, infoLabel);
        TIListener(installmentTotal, infoLabel);
        TIListener(schemeName, infoLabel);
        TIListener(weeklyInstallment, infoLabel);
        TIListener(mandatorySavings, infoLabel);

    }

    public void getAccountDetails() {
        // if(!loanerAccountNumber.getText().isEmpty()){
        loanerAccountNumber.addEventHandler(KeyEvent.KEY_RELEASED, new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent event) {
                storeHere();
                reportListview.getItems().clear();
                checkEligible.setDisable(true);
                //storeHere();
                if (!loanerAccountNumber.getText().isEmpty()) {

                    String accountnumber = loanerAccountNumber.getText();
                    UtilityExtractor util = new UtilityExtractor();
                    String query = "FROM Customer c where c.accountNumber like '";
                    List list = util.queryTable(query + accountnumber + "'");
                    if (!list.isEmpty()) {
                        if (Common.activeAccount(accountnumber, util)) {

                            checkEligible.setDisable(false);
                            approveLoan.setDisable(false);
                            for (Object o : list) {
                                Customer c = (Customer) o;
                                loanerSurname.setText(c.getSurname());
                                reportListview.getItems().add("Surname: " + c.getSurname());
                                loanerothernames.setText(c.getOtherNames());
                                reportListview.getItems().add("Othernames: " + c.getOtherNames());
                                loanergroupname.setText(c.getGroupName());
                                reportListview.getItems().add("Group Name: " + c.getGroupName());
                                loanerphonenumber.setText(c.getPhoneNumber());
                                reportListview.getItems().add("Contact: " + c.getPhoneNumber());
                                loanerAddress.setText(c.getAddress());
                                reportListview.getItems().add("Address: " + c.getAddress());
                                InputStream fis = null;
                                if (c.getPictureurl() != null) {
                                    try {

                                        File file = new File(c.getPictureurl());
                                        if (file.exists()) {
                                            fis = new FileInputStream(file);
                                        } else {
                                            fis = getClass().getResourceAsStream("/icons/noIm.jpg");
                                        }

                                    } catch (FileNotFoundException ex) {
                                        Logger.getLogger(CreditController.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                    lpassport.setImage(new Image(fis));
                                    checkEligible.setStyle("-fx-background-color:red");
                                }
                            }
                        } else {
                            infoLabel.setTextFill(javafx.scene.paint.Color.RED);
                            infoLabel.setText("Account Deactivated");
                            checkEligible.setDisable(true);
                            approveLoan.setDisable(true);
                        }
                    } else {
                        checkEligible.setDisable(true);
                        approveLoan.setDisable(true);
                    }

                }
            }
        });
        //  }
    }

    @FXML
    private void savetoDatabase(ActionEvent event) {
        ModelListener model = new ModelListener();
        if (Rocket.isValidatedLoanAmount() && Rocket.isValidatedLoanAmountperWeeklyIstall() && Rocket.isValidatedLoanDisbursementDate()
                && Rocket.isValidatedLoanInstallmentTotal() && Rocket.isValidatedLoanIssuer() && Rocket.isValidatedLoanIssuingDate()
                && Rocket.isValidatedLoanMandatorySavingWeek() //&& Rocket.isValidatedLoanPlusServiceCharge()
                && Rocket.isValidatedLoanSchemeName() && Rocket.isValidatedLoanServiceCharge() && Rocket.isValidatedloanId() && isActiveLoan() == false) {
            SessionFactory factory = HibernateUtil.getSessionFactory();
            Session session = factory.openSession();
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                String loanId = loanID.getText();
                String accountNumber = loanerAccountNumber.getText();
                String totalinstallmentDays = installmentTotal.getText();
                Double amount = Double.parseDouble(loanAmount.getText());
                Double serviceCharg = Double.parseDouble(serviceCharge.getText());
                // loanWithservicecharge.textProperty().bind(null);
                Double loanTotal = amount + serviceCharg;
                Double currentBalance = loanTotal;
                String loantype = loanType.getText();
                Date dateOfDisbursemen = Date.valueOf(dateOfDisbursement.getValue());
                double weeklyinstall = Double.parseDouble(weeklyInstallment.getText());
                String completestatus = "false";
                Loan loan = new Loan(loanId, accountNumber, totalinstallmentDays, amount, serviceCharg, loanTotal, currentBalance, loantype, dateOfDisbursemen, weeklyinstall, completestatus);
                session.save(loan);
                tx.commit();
                clearAction();
                infoLabel.setText("Loan Application sucessful");
                reportListview.getItems().add("Loan Application Successful");
                reportListview.getItems().add("Account Name: " + loanerSurname + " " + loanerothernames);
                reportListview.getItems().add("Account Id: " + accountNumber);
                reportListview.getItems().add("Loan Id: " + loanId);
                reportListview.getItems().add("Requested Amount: NGN" + formatDouble(amount) + "k");
                reportListview.getItems().add("Service charge: NGN" + formatDouble(serviceCharg) + "k");
                reportListview.getItems().add("Loan + service charge: " + formatDouble(amount + serviceCharg) + "k");
                reportListview.getItems().add("Date of Disbursement: " + Common.stringedDate(dateOfDisbursemen));

            } catch (HibernateException e) {
                if (tx != null) {
                    tx.rollback();
                }
            } finally {
                session.close();
            }
        } else {
            infoLabel.setStyle("-fx-background-color:white");
            infoLabel.setText("Fill all fields and/or check your eligibility status");

        }
    }

    public void clearAction() {
        ObservableList<Node> list = detailsBlock1.getChildren();
        for (Node node : list) {
            System.out.print(node + " ");
            clearOut(node);

        }
        ObservableList<Node> list2 = detailsBlock2.getChildren();
        for (Node node : list2) {
            System.out.print(node + " ");
            clearOut(node);

        }
        ObservableList<Node> list3 = detailsBlock3.getChildren();
        for (Node node : list3) {
            System.out.print(node + " ");
            //  if(node == loanerAccountNumber){
            //      continue;
            //  }
            clearOut(node);

        }
    }

    public void clearOut(Node tx) {

        if (tx.getClass() == TextField.class) {

            System.out.println("TextField Class");
            TextField text = (TextField) tx;
            text.clear();
        } else if (tx.getClass() == DatePicker.class) {
            DatePicker date = (DatePicker) tx;
            date.setValue(null);
        } else if (tx.getClass() == ImageView.class) {
            ImageView iv = (ImageView) tx;
            iv.setImage(null);
        } else if (tx.getClass() == Label.class) {
            Label label = (Label) tx;
            label.setText(null);
        }
    }

    public void storeHere() {
        clearOut(LoanissuingDate);
        clearOut(dateOfDisbursement);
        clearOut(installmentTotal);
        clearOut(issuingName);
        clearOut(loanAmount);
        clearOut(loanID);
        clearOut(loanType);
        clearOut(loanWithservicecharge);
        clearOut(elligibilitychecklabel);
        clearOut(infoLabel);
        //clearOut(loanerAccountNumber);
        clearOut(loanerAddress);
        clearOut(loanerSurname);
        clearOut(loanergroupname);
        clearOut(loanerothernames);
        clearOut(loanerphonenumber);
        clearOut(lpassport);
        clearOut(schemeName);
        // clearOut(this);
    }

    @FXML
    private void clear(MouseEvent event) {

    }

    @FXML
    private void checkEligible(ActionEvent event
    ) {
        reportListview.getItems().clear();
        elligibilitychecklabel.setText(null);
        UtilityExtractor util = new UtilityExtractor();
        String loanStatusquery = "FROM Loan l where l.accountNumber ='";
        List li = util.queryTable(loanStatusquery + loanerAccountNumber.getText() + "'" + " and l.loanCompleteStatus='false'");
        if (!li.isEmpty()) {
            for (Object o : li) {
                reportListview.getItems().add(" ");
                reportListview.getItems().add("Loan history");
                Loan loan = (Loan) o;
                String status = loan.getLoanCompleteStatus();
                if (status.equalsIgnoreCase("false")) {
                    setActiveLoan(true);
                    approveLoan.setDisable(true);
                    elligibilitychecklabel.setStyle("-fx-fill:red;");
                    elligibilitychecklabel.setText("Your account is not eligible for any loan,check report log");
                    reportListview.getItems().add("Loan Id:" + loan.getCustomerLoanNumber());
                    reportListview.getItems().add("Loan Total: NGN" + formatDouble(loan.getLoantotal()));
                    reportListview.getItems().add("Loan balance: NGN" + formatDouble(loan.getCurrentLoanBalance()));
                    reportListview.getItems().add("Loan Status: Active");
                } else {
                    elligibilitychecklabel.setStyle("-fx-fill:blue;");
                    approveLoan.setDisable(false);
                    setActiveLoan(false);
                    elligibilitychecklabel.setText(loanerAccountNumber + "you have no pending loan balance,proceed...");

                }
            }
        } else {
            approveLoan.setDisable(false);
            setActiveLoan(false);

            elligibilitychecklabel.setText("No loan record on this account");
        }
    }

    public boolean isActiveLoan() {
        return activeLoan;
    }

    public void setActiveLoan(boolean activeLoan) {
        this.activeLoan = activeLoan;
    }

    public String formatDouble(double d) {
        return String.format("%,.2f", d);
    }

}
