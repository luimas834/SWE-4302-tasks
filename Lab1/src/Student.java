import java.util.ArrayList;
public class Student extends Person {
    String studentId;
    ArrayList<Book> books;
    
    public Student(){
        super("yunus","sorkar007@gmail.com");
        this.studentId="007";
    }

    public Student(String name, String email,String studenId) {
        super(name, email);
        this.studentId=studenId; 
        books=new ArrayList<>();
    }

    @Override
    public void displayDetails() {
       System.out.println("student name : "+this.getName());
       System.out.println("student ID : "+this.studentId);
       System.out.println("email : "+this.getEmail()); 
    }

    public void addBook(Book book){
        this.books.add(book);
        Book.totalBookIssued++;
    }
    
    
}
