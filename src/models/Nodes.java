package models;

public class Nodes {
    private  Integer idOfFirst;
    private  Integer IdOfSecond;
    private  Integer min;
    private  Integer max;
    private  Integer cost;

    public void setFirst(Integer first) {
        this.idOfFirst = first;
    }

    public void setIdOfSecond(Integer second) {
        this.IdOfSecond = second;
    }

    public void setMin(Integer min) {
        this.min = min;
    }

    public void setMax(Integer max) {
        this.max = max;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }


    public Integer getIdOfFirst() {
        return this.idOfFirst;
    }

    public Integer getIdOfSecond() {
        return this.IdOfSecond;
    }

    public Integer getMin() {
        return this.min;
    }

    public  Integer getMax() {
        return this.max;
    }

    public  Integer getCost() {
        return this.cost;
    }

}
