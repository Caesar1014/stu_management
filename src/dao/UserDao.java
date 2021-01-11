package dao;

import model.User;
import utils.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 用户访问数据库
 */

public class UserDao {
    // 登录验证，验证用户名、密码
    public User login(Connection conn, User user) throws Exception{
        User resultUser = null;
        String sql = "select * from stu_user where username = ? and password = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, user.getUserName());
        pstmt.setString(2, user.getPassWord());
        ResultSet res = pstmt.executeQuery();

        if (res.next()) {
            resultUser = new User();
            resultUser.setId(res.getInt("id"));
            resultUser.setUserName(res.getString("userName"));
            resultUser.setPassWord(res.getString("passWord"));
        }
        return resultUser;
    }

    public static boolean login(String name, String password) throws Exception {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet res = null;

        try {
            conn = DbUtil.getCon();
            String sql = "select * from stu_user where username = ? and password = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setString(2, password);
            res = pstmt.executeQuery();

            return res.next();
        }catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DbUtil.closeCon(conn);
            if (pstmt != null){
                pstmt.close();
            }
            if (res != null){
                res.close();
            }
        }
        return false;
    }
}
