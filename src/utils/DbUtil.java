package utils;

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Properties;

/**
 * 连接数据库工具类
 */

public class DbUtil {
    // 数据库的连接地址
    public static String url;
    // 数据库用户名
    public static String user;
    // 数据库用户密码
    public static String password;
    // 驱动名称
    public static String driver;
    // 读取配置文件，获取值
    static {
        try {
            // 创建 Properties 文件类
            Properties pro = new Properties();
            // 获取文件的路径（文件路径不可有中文）
//            ClassLoader classLoader = DbUtil.class.getClassLoader();
//            URL res = classLoader.getResource("jdbc.properties");
//            String path = res.getPath();

            // 加载文件，path
            //pro.load(new FileReader(path));
            pro.load(new FileReader("src/jdbc.properties"));

            url = pro.getProperty("url");
            user = pro.getProperty("user");
            password = pro.getProperty("password");
            driver = pro.getProperty("driver");
            //注册驱动
            Class.forName(driver);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    // 数据库连接
    public static Connection getCon() throws Exception{
        return DriverManager.getConnection(url, user, password);
    }
    // 数据库关闭
    public static void closeCon(Connection con) throws Exception {
        if (con != null) {
            con.close();
        }
    }
}
