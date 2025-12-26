package org.example;

public class Smoothies extends Product{
    String owner;
    String add_ons;
    public Smoothies( ) {
        super("Smoothies", 400);
        this.owner="jenny's juice bar";
        this.add_ons="chia seeds";
    }

    String getOwner(){
        return this.owner;
    }
    String getAdd_ons(){
        return this.add_ons;
    }
}
