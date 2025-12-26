package org.example;

public class Main {
    public static void main(String[] args){
        Resturant r1=new Resturant();
        Product p1=new Juices();
        Product p2=new Smoothies();
        Order o1=r1.orders.getFirst();
        o1.addProduct(p1);
        o1.addProduct(p2);
        System.out.println(o1.products.getFirst());
    }
}
