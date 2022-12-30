package test10_1;

import bean.Customer;
import bean.Order;
import org.junit.jupiter.api.Test;
import util.JDBCUtils;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

public class PreparedStatementSelectTest
{
    /**
     * @Description 适用于仅返回一条记录的SELECT语句
     */
    //clazz表示所查数据表对应的Java类；返回值是该类的一个对象（对应一条记录）
    public <T> T selectForOne (Class<T> clazz, String sql, Object ... args)
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try
        {
            //1.获取连接、preparedStatement，填充占位符
            connection = JDBCUtils.getConnection();
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
            if(resultSet.next())
            {
                //5.新建一个对象t
                T t = clazz.getConstructor().newInstance();
                for(int i = 0; i<columnCount; i++)
                {
                    //6.通过metaData分别获取每一个字段别名（或字段名）columnLabel
                    String columnLabel = metaData.getColumnLabel(i + 1);
                    //7.通过resultSet分别获取每一个字段值columnValue
                    Object columnValue = resultSet.getObject(i + 1);
                    //8.通过反射，给对象t名为columnLabel的属性赋予columnValue值
                    Field field = clazz.getDeclaredField(columnLabel);
                    field.setAccessible(true);
                    field.set(t,columnValue);
                }
                //9.返回对象t
                return t;
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        } finally
        {
            //10.关闭资源
            JDBCUtils.closeResource(connection,preparedStatement,resultSet);
        }
        //11.若结果集没有记录，则返回null
        return null;
    }

    @Test
    public void test1()
    {
        String sql = "SELECT id,name,email,birth FROM customers WHERE id = ?";
        Customer customer = selectForOne(Customer.class, sql, 3);
        System.out.println(customer);
    }

    /**
     * @Description 适用于返回多条记录的SELECT语句
     */
    //clazz表示所查数据表对应的Java类；返回值是该类的一个集合（对应多条记录）
    public <T> List<T> selectNotForOne (Class<T> clazz, String sql, Object ... args)
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try
        {
            //1.获取连接、preparedStatement，填充占位符
            connection = JDBCUtils.getConnection();
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
            //12.关闭资源
            JDBCUtils.closeResource(connection,preparedStatement,resultSet);
        }
        return null;
    }

    @Test
    public void test2()
    {
        String sql = "SELECT order_id orderId, order_name orderName, order_date orderDate FROM `order` WHERE order_id >= ?";
        List<Order> orders = selectNotForOne(Order.class, sql, 1);
        orders.forEach(System.out::println);
    }
}
