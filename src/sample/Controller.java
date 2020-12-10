package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import models.Recipient;
import models.Supplier;

import java.util.Arrays;
import java.util.List;

public class Controller{

    @FXML private TextField rec0;
    @FXML private TextField rec1;

    @FXML private TextField sup0;
    @FXML private TextField sup1;
    @FXML private TextField sup2;

    @FXML private TextField purchasePrice0;
    @FXML private TextField purchasePrice1;
    @FXML private TextField purchasePrice2;

    @FXML private TextField sellingPrice0;
    @FXML private TextField sellingPrice1;

    @FXML private TextField costOfTransport00;
    @FXML private TextField costOfTransport01;
    @FXML private TextField costOfTransport10;
    @FXML private TextField costOfTransport11;
    @FXML private TextField costOfTransport20;
    @FXML private TextField costOfTransport21;

    @FXML private TextField blockedRow;
    @FXML private TextField blockedCol;

    @FXML private TextField result;

    @FXML private TextField o1;
    @FXML private TextField o2;
    @FXML private TextField point;

    @FXML private ChoiceBox cb1;
    @FXML private ChoiceBox cb2;

    @FXML private TextField min;
    @FXML private TextField max;
    @FXML private TextField cost;


    private TransportIssueAlgorithm transportIssueAlgorithm = new TransportIssueAlgorithm();
    String[][] paths = new String[3][5];

    public void handleCb2Click(){
        String checkedValue = this.cb1.getValue().toString();
        ObservableList<String> db= FXCollections.observableArrayList();
        switch(checkedValue) {
            case "O1":
                for(int i=0;i<5;i++){
                    if(paths[0][i] !=null && paths[0][i]!="") {
                        db.add(paths[0][i]);
                    }
                    else
                    {
                        break;
                    }
                }
                break;

            case "O2":
                for(int i=0;i<5;i++){
                    if(paths[1][i] !=null && paths[1][i]!="") {
                        db.add(paths[1][i]);
                    }
                    else
                    {
                        break;
                    }
                }
                break;

            case "punkt pośredniczący":
                for(int i=0;i<5;i++){
                    if(paths[2][i] !=null && paths[2][i]!="") {
                        db.add(paths[2][i]);
                    }
                    else
                    {
                        break;
                    }
                }
                break;
        }
        this.cb2.setItems(db);
    }
    public void handleResetBeforeButton(){
        this.min.setText("");
        this.max.setText("");
            }

    public void handleBeforeConfirmButton() {
        ObservableList<String> db= FXCollections.observableArrayList("O1", "O2", "punkt pośredniczący");
        this.cb1.setItems(db);

        String before_o1 = this.o1.getText();
        String before_o2 = this.o2.getText();
        String before_point = this.point.getText();

        List<String> o1List = Arrays.asList(before_o1.split(","));
        List<String> o2List = Arrays.asList(before_o2.split(","));
        List<String> pointList = Arrays.asList(before_point.split(","));

        Integer count =0;
        for(String before : o1List){
            paths[0][count]=before;
            count++;
        }
        count =0;
        for(String before : o2List){
            paths[1][count]=before;
            count++;
        }

        count =0;
        for(String before : pointList){
            paths[2][count]=before;
            count++;
        }
    }



    public void handleConfirmButton() {
        this.transportIssueAlgorithm.getSuppliers()[0].setSupply(Integer.parseInt(this.sup0.getText()));
        this.transportIssueAlgorithm.getSuppliers()[0].setSupply(Integer.parseInt(this.sup0.getText()));
        this.transportIssueAlgorithm.getSuppliers()[0].setPurchasePrice(Integer.parseInt(this.purchasePrice0.getText()));
        this.transportIssueAlgorithm.getSuppliers()[1].setSupply(Integer.parseInt(this.sup1.getText()));
        this.transportIssueAlgorithm.getSuppliers()[1].setPurchasePrice(Integer.parseInt(this.purchasePrice1.getText()));
        this.transportIssueAlgorithm.getSuppliers()[2].setSupply(Integer.parseInt(this.sup2.getText()));
        this.transportIssueAlgorithm.getSuppliers()[2].setPurchasePrice(Integer.parseInt(this.purchasePrice2.getText()));

        this.transportIssueAlgorithm.getRecipients()[0].setDemand(Integer.parseInt(this.rec0.getText()));
        this.transportIssueAlgorithm.getRecipients()[0].setSellingPrice(Integer.parseInt(this.sellingPrice0.getText()));
        this.transportIssueAlgorithm.getRecipients()[1].setDemand(Integer.parseInt(this.rec1.getText()));
        this.transportIssueAlgorithm.getRecipients()[1].setSellingPrice(Integer.parseInt(this.sellingPrice1.getText()));

        this.transportIssueAlgorithm.setCostOfTransport(0, 0, Integer.parseInt(this.costOfTransport00.getText()));
        this.transportIssueAlgorithm.setCostOfTransport(0, 1, Integer.parseInt(this.costOfTransport01.getText()));
        this.transportIssueAlgorithm.setCostOfTransport(1, 0, Integer.parseInt(this.costOfTransport10.getText()));
        this.transportIssueAlgorithm.setCostOfTransport(1, 1, Integer.parseInt(this.costOfTransport11.getText()));
        this.transportIssueAlgorithm.setCostOfTransport(2, 0, Integer.parseInt(this.costOfTransport20.getText()));
        this.transportIssueAlgorithm.setCostOfTransport(2, 1, Integer.parseInt(this.costOfTransport21.getText()));

        this.transportIssueAlgorithm.setBlockedRowIndex(Integer.parseInt(this.blockedRow.getText()));
        this.transportIssueAlgorithm.setBlockedColIndex(Integer.parseInt(this.blockedCol.getText()));
    }

    public void handleResultButton() {
        double optimizedProfit = transportIssueAlgorithm.countOptimizedProfit();
        result.setText(String.valueOf(optimizedProfit));
    }

    
}
