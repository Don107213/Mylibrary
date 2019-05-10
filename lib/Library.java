package MyLibrary.lib;
import java.util.*;

public class Library {
    public Set<Book> MyLibrary = new HashSet<Book>();

    public void showLibrary(Library library){
        for(Book book : library.MyLibrary){
            book.print();
        }
    }


    public Book getBookByIsbn(Library library,String isbn){
        if(ISBN.checkIsbn(isbn)){
            for(Book book : library.MyLibrary){
                if(isbn.equals(book.getIsbn())){
                    return book;
                }
            }
        }
        return null;
    }


    public List<Book> getBookByKeywork(Library library, String keyword){
        List<Book> books = new ArrayList<>();
        for(Book book : library.MyLibrary){
            if(book.getTitle().indexOf(keyword) != -1){
                books.add(book);
            }
        }
        Collections.sort(books);
        return books;
    }

    public boolean addBook(Library library,Book b){
        if(Book.isBook(b)){
            boolean e = false;
            for(Book book : library.MyLibrary){
                if (b.equals(book)){
                    book.setInventory(book.getInventory() + b.getInventory());
                    book.setBookNum(book.getBookNum()+b.getBookNum());
                    e = true;
                    break;
                }
            }
            if(!e){
                library.MyLibrary.add(b);
            }
        }
        return true;
    }

    private boolean deleteBook(Library library, String isbn){
        if(ISBN.checkIsbn(isbn)){
            for(Book book:library.MyLibrary){
                if(book.getIsbn().equals(isbn)){
                    library.MyLibrary.remove(book);
                    //System.out.println("Book deleted!");
                    return true;
                }
            }
            return false;
        }
        else{
            //System.out.println("Book not found!");
            return false;
        }
    }

    public boolean modifyBook(Library library, String isbn, Book book){
        if(ISBN.checkIsbn(isbn)){
            for(Book b : library.MyLibrary) {
                if (b.getIsbn().equals(isbn)) {
                    if (Book.isBook(book)) {
                        library.MyLibrary.remove(b);
                        Book b1 = getBookByIsbn(library, book.getIsbn());
                        if (b1 != null) {//新书的isbn与图书馆原有的书的isbn冲突
                            library.MyLibrary.add(b);
                        } else {
                            library.MyLibrary.add(book);
                            return true;
                        }
                    }
                    break;
                }
            }
        }
        return false;
    }
}

