package test10_5.util;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.apache.commons.dbutils.DbUtils;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCUtils
{
    /**
     * @Description 创建Druid数据库连接池并获取连接
     */
    //将连接池dataSource声明为属性
    private static DruidDataSource dataSource = null;
    //在静态代码块中创建连接池dataSource，因为连接池只需一个即可
    static
    {
        try
        {
            //加载配置文件
            Properties properties = new Properties();
            InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("druid.properties");
            properties.load(is);
            //使用连接池工厂DruidDataSourceFactory传入配置信息，创建连接池dataSource
            dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    //使用该方法获取连接
    public static Connection getConnectionByDruid() throws SQLException
    {
        //直接调用连接池dataSource的getConnection()即可
         return dataSource.getConnection();
    }

    /**
     * @Description 使用Apache提供的DbUtils实现资源的关闭
     */
    public static void closeResource(Connection connection, Statement statement, ResultSet resultSet)
    {
        //方式1：无需try/catch环绕，方法内已将异常处理
        DbUtils.closeQuietly(connection);
        DbUtils.closeQuietly(statement);
        DbUtils.closeQuietly(resultSet);

        //方式2：仍需try/catch环绕，但是不用判断是否为空，方法内已判断
        // try
        // {
        //     DbUtils.close(connection);
        // } catch (SQLException e)
        // {
        //     e.printStackTrace();
        // }
        //
        // try
        // {
        //     DbUtils.close(statement);
        // } catch (SQLException e)
        // {
        //     e.printStackTrace();
        // }
        //
        // try
        // {
        //     DbUtils.close(resultSet);
        // } catch (SQLException e)
        // {
        //     e.printStackTrace();
        // }
    }
}
