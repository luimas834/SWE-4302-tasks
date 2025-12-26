package org.example;

public class Energy_Drinks extends Product{
    String owner;
    String add_ons;
    public Energy_Drinks( ) {
        super("Energy Drinks", 100);
        this.owner="jenny's juice bar";
        this.add_ons="extra fruit";
    }

    String getOwner(){
        return this.owner;
    }
    String getAdd_ons(){
        return this.add_ons;
    }
}
