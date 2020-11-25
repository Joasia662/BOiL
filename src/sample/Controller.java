package sample;

import models.Recipient;
import models.Supplier;

public class Controller {

    Supplier[]  suppliers = new Supplier[3];
    Recipient[] recipients = new Recipient[2];
    Integer[][] costOfTransport = new Integer[2][3];

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

    Integer [] unitProfit = calculateUnitProfit(this.recipients,this.suppliers);

    public Integer[] calculateUnitProfit( Recipient[] rec, Supplier[]  sup){
        Integer [] profit = new Integer[ rec.length*sup.length];
        int indexUnit =0;

        for (int indexRec =0; indexRec< rec.length; indexRec ++)
        {
            for ( int indexSup =0; indexSup < sup.length; indexSup++){
                profit[indexUnit] = rec[indexRec].sellingPrice - this.suppliers[indexSup].purchasePrice - this.costOfTransport[indexSup][indexRec];
                indexUnit++;
            }
        }

        return profit;
    }

    //--------------------------------------------------------------------------------------------------


    
}
