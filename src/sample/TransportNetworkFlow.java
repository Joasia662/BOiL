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
            id_first  //sup1=0;   sub2=1 ;    d1=2;   d2=3    point=4
            id_Second  //sup1=0;   sub2=1 ;    d1=2;   d2=3   point=4
             min;
             max
             cost;
        */

        LinearObjectiveFunction f = new LinearObjectiveFunction(new double [] {0,0,0,0,0,0,0,0,0,0},0);






        Collection constrains = new ArrayList();

            for (Nodes node: nodes) {

                Supplier sup1=null;
                Recipient rec1 = null;
                int point1;
                switch (node.getIdOfFirst())
                {
                    case 0 :
                        sup1=this.suppliers[0];
                        break;
                    case 1:
                        sup1=this.suppliers[1];
                        break;
                    case 2:
                        rec1=this.recipients[0];
                        break;

                    case 3:
                        rec1=this.recipients[1];
                        break;

                    case 4:
                        point1=this.point;
                        break;

                }

                Supplier sup2=null;
                Recipient rec2 = null;
                int point2;

                switch (node.getIdOfSecond())
                {
                    case 0 :
                        sup2=this.suppliers[0];
                        break;
                    case 1:
                        sup2=this.suppliers[1];
                        break;
                    case 2:
                        rec2=this.recipients[0];
                        break;

                    case 3:
                        rec2=this.recipients[1];
                        break;

                    case 4:
                        point2=this.point;
                        break;

                }



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
