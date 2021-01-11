package dao;

import model.ClassType;
import utils.StringUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 班级类连接数据库进行操作
 * 主要用于班级信息的增删改查
 */

public class ClassTypeDao {
    // 增加班级
    public int add(Connection conn, ClassType classType) throws Exception {
        PreparedStatement pstmt = null;

        try {
            String sql = "insert into stu_class values(null, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, classType.getCollege());
            pstmt.setString(2, classType.getClassName());
            pstmt.setString(3, classType.getClassDesc());

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

    // 查询功能
    public ResultSet search(Connection conn, ClassType classType) throws Exception {
        PreparedStatement pstmt = null;

        try {
            StringBuilder sql = new StringBuilder("select * from stu_class");
            if (!StringUtil.isEmpty(classType.getClassName())){
                sql.append(" where className like '%").append(classType.getClassName()).append("%'");
            }

            if (!StringUtil.isEmpty(classType.getClassName()) && !StringUtil.isEmpty(classType.getCollege())) {
                sql.append(" and college like '%").append(classType.getCollege()).append("%'");
            }else if(!StringUtil.isEmpty(classType.getCollege())) {
                sql.append(" where college like '%").append(classType.getCollege()).append("%'");
            }
            pstmt = conn.prepareStatement(sql.toString());

            return pstmt.executeQuery();
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // 修改班级信息
    public int update(Connection conn, ClassType classType) throws Exception{
        String sql = "update stu_class set college = ?, className = ?, classDesc = ? where id = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, classType.getCollege());
        pstmt.setString(2, classType.getClassName());
        pstmt.setString(3, classType.getClassDesc());
        pstmt.setInt(4, classType.getId());

        return pstmt.executeUpdate();
    }

    // 删除班级
    public int delete(Connection conn, String id) throws Exception {
        String sql = "delete from stu_class where id = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, id);

        return pstmt.executeUpdate();
    }
}
