package sample;

import models.Recipient;
import models.Supplier;

import java.util.Arrays;

import org.apache.commons.math.optimization.GoalType;
import org.apache.commons.math.optimization.OptimizationException;
import org.apache.commons.math.optimization.RealPointValuePair;
import org.apache.commons.math.optimization.linear.LinearConstraint;
import org.apache.commons.math.optimization.linear.LinearObjectiveFunction;
import org.apache.commons.math.optimization.linear.Relationship;
import org.apache.commons.math.optimization.linear.SimplexSolver;


public class TransportIssueAlgorithm {
    private Supplier[]  suppliers = new Supplier[3];
    private Recipient[] recipients = new Recipient[2];
    private int[][] costOfTransport = new int[3][2];
    private int blockedRowIndex;
    private int blockedColIndex;

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

    public double[] calculateUnitProfit(Recipient[] rec, Supplier[]  sup, int[][] costOfTransport){
        double[] profit = new double[rec.length*sup.length];
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

    public double countOptimizedProfit(){
        double max = 0;
        double[] unitProfit = calculateUnitProfit(this.recipients, this.suppliers, this.costOfTransport);
        int blockedRouteIndex = 0;
        if (this.blockedRowIndex == 0) {
            if(this.blockedColIndex == 1) blockedRouteIndex = 1;
        } else if (this.blockedRowIndex == 1) {
            if (this.blockedColIndex == 0) blockedRouteIndex = 2;
            if (this.blockedColIndex == 1) blockedRouteIndex = 3;
        } else if (this.blockedRowIndex == 2) {
            if (this.blockedColIndex == 0) blockedRouteIndex = 4;
            if (this.blockedColIndex == 1) blockedRouteIndex = 5;
        }
        double[] blockedRouteConstraint = new double[6];
        Arrays.fill(blockedRouteConstraint, 0);
        blockedRouteConstraint[blockedRouteIndex] = 1;

        LinearObjectiveFunction f = new LinearObjectiveFunction(unitProfit, 0);
        Collection constraints = new ArrayList();
        constraints.add(new LinearConstraint(blockedRouteConstraint, Relationship.EQ, 0));

        constraints.add(new LinearConstraint(new double[] { 1, 0, 1, 0, 1, 0 }, Relationship.LEQ, recipients[0].getDemand()));
        constraints.add(new LinearConstraint(new double[] { 0, 1, 0, 1, 0, 1 }, Relationship.LEQ, recipients[1].getDemand()));

        constraints.add(new LinearConstraint(new double[] { 1, 1, 0, 0, 0, 0 }, Relationship.LEQ, suppliers[0].getSupply()));
        constraints.add(new LinearConstraint(new double[] { 0, 0, 1, 1, 0, 0 }, Relationship.LEQ, suppliers[1].getSupply()));
        constraints.add(new LinearConstraint(new double[] { 0, 0, 0, 0, 1, 1 }, Relationship.LEQ, suppliers[2].getSupply()));

        try {
            RealPointValuePair solution = new SimplexSolver().optimize(f, constraints, GoalType.MAXIMIZE, true);
            double x1 = solution.getPoint()[0];
            double x2 = solution.getPoint()[1];
            double x3 = solution.getPoint()[2];
            double x4 = solution.getPoint()[3];
            double x5 = solution.getPoint()[4];
            double x6 = solution.getPoint()[5];
            max = solution.getValue();
        } catch (OptimizationException e) {
            e.printStackTrace();
        }
        return  max;
    }


    public Supplier[] getSuppliers() {
        return suppliers;
    }

    public Recipient[] getRecipients() {
        return recipients;
    }

    public int[][] getCostOfTransport() {
        return costOfTransport;
    }

    public void setCostOfTransport(int row, int col, int cost) {
        this.costOfTransport[row][col] = cost;
    }

    public int getBlockedRowIndex() {
        return blockedRowIndex;
    }

    public void setBlockedRowIndex(int blockedRowIndex) {
        this.blockedRowIndex = blockedRowIndex;
    }

    public int getBlockedColIndex() {
        return blockedColIndex;
    }

    public void setBlockedColIndex(int blockedColIndex) {
        this.blockedColIndex = blockedColIndex;
    }
}
