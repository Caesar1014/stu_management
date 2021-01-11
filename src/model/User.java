package model;

/**
 * 用户的实体，如用户名和密码
 */

public class User {
    // 用户编号
    private int id;
    // 用户名
    private String userName;
    // 用户密码
    private String passWord;
    // 是否为管理员
    private int is_admin;

    public User() {

    }
    public User(String name, String passWord) {
        super();
        this.userName = name;
        this.passWord = passWord;
    }
    public int getId() {
        return id;
    }

    public int getIs_admin() {
        return is_admin;
    }

    public String getPassWord() {
        return passWord;
    }

    public String getUserName() {
        return userName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public void setIs_admin(int is_admin) {
        this.is_admin = is_admin;
    }
}
