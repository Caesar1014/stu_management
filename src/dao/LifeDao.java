package dao;

import model.Life;
import utils.StringUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 生活类数据库操作
 * 生活事务的添加、删除、修改、查询
 */

public class LifeDao {
    // 寝室信息的添加
    public int add(Connection conn, Life life) throws Exception {
        PreparedStatement pstmt = null;

        try {
            String sql = "insert into stu_bedroom(stuBedroom, electricity_bill, water_fee) values(?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, life.getStuBedroom());
            pstmt.setString(2, life.getElectricity_bill());
            pstmt.setString(3, life.getWater_fee());

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

    // 寝室信息的查询
    public ResultSet search(Connection conn, Life life) throws Exception {
        StringBuilder sql = new StringBuilder("select * from stu_bedroom");

        if(!StringUtil.isEmpty(life.getStuBedroom())) {
            sql.append(" where stuBedroom like '%").append(life.getStuBedroom()).append("%'");
        }
        PreparedStatement pstmt = conn.prepareStatement(sql.toString());

        return pstmt.executeQuery();
    }

    // 寝室信息的删除
    public int delete(Connection conn, String stuBedroom) throws Exception {
        String sql = "delete from stu_bedroom where stuBedroom = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setString(1, stuBedroom);

        return pstmt.executeUpdate();
    }

    // 寝室信息的修改
    public int modify(Connection conn, Life life) throws Exception {
        String sql = "update stu_bedroom set electricity_bill = ?, water_fee = ? where stuBedroom = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, life.getElectricity_bill());
        pstmt.setString(2, life.getWater_fee());
        pstmt.setString(3, life.getStuBedroom());

        return pstmt.executeUpdate();
    }
}
