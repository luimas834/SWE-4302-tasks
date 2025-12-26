package org.example;

public class Muffin extends Product{
    String owner;
    String add_ons;
    public Muffin( ) {
        super("Muffin", 400);
        this.owner="Mio's Muffin";
        this.add_ons="Chocolates";
    }

    String getOwner(){
        return this.owner;
    }
    String getAdd_ons(){
        return this.add_ons;
    }
}
