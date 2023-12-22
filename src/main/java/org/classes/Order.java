package org.classes;

import org.entities.Database;
import org.entities.Product;
import org.entities.Regis;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private int id;
    private Regis customer;
    private LocalDate date;
    private Double totalPrice;
    private  List<Product> products ;
    private int productID;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public Regis getCustomer() {
        return customer;
    }

    public void setCustomer(Regis customer) {
        this.customer = customer;
    }


    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }



    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }




    public List<Product> getProducts() {
        return products;
    }




    public void setProducts(List<Product> products) {

        this.products = products;

    }



    @Override
    public String toString() {

        return String.format("%s,%s,%s,%s,%s"+"\n",id, getCustomer().getId(), date, totalPrice==null?"":totalPrice,productID);
    }




    public  String getString(){
        StringBuilder str;
        str = new StringBuilder( this.getCustomer().getname() + "\t\t\t\t\t\t"+
                this.getDate() + "\t\t\t\t\t\t"+this.getTotalPrice()+ "\n");
        for (Product product:this.getProducts()){
            str.append(product.toString()).append("  ").append("\n");
        }

       str.append("\t\t\n");
        return str.toString();

    }

    public Order() {
        this.products=new ArrayList<>();

    }

    public Order(List<Product> products) {
        this.id= Database.getOrderId();
        this.date=LocalDate.now();
        this.products=products;
        this.totalPrice = calcTotalPrice();
    }


    public double calcTotalPrice(){

        double out=0;
        for (Product product:products){
            out+=product.getPrice();
        }
        this.totalPrice=out;
        return out;
    }





}
