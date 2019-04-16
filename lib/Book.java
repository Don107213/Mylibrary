package MyLibrary.lib;
import java.math.BigDecimal;

public class Book implements Comparable<Book>{
    private String isbn, title,authors;
    private BigDecimal price = new BigDecimal("0.00");
    private int inventory = 0;

    public Book(String i, String t, String a, double p, int inv){
        this.isbn = i;
        this.title = t;
        this.authors = a;
        this.inventory = inv;
        BigDecimal p1 = new BigDecimal(p);
        this.price = p1.setScale(2, BigDecimal.ROUND_DOWN);
    }

    public String bookInfo(){
        return isbn +","+title+","+authors+","+inventory+","+price;
    }

    public static void showBook(Book book){
        if(book!=null){
            book.print();
        }
        else{
            System.out.println("Book not found");
        }
    }


    public void print(){
        System.out.println("ISBN:"+isbn+"  Title:"+title+"  Authors:"+authors+"  Price: "+price+"  Inventory: "+inventory);
    }

    public String getIsbn(){
        return isbn;
    }
    public String getTitle(){
        return title;
    }
    public String getAuthors(){
        return authors;
    }
    public BigDecimal getPrice(){
        return price;
    }

    public int getInventory() {
        return inventory;
    }

    public static boolean isBook(Book b){
        BigDecimal zero = new BigDecimal("0.00");
        if(ISBN.checkIsbn(b.getIsbn())){
            if(b.getPrice().compareTo(zero) == 1){
                if(b.getInventory() >= 0) return true;
                else System.out.println("库存不能为负数！");
            }
            else System.out.println("价格为负数！");
        }
        else System.out.println("ISBN不合法！");
        return false;
    }

    public void returnBook(){
        inventory += 1;
    }


    public boolean borrowBook(){
        if(inventory>0){
            inventory -= 1;
            return true;
        }
        else return false;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int compareTo(Book book){
        return this.getTitle().compareTo(book.getTitle());
    }
}
