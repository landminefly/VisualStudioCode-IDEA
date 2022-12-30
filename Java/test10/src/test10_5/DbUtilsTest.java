package test10_5;

import bean.Customer;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.*;
import org.junit.jupiter.api.Test;
import test10_5.util.JDBCUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class DbUtilsTest
{
    /**
     * @Description 使用QueryRunner实现增删改操作
     */
    @Test
    public void updateTest()
    {
        Connection conn = null;
        try
        {
            conn = JDBCUtils.getConnectionByDruid();
            String sql = "INSERT INTO customers(name, email, birth) VALUES (?,?,?)";
            QueryRunner queryRunner = new QueryRunner();
            //update方法同时适用于增删改语句，返回int值，表示受影响的记录数
            //update方法会自行创建preparedStatement，最后自行关闭
            //update方法也可以不传入连接conn，这种情况下，update方法会自行创建连接，最后自行关闭
            int update = queryRunner.update(conn, sql, "朱杨飞", "ZYF@qq.com", "2003-10-10");
            System.out.println("影响了" + update + "条记录");
        } catch (SQLException e)
        {
            e.printStackTrace();
        } finally
        {
            //仅需关闭连接conn即可
            //注意：在连接池中，conn.close()并没有真正关闭数据库连接，只是将数据库连接释放，归还给了连接池。
            JDBCUtils.closeResource(conn, null, null);
        }
    }

    /**
     * @Description 使用QueryRunner实现查询操作（仅返回一条记录）
     */
    @Test
    public void selectForOneTest()
    {
        Connection conn = null;
        try
        {
            conn = JDBCUtils.getConnectionByDruid();
            String sql = "SELECT name,email,birth FROM customers WHERE id = ?";
            QueryRunner queryRunner = new QueryRunner();
            //ResultSetHandler是一个接口，用于处理ResultSet，将ResultSet以某种形式返回
            //BeanHandler是ResultSetHandler的一个实现类，它会将ResultSet中第一条记录的数据保存到对应的Bean对象中并返回
            BeanHandler<Customer> beanHandler = new BeanHandler<>(Customer.class);
            //query方法适用于查询语句
            //query方法必须传入一个Handler实现类对象，指定处理ResultSet的方式和返回的数据类型
            //query方法会自行创建preparedStatement和ResultSet，最后自行关闭
            //于update方法类似，query方法也可以不传入连接conn，会自行创建连接，最后自行关闭
            Customer query = queryRunner.query(conn, sql, beanHandler, 32);
            System.out.println(query);
        } catch (SQLException e)
        {
            e.printStackTrace();
        } finally
        {
            JDBCUtils.closeResource(conn, null, null);
        }
    }

    /**
     * @Description 使用QueryRunner实现查询操作（返回多条记录）
     */
    @Test
    public void selectNotForOneTest()
    {
        Connection conn = null;
        try
        {
            conn = JDBCUtils.getConnectionByDruid();
            String sql = "SELECT name,email,birth FROM customers WHERE id < ?";
            QueryRunner queryRunner = new QueryRunner();
            //BeanListHandler是ResultSetHandler的一个实现类，它会将ResultSet中所有记录的数据按顺序保存到多个对应的Bean对象中，
            //再将这些Bean对象放到List里并返回
            BeanListHandler<Customer> beanListHandler = new BeanListHandler<>(Customer.class);
            List<Customer> query = queryRunner.query(conn, sql, beanListHandler, 32);
            System.out.println(query);
        } catch (SQLException e)
        {
            e.printStackTrace();
        } finally
        {
            JDBCUtils.closeResource(conn, null, null);
        }
    }

    @Test
    public void selectNotForOneReturnMapTest()
    {
        Connection conn = null;
        try
        {
            conn = JDBCUtils.getConnectionByDruid();
            String sql = "SELECT name,email,birth FROM customers WHERE id < ?";
            QueryRunner queryRunner = new QueryRunner();
            //MapListHandler是ResultSetHandler的一个实现类，它会将ResultSet中所有记录的数据按顺序保存到多个Map中，
            //再将这些Map放到List里并返回
            MapListHandler mapListHandler = new MapListHandler();
            List<Map<String, Object>> query = queryRunner.query(conn, sql, mapListHandler, 32);
            System.out.println(query);
        } catch (SQLException e)
        {
            e.printStackTrace();
        } finally
        {
            JDBCUtils.closeResource(conn, null, null);
        }
    }

    /**
     * @Description 使用QueryRunner查询特殊值
     */
    @Test
    public void getValueTest()
    {
        Connection conn = null;
        try
        {
            conn = JDBCUtils.getConnectionByDruid();
            String sql = "SELECT COUNT(*) FROM customers";
            QueryRunner queryRunner = new QueryRunner();
            //ScalarHandler是ResultSetHandler的一个实现类，用于返回单个值
            ScalarHandler<Long> scalarHandler = new ScalarHandler<>();
            Long count = queryRunner.query(conn, sql, scalarHandler);
            System.out.println(count);
        } catch (SQLException e)
        {
            e.printStackTrace();
        } finally
        {
            JDBCUtils.closeResource(conn, null, null);
        }
    }
}
