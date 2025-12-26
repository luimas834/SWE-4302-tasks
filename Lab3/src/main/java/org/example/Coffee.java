package org.example;

public class Coffee extends Product{
    String owner;
    String add_ons;
    public Coffee( ) {
        super("Coffee", 200);
        this.owner="Mio's Muffin";
        this.add_ons="extra sugar";
    }

    String getOwner(){
        return this.owner;
    }
    String getAdd_ons(){
        return this.add_ons;
    }
}
