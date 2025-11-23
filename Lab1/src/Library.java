import java.util.*; 
public class Library {

   public void printPersonDetails(Person p){
        p.displayDetails();
   }

   public static void main (String[] args){
         Scanner scanner =new Scanner(System.in);
         ArrayList<Student> students =new ArrayList<>();
         ArrayList<Faculty> faculty =new ArrayList<>();
      while(true){
         System.out.println("press 1 to enter student\npress 2 to enter teacer");
         System.out.println("press 3 to enter a book and add\npress 4 to stop");

         int x;
         x=scanner.nextInt();
         scanner.nextLine();
         if(x==1){
            Student s;
            String n,i,e;
            System.out.println("Enter student name,id &email");
            n=scanner.nextLine();
            i=scanner.nextLine();
            e=scanner.nextLine();
            s=new Student(n,i,e);
            students.add(s);
         }
         else if(x==2){
            Faculty f;
            String n,p,e;
            System.out.println("Enter faculty name,post & email");
            n=scanner.nextLine();
            p=scanner.nextLine();
            e=scanner.nextLine();
            f=new Faculty(n, e, p);
            faculty.add(f);
         }
         else if(x==3){
            Book book;
            String t,a,i,p;
            System.out.println("Enter book title,author,ISBN,price");
            t=scanner.nextLine();
            a=scanner.nextLine();
            i=scanner.nextLine();
            p=scanner.nextLine();
            book=new Book(t,a,i,p);
            System.out.println("for faculty press 1 and for student press 2");
            int num=scanner.nextInt();
            scanner.nextLine();
            if(num==1){
               System.out.println("Enter faculty index no. ");
               int index=scanner.nextInt();
               scanner.nextLine();
               Faculty teacher=faculty.get(index);
               teacher.addBook(book);
            }
            else{
               System.out.println("Enter student index no. ");
               int index=scanner.nextInt();
               scanner.nextLine();
               Student student=students.get(index);
               student.addBook(book);
            } 
         }
         else{
            break;
         }
      }
      System.out.println("\n\nShowing runtime polymorphism");
      Library library =new Library();
      
      for(Student s : students){
         library.printPersonDetails(s);
         System.out.println("Books borrowed : "+s.books.size()+"\n");
      }
      for(Faculty f: faculty){
         library.printPersonDetails(f);
         System.out.println("Books borrowed : "+f.books.size()+"\n");
      }
      System.out.println("total boooks issued : "+Book.totalBookIssued);
      scanner.close();
   } 
}
