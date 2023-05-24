package test12_3;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

public class DMTest {
    static Connection con = null;
    static PreparedStatement preparedStatement = null;
    static InputStream is = DMTest.class.getClassLoader().getResourceAsStream("dm8.properties");
    static Properties properties = new Properties();
    public static void main(String[] args) {

        try {
            properties.load(is);
            String cname = properties.getProperty("cname");
            String url = properties.getProperty("url");
            String user = properties.getProperty("user");
            String pwd = properties.getProperty("pwd");

            Class.forName(cname);
            con = DriverManager.getConnection(url, user, pwd);
            con.setAutoCommit(true);
            System.out.println("[SUCCESS]conn database");

            String sql = "INSERT INTO t1 (name,age,grade) VALUES (?,?,?)";
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setObject(1,"bella");
            preparedStatement.setObject(2,"19");
            preparedStatement.setObject(3,"95");
            preparedStatement.execute();

        } catch (Exception e) {
            System.out.println("[FAIL]conn database：" + e.getMessage());
        }
    }
    public void disConn(Connection con) throws SQLException {
        if (con != null) {
            con.close();
        }
        if (con != null) {
            preparedStatement.close();
        }
    }
}
