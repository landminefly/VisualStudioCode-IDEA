package test10_1;

import org.junit.jupiter.api.Test;
import util.JDBCUtils;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;

public class PreparedStatementEditTest
{
    //将获取连接、关闭资源的单调且重复的代码封装到JDBCUtils工具类中
    @Test
    public void test1()
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try
        {
            //1.获取连接
            connection = JDBCUtils.getConnection();
            //2.调用connection的prepareStatement方法，预编译SQL语句，返回PreparedStatement实例（?为占位符）
            String sql = "INSERT INTO customers(name,email,birth) VALUES (?,?,?)";
            preparedStatement = connection.prepareStatement(sql);
            //3.填充占位符（parameterIndex为int类型，表示第几个占位符，注意该参数从1开始）
            preparedStatement.setString(1,"习近平");
            preparedStatement.setString(2,"PresidentXi@163.com");
            //3.1. 填充日期（java.util.date -> java.sql.date）
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date util_date = simpleDateFormat.parse("1953-06-02");
            preparedStatement.setDate(3,new java.sql.Date(util_date.getTime()));
            //4.执行
            preparedStatement.execute();
        } catch (Exception e)
        {
            e.printStackTrace();
        } finally
        {
            //5.关闭资源（Connection、PreparedStatement都要关闭）
            JDBCUtils.closeResource(connection, preparedStatement);
        }
    }

    //通用的增删改方法
    //传入的args数量应与占位符数量相等，且顺序一致
    public static void editTable(String sql,Object ... args)
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try
        {
            //1.获取连接
            connection = JDBCUtils.getConnection();

            //2.预编译SQL语句，获取prepareStatement实例
            preparedStatement = connection.prepareStatement(sql);

            //3.填充占位符
            for(int i = 0; i<args.length; i++)
            {
                //可以用更通用的setObject替代setString、setDate等方法
                preparedStatement.setObject(i+1, args[i]);
            }

            //4.执行
            preparedStatement.executeUpdate();
        } catch (Exception e)
        {
            e.printStackTrace();
        } finally
        {
            //5.关闭资源
            JDBCUtils.closeResource(connection,preparedStatement);
        }
    }

    @Test
    public void test2()
    {
        String sql = "update `order` set order_name = ? where order_id = ?";
        editTable(sql,"CC",4);
    }
}
