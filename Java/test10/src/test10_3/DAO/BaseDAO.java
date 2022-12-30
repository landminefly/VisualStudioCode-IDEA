package test10_3.DAO;

import util.JDBCUtils;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description 封装了对于数据表的通用操作
 *              将此类设计为抽象类，旨在表示该类不可实例化
 */
public abstract class BaseDAO
{
    /**
     * @Description 针对事务修改的通用增删改方法
     */
    public void editTableWithTx(Connection connection, String sql, Object ... args)
    {
        PreparedStatement preparedStatement = null;
        try
        {
            preparedStatement = connection.prepareStatement(sql);
            for(int i = 0; i<args.length; i++)
            {
                preparedStatement.setObject(i+1, args[i]);
            }
            preparedStatement.executeUpdate();
        } catch (Exception e)
        {
            e.printStackTrace();
        } finally
        {
            JDBCUtils.closeResource(null,preparedStatement);
        }
    }

    /**
     * @Description 针对事务修改的通用查询方法，适用于仅返回一条记录的SELECT语句
     */
    public <T> T selectForOneWithTx (Connection connection,Class<T> clazz, String sql, Object ... args)
    {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try
        {
            preparedStatement = connection.prepareStatement(sql);
            for(int i = 0; i< args.length; i++)
            {
                preparedStatement.setObject(i+1, args[i]);
            }
            resultSet = preparedStatement.executeQuery();
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            if(resultSet.next())
            {
                T t = clazz.getConstructor().newInstance();
                for(int i = 0; i<columnCount; i++)
                {
                    String columnLabel = metaData.getColumnLabel(i + 1);
                    Object columnValue = resultSet.getObject(i + 1);
                    Field field = clazz.getDeclaredField(columnLabel);
                    field.setAccessible(true);
                    field.set(t,columnValue);
                }
                return t;
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        } finally
        {
            JDBCUtils.closeResource(null,preparedStatement,resultSet);
        }
        return null;
    }

    /**
     * @Description 针对事务修改的通用查询方法，适用于返回多条记录的SELECT语句
     */
    public <T> List<T> selectNotForOneWithTx (Connection connection, Class<T> clazz, String sql, Object ... args)
    {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try
        {
            preparedStatement = connection.prepareStatement(sql);
            for(int i = 0; i< args.length; i++)
            {
                preparedStatement.setObject(i+1, args[i]);
            }
            resultSet = preparedStatement.executeQuery();
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            ArrayList<T> list = new ArrayList<>();
            while (resultSet.next())
            {
                T t = clazz.getConstructor().newInstance();
                for(int i = 0; i<columnCount; i++)
                {
                    String columnLabel = metaData.getColumnLabel(i + 1);
                    Object columnValue = resultSet.getObject(i + 1);
                    Field field = clazz.getDeclaredField(columnLabel);
                    field.setAccessible(true);
                    field.set(t,columnValue);
                }
                list.add(t);
            }
            return list;
        } catch (Exception e)
        {
            e.printStackTrace();
        } finally
        {
            JDBCUtils.closeResource(null,preparedStatement,resultSet);
        }
        return null;
    }

    /**
     * @Description 针对事务修改的，用于查询特殊值的通用方法
     */
    public <E> E getValueWhitTx(Connection conn,String sql,Object...args)
    {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try
        {
            preparedStatement = conn.prepareStatement(sql);
            for(int i = 0;i < args.length;i++)
            {
                preparedStatement.setObject(i + 1, args[i]);
            }
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next())
            {
                return (E) resultSet.getObject(1);
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        } finally
        {
            JDBCUtils.closeResource(null, preparedStatement, resultSet);
        }
        return null;
    }

}
