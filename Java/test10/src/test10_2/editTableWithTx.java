package test10_2;

import bean.Order;
import org.junit.jupiter.api.Test;
import util.JDBCUtils;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class editTableWithTx<T>
{
    /**
     * @Description 一个完整的事务
     */
    @Test
    public void test1()
    {
        Connection connection = null;
        try
        {
            //1.获取连接
            connection = JDBCUtils.getConnection();
            //2.设置AutoCommit为false
            connection.setAutoCommit(false);
            //3.执行语句1
            String sql1 = "INSERT INTO `order`(order_name, order_date) VALUES (?,?)";
            editTable(connection,sql1,"DD","2022-08-17");
            //4.执行语句2
            String sql2 = "SELECT order_name orderName FROM `order` WHERE order_id >= ?";
            List<Order> orders = selectNotForOneWithTx(connection, Order.class, sql2, 1);
            //5.提交事务
            connection.commit();
            //6.最好把AutoCommit再调回去为true
            connection.setAutoCommit(true);
        } catch (Exception e)
        {
            e.printStackTrace();
            //6.如果有异常，则事务回滚
            try
            {
                connection.rollback();
            } catch (SQLException ex)
            {
                ex.printStackTrace();
            }
        } finally
        {
            //7.关闭连接
            JDBCUtils.closeResource(connection,null);
        }
    }

    /**
     * @Description 针对事务修改的通用增删改方法
     */
    //从外部传入connection
    public static void editTable(Connection connection,String sql,Object ... args)
    {
        PreparedStatement preparedStatement = null;
        try
        {
            //1.获取prepareStatement
            preparedStatement = connection.prepareStatement(sql);
            //2.填充占位符
            for(int i = 0; i<args.length; i++)
            {
                preparedStatement.setObject(i+1, args[i]);
            }
            //3.执行
            preparedStatement.executeUpdate();
        } catch (Exception e)
        {
            e.printStackTrace();
        } finally
        {
            //4.只关闭preparedStatement，不要关闭connection
            JDBCUtils.closeResource(null,preparedStatement);
        }
    }

    /**
     * @Description 针对事务修改的适用于返回多条记录的SELECT语句
     */
    //从外部传入connection
    public <T> List<T> selectNotForOneWithTx (Connection connection,Class<T> clazz, String sql, Object ... args)
    {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try
        {
            //1.获取preparedStatement，填充占位符
            preparedStatement = connection.prepareStatement(sql);
            for(int i = 0; i< args.length; i++)
            {
                preparedStatement.setObject(i+1, args[i]);
            }
            //2.执行executeQuery()，返回结果集resultSet
            resultSet = preparedStatement.executeQuery();
            //3.通过resultSet获取结果集元数据metaData
            ResultSetMetaData metaData = resultSet.getMetaData();
            //4.通过metaData获取结果集的字段数columnCount
            int columnCount = metaData.getColumnCount();
            //5.新建集合list
            ArrayList<T> list = new ArrayList<>();
            while (resultSet.next())
            {
                //6.新建一个对象t
                T t = clazz.getConstructor().newInstance();
                for(int i = 0; i<columnCount; i++)
                {
                    //7.通过metaData分别获取每一个字段别名（或字段名）columnLabel
                    String columnLabel = metaData.getColumnLabel(i + 1);
                    //8.通过resultSet分别获取每一个字段值columnValue
                    Object columnValue = resultSet.getObject(i + 1);
                    //9.通过反射，给对象t名为columnLabel的属性赋予columnValue值
                    Field field = clazz.getDeclaredField(columnLabel);
                    field.setAccessible(true);
                    field.set(t,columnValue);
                }
                //10.将对象t加到集合list中
                list.add(t);
            }
            //11.返回集合list，若结果集没有记录，则list为空
            return list;
        } catch (Exception e)
        {
            e.printStackTrace();
        } finally
        {
            //12.只关闭preparedStatement，resultSet，不关闭connection
            JDBCUtils.closeResource(null,preparedStatement,resultSet);
        }
        return null;
    }

    @Test
    public void test2() throws Exception
    {
        Connection connection = JDBCUtils.getConnection();
        //设置AutoCommit值
        connection.setAutoCommit(false);
        //获取AutoCommit值
        boolean autoCommit = connection.getAutoCommit();
        //设置隔离级别
        //1.用常量名
        connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
        //2.也可以直接用数字
        connection.setTransactionIsolation(4);//表示TRANSACTION_REPEATABLE_READ
        //获取隔离级别
        int transactionIsolation = connection.getTransactionIsolation();
    }
}
