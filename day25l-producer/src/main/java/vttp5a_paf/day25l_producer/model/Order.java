package vttp5a_paf.day25l_producer.model;

import java.util.List;

public class Order {
    
    private int id;

    private String fullName;

    private List<OrderDetail> lineItems;

    public Order() {
    }

    public Order(int id, String fullName, List<OrderDetail> lineItems) {
        this.id = id;
        this.fullName = fullName;
        this.lineItems = lineItems;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public List<OrderDetail> getLineItems() {
        return lineItems;
    }

    public void setLineItems(List<OrderDetail> lineItems) {
        this.lineItems = lineItems;
    }

    
}
