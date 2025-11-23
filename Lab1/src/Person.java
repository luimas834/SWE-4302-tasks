public abstract class Person {
   String name;
   String email;

   public Person(String name, String email){
    this.name=name;
    this.email=email;
   }

   public abstract void displayDetails();
   
   public String getName(){
        return this.name;
   }
   public String getEmail(){
      return this.email;
   }

}
