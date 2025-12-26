package org.example;

public abstract class Product  {
    public String name;
    public double basePrice;

    public Product(String name,double basePrice){
        this.basePrice=basePrice;
        this.name=name;

    }

    public String getName(){
        return this.name;
    }
    public double getBasePrice(){
        return this.basePrice;
    }

}
