package org.threading.sync;
import java.util.ArrayList;

class VibeGamingCustomer{
    private ArrayList<String> complaints = new ArrayList<>();
    private static int totalComplaints = 0;

    public synchronized void launchcomplaint(String complaint){
        complaints.add(complaint);
        incrimentTotalComplaints();
    }
    public static synchronized void incrimentTotalComplaints(){
        totalComplaints++;
    }
    public static int getCount(){
        return totalComplaints;
    }
    public int getIndividualComplain(){
        return complaints.size();
    }
}
class complaintTask implements Runnable{
    private VibeGamingCustomer customer;
    public complaintTask(VibeGamingCustomer customer){
        this.customer=customer;
    }
    @Override
    public void run(){
        for(int i=0;i<1000;i++){
            String s= "complaint number : "+i +".";
            customer.launchcomplaint(s);
        }
    }
}


public class Main {
    public static void main (String[] args) throws InterruptedException{
        VibeGamingCustomer customerA= new VibeGamingCustomer();
        VibeGamingCustomer customerB= new VibeGamingCustomer();
        
        Thread t1 = new Thread(new complaintTask(customerA));
        Thread t2 = new Thread(new complaintTask(customerA));
        Thread t3 = new Thread(new complaintTask(customerB));
        Thread t4 = new Thread(new complaintTask(customerB));

        t1.start();t2.start();t3.start();t4.start();
        t1.join();t2.join();t3.join();t4.join();

        System.out.println("thread 1,2 complaint: "+customerA.getIndividualComplain());
        System.out.println("thread 3,4 complaint: "+customerB.getIndividualComplain());
        System.out.println("total complain:"+customerA.getCount());
    }
}