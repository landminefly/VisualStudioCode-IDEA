package test10_1;

import org.junit.jupiter.api.Test;
import util.JDBCUtils;

import java.io.*;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PreparedStatementBlobTest
{
    /**
     * @Description 将Blob数据插入到数据表中
     */
    @Test
    public void test1()
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        FileInputStream fileInputStream = null;
        try
        {
            connection = JDBCUtils.getConnection();
            String sql = "INSERT INTO customers(name, email, birth, photo) VALUES (?,?,?,?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1,"胡锦涛");
            preparedStatement.setObject(2,"PresidentHu@163.com");
            preparedStatement.setObject(3,"1940-10-01");
            //要用FileInputStream将Blob数据传到数据表中
            fileInputStream = new FileInputStream("Forza Horizon 5.png");
            //这里当然也可以使用setObject()
            preparedStatement.setBlob(4,fileInputStream);
            preparedStatement.execute();
        } catch (Exception e)
        {
            e.printStackTrace();
        } finally
        {
            //关闭IO流
            try
            {
                if(fileInputStream != null)
                    fileInputStream.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
            //关闭资源
            JDBCUtils.closeResource(connection,preparedStatement);
        }
    }

    /**
     * @Description 将数据表中的Blob数据取出并保存到磁盘上
     */
    @Test
    public void test2()
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        InputStream binaryStream = null;
        FileOutputStream fileOutputStream = null;
        try
        {
            connection = JDBCUtils.getConnection();
            String sql = "SELECT photo FROM customers WHERE id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1,27);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next())
            {
                //使用getBlob()取出Blob数据，赋给Blob类型变量
                //也可以使用getObject()，不过要麻烦一些
                Blob blob = resultSet.getBlob(1);
                //调用Blob的getBinaryStream()，获取Blob数据的InputStream
                binaryStream = blob.getBinaryStream();
                //再使用FileOutputStream，将数据保存到磁盘上
                fileOutputStream = new FileOutputStream("photoFromCustomers.png");
                byte[] buffer = new byte[1024];
                int length;
                while ((length = binaryStream.read(buffer)) != -1)
                {
                    fileOutputStream.write(buffer, 0, length);
                }
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        } finally
        {
            //关闭IO流
            try
            {
                if(binaryStream != null)
                    binaryStream.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
            try
            {
                if(fileOutputStream != null)
                    fileOutputStream.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
            //关闭资源
            JDBCUtils.closeResource(connection,preparedStatement,resultSet);
        }
    }

    /**
     * @Description 最终版本的批量插入操作
     */
    @Test
    public void test3()
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try
        {
            connection = JDBCUtils.getConnection();
            //1.通过setAutoCommit()设置AutoCommit为false，减少刷盘操作以提高效率
            connection.setAutoCommit(false);
            //2.将SQL语句放在循环外，防止每次循环都要生成SQL语句从而降低效率
            String sql = "INSERT INTO goods(NAME) VALUES (?)";
            preparedStatement = connection.prepareStatement(sql);
            for(int i = 0; i<100000; i++)
            {
                //3.填充占位符
                preparedStatement.setObject(1,"name_"+i);
                //4.添加SQL语句到Batch
                preparedStatement.addBatch();
                if(i % 5000 == 0)
                {
                    //5.执行Batch中的SQL语句
                    preparedStatement.executeBatch();
                    //6.清空Batch中的SQL语句
                    preparedStatement.clearBatch();
                }
                if(i == 100000 - 1)
                {
                    //7.在最后一次循环中，执行并清空Batch中的SQL语句，防止漏句
                    preparedStatement.executeBatch();
                    preparedStatement.clearBatch();
                }
            }
            //8.提交事务
            connection.commit();
        } catch (Exception e)
        {
            e.printStackTrace();
        } finally
        {
            //9.关闭资源
            JDBCUtils.closeResource(connection,preparedStatement);
        }
    }
}
