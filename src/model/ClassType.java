package model;

/**
 * 班级实体
 */

public class ClassType {
    // 班级编号、学院、班级名称、班级备注
    private int id;
    private String college;
    private String className;
    private String classDesc;

    public ClassType() {
        id = 0;
        college = "";
        classDesc = "";
        className = "";
    }

    public ClassType(String college, String className, String classDesc) {
        this.college = college;
        this.className = className;
        this.classDesc = classDesc;
    }

    public ClassType(int id, String college, String className, String classDesc) {
        this.id = id;
        this.college = college;
        this.className = className;
        this.classDesc = classDesc;
    }

    public int getId() {
        return id;
    }

    public String getCollege() {
        return college;
    }

    public String getClassName() {
        return className;
    }

    public String getClassDesc() {
        return classDesc;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public void setClassDesc(String classDesc) {
        this.classDesc = classDesc;
    }
}
