import java.util.ArrayList;
public class Faculty extends Person {
    String post;
    ArrayList<Book> books;

    public Faculty(){
        super("Ronaldo","cr7@gmail.com");
        this.post="sports";
    }

    public Faculty(String name, String email,String post) {
        super(name, email);
        this.post=post;
        books = new ArrayList<>();
    }

    @Override
    public void displayDetails() {
       System.out.println("faculty name : "+this.getName());
       System.out.println("Post : "+this.post);
       System.out.println("email : "+this.getEmail());
    }
    public void addBook(Book book){
        this.books.add(book);
        Book.totalBookIssued++;
    } 
}
