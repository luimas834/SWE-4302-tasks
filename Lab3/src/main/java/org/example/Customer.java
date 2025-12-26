package org.example;

import java.util.ArrayList;

public class Customer {
    String name;
    ArrayList<Product> product_list;
    ArrayList<Order> order;
    public Customer(){
        this.order=new ArrayList<>();
        this.product_list=new ArrayList<>();
        this.name="messi";
    }

    public void add_product(Product p){

        product_list.add(p);
    }
    String getName(){
        return this.name;
    }

}
