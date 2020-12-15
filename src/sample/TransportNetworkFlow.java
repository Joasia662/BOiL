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
    private Recipient[] recipients = new Recipient[2];
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
        public double getCost(){

        /*Nodes:
            id_first  //sup1=0;   sub2=1 ;    d1=3;   d2=4    point=5
            id_Second  //sup1=0;   sub2=1 ;    d1=3;   d2=4    point=5
             min;
             max
             cost;
        */


        LinearObjectiveFunction f = new LinearObjectiveFunction(new double [] {0,0,0,0,0,0,0,0,0,0},0);

        Collection constrains = new ArrayList();

            for (Nodes node: nodes) {
                if (node.getMin()!=null)
                {
                    constrains.add(new LinearConstraint(new double[]  {0,0,0,0,0,0,0,0,0}, Relationship.LEQ, node.getMin()));
                }
                if (node.getMax()!=NULL)
                {
                    constrains.add(new LinearConstraint(new double[]  {0,0,0,0,0,0,0,0,0}, Relationship.GEQ, node.getMin()));
                }

                constrains.add(new LinearConstraint(new double[]  {0,0,0,0,0,0,0,0,0}, Relationship.LEQ, node.getMin()));

            }

            
        constrains.add(new LinearConstraint(new double[]  {0,0,0,0,0,0,0,0,0}, Relationship.LEQ, 250));
        constrains.add(new LinearConstraint(new double[]  {0,0,0,0,0,0,0,0,0}, Relationship.LEQ, 250));
        constrains.add(new LinearConstraint(new double[]  {0,0,0,0,0,0,0,0,0}, Relationship.LEQ, 250));
        constrains.add(new LinearConstraint(new double[]  {0,0,0,0,0,0,0,0,0}, Relationship.LEQ, 250));
        constrains.add(new LinearConstraint(new double[]  {0,0,0,0,0,0,0,0,0}, Relationship.LEQ, 250));

        RealPointValuePair solution = null;
        try {
            solution = new SimplexSolver().optimize(f,constrains,GoalType.MINIMIZE,true);
        } catch (OptimizationException e) {
            e.printStackTrace();
        }
        return solution.getValue();
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
