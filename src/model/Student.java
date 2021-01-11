package model;

/**
 * 学生模型
 */

public class Student {
    private String stuName;
    private String stuAge;
    private String stuSex;
    private String stuTel;
    private String stuID;
    private String stuClass;
    private String stuCollege;
    private String stuPersonalID;
    private String stuHometown;
    private String stuBedroom;

    public Student() {

    }

    public Student(String stuName, String stuAge, String stuSex, String stuTel, String stuID,
                   String stuClass, String stuCollege, String stuPersonalID, String stuHometown, String stuBedroom) {
        this.stuName = stuName;
        this.stuAge = stuAge;
        this.stuSex = stuSex;
        this.stuTel = stuTel;
        this.stuID = stuID;
        this.stuClass = stuClass;
        this.stuCollege = stuCollege;
        this.stuPersonalID = stuPersonalID;
        this.stuHometown = stuHometown;
        this.stuBedroom = stuBedroom;
    }

    public String getStuName() {
        return stuName;
    }

    public String getStuAge() {
        return stuAge;
    }

    public String getStuSex() {
        return stuSex;
    }

    public String getStuTel() {
        return stuTel;
    }

    public String getStuID() {
        return stuID;
    }

    public String getStuClass() {
        return stuClass;
    }

    public String getStuCollege() {
        return stuCollege;
    }

    public String getStuPersonalID() {
        return stuPersonalID;
    }

    public String getStuHometown() {
        return stuHometown;
    }

    public String getStuBedroom() {
        return stuBedroom;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public void setStuAge(String stuAge) {
        this.stuAge = stuAge;
    }

    public void setStuSex(String stuSex) {
        this.stuSex = stuSex;
    }

    public void setStuTel(String stuTel) {
        this.stuTel = stuTel;
    }

    public void setStuID(String stuID) {
        this.stuID = stuID;
    }

    public void setStuClass(String stuClass) {
        this.stuClass = stuClass;
    }

    public void setStuCollege(String stuCollege) {
        this.stuCollege = stuCollege;
    }

    public void setStuPersonalID(String stuPersonalID) {
        this.stuPersonalID = stuPersonalID;
    }

    public void setStuHometown(String stuHometown) {
        this.stuHometown = stuHometown;
    }

    public void setStuBedroom(String stuBedroom) {
        this.stuBedroom = stuBedroom;
    }
}
