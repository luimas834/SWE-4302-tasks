public class Book {
    String title;
    String author;
    String isbn;
    String price;
    
    public static int totalBookIssued=0;

    public Book(){
        this.title="Sad story in java";
        this.author="unknow";
        this.isbn="000-000-00";
        this.price="999999 $";
    }
    
    public Book(String title,String author,String isbn,String price){
        this.title=title;
        this.author=author;
        this.isbn=isbn;
        this.price=price;
    }
    
    public void displayInfo() {
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("ISBN: " + isbn);
        System.out.println("Price: " + price);
    }

}
