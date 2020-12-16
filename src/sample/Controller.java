package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import models.Nodes;

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

    @FXML private ChoiceBox cb1;
    @FXML private ChoiceBox cb2;

    @FXML private TextField min;
    @FXML private TextField max;
    @FXML private TextField networkResult;


    private TransportIssueAlgorithm transportIssueAlgorithm = new TransportIssueAlgorithm();
    private TransportNetworkFlow transportNetworkFlow = new TransportNetworkFlow();
    private Nodes node = new Nodes();

    public void handleConfirmNode() {
        String value1 = this.cb1.getValue().toString();
        String value2 = this.cb2.getValue().toString();

        int id_first=0;
        //sup1=0;   sub2=1 ;    d1=3;   d2=4    point=5

        switch(value1){
            case "O1":
                id_first=0;
                break;
            case "O2":
                id_first=1;
                break;
            case "D1":
                id_first=3;
                break;
            case "D2":
                id_first=4;
                break;
            case "Point":
                id_first=5;
                break;
        }

        int id_second=0;
        switch(value2){
            case "O1":
                id_second=0;
                break;
            case "O2":
                id_second=1;
                break;
            case "D1":
                id_second=3;
                break;
            case "D2":
                id_second=4;
                break;
            case "Point":
                id_second=5;
                break;
        }

        Nodes node = new Nodes();
        node.setFirst(id_first);
        node.setIdOfSecond(id_second);
        node.setMin(Integer.parseInt(this.min.getText()));
        node.setMax(Integer.parseInt(this.max.getText()));
        this.transportNetworkFlow.addNode(node);

        this.min.setText("");
        this.max.setText("");
    }


public void handleResultNetworkButton() {
      double result = this.transportNetworkFlow.getCost(transportIssueAlgorithm);
      this.networkResult.setText(Double.toString(result));

}

    public void handleConfirmButton() {

        ObservableList<String> db= FXCollections.observableArrayList();
        db.add("O1");
        db.add("O2");
        db.add("D1");
        db.add("D2");
        db.add("D3");
        this.cb1.setItems(db);
        this.cb2.setItems(db);

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

        this.transportNetworkFlow.getSuppliers()[0].setSupply(Integer.parseInt(this.sup0.getText()));
        this.transportNetworkFlow.getSuppliers()[0].setSupply(Integer.parseInt(this.sup0.getText()));
        this.transportNetworkFlow.getSuppliers()[0].setPurchasePrice(Integer.parseInt(this.purchasePrice0.getText()));
        this.transportNetworkFlow.getSuppliers()[1].setSupply(Integer.parseInt(this.sup1.getText()));
        this.transportNetworkFlow.getSuppliers()[1].setPurchasePrice(Integer.parseInt(this.purchasePrice1.getText()));
        this.transportNetworkFlow.getSuppliers()[2].setSupply(Integer.parseInt(this.sup2.getText()));
        this.transportNetworkFlow.getSuppliers()[2].setPurchasePrice(Integer.parseInt(this.purchasePrice2.getText()));

        this.transportNetworkFlow.getRecipients()[0].setDemand(Integer.parseInt(this.rec0.getText()));
        this.transportNetworkFlow.getRecipients()[0].setSellingPrice(Integer.parseInt(this.sellingPrice0.getText()));
        this.transportNetworkFlow.getRecipients()[1].setDemand(Integer.parseInt(this.rec1.getText()));
        this.transportNetworkFlow.getRecipients()[1].setSellingPrice(Integer.parseInt(this.sellingPrice1.getText()));


        this.transportIssueAlgorithm.setBlockedRowIndex(Integer.parseInt(this.blockedRow.getText()));
        this.transportIssueAlgorithm.setBlockedColIndex(Integer.parseInt(this.blockedCol.getText()));
    }

    public void handleResultButton() {
        double optimizedProfit = transportIssueAlgorithm.countOptimizedProfit();
        result.setText(String.valueOf(optimizedProfit));
    }

    
}
