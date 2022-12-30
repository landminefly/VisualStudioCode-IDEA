package util;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class JDBCUtils
{
    /**
     * @Description 获取数据库连接
     */
    public static Connection getConnection() throws Exception
    {
        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("jdbc.properties");
        Properties properties = new Properties();
        properties.load(is);

        String driver = properties.getProperty("driver");
        String url = properties.getProperty("url");
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");

        Class.forName(driver);

        return DriverManager.getConnection(url, user, password);
    }

    /**
     * @Description 关闭Connection、Statement
     */
    public static void closeResource(Connection connection, Statement statement)
    {
        try
        {
            if(connection != null)
                connection.close();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        try
        {
            if(statement != null)
                statement.close();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * @Description 关闭Connection、Statement、ResultSet
     */
    public static void closeResource(Connection connection, Statement statement, ResultSet resultSet)
    {
        try
        {
            if(connection != null)
                connection.close();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        try
        {
            if(statement != null)
                statement.close();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        try
        {
            if(resultSet != null)
            resultSet.close();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
