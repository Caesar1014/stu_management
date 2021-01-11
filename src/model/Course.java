package model;

/**
 * 课程实体
 */

public class Course {
    private String cno;
    private String cname;
    private String college;
    private String caddress;

    public Course() {
        cno = "";
        cname = "";
        college = "";
    }

    public Course(String cno, String cname, String college, String caddress) {
        this.cname = cname;
        this.cno = cno;
        this.college = college;
        this.caddress = caddress;
    }

    public String getCno() {
        return cno;
    }

    public String getCname() {
        return cname;
    }

    public String getCollege() {
        return college;
    }

    public String getCaddress() {
        return caddress;
    }

    public void setCno(String cno) {
        this.cno = cno;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public void setCaddress(String caddress) {
        this.caddress = caddress;
    }
}
