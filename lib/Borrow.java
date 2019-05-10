package MyLibrary.lib;
import java.util.Calendar;
import java.util.Date;

public class Borrow implements Info{
    protected String id;
    protected String isbn;
    protected String date;
    private String status;
    private long expiredTime;
    private boolean renewable;
    static Calendar calendar = Calendar.getInstance();

    public static long mssInAMonth(){
        calendar.setTime(new Date());
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH)*24*3600;
    }

    public static long mssInTwoWeeks(){
        return 14*24*3600;
    }

    //借书操作时的构造函数
    public Borrow(String id, String isbn, Status status){
        this.id = id;
        this.isbn = isbn;
        this.status = status.toString();
        this.date = new Date().toString();
        this.expiredTime = new Date().getTime()+mssInAMonth();
        this.renewable= true;
    }

    //打开数据时的构造函数
    public Borrow(String id, String isbn, String date, long expiredTime, String status, String renewable){
        this.isbn = isbn;
        this.id = id;
        this.status = status;
        this.date = date;
        this.expiredTime = expiredTime;
        this.renewable = Boolean.valueOf(renewable);
    }


    public void setStatus(Status status){this.status = status.toString();}

    public long getExpiredTime(){return this.expiredTime;}

    public void setExpiredTime(long addTime){this.expiredTime+=addTime;}


    public String showBorrow(){
        return "UserId:" + id + "\tISBN:"+isbn + "\tBorrow Date:" + date
                + "\tBorrow Status:"+status;
    }

    @Override
    public String getInfo(){
        return this.id+","+this.isbn+","+this.date + "," + this.expiredTime+','+this.status+','+this.renewable;
    }

    public String getId(){
        return id;
    }

    public String getIsbn(){
        return isbn;
    }

    public boolean getRenewable(){ return renewable;}
    public void setRenewable(){
        this.renewable = false;
    }

}
