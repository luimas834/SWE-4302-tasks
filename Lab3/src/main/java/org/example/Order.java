package org.example;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Order {
    double total_cost=0;
    Customer customer;
    ArrayList<Product> products;
    Payment payment;
    Service service ;
    public Order(){
        customer=new Customer();
        products=new ArrayList<>();
        payment =new Payment();
        service =new Service();
    }

    void addProduct(Product p){

        products.add(p);
        total_cost+=p.basePrice;
        System.out.println("added successfully");
    }
    void removeProduct(Product p){
        products.remove(p);
    }
    ArrayList<Product> order_items(Order s){
        return s.products;
    }
    double getTotal_cost(){
        return this.total_cost;
    }
}
