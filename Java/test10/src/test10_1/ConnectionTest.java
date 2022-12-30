package test10_1;

import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.util.Properties;

/*
--------------------------------------
目前找到的最完整的URL
jdbc:mysql://localhost:3306/test?useSSL=false&serverTimezone=UTC&characterEncoding=utf8&rewriteBatchedStatements=true&allowPublicKeyRetrieval=true
应该不会报错了
--------------------------------------
*/

public class ConnectionTest
{
    @Test
    //最朴素
    public void test1() throws Exception
    {
        //1.实例化Driver
        Driver driver = new com.mysql.cj.jdbc.Driver();
        //2.提供url
        String url = "jdbc:mysql://localhost:3306/test?useSSL=false&serverTimezone=UTC";
        //3.提供Properties，包含user和password
        Properties info = new Properties();
        info.setProperty("user","root");
        info.setProperty("password","073412");
        //4.调用driver的connect方法，获取连接
        Connection connection = driver.connect(url,info);
        System.out.println(connection);
    }

    @Test
    //使用反射
    public void test2() throws Exception
    {
        //1.利用反射，实例化Driver
        String driverName = "com.mysql.cj.jdbc.Driver";
        Class<?> aClass = Class.forName(driverName);
        Constructor<?> aConstructor = aClass.getConstructor();
        Driver driver = (Driver) aConstructor.newInstance();
        //2.提供url
        String url = "jdbc:mysql://localhost:3306/test?useSSL=false&serverTimezone=UTC";
        //3.提供Properties，包含user和password
        Properties info = new Properties();
        info.setProperty("user","root");
        info.setProperty("password","073412");
        //4.调用driver的connect方法，获取连接
        Connection connection = driver.connect(url,info);
        System.out.println(connection);
    }

    @Test
    //使用DriverManager
    public void test3() throws Exception
    {
        //1.数据库连接的4个基本要素：
        String url = "jdbc:mysql://localhost:3306/test?useSSL=false&serverTimezone=UTC";
        String user = "root";
        String password = "073412";
        String driverName = "com.mysql.cj.jdbc.Driver";
        //2.实例化Driver
        Class<?> aClass = Class.forName(driverName);
        Constructor<?> aConstructor = aClass.getConstructor();
        Driver driver = (Driver) aConstructor.newInstance();
        //3.使用DriverManager注册驱动
        DriverManager.registerDriver(driver);
        //4.使用DriverManager的getConnection方法，提供user和password，获取连接
        Connection connection = DriverManager.getConnection(url,user,password);
        System.out.println(connection);
    }

    @Test
    //省略实例化、注册Driver的代码
    //这些操作在mysql的Driver类的静态代码块中已经实现
    //加载驱动的时候就会自动执行这些操作
    public void test4() throws Exception
    {
        //1.数据库连接的4个基本要素：
        String url = "jdbc:mysql://localhost:3306/test?useSSL=false&serverTimezone=UTC";
        String user = "root";
        String password = "073412";
        String driverName = "com.mysql.cj.jdbc.Driver";
        //2.加载驱动
        Class.forName(driverName);
        //3.使用DriverManager的getConnection方法，提供user和password，获取连接
        Connection connection = DriverManager.getConnection(url,user,password);
        System.out.println(connection);
    }

    @Test
    //使用配置文件
    public void test5() throws Exception
    {
        // 1.加载配置文件
        InputStream is = ConnectionTest.class.getClassLoader().getResourceAsStream("jdbc.properties");
        Properties properties = new Properties();
        properties.load(is);

        //2.读取配置信息
        String url = properties.getProperty("url");
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String driver = properties.getProperty("driver");

        //3.加载 & 注册驱动
        Class.forName(driver);

        //4.获取连接
        Connection connection = DriverManager.getConnection(url, user, password);
        System.out.println(connection);
    }

}
