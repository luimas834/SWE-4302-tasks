package org.example;

public class Bakery extends Product{
    String owner;
    String add_ons;
    public Bakery( ) {
        super("Bakery", 400);
        this.owner="Mio's Muffin";
        this.add_ons="cheeses";
    }

    String getOwner(){
        return this.owner;
    }
    String getAdd_ons(){
        return this.add_ons;
    }
}
