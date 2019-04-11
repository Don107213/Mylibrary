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
}
