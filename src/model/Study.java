package model;

public class Study {
    private String stuId;
    private String cNo;
    private String cGrade;

    public Study() {

    }

    public Study(String stuId, String cNo, String cGrade) {
        this.stuId = stuId;
        this.cNo = cNo;
        this.cGrade = cGrade;
    }

    public String getStuId() {
        return stuId;
    }

    public String getNo() {
        return cNo;
    }

    public String getGrade() {
        return cGrade;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    public void setNo(String cNo) {
        this.cNo = cNo;
    }

    public void setGrade(String cGrade) {
        this.cGrade = cGrade;
    }
}
