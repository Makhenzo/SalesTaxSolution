package com.example.salestax;

public class Goods{

    private String type;
    private String desc;
    private double price;
    private int quantity;
    private String importStatus;


    public Goods(String type, String desc , double price , int quantity , String importStatus ) {
        this.type = type;
        this.desc = desc;
        this.price = price;
        this.quantity = quantity;
        this.importStatus = importStatus;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getImportStatus() {
        return importStatus;
    }

    public void setImportStatus(String importStatus) {
        this.importStatus = importStatus;
    }
}
