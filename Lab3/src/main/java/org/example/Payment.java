package org.example;

public class Payment {
    String paying_date;
    String payment_type;

    public Payment(){
       this.paying_date="12 december";
       this.payment_type="online";
    }

    String getDate(){
        return this.paying_date;
    }
    String getPayment_type(){
        return this.payment_type;
    }

}
