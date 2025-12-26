package org.example;

public class Juices extends Product {
    String owner;
    String add_ons;
    public Juices( ) {
        super("juices", 200);
        this.owner="jenny's juice bar";
        this.add_ons="protein";
    }

    String getOwner(){
        return this.owner;
    }
    String getAdd_ons(){
        return this.add_ons;
    }
}
