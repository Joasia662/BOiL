package sample;

import models.Nodes;
import models.Recipient;
import models.Supplier;

import org.apache.commons.math.optimization.GoalType;
import org.apache.commons.math.optimization.OptimizationException;
import org.apache.commons.math.optimization.RealPointValuePair;
import org.apache.commons.math.optimization.linear.LinearConstraint;
import org.apache.commons.math.optimization.linear.LinearObjectiveFunction;
import org.apache.commons.math.optimization.linear.Relationship;
import org.apache.commons.math.optimization.linear.SimplexSolver;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TransportNetworkFlow {

    private Supplier[]  suppliers = new Supplier[2];
    private Recipient[] recipients = new Recipient[3];
    private Integer point;

    private List<Nodes> nodes = new ArrayList<Nodes>();

    public TransportNetworkFlow() {
        for (int i = 0; i < this.suppliers.length; i++) {
            this.suppliers[i] = new Supplier();
        }
        for (int i = 0; i < this.recipients.length; i++) {
            this.recipients[i] = new Recipient();
        }
    }

        public double getCost(TransportIssueAlgorithm transform){

        /*Nodes:
            id_first  //sup1=0;   sub2=1 ;    r1=2;   r2=3    r3=4
            id_Second  //sup1=0;   sub2=1 ;    r1=2;  r2=3    r3= 4
             min;
             max
             cost;
        */

            double[] profit= transform.calculateUnitProfit(transform.getRecipients(),transform.getSuppliers(),transform.getCostOfTransport());
            LinearObjectiveFunction f = new LinearObjectiveFunction(profit, 0);

            Collection constrains = new ArrayList();
            double[] cos = getCelanCstr(this.nodes);

            for(int index=0; index <nodes.size();index++){
                int lookedId = nodes.get(index).getIdOfFirst();
                for (Nodes node:nodes
                ) {
                    if(node.getIdOfFirst()==lookedId){
                        cos[node.getIdOfSecond()]=1;
                    }

                    if(node.getIdOfSecond()==lookedId)
                    {
                        cos[node.getIdOfFirst()]=-1;
                    }
                }
             if (index <2){
                 constrains.add(new LinearConstraint(cos, Relationship.LEQ, this.suppliers[index].getSupply()));
             }
                else if (index >2){
                 constrains.add(new LinearConstraint(cos, Relationship.LEQ, this.recipients[index-2].getDemand()));
              }

            }



        RealPointValuePair solution = null;
        try {
            solution = new SimplexSolver().optimize(f,constrains,GoalType.MINIMIZE,true);
        } catch (OptimizationException e) {
            e.printStackTrace();
        }
        return solution.getValue();

        /*EXAMPLE FROM TASK

        * LinearObjectiveFunction funkcjaCelu = new LinearObjectiveFunction(new double [] {2,6,2,5,3,5,4,1,8,4},0);

           constrains.add(new LinearConstraint(new double[]  {-1,0,0,1,1,0,0,0,0,0}, Relationship.LEQ, 250));
           constrains.add(new LinearConstraint(new double[]  {1,1,1,0,0,0,0,0,0,0}, Relationship.LEQ, 300);

           constrains.add(new LinearConstraint(new double[]  {0,0,0,0,1,1,0,0,-1,0}, Relationship.GEQ, 120));
           constrains.add(new LinearConstraint(new double[]  {0,0,0,0,0,0,1,0,1,-1}, Relationship.GEQ, 250));
           constrains.add(new LinearConstraint(new double[]  {0,0,1,0,0,0,0,1,0,1}, Relationship.GEQ, 100));

           constrains.add(new LinearConstraint(new double[]  {0,1,0,1,0,-1,-1,-1,0,0}, Relationship.EQ, 0));

          RealPointValuePair solution = null;
            try {
                solution = new SimplexSolver().optimize(f,constrains,GoalType.MINIMIZE,true);
            } catch (OptimizationException e) {
                 e.printStackTrace();
            }
       return solution.getValue();

        * */





    }
public double[]  getCelanCstr(List<Nodes> nodes){
    double[] costr = new double [nodes.size()];
        for (int i=0; i <nodes.size();i++) {
        costr[i]=0;
    }

        return costr;
}

    public Supplier[] getSuppliers() {
        return suppliers;
    }

    public Recipient[] getRecipients() {
        return recipients;
    }

    public int getPoint() {
        return point;
    }
    public void setPoint(int point){
        this.point=point;
    }

    public void addNode (Nodes node){
        this.nodes.add(node);
    }

}
