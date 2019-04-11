package MyLibrary.lib;
import java.util.*;

//class LibraryTest{
//    public static void main(String[] args){
//        Library myLibrary = new Library();
//        myLibrary.addBook(myLibrary, new Book("979-7-121-31638-7","ss","s", -1.23, 2));
//        myLibrary.addBook(myLibrary, new Book("979-7-121-31638-7","ss","s", 1.23, -2));
//        myLibrary.addBook(myLibrary, new Book("0-670-82162-4","ss","s", 1.23, 2));
//        myLibrary.addBook(myLibrary, new Book("0-670-82162-4","ss","s", 1.23, 2));
//        myLibrary.addBook(myLibrary, new Book("979-7-121-31638-7","ss","s", 1.23, 2));
//        myLibrary.modifyBook(myLibrary,"979-7-121-31638-7", new Book("979-7-121-31638-7","s","s", 1.23, 2));
//        myLibrary.getBookByKeywork(myLibrary, "ss");
//        myLibrary.getBookByIsbn(myLibrary, "0-670-82162-4").print();
//        myLibrary.showLibrary(myLibrary);
//    }
//}

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

    public void addBook(Library library,Book b){
        if(Book.isBook(b)){
            boolean e = false;
            for(Book book : library.MyLibrary){
                if (book.getIsbn().equals(b.getIsbn())){
                    book.setInventory(book.getInventory() + b.getInventory());
                    e = true;
                    break;
                }
            }
            if(!e){
                library.MyLibrary.add(b);
            }
        }
        System.out.println("Add books succeeded!");
    }

    public void deleteBook(Library library, String isbn){
        if(ISBN.checkIsbn(isbn)){
            for(Book book:library.MyLibrary){
                if(book.getIsbn().equals(isbn)){
                    library.MyLibrary.remove(book);
                    System.out.println("Book deleted!");
                    break;
                }
            }
        }
        else{
            System.out.println("Book not found!");
        }
    }

    public void modifyBook(Library library, String isbn, Book book){
        if(ISBN.checkIsbn(isbn)){
            for(Book b : library.MyLibrary) {
                if (b.getIsbn().equals(isbn)) {
                    if (Book.isBook(book)) {
                        library.MyLibrary.remove(b);
                        Book b1 = getBookByIsbn(library, book.getIsbn());
                        if (b1 != null) {
                            System.out.println("ISBN冲突!");
                        } else {
                            library.MyLibrary.add(book);
                        }
                    }
                    break;
                }
            }
        }
    }
}

