package MyLibrary.lib;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class Save {
    public static void saveUsers(Set<User> users) throws IOException{
        BufferedWriter bw = new BufferedWriter(new FileWriter(new File("UsersData.txt")));
        for (User user : users){
            bw.write(user.userInfo());
            bw.newLine();
            bw.flush();
        }
        bw.close();
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
    public static void saveSuperAdmins(Set<SuperAdmin> superAdmins) throws IOException{
        BufferedWriter bw = new BufferedWriter(new FileWriter(new File("SuperAdminsData.txt")));
        for (SuperAdmin superAdmin : superAdmins){
            bw.write(superAdmin.userInfo());
            bw.newLine();
            bw.flush();
        }
        bw.close();
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
    public static void saveAdmins(Set<Admin> admins) throws IOException{
        BufferedWriter bw = new BufferedWriter(new FileWriter(new File("AdminsData.txt")));
        for (Admin admin : admins){
            bw.write(admin.userInfo());
            bw.newLine();
            bw.flush();
        }
        bw.close();
    }

    public static void saveBooks(Set<Book> books) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(new File("BooksData.txt")));
        for (Book book : books){
            bw.write(book.bookInfo());
            bw.newLine();
            bw.flush();
        }
        bw.close();
    }
    public static Set<Book> getBooks() throws IOException{
        BufferedReader br = new BufferedReader(new FileReader(new File("BooksData.txt")));
        Set<Book> books = new HashSet<>();
        String line;
        while((line = br.readLine()) != null) {
            //分割字符串,分割后的元素存储到数组中,以","为分界点
            String[] strArray = line.split(",");
            Book book = new Book(strArray[0], strArray[1], strArray[2], Double.parseDouble(strArray[4]), Integer.parseInt(strArray[3]));
            books.add(book);
        }
        br.close();
        return books;
    }
}
