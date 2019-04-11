package MyLibrary.lib;

public class User {
    protected String id;
    protected String password;
    public User(String id, String password){
        this.id = id;
        this.password = password;
    }
    public String userInfo(){
        return id +","+ password;
    }
    public String getDetailUserInfo(){
        return "id:" + id + "\tpassword:" + password;
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

