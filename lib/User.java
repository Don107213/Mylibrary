package MyLibrary.lib;

import java.util.Date;

public class User implements Info{
    protected String id;
    protected String password;
    private int bookBorrowed = 0;
    private long bannedTime = 0;
    public User(String id, String password){
        this.id = id;
        this.password = password;
    }

    public void setBannedTime(long time){
        this.bannedTime = time;
    }
    public long getBannedTime(){return bannedTime;}

    public void modifyBookBorrowed(int num) {
        this.bookBorrowed += num;
    }

    public boolean canBorrowBook(){
        if(bannedTime<new Date().getTime() && bookBorrowed<4) return true;
        return false;
    }

    public String getDetailUserInfo(){
        return "id:" + id + "\tpassword:" + password;
    }

    @Override
    public String getInfo(){
        return id +","+ password;
    }

    public void setId(String id){
        this.id = id;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public String getId(){
        return id;
    }
    public String getPassword(){
        return password;
    }

}

