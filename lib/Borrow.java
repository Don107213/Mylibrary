package MyLibrary.lib;
import java.util.Date;

public class Borrow {
    protected String id;
    protected String isbn;
    protected String date;
    public Borrow(String id, String isbn){
        this.id = id;
        this.isbn = isbn;
        this.date = new Date().toString();
    }

    public Borrow(String id, String isbn, String date){
        this(id, isbn);
        this.date = date;
    }

    public String showBorrow(){
        return "UserId:" + id + "\tISBN:"+isbn + "\tBorrow Date:" + date;
    }

    public String borrowInfo(){
        return this.id+","+this.isbn+","+this.date;
    }

    public String getId(){
        return id;
    }

    public String getIsbn(){
        return isbn;
    }

    public String getDate(){
        return date;
    }
}
