package MyLibrary.lib;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class Save {

    public static <E extends Info> void save(Set<E> eList, String pathName) throws IOException{
        BufferedWriter bw = new BufferedWriter(new FileWriter(new File(pathName)));
        for(E e:eList){
            bw.write(e.getInfo());
            bw.newLine();
            bw.flush();
        }
        bw.close();
    }


    public static Set<Borrow> getReserveList() throws IOException{
        BufferedReader br = new BufferedReader(new FileReader(new File("ReserveList.txt")));
        Set<Borrow> reserveList = new HashSet<>();
        String line;
        while((line = br.readLine())!= null){
            String[] strArray = line.split(",");
            Borrow reserve = new Borrow(strArray[0], strArray[1], strArray[2], Long.valueOf(strArray[3]),strArray[4],strArray[5]);
            reserveList.add(reserve);
        }
        br.close();
        return reserveList;
    }

    public static Set<Borrow> getBorrowList() throws IOException{
        BufferedReader br = new BufferedReader(new FileReader(new File("BorrowList.txt")));
        Set<Borrow> borrowList = new HashSet<>();
        String line;
        while((line = br.readLine())!= null){
            String[] strArray = line.split(",");
            Borrow borrow = new Borrow(strArray[0], strArray[1], strArray[2], Long.valueOf(strArray[3]),strArray[4],strArray[5]);
            borrowList.add(borrow);
        }
        br.close();
        return borrowList;
    }

    public static Set<User> getUsers() throws IOException{
        BufferedReader br = new BufferedReader(new FileReader(new File("UsersData.txt")));
        Set<User> users = new HashSet<>();
        String line;
        while((line = br.readLine()) != null) {
            //分割字符串,分割后的元素存储到数组中,以","为分界点
            String[] strArray = line.split(",");
            User user = new User(strArray[0], strArray[1]);
            users.add(user);
        }
        br.close();
        return users;
    }

    public static Set<SuperAdmin> getSuperAdmins() throws IOException{
        BufferedReader br = new BufferedReader(new FileReader(new File("SuperAdminsData.txt")));
        Set<SuperAdmin> superAdmins = new HashSet<>();
        String line;
        while((line = br.readLine()) != null) {
            //分割字符串,分割后的元素存储到数组中,以","为分界点
            String[] strArray = line.split(",");
            SuperAdmin superAdmin = new SuperAdmin(strArray[0], strArray[1]);
            superAdmins.add(superAdmin);
        }
        br.close();
        return superAdmins;
    }

    public static Set<Admin> getAdmins() throws IOException{
        BufferedReader br = new BufferedReader(new FileReader(new File("AdminsData.txt")));
        Set<Admin> admins = new HashSet<>();
        String line;
        while((line = br.readLine()) != null) {
            //分割字符串,分割后的元素存储到数组中,以","为分界点
            String[] strArray = line.split(",");
            Admin admin = new Admin(strArray[0], strArray[1]);
            admins.add(admin);
        }
        br.close();
        return admins;
    }

    public static Set<Book> getBooks() throws IOException{
        BufferedReader br = new BufferedReader(new FileReader(new File("BooksData.txt")));
        Set<Book> books = new HashSet<>();
        String line;
        while((line = br.readLine()) != null) {
            //分割字符串,分割后的元素存储到数组中,以","为分界点
            String[] strArray = line.split(",");
            Book book = new Book(strArray[0], strArray[1], strArray[2], Double.parseDouble(strArray[4]), Integer.parseInt(strArray[3]),Integer.parseInt(strArray[5]));
            books.add(book);
        }
        br.close();
        return books;
    }
}
