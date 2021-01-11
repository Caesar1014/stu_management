package dao;

import com.mysql.cj.exceptions.ClosedOnExpiredPasswordException;
import model.Study;
import utils.StringUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * 学习信息连接数据库
 * 用于增删改查
 */

public class StudyDao {
    // 查找学习信息
    public ResultSet search(Connection conn, Study study) throws Exception {
        PreparedStatement pstmt = null;

        try {
            StringBuilder sql = new StringBuilder("select stu_sc.stuID, stu_student.stuName, stu_sc.cno, stu_course.cname, stu_sc.grade " +
                                                  "from stu_sc, stu_course, stu_student " +
                                                  "where stu_sc.stuID = stu_student.stuID and stu_sc.cno = stu_course.cno");
            if (!StringUtil.isEmpty(study.getStuId()) && !StringUtil.isEmpty(study.getNo())) {
                sql.append(" and stu_sc.cno = '").append(study.getNo()).append("'").append(" and stu_sc.stuID = '").append(study.getStuId()).append("'");
            }

            pstmt = conn.prepareStatement(sql.toString());

            return pstmt.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    // 按照学生姓名进行查找
    public ResultSet stuSearch(Connection conn, String stuName, String stuID) throws Exception {
        PreparedStatement pstmt = null;

        try {
            StringBuilder sql = new StringBuilder("select stu_sc.stuID, stu_student.stuName, stu_sc.cno, stu_course.cname, stu_sc.grade " +
                                                  "from stu_sc, stu_course, stu_student " +
                                                  "where stu_sc.stuID = stu_student.stuID and stu_sc.cno = stu_course.cno");

            if (!StringUtil.isEmpty(stuName)) {
                sql.append(" and stu_student.stuName like '%").append(stuName).append("%'");
            }

            if (!StringUtil.isEmpty(stuName) && !StringUtil.isEmpty(stuID)) {
                sql.append(" and stu_student.stuID like '%").append(stuID).append("%'");
            }else if (!StringUtil.isEmpty(stuID)) {
                sql.append(" and stu_student.stuID like '%").append(stuID).append("%'");
            }

            pstmt = conn.prepareStatement(sql.toString());

            return pstmt.executeQuery();

        }catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    // 添加学习信息
    public int add(Connection conn, Study study) throws Exception {
        PreparedStatement pstmt = null;

        try {
            String sql = "insert into stu_sc values(?, ?, ?)";
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, study.getStuId());
            pstmt.setString(2, study.getNo());
            pstmt.setString(3, study.getGrade());

            return pstmt.executeUpdate();
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (pstmt != null) {
                pstmt.close();
            }
        }

        return 0;
    }

    // 修改学习信息
    public int update(Connection conn, Study study) throws Exception {
        String sql = "update stu_sc set grade = ?where stuID = ? and cno = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setString(1, study.getGrade());
        pstmt.setString(2, study.getStuId());
        pstmt.setString(3, study.getNo());

        return pstmt.executeUpdate();
    }

    // 删除学习信息
    public int delete(Connection conn, String cNo, String stuID) throws Exception {
        String sql = "delete from stu_sc where cno = ? and stuID = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setString(1, cNo);
        pstmt.setString(2, stuID);

        return pstmt.executeUpdate();
    }
}
