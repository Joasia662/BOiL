package sample;

import models.Recipient;
import models.Supplier;

public class TransportIssueAlgorithm {
    private Supplier[]  suppliers = new Supplier[3];
    private Recipient[] recipients = new Recipient[2];
    private int[][] costOfTransport = new int[3][2];
    private Integer[][] unitProfit;

    public TransportIssueAlgorithm() {
        for (int i = 0; i < this.suppliers.length; i++) {
            this.suppliers[i] = new Supplier();
        }
        for (int i = 0; i < this.recipients.length; i++) {
            this.recipients[i] = new Recipient();
        }
    }

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

    public void calculateUnitProfit(Recipient[] rec, Supplier[]  sup, Integer[][] costOfTransport){
        Integer[][] profit = new Integer[sup.length][rec.length];
        int indexUnit =0;

        for (int indexSup =0; indexSup < sup.length; indexSup++)
        {
            for (int indexRec =0; indexRec< rec.length; indexRec ++){
                profit[indexSup][indexRec] = rec[indexRec].getSellingPrice() - sup[indexSup].getPurchasePrice() - costOfTransport[indexSup][indexRec];
                indexUnit++;
            }
        }
        this.unitProfit = profit;
    }
    //--------------------------------------------------------------------------------------------------


    public Supplier[] getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(Supplier[] suppliers) {
        this.suppliers = suppliers;
    }

    public Recipient[] getRecipients() {
        return recipients;
    }

    public void setRecipients(Recipient[] recipients) {
        this.recipients = recipients;
    }

    public int[][] getCostOfTransport() {
        return costOfTransport;
    }

    public void setCostOfTransport(int[][] costOfTransport) {
        this.costOfTransport = costOfTransport;
    }

    public Integer[][] getUnitProfit() {
        return unitProfit;
    }

    public void setUnitProfit(Integer[][] unitProfit) {
        this.unitProfit = unitProfit;
    }
}
