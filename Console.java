package MyLibrary;
import MyLibrary.lib.*;

import java.io.IOException;
import java.util.*;

public class Console {
    static Scanner scanner = new Scanner(System.in);
    static Library myLibrary = new Library();
    static Set<User> userList = new HashSet<>();
    static Set<Admin> adminList = new HashSet<>();
    static Set<SuperAdmin> superAdminList = new HashSet<>();
    static Set<Borrow> borrowList = new HashSet<>();
    static Set<Borrow> reserveList = new HashSet<>();
    public static void main(String[] args) throws IOException {
        //保存用户数据
        userList.add(new User("12","12"));
        userList.add(new User("13","13"));
        Save.saveUsers(userList);
        //保存管理员数据
        adminList.add(new Admin("g","g"));
        Save.saveAdmins(adminList);
        //保存图书数据
        myLibrary.addBook(myLibrary, new Book("0-670-82162-4","ss","s", 1.23, 2));
        myLibrary.addBook(myLibrary, new Book("979-7-121-31638-7","aas","s", 1.23, 2));
        Save.saveBooks(myLibrary.MyLibrary);
        //保存超级管理员数据
        superAdminList.add(new SuperAdmin("h","h"));
        Save.saveSuperAdmins(superAdminList);
        //保存借阅记录
        borrowList.add(new Borrow("12","0-670-82162-4"));
        borrowList.add(new Borrow("13","979-7-121-31638-7"));
        Save.saveBorrowList(borrowList);
        //保存预约记录
        reserveList.add(new Borrow("12","0-670-82162-4"));
        reserveList.add(new Borrow("13","979-7-121-31638-7"));
        Save.saveReserveList(reserveList);
        reserveList = Save.getReserveList();
        borrowList = Save.getBorrowList();
        superAdminList = Save.getSuperAdmins();
        myLibrary.MyLibrary = Save.getBooks();
        userList = Save.getUsers();
        adminList = Save.getAdmins();
//        System.out.println(userList.iterator().next().userInfo());
//        System.out.println(myLibrary.MyLibrary.iterator().next().bookInfo());
        int mode;
        while(true)
        {
            System.out.println("Choose mode:\n1:sign in\n" +
                    "2:sign up\nothers:exit");
            User user = null;
            mode = getValue(scanner.next());
            if(mode==0) break;
            switch (mode){
                 case 1:user = signIn();break;
                case 2:user = signUp();break;
                default: break;
            }
            //判断是否为用户或管理员
            int mode1;
            if(user != null){
                // 用户登录
                if(user.getClass().equals(User.class)){
                    while(true){
                        System.out.println("Choose mode:\n1:search books by keyword\n" +"2:search books by ISBN\n" +
                                "3:borrow book\n4:borrow history\n5:return book\n6:reserve a book\nothers:sign out");
                        mode1 = getValue(scanner.next());
                        if(mode1==0) break;
                        switch (mode1){
                            case 1:getBookByKeyword();break;
                            case 2:Book.showBook(myLibrary.getBookByIsbn(myLibrary, getIsbn()));break;
                            case 3:borrowBook(user.getId());break;
                            case 4:searchBorrowHistory(user.getId());break;
                            case 5:returnBook(user.getId());break;
                            case 6:reserveBook(user.getId());break;
                            default: break;
                        }
                        if(mode1<1 ||mode1>6) break;
                    }
                }
                //管理员登录
                else if(user.getClass().equals(Admin.class)){
                    while(true){
                        System.out.println("Choose mode:\n1:add books\n" +"2:modify books\n3:delete books\n" +
                                "4:search books\n5:search user\n6:modify user info\n7:delete user\nothers:sign out");
                        mode1 = getValue(scanner.next());

                        if(mode1==0) break;
                        switch (mode1){
                            case 1:myLibrary.addBook(myLibrary, getBook());break;
                            case 2:myLibrary.modifyBook(myLibrary, getIsbn(), getBook());break;
                            case 3:myLibrary.deleteBook(myLibrary, getIsbn());break;
                            case 4:getBookByKeyword();break;
                            case 5:searchUser();break;
                            case 6:modifyUserInfo();break;
                            case 7:deleteUser();break;
                            default: break;
                        }
                        if(mode1<1 ||mode1>7) break;
                    }
                }
                else if(user.getClass().equals(SuperAdmin.class)){
                    while(true){
                        System.out.println("Choose mode:\n1:add books\n" +"2:modify books\n3:delete books\n" +
                                "4:search books\n5:search user\n6:modify user info\n7:delete user" +
                                "\n8:add admin\n9:delete admin\n10:modify admin \n11:search admin\nothers:sign out");
                        mode1 = getValue(scanner.next());
                        if(mode1==0) break;
                        switch (mode1){
                            case 1:myLibrary.addBook(myLibrary, getBook());break;
                            case 2:myLibrary.modifyBook(myLibrary, getIsbn(), getBook());break;
                            case 3:myLibrary.deleteBook(myLibrary, getIsbn());break;
                            case 4:getBookByKeyword();break;
                            case 5:searchUser();break;
                            case 6:modifyUserInfo();break;
                            case 7:deleteUser();break;
                            case 8:addAdmin();break;
                            case 9:deleteAdmin();break;
                            case 10:modifyAdmin();break;
                            case 11:searchAdmin();break;
                            default: break;
                        }
                        if(mode1<1 ||mode1>11) break;
                    }
                }
            }
            if(mode<1 ||mode>2) break;
        }
        Save.saveAdmins(adminList);
        Save.saveUsers(userList);
        Save.saveBooks(myLibrary.MyLibrary);
        Save.saveReserveList(reserveList);
        Save.saveBorrowList(borrowList);
        Save.saveSuperAdmins(superAdminList);
    }

    public static void reserveBook(String id){
        Book book = myLibrary.getBookByIsbn(myLibrary, getIsbn());
        reserveList.add(new Borrow(id, book.getIsbn()));
    }

    public static void returnBook(String id){
        Book book = myLibrary.getBookByIsbn(myLibrary, getIsbn());
        for(Borrow borrow:borrowList){
            if(borrow.getIsbn().equals(book.getIsbn()) && id.equals(borrow.getId())){
                book.returnBook();
                borrowList.remove(borrow);
                System.out.println("return succeed");
                return ;
            }
        }
        System.out.println("No borrow history!");

    }

    public static void borrowBook(String id){
        Book book = myLibrary.getBookByIsbn(myLibrary, getIsbn());
        if(book.borrowBook()){
            borrowList.add(new Borrow(id, book.getIsbn()));
            System.out.println("borrow succeed！");
        }
        else{
            System.out.println("Empty inventory!");
        }
    }

    public static void searchBorrowHistory(String id){
        for(Borrow borrow:borrowList){
            if(borrow.getId().equals(id)){
                System.out.println(borrow.showBorrow());
            }
        }
    }

    public static void addAdmin(){
        System.out.println("Please input id");
        String id = scanner.next();
        System.out.println("Please input password");
        String password1 = scanner.next();
        System.out.println("Please confirm password");
        String password2 = scanner.next();
        if(password1.equals(password2)){
            if(!isIdUnique(id)){
                System.out.println("id repeated!");
                return ;
            }
            Admin admin = new Admin(id, password1);
            adminList.add(admin);
            System.out.println("Add admin succeed");
        }
        else System.out.println("Please input same passwords");
    }

    public static void deleteAdmin(){
        System.out.println("Please input admin id");
        String id = scanner.next();
        for(Admin admin:adminList){
            if(admin.getId().equals(id)){
                adminList.remove(admin);
                System.out.println("Admin delete succeed");
                return ;
            }
        }
        System.out.println("Admin not found");
    }

    public static void modifyAdmin(){
        Admin admin = searchAdmin();
        if(admin != null){
            System.out.println("Please input id");
            String id = scanner.next();
            System.out.println("Please input password");
            String password1 = scanner.next();
            System.out.println("Please confirm password");
            String password2 = scanner.next();
            if(password1.equals(password2)){
                if(!id.equals(admin) && !isIdUnique(id)){
                    System.out.println("id repeated!");
                    return ;
                }
                admin.setPassword(password1);
                admin.setId(id);
                System.out.println("modify succeed");
            }
            else System.out.println("Please input same passwords");
        }
    }

    public static Admin searchAdmin(){
        System.out.println("Input admin id");
        String id = scanner.next();
        for(Admin admin:adminList){
            if(admin.getId().equals(id))
                System.out.println("Admin found");
                System.out.println(admin.getDetailUserInfo());
                return admin;
        }
        System.out.println("Admin not found");
        return null;
    }

    public static int getValue(String s){
        if(s.length() == 1){
            if (s.charAt(0)>='1' && s.charAt(0)<='9')
                return ((int)s.charAt(0) - '0');
        }
        else if(s.length()==2){
            if (s.charAt(1)>='0' && s.charAt(1)<='9'&&(s.charAt(0)=='0' || s.charAt(0)=='1'))
                return 10*(s.charAt(0)-'0')+(s.charAt(1)-'0');
        }
        return 0;
    }

    public static void deleteUser(){
        User user = searchUser();
        userList.remove(user);
        System.out.println("User deleted!");
    }

    public static boolean isIdUnique(String s){
        for(User user:userList){
            if(user.getId().equals(s)) return false;
        }
        for (Admin admin:adminList){
            if(admin.getId().equals(s)) return false;
        }
        for(SuperAdmin superAdmin:superAdminList){
            if(superAdmin.getId().equals(s)) return false;
        }
        return true;
    }


    public static void modifyUserInfo(){
        User user1 = searchUser();
        if(user1 != null){
            System.out.println("Please input id");
            String id = scanner.next();
            System.out.println("Please input password");
            String password1 = scanner.next();
            System.out.println("Please confirm password");
            String password2 = scanner.next();
            if(password1.equals(password2)){
                if(!id.equals(user1) && !isIdUnique(id)){
                    System.out.println("id repeated!");
                    return ;
                }
                user1.setPassword(password1);
                user1.setId(id);
                System.out.println("modify succeed");
            }
            else System.out.println("Please input same passwords");
        }
    }

    public static User searchUser(){
        String id;
        System.out.println("Please input user id");
        id = scanner.next();
        for(User user:userList){
            if (id.equals(user.getId())){
                System.out.println("User found:");
                System.out.println(user.getDetailUserInfo());
                return user;
            }
        }
        System.out.println("User not found");
        return null;
    }

    public static User signUp(){
        System.out.println("Please input id");
        String id = scanner.next();
        System.out.println("Please input password");
        String password1 = scanner.next();
        System.out.println("Please confirm password");
        String password2 = scanner.next();
        if(password1.equals(password2)){
            if(!isIdUnique(id)){
                System.out.println("id repeated!");
                return null;
            }
            User user = new User(id, password1);
            userList.add(user);
            return user;
        }
        System.out.println("Please input same passwords");
        return null;
    }

    public static User signIn(){
        System.out.println("Please input id");
        String id = scanner.next();
        System.out.println("Please input password");
        String password = scanner.next();
        for(User user: userList){
            if(user.getId().equals(id) && user.getPassword().equals(password))
                return user;
        }
        for(User admin: adminList){
            if(admin.getId().equals(id) && admin.getPassword().equals(password))
                return admin;
        }
        for(SuperAdmin superAdmin:superAdminList){
            if (superAdmin.getId().equals(id)&&superAdmin.getPassword().equals(password))
                return superAdmin;
        }
        System.out.println("Invalid id and password combination.");
        return null;
    }

    public static void getBookByKeyword(){
        System.out.println("Please input keyword");
        List<Book> books = new ArrayList<>();
        books = myLibrary.getBookByKeywork(myLibrary, scanner.next());
        int len = books.size();
        if(len == 0){
            System.out.println("No related books!");
        }
        else{
            System.out.println("Present page:" + 1);
            for(int i=0; i<min(10,len);i++){
                books.get(i).print();
            }
            String mode, mode1;
            int presentPage = 0;
            while(true)
            {
                if(presentPage == 0 && len <= 10) break; // only 1 page
                if(presentPage == 0 && len >=10){ // at first page
                    System.out.println("Choose mode:\n1:next page\n" +
                            "2:input page number\nothers:exit");
                    mode1 = scanner.next();
                    int mode1Int = (int)mode1.charAt(0) - '0';
                    if(mode1Int == 1){
                        presentPage += 1;
                    }
                    else if(mode1Int == 2){
                        System.out.println("Page number from 1 to " + len/10 +1);
                        int y = scanner.nextInt();
                        if(y>=1 && y<=len/10 + 1) presentPage = y-1;
                        else break;
                    }
                    else break;
                }
                if(presentPage > 0 && (presentPage + 1)*10 >=len){//no next page
                    System.out.println("Choose mode:\n1:last page\n" +
                            "2:input page number\nothers:exit");
                    mode1 = scanner.next();
                    int mode1Int = (int)mode1.charAt(0) - '0';
                    if(mode1Int == 1){
                        presentPage -= 1;
                    }
                    else if(mode1Int == 2){
                        System.out.println("Page number from 1 to " + len/10 +1);
                        int y = scanner.nextInt();
                        if(y>=1 && y<=len/10 + 1) presentPage = y-1;
                        else break;
                    }
                    else break;
                }
                if(presentPage>0 && (presentPage + 1)*10 <len){
                    System.out.println("Choose mode:\n1:next page\n" + "2:last page\n"+
                            "3:input page number\nothers:exit");
                    mode1 = scanner.next();
                    int mode1Int = (int)mode1.charAt(0) - '0';
                    if(mode1Int == 1){
                        presentPage += 1;
                    }
                    else if(mode1Int == 2){
                        presentPage -= 1;
                    }
                    else if(mode1Int == 3){
                        System.out.println("Page number from 1 to " + len/10 +1);
                        int y = scanner.nextInt();
                        if(y>=1 && y<=len/10 + 1) presentPage = y-1;
                        else break;
                    }
                    else break;
                }
                for(int i = presentPage*10; i<min(presentPage*10+10,len); i++){
                    books.get(i).print();
                }
            }
        }


    }

    public static int min(int a, int b){
        if(a<b) return a;
        else return b;
    }

    public static String getKeyword(){
        return scanner.next();
    }

    public static Book getBook(){
        String isbn, title,authors;
        double p;
        int inventory;
        System.out.println("Please input isbn, title, authors, price and inventory");
        isbn = getIsbn();
        System.out.println("Please input the title");
        title = scanner.next();
        System.out.println("Please input the authors");
        authors = scanner.next();
        System.out.println("Please input the price");
        p = scanner.nextDouble();
        System.out.println("Please input the inventory");
        inventory = scanner.nextInt();
        return new Book(isbn, title, authors, p, inventory);
    }

    public static String getIsbn(){
        String isbn;
        do {
            System.out.println("Please input legal ISBN with '-'");
            isbn = scanner.next();
        }while(!ISBN.checkIsbn(isbn));
        return isbn;
    }

}

