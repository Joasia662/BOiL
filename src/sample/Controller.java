package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import models.Recipient;
import models.Supplier;

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

    private TransportIssueAlgorithm transportIssueAlgorithm = new TransportIssueAlgorithm();
    Supplier[]  suppliers = new Supplier[3];
    Recipient[] recipients = new Recipient[2];
    int[][] costOfTransport = new int[3][2];

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

        this.transportIssueAlgorithm.getCostOfTransport()[0][0] = Integer.parseInt(this.costOfTransport00.getText());
        this.transportIssueAlgorithm.getCostOfTransport()[0][1] = Integer.parseInt(this.costOfTransport01.getText());
        this.transportIssueAlgorithm.getCostOfTransport()[1][0] = Integer.parseInt(this.costOfTransport10.getText());
        this.transportIssueAlgorithm.getCostOfTransport()[1][1] = Integer.parseInt(this.costOfTransport11.getText());
        this.transportIssueAlgorithm.getCostOfTransport()[2][0] = Integer.parseInt(this.costOfTransport20.getText());
        this.transportIssueAlgorithm.getCostOfTransport()[2][1] = Integer.parseInt(this.costOfTransport21.getText());
    }

//    Integer [] unitProfit = transportIssueAlgorithm.calculateUnitProfit(this.recipients,this.suppliers, this.costOfTransport);
    // TRANSCRIPTION
    // costOfTransport[0][0] --->  suppliers[0] and recipients[0]
    // costOfTransport[0][1] --->  suppliers[0] and recipients[1]
    // ...
    // costOfTransport[2][1] --> suppliers[2] and recipients[1]

//----------------------------------------------------------------------------------

    /* STEP 1
         Wyznaczenie jednostkowego zysku z na poszczególnych trasach od dostawców do odbiorców na podstawie cen sprzedaży c, kosztów zakupu kz oraz kosztów jednostkowych transportu kt
        z = c – kz- kt
    */

    public Integer[] calculateUnitProfit(Recipient[] rec, Supplier[]  sup){
        Integer [] profit = new Integer[ rec.length*sup.length];
        int indexUnit =0;

        for (int indexRec = 0; indexRec < rec.length; indexRec++)
        {
            for (int indexSup = 0; indexSup < sup.length; indexSup++){
                profit[indexUnit] = rec[indexRec].getSellingPrice() - this.suppliers[indexSup].getPurchasePrice() - this.costOfTransport[indexSup][indexRec];
                indexUnit++;
            }
        }

        return profit;
    }

    //--------------------------------------------------------------------------------------------------


    
}
