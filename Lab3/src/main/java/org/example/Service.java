package org.example;

import java.util.ArrayList;

public class Service {
   String serviceType;
   String delivery_time;

   public Service(){
       this.serviceType="Home Delivery";
       this.delivery_time="morning";
   }


   String serviceTypedetails(Service s){
       return s.serviceType;
   }
}
