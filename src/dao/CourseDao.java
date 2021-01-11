package dao;

import model.Course;
import utils.StringUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 课程类连接数据库
 * 用于增删改查操作
 */

public class CourseDao {
    // 增加课程
    public int add(Connection conn, Course course) throws Exception {
        PreparedStatement pstmt = null;

        try{
            String sql = "insert into stu_course values(?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, course.getCno());
            pstmt.setString(2, course.getCname());
            pstmt.setString(3, course.getCollege());
            pstmt.setString(4, course.getCaddress());

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

    // 查询课程
    public ResultSet search(Connection conn, Course course) throws Exception {
        PreparedStatement pstmt = null;

        try{
            StringBuilder sql = new StringBuilder("select * from stu_course");
            if (!StringUtil.isEmpty(course.getCname())) {
                sql.append(" where cname like '%").append(course.getCname()).append("%'");
            }
            if (!StringUtil.isEmpty(course.getCname()) && !StringUtil.isEmpty(course.getCollege())) {
                sql.append(" and college like '%").append(course.getCollege()).append("%'");
            }else if(!StringUtil.isEmpty(course.getCollege())){
                sql.append(" where college like '%").append(course.getCollege()).append("%'");
            }
            pstmt = conn.prepareStatement(sql.toString());

            return pstmt.executeQuery();
        }catch (SQLException e){
            e.printStackTrace();
        }

        return null;
    }

    // 修改课程信息
    public int update(Connection conn, Course course) throws Exception {
        String sql = "update stu_course set cname = ?, college = ?, caddress = ? where cno = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, course.getCname());
        pstmt.setString(2, course.getCollege());
        pstmt.setString(3, course.getCaddress());
        pstmt.setString(4, course.getCno());

        return pstmt.executeUpdate();
    }

    // 删除课程信息
    public int delete(Connection conn, String cno) throws Exception {
        String sql = "delete from stu_course where cno = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, cno);

        return pstmt.executeUpdate();
    }
}
