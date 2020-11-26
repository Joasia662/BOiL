package sample;

import models.Recipient;
import models.Supplier;

public class TransportIssueAlgorithm {
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

    public Integer[] calculateUnitProfit(Recipient[] rec, Supplier[]  sup, Integer[][] costOfTransport){
        Integer[] profit = new Integer[rec.length*sup.length];
        int indexUnit =0;

        for (int indexSup =0; indexSup < sup.length; indexSup++)
        {
            for (int indexRec =0; indexRec< rec.length; indexRec ++){
                profit[indexUnit] = rec[indexRec].getSellingPrice() - sup[indexSup].getPurchasePrice() - costOfTransport[indexSup][indexRec];
                indexUnit++;
            }
        }
        return profit;
    }
    //--------------------------------------------------------------------------------------------------
}
