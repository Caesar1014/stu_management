package dao;

import model.Student;
import utils.StringUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 学生类数据库操作
 * 学生的添加、删除、修改、查询
 */

public class StudentDao {
    // 学生的添加
    public int add(Connection conn, Student student) throws Exception {
        PreparedStatement pstmt = null;

        try {
            String sql = "insert into stu_student(stuName, stuAge, stuSex, stuTel, stuID, stuClass, stuCollege, " +
                         "stuPersonalID, stuHometown, stuBedroom) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, student.getStuName());
            pstmt.setString(2, student.getStuAge());
            pstmt.setString(3, student.getStuSex());
            pstmt.setString(4, student.getStuTel());
            pstmt.setString(5, student.getStuID());
            pstmt.setString(6, student.getStuClass());
            pstmt.setString(7, student.getStuCollege());
            pstmt.setString(8, student.getStuPersonalID());
            pstmt.setString(9, student.getStuHometown());
            pstmt.setString(10, student.getStuBedroom());

            return pstmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            if (pstmt != null){
                pstmt.close();
            }
        }

        return 0;
    }

    // 查询学生信息
    public ResultSet search(Connection conn, Student student) throws Exception {
        StringBuilder sql = new StringBuilder("select * from stu_student");

        if (!StringUtil.isEmpty(student.getStuName())) {
            sql.append(" where stuName like '%").append(student.getStuName()).append("%'");
        }

        if (!StringUtil.isEmpty(student.getStuName()) && !StringUtil.isEmpty(student.getStuCollege())) {
            sql.append(" and stuCollege like '%").append(student.getStuCollege()).append("%'");
        }else if (!StringUtil.isEmpty(student.getStuCollege())) {
            sql.append(" where stuCollege like '%").append(student.getStuCollege()).append("%'");
        }
        PreparedStatement pstmt = conn.prepareStatement(sql.toString());

        return pstmt.executeQuery();
    }
    // 删除学生信息
    public int delete(Connection conn, String id) throws Exception {
        String sql = "delete from stu_student where stuID = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setString(1, id);

        return pstmt.executeUpdate();
    }
    // 修改学生信息
    public int modify(Connection conn, Student student) throws Exception {
        String sql = "update stu_student set stuName = ?, stuAge = ?, stuSex = ?, stuTel = ?, stuClass = ?, " +
                     "stuCollege = ?, stuPersonalID = ?, stuHometown = ?, stuBedroom = ? where stuID = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, student.getStuName());
        pstmt.setString(2, student.getStuAge());
        pstmt.setString(3, student.getStuSex());
        pstmt.setString(4, student.getStuTel());
        pstmt.setString(5, student.getStuClass());
        pstmt.setString(6, student.getStuCollege());
        pstmt.setString(7, student.getStuPersonalID());
        pstmt.setString(8, student.getStuHometown());
        pstmt.setString(9, student.getStuBedroom());
        pstmt.setString(10, student.getStuID());

        return pstmt.executeUpdate();
    }
}
